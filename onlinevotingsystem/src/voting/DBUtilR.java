package voting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
public class DBUtilR {
	  static Connection conn = null;
	static
	 {	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/votingdb", "root", null);
			
			if(!conn.isClosed()) {
				System.out.println("Connection established");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error in DBUtilFile");
			e.printStackTrace();
		}
	}
	
	public static  Connection getDBConnection() {
		// TODO Auto-generated method stub
		return conn;
	}
}
