package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtililty {
		
	  private static ConnectionUtililty cf = new ConnectionUtililty(1);
	  
	  public static ConnectionUtililty getConnectionFactory() {		  
		  return cf;
	  }
	  
	  
	  private Connection[] conn;
	  
	  private ConnectionUtililty(int numsOfConnections) {
		  
		  try {
			    this.conn = new Connection[numsOfConnections];
			    for(int i=0 ; i< this.conn.length;i++) {
			    	Connection conn = DriverManager.getConnection("jdbc:postgresql://java-uta-2021.cgwkgluikw7p.us-west-1.rds.amazonaws.com:5432/socaildb","socialuser","password");
			    	this.conn[i] = conn;
			    }
			    
			    
		  } catch (SQLException e) {
			  e.printStackTrace();
		  }
	  }
	  
	  
	  public Connection getConnection() {
		    return this.conn[0];
	  }
	  
	  public void releaseConnection(Connection conn) throws SQLException {
		 
	  }
	  
}
