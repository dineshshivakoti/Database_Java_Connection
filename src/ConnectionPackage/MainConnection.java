package ConnectionPackage;
/*
 * This class connects everything together.
 * It also launches JFrame
 */
import java.util.Properties;

import javax.swing.JFrame;


public class MainConnection {
	public static void main(String[] args){
		JFrame frame = new JFrame("Database");
		Properties properties = new Properties();
		// add the ConnectionDialog class to the MainConnection class
		ConnectionDialog dialog = new ConnectionDialog(frame, "Database Connector", properties);
		dialog.setVisible(true);
		if(dialog.isCancelled)
			System.exit(0);
		Connector conn = new Connector(dialog.getProps(), new String(dialog.pass.getPassword()));
		if(!conn.connectionOpen())
			System.exit(0);
		// add the database class to the MainConnection class
		Database databasePanel = new Database(conn);
		frame.setSize(800, 600);
		frame.add(databasePanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
