package voting;

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

import voting.DBUtilR;
	public class registration extends HttpServlet{
		/**
		 * 
		 */
    
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			response.setContentType("text/html");  
			PrintWriter out = response.getWriter();
			
			
			String f=request.getParameter("fname");
			
			String c=request.getParameter("cardno");
			String cn=request.getParameter("cono");
			String ad=request.getParameter("add");
			String dob=request.getParameter("dob");
			String email=request.getParameter("email");
			String pin=request.getParameter("pin");
			try
			{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/votingdb","root","");
			PreparedStatement ps=con.prepareStatement("insert into register values(?,?,?,?,?,?,?)");
			ps.setString(1,f);
			
			ps.setString(2,c);
			ps.setString(3,cn);
			ps.setString(4,ad);
			ps.setString(5,dob);
			ps.setString(6,email);
			ps.setString(7,pin);
			int i=ps.executeUpdate();
			if(i>0)
			{
				out.print("Succesful Account Created. Please Login");
				 RequestDispatcher rd=request.getRequestDispatcher("loginpage.html");  
			        rd.include(request,response);
			}
			else
			{
				out.print("Failed account creation try again");
				 RequestDispatcher rd=request.getRequestDispatcher("registration.html");  
			        rd.include(request,response);
			}
				
			}
			catch (Exception e2) {
				out.print("Invalid , Failed account creation try again  "+e2);
				 RequestDispatcher rd=request.getRequestDispatcher("registration.html");  
			        rd.include(request,response);
			}
			
			out.close();
		
	
	}
		protected void service(HttpServletRequest request, HttpServletResponse   response) throws ServletException, IOException {
	        doPost(request, response);
	}
	}

