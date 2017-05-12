package ieiProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionLink implements ConnectionMaker{
	@Override
	public ConnectionLink makeConnection() throws ClassNotFoundException, SQLException { 
		// TODO Auto-generated method stub
		Class.forName("oracle.jdbc.driver.OracleDriver");
		ConnectionLink c = (ConnectionLink) DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		return c;
	}
}
