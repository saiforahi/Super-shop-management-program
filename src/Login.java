import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login {
	public static String user;
	public JFrame frmLogin;
	Connection connection;
	public JTextField textField;
	public JRadioButton adminRdBtn;
	public JRadioButton guestRdBtn;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login go=new Login();
					go.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the application.
	 */
	public Login() {
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
		frmLogin = new JFrame();
		frmLogin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("image_lib\\besof.png"));
		frmLogin.getContentPane().setBackground(SystemColor.menu);
		frmLogin.setBackground(SystemColor.menu);
		frmLogin.setResizable(false);
		frmLogin.setTitle("Welcome!");
		frmLogin.setBounds(100, 100, 778, 439);
		
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setAlwaysOnTop(true);
		frmLogin.getContentPane().setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(null);
				passwordField.setText(null);
				adminRdBtn.setSelected(false);
				guestRdBtn.setSelected(false);
			}
		});
		btnCancel.setToolTipText("Click to log in\r\n");
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Constantia", Font.BOLD, 14));
		btnCancel.setBorder(new LineBorder(Color.WHITE));
		btnCancel.setBackground(SystemColor.controlDkShadow);
		btnCancel.setBounds(597, 271, 85, 28);
		frmLogin.getContentPane().add(btnCancel);
		
		JButton button = new JButton("Login");
		button.setBackground(SystemColor.controlDkShadow);
		button.setBounds(501, 271, 85, 28);
		frmLogin.getContentPane().add(button);
		button.setToolTipText("Click to log in\r\n");
		button.setBorder(new LineBorder(Color.WHITE));
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				try{
					connection=sqliteConnection.dbConnection();
					ResultSet rst= connection.createStatement().executeQuery("SELECT * FROM loginDB;");
					ResultSetMetaData rsmd=rst.getMetaData();
					String name=textField.getText().toString().trim();
					String pass=new String(passwordField.getPassword()).trim();
					rsmd.getColumnCount();
					boolean match=false;
					if(adminRdBtn.isSelected()==true){
						while (rst.next()){
							if(rst.getString("type").equalsIgnoreCase("admin"))
							{
								if(rst.getString("name").equalsIgnoreCase(name) && rst.getString("password").equalsIgnoreCase(pass))
								{
									match=true;
									user=rst.getString("name");
									rst.close();
									connection.close();
									frmLogin.dispose();
									AdminWindow goToAdminSection=new AdminWindow();
									goToAdminSection.frmBirdsEye.setVisible(true);
									goToAdminSection.frmBirdsEye.setExtendedState(JFrame.MAXIMIZED_BOTH);
									JOptionPane.showMessageDialog(goToAdminSection.frmBirdsEye, "You have successfully logged in admin!!");
									break;
								}
							}
							else continue;
						}
						rst.close();
						connection.close();
						if(match==false)
						{
							JOptionPane.showMessageDialog(frmLogin, "Wrong User Name & Password");
						}
					}
					else if(guestRdBtn.isSelected()==true){
						while (rst.next()){
							if(rst.getString("type").equals("guest"))
							{
								if(rst.getString("name").equalsIgnoreCase(name) && rst.getString("password").equalsIgnoreCase(pass))
								{
									user=rst.getString("name");
									match=true;
									rst.close();
									connection.close();
									frmLogin.dispose();
									GuestWindow goToGuestSection=new GuestWindow();
									goToGuestSection.frmBirdsEye.setVisible(true);
									goToGuestSection.frmBirdsEye.setExtendedState(JFrame.MAXIMIZED_BOTH);
									JOptionPane.showMessageDialog(goToGuestSection.frmBirdsEye, "You have successfully logged in as a guest!!");
									break;
								}
							}
							
							else continue;
						}
						rst.close();
						connection.close();
						if(match==false)
						{
							JOptionPane.showMessageDialog(frmLogin, "Wrong User Name & Password");
						}
					}
					else JOptionPane.showMessageDialog(frmLogin, "No type of login is selected!");
					
				}
				catch(Exception e){
					e.printStackTrace();
					
				}
			}
		});
		button.setForeground(SystemColor.textText);
		button.setFont(new Font("Constantia", Font.BOLD, 14));
		
		adminRdBtn = new JRadioButton("Admin");
		adminRdBtn.setBounds(519, 227, 67, 28);
		frmLogin.getContentPane().add(adminRdBtn);
		adminRdBtn.setFont(new Font("Constantia", Font.BOLD, 13));
		adminRdBtn.setOpaque(false);
		adminRdBtn.setBackground(new Color(0,0,0,0));
		adminRdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (guestRdBtn.isSelected()==true){
					guestRdBtn.setSelected(false);
				}
			}
		});
		adminRdBtn.setHorizontalAlignment(SwingConstants.LEFT);
		
		guestRdBtn = new JRadioButton("Guest");
		guestRdBtn.setHorizontalAlignment(SwingConstants.LEFT);
		guestRdBtn.setBounds(597, 227, 67, 28);
		frmLogin.getContentPane().add(guestRdBtn);
		guestRdBtn.setFont(new Font("Constantia", Font.BOLD, 13));
		guestRdBtn.setOpaque(false);
		guestRdBtn.setBackground(new Color(0,0,0,0));
		guestRdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (adminRdBtn.isSelected()==true){
					adminRdBtn.setSelected(false);
				}
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.text, 1, true));
		panel.setBounds(465, 129, 254, 87);
		panel.setBackground(new Color(0,0,0,0));
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("User Name");
		label.setBounds(10, 11, 95, 29);
		panel.add(label);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Constantia", Font.BOLD, 16));
		
		textField = new JTextField();
		textField.setBounds(125, 11, 116, 24);
		panel.add(textField);
		textField.setBackground(SystemColor.text);
		textField.setToolTipText("write your user name");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Constantia", Font.BOLD, 14));
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setBounds(10, 51, 95, 29);
		panel.add(label_1);
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("Constantia", Font.BOLD, 16));
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(125, 52, 116, 24);
		panel.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("User Login");
		lblNewLabel_1.setBackground(SystemColor.controlDkShadow);
		lblNewLabel_1.setFont(new Font("Georgia", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(479, 85, 220, 33);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon("image_lib\\login_window.png"));
		lblNewLabel.setBounds(0, 0, 772, 410);
		frmLogin.getContentPane().add(lblNewLabel);
	}
}
