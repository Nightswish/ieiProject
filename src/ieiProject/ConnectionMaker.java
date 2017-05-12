package ieiProject;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
	public ConnectionLink makeConnection() throws ClassNotFoundException, SQLException;
}
