import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class AddCustomer {

	public JFrame frmAddCustomer;
	public JTextField nameField;
	public JTextField cellField;
	private JTextField desigField;
	Connection conn=sqliteConnection.dbConnection();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCustomer window = new AddCustomer();
					window.frmAddCustomer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddCustomer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
		
		frmAddCustomer = new JFrame();
		frmAddCustomer.setType(Type.POPUP);
		frmAddCustomer.setIconImage(Toolkit.getDefaultToolkit().getImage("image_lib\\besof.png"));
		frmAddCustomer.setTitle("Add Customer");
		frmAddCustomer.setBounds(100, 100, 450, 300);
		frmAddCustomer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddCustomer.setLocationRelativeTo(AdminWindow.frmBirdsEye);
		frmAddCustomer.setResizable(false);
		frmAddCustomer.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.DARK_GRAY, 3, true));
		panel.setBounds(43, 33, 356, 199);
		frmAddCustomer.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBackground(SystemColor.controlDkShadow);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nameField.getText().equals(null)||nameField.getText().equals("")||cellField.getText().equals(null)||cellField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Nothing to save");
				}
				else
				{
					try {
						String select="SELECT * FROM customerTable;";
						Statement st=conn.createStatement();
						ResultSet rs=st.executeQuery(select);
						boolean match=false;
						while(rs.next())
						{
							if(nameField.getText().equals(rs.getString("name"))&&cellField.getText().equals(rs.getString("cellNumber")))
							{
								JOptionPane.showMessageDialog(null, "This Customer already have been added!");
								match=true;
								break;
							}
						}
						if(match==false)
						{
							Statement stAdd=conn.createStatement();
							stAdd.execute("INSERT INTO customerTable VALUES('"+nameField.getText()+"','"+cellField.getText()+"',0,0,'"+desigField.getText()+"');");
							stAdd.close();
						}
						rs.close();
						st.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					AdminWindow.customerSet(nameField.getText(), cellField.getText());
					frmAddCustomer.setVisible(false);
				}
			}
		});
		btnNewButton.setBounds(139, 151, 89, 23);
		panel.add(btnNewButton);
		
		cellField = new JTextField();
		cellField.setHorizontalAlignment(SwingConstants.CENTER);
		cellField.setBounds(173, 80, 111, 20);
		cellField.setText(null);
		panel.add(cellField);
		cellField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBounds(173, 49, 111, 20);
		nameField.setText(null);
		panel.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblCellNumber = new JLabel("Cell Number");
		lblCellNumber.setBounds(53, 80, 89, 20);
		panel.add(lblCellNumber);
		lblCellNumber.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(53, 49, 89, 20);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		desigField = new JTextField();
		desigField.setHorizontalAlignment(SwingConstants.CENTER);
		desigField.setBounds(173, 111, 111, 20);
		panel.add(desigField);
		desigField.setColumns(10);
		
		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesignation.setBounds(53, 111, 89, 20);
		panel.add(lblDesignation);
		
		JLabel lblAddNewCustomer = new JLabel("Add new Customer");
		lblAddNewCustomer.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddNewCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewCustomer.setBounds(64, 11, 232, 28);
		panel.add(lblAddNewCustomer);
	}
}
