import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class nilAchalConnection {
		Connection conn=null;
		public static Connection dbConnection(){
			try{
				Class.forName("org.sqlite.JDBC");
				Connection conn=DriverManager.getConnection("jdbc:sqlite:Data//NilAchal.db");
				//Connection conn=DriverManager.getConnection("jdbc:sqlite::resource:NilAchal.db");
				//JOptionPane.showMessageDialog(null,"database Connected");
				return conn;
			}
			catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,"database problem  "+e.getMessage());
				return null;
			}
		}
		
	
}
