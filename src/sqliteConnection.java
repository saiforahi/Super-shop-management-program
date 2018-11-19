import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class sqliteConnection {
		Connection conn=null;
		public static Connection dbConnection(){
			try{
				Class.forName("org.sqlite.JDBC");
				Connection conn=DriverManager.getConnection("jdbc:sqlite:Data//BirdsEyeDatabase.db");
				//Connection conn=DriverManager.getConnection("jdbc:sqlite::resource:BirdsEyeDatabase.db");
				//JOptionPane.showMessageDialog(null,"database Connected");
				return conn;
			}
			catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,"database problem  "+e.getMessage());
				return null;
			}
		}
		
		/*public static Image setIcon(){
			try {
				Class.forName("org.sqlite.JDBC");
				Connection conn=DriverManager.getConnection("jdbc:sqlite:Data//BirdsEyeDatabase.db");
				//PreparedStatement pstmt= con.prepareStatement("SELECT describeImage FROM test1question WHERE rowid=?;");
				//pstmt.setInt(1, 1);
				ResultSet rs=con.createStatement().executeQuery("SELECT image FROM image_table WHERE rowid=5;");
				if (rs.next())
				{
					byte[] blob=rs.getBytes("image");
					ImageIcon image= new ImageIcon(blob);
					Image im=image.getImage();
					rs.close();
					con.close();
					return im;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
			
		}*/
		
	
}
