package ConnectionPackage;

/*
 * This class prompts for user informations where it requires authentication to DB. Connector will use the information passed from ConnectionDialog class
 * and connect to DB.
 * 
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.*;


public class ConnectionDialog extends JDialog implements ActionListener{
	
	// create labels and text fields for user and Db information.
	boolean isCancelled = true; // keeps track if user has pressed OK or not. Once pressed OK it is set to false.
	JLabel labelhost = new JLabel("Host Name");
	JTextField host = new JTextField();
	JLabel labelport = new JLabel("Port");
	JTextField port = new JTextField();
	JLabel labeldatabase = new JLabel("Database");
	JTextField database = new JTextField();
	JLabel labeluser = new JLabel("User Name");
	JTextField user = new JTextField();
	JLabel labelpass = new JLabel("Password");
	JPasswordField pass = new JPasswordField();
	
	JButton ok = new JButton("OK");
	JButton cancel = new JButton("Cancel");
	
	Properties props;  // This object to pass all the information of the database at once.
	
	// constructor 
	public ConnectionDialog(JFrame owner, String title, Properties prop){
		super(owner, title, true);
		setSize(300, 200);
		setLocation(250,200);
		props = new Properties(prop);
		ok.setPreferredSize(new Dimension(75, 50));  // set button size
		ok.addActionListener(this);
		cancel.setPreferredSize(new Dimension(75, 50));
		cancel.addActionListener(this);
		
		// Different Panels for buttons and text fields
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		
		panel1.setLayout(new GridLayout(5,2)); // first panellayout
		// add text fields and labels
		panel1.add(labelhost);
		panel1.add(host);
		panel1.add(labelport);
		panel1.add(port);
		panel1.add(labeldatabase);
		panel1.add(database);
		panel1.add(labeluser);
		panel1.add(user);
		panel1.add(labelpass);
		panel1.add(pass);
		
		panel2.add(ok);
		panel2.add(cancel);
		
		add(panel1, BorderLayout.NORTH); // add text and label panel to North
		add(panel2, BorderLayout.SOUTH); // add button panel to south
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == ok)
			isCancelled = false;
		this.dispose();
	}
	
	public Properties getProps(){
		props.setProperty("Database", database.getText());
		props.setProperty("Host Name", host.getText());
		props.setProperty("Port", port.getText());
		props.setProperty("User Name", user.getText());
		return props;
	}

}
