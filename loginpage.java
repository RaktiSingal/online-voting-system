package voting;
import java.sql.PreparedStatement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginpage extends HttpServlet{
	
	final static  Connection con=DBUtilR.getDBConnection();
	static PreparedStatement ps = null;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		PrintWriter out = res.getWriter();  
		String card=req.getParameter("cardno");
		Integer pin=Integer.parseInt(req.getParameter("pin"));
		
		try {
			if(check(card,pin))
			{
				if(checkvote(card))
				{
					RequestDispatcher rd=req.getRequestDispatcher("againvote.html");
					 rd.include(req,res);
				}
				else
				{
				out.print("Succesful Login You Can Vote Now");
				RequestDispatcher rd=req.getRequestDispatcher("vote.html");
				 rd.include(req,res);
				}
			}	
			else {
				 out.print("Sorry username or password error , Make new account");  
				 RequestDispatcher rd=req.getRequestDispatcher("registration.html");  
			        rd.include(req,res);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
	
	}
	static boolean check(String card,Integer pin) throws SQLException
	 {
		 boolean r=false;
		 ps=con.prepareStatement("Select * from register where votercardno=? and pin=?");
		 ps.setString(1,card);
		 ps.setInt(2,pin);
		 ResultSet rs=ps.executeQuery();
		 r=rs.next();
		 
		 return r;
	 }
	
	static boolean checkvote(String card) throws SQLException
	 {
		 boolean r=false;
		 ps=con.prepareStatement("Select * from vote where votercardno=?");
		 ps.setString(1,card);
		 
		 ResultSet rs=ps.executeQuery();
		 r=rs.next();
		 
		 return r;
	 }
	 
	
}