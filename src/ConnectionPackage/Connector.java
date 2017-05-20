package ConnectionPackage;

/*
 * This class loads the drivers to connect to the database
 */
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.Statement;


public class Connector {
	Connection conn;
	Statement statement;
	static String url, database, username, password, hostname, port, driver;
	
	public Connector(Properties props, String pass){
		database = props.getProperty("Database");
		username = props.getProperty("User Name");
		password = pass;
		hostname = props.getProperty("Host Name");
		port = props.getProperty("Port");
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://"+hostname+":"+port+"/"+database;
	}
	public boolean connectionOpen(){
		try {
			DriverManager.registerDriver((java.sql.Driver) Class.forName(driver).newInstance());
			conn = DriverManager.getConnection(url, username, password);
			statement = conn.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn == null)
				return false;
		}
		System.out.println("Connection successful");
		return true;
	}
	
	public ResultSet executeQuery(String s) throws SQLException{
		return statement.executeQuery(s);
	}
	public void executeUpdate(String s) throws SQLException{
		statement.executeUpdate(s);
	}
}
