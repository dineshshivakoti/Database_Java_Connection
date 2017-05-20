package ConnectionPackage;

/* This class displays records and execute queries. For this it uses Connector to execute queries. 
 * 
 */

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// This class goes into JFrame which is in the MainConnection class.
public class Database extends JPanel{
	static JTextArea sqlStatement = new JTextArea();
	JLabel prompt = new JLabel("Please enter your SQL statement below:");
	JButton execute = new JButton("Execute"); // execute button
	JButton reset = new JButton("Reset"); // reset button
	static JTable table = new JTable(); // to store results
	static DefaultTableModel model = (DefaultTableModel) table.getModel();
	static Connector Sconn;
	
	// constructor which is to be added to the MainConection class
	public Database(Connector conn){
		add(prompt); // add to JPanel
		Sconn = conn;
		JScrollPane scrollPane = new JScrollPane(sqlStatement);  // in case user has more text in the query
		scrollPane.setPreferredSize(new Dimension(750, 100));
		add(scrollPane);
		execute.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				execute();
			}
			
		});
		reset.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				model.setColumnCount(0);
				model.setRowCount(0);
			}
			
		});
		add(execute);
		add(reset);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setPreferredSize(new Dimension(750, 400));
		add(tablePane);
	}
	private static void execute(){
		model.setColumnCount(0);
		model.setRowCount(0);
		String s = sqlStatement.getText();
		try{
			// selects if first sub-string is select
			if((s.length()>=6 && s.substring(0,6).equalsIgnoreCase("SELECT"))){
				ResultSet rs = Sconn.executeQuery(s);
				ResultSetMetaData rsmd = rs.getMetaData();
				for(int i = 1; i<= rsmd.getColumnCount(); i++){
					model.addColumn(rsmd.getColumnName(i));
				}
				while(rs.next()){
					String[] data = new String[rsmd.getColumnCount()];
					for(int i = 1; i<= rsmd.getColumnCount(); i++){
						data[i-1] = rs.getString(i);
					}
					model.addRow(data);
				}
			}
			// updates if first sub-string is not select
			else
				Sconn.executeUpdate(s);
		}
		catch (SQLException e){
			System.out.println("Error: " + e);
		}
	}
}
