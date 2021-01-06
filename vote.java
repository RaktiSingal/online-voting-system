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
public class vote extends HttpServlet{

	private static final long serialVersionUID = 1L;
    final static Connection con=DBUtilR.getDBConnection();
    PreparedStatement ps=null;
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			response.setContentType("text/html");  
			PrintWriter out = response.getWriter();
			
			
			String f=request.getParameter("cardno");
			String l=request.getParameter("party");
			try
			{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/votingdb","root","");
			PreparedStatement ps=con.prepareStatement("insert into vote values(?,?)");
			ps.setString(1,f);
			ps.setString(2,l);
			int i=ps.executeUpdate();
			if(i>0)
			{
				out.print("Your Vote is being submitted Thank you");
				 RequestDispatcher rd=request.getRequestDispatcher("thankyou.html");  
			        rd.include(request,response);
			}
			else
			{
				out.print("Failed to submit vote, try again");
				 RequestDispatcher rd=request.getRequestDispatcher("vote.html");  
			        rd.include(request,response);
			}
				
			}catch (Exception e2) {
				out.print("Invalid , "+e2);
				 RequestDispatcher rd=request.getRequestDispatcher("vote.html");  
			        rd.include(request,response);
			}
			
			out.close();
		
			
}
		protected void service(HttpServletRequest request, HttpServletResponse   response) throws ServletException, IOException {
	        doPost(request, response);
	}
}
