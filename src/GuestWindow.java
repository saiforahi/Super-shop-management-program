import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GuestWindow {
	
	public static JFrame frmBirdsEye;
	private JTextField ItemNameTextField;
	private JTextField brandNameTextField;
	private JTextField vwTextField;
	private JTextField quantityTextField;
	private JTextField buyPriceTextField;
	private JTextField vatTextField;
	private JTextField sellPriceTextField;
	private JTextField fixedVatText;
	public JRadioButton rdbtnNewCustomer;
	public JRadioButton rdbtnExistingCustomer;
	JComboBox dayComboBox,monthComboBox,yearComboBox;
	@SuppressWarnings("rawtypes")
	public JList rowHeader;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					GuestWindow window = new GuestWindow();
					window.frmBirdsEye.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	Connection conn;
	Connection conn1;
	public static Vector <SoldProductInfo> soldItems=new Vector<SoldProductInfo>();
	private JTextField genBarTextField;
	public static JTable salesTable;
	private JTextField barcodeField;
	public static JTextField totalAmount;
	public static JTextField nameTextField;
	public static JTextField cellTextField;
	public static JTextField rcvdTextField;
	public static JTextField changeField;
	private JTextField usePointField;
	public static JTextField pointField;
	public static JTextField invoiceField;
	public static JRadioButton nilAchal;
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		public GuestWindow() throws IOException, SQLException,ParseException {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws SQLException 
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private void initialize() throws IOException, SQLException, ParseException {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
		
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frmBirdsEye = new JFrame();
		
		frmBirdsEye.setTitle("Birds Eye");
		//frmBirdsEye.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\sdk\\b1rdsEy3\\image_lib\\besof.png"));
		frmBirdsEye.setIconImage(Toolkit.getDefaultToolkit().getImage("image_lib\\besof.png"));
		//frame.setBounds(100, 100,screenSize.width,screenSize.height);
		frmBirdsEye.setResizable(false);
		frmBirdsEye.getContentPane().setPreferredSize(new Dimension(screenSize.width, screenSize.height));
		frmBirdsEye.pack();
		frmBirdsEye.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBirdsEye.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmBirdsEye.setLocationRelativeTo(null);
		frmBirdsEye.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        barcodeField.requestFocus();
		       
		    }
		});
		frmBirdsEye.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(84,122,130));
		frmBirdsEye.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBackground(SystemColor.controlDkShadow);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login window=new Login();
				window.user=null;
				window.frmLogin.setVisible(true);
				frmBirdsEye.dispose();
			}
		});
		btnLogout.setBounds(1237, 11, 89, 23);
		panel.add(btnLogout);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		tabbedPane.setBounds(25, 39, 1312, 659);
		tabbedPane.setBackground(new Color(0,0,0,0));
		
		panel.add(tabbedPane);
		
		JPanel salesPanel = new JPanel();
		salesPanel.setBorder(null);
		tabbedPane.addTab("Sales", null, salesPanel, null);
		salesPanel.setBackground(new Color(255, 255, 255));
		salesPanel.setLayout(null);
		
		JPanel panel_24 = new JPanel();
		panel_24.setBackground(new Color(90,80,80));
		panel_24.setBounds(996, 0, 311, 625);
		salesPanel.add(panel_24);
		panel_24.setLayout(null);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(SystemColor.window, 2, true));
		panel_10.setBounds(28, 63, 262, 131);
		panel_24.add(panel_10);
		panel_10.setBackground(new Color(0,0,0,0));
		panel_10.setLayout(null);
		
		JLabel lblPoints = new JLabel("Points");
		lblPoints.setForeground(SystemColor.textHighlightText);
		lblPoints.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPoints.setHorizontalAlignment(SwingConstants.LEFT);
		lblPoints.setBounds(10, 89, 94, 28);
		panel_10.add(lblPoints);
		
		pointField = new JTextField();
		pointField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pointField.setHorizontalAlignment(SwingConstants.CENTER);
		pointField.setEditable(false);
		pointField.setColumns(10);
		pointField.setBounds(123, 89, 129, 28);
		panel_10.add(pointField);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setForeground(SystemColor.textHighlightText);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setBounds(10, 11, 94, 28);
		panel_10.add(lblNewLabel_2);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameTextField.setEditable(false);
		nameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		nameTextField.setBounds(123, 11, 129, 28);
		panel_10.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblCellNumber_1 = new JLabel("Cell Number");
		lblCellNumber_1.setForeground(SystemColor.textHighlightText);
		lblCellNumber_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCellNumber_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCellNumber_1.setBounds(10, 50, 94, 28);
		panel_10.add(lblCellNumber_1);
		
		cellTextField = new JTextField();
		cellTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cellTextField.setEditable(false);
		cellTextField.setHorizontalAlignment(SwingConstants.CENTER);
		cellTextField.setColumns(10);
		cellTextField.setBounds(123, 50, 129, 28);
		panel_10.add(cellTextField);
		
		
		
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(28, 205, 262, 125);
		panel_24.add(panel_8);
		panel_8.setBackground(new Color(221,210,205));
		panel_8.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_8.setLayout(null);
		
		totalAmount = new JTextField();
		totalAmount.setBackground(SystemColor.text);
		totalAmount.setText(Integer.toString(0));
		totalAmount.setEditable(false);
		totalAmount.setHorizontalAlignment(SwingConstants.CENTER);
		totalAmount.setBounds(10, 55, 242, 59);
		panel_8.add(totalAmount);
		totalAmount.setFont(new Font("Times New Roman", Font.BOLD, 24));
		totalAmount.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Total Amount");
		lblNewLabel_1.setBounds(56, 15, 143, 29);
		panel_8.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(SystemColor.window, 2, true));
		panel_11.setBackground(new Color(0,0,0,0));
		panel_11.setBounds(28, 341, 262, 92);
		panel_24.add(panel_11);
		panel_11.setLayout(null);
		
		rcvdTextField = new JTextField();
		rcvdTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				rcvdTextField.setText("");
			}
			
		});
		rcvdTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(totalAmount.getText().equals("0")||totalAmount.getText().equals(null))
				{
					JOptionPane.showMessageDialog(frmBirdsEye, "No Item have been added!");
					rcvdTextField.setText("0");
					barcodeField.grabFocus();
				}
				else
				{
					changeField.setText(Integer.toString(Integer.parseInt(rcvdTextField.getText())-Integer.parseInt(totalAmount.getText())));
					salesTable.clearSelection();
					barcodeField.grabFocus();
				}
			}
		});
		rcvdTextField.setHorizontalAlignment(SwingConstants.CENTER);
		rcvdTextField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rcvdTextField.setBounds(10, 44, 117, 37);
		rcvdTextField.setColumns(10);
		panel_11.add(rcvdTextField);
		
		
		changeField = new JTextField();
		changeField.setEditable(false);
		changeField.setHorizontalAlignment(SwingConstants.CENTER);
		changeField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		changeField.setColumns(10);
		changeField.setBounds(135, 44, 117, 37);
		panel_11.add(changeField);
		
		JLabel lblCashReceived = new JLabel("Cash Received");
		lblCashReceived.setForeground(SystemColor.textHighlightText);
		lblCashReceived.setHorizontalAlignment(SwingConstants.CENTER);
		lblCashReceived.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCashReceived.setBounds(10, 11, 117, 29);
		panel_11.add(lblCashReceived);
		
		JLabel lblChange = new JLabel("Change");
		lblChange.setForeground(SystemColor.textHighlightText);
		lblChange.setHorizontalAlignment(SwingConstants.CENTER);
		lblChange.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChange.setBounds(135, 11, 117, 29);
		panel_11.add(lblChange);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new LineBorder(SystemColor.window, 2, true));
		panel_13.setBackground(new Color(0,0,0,0));
		panel_13.setBounds(28, 444, 262, 47);
		panel_24.add(panel_13);
		panel_13.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Use Points");
		lblNewLabel_3.setForeground(SystemColor.textHighlightText);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 11, 107, 25);
		panel_13.add(lblNewLabel_3);
		
		usePointField = new JTextField();
		usePointField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				usePointField.setText(null);
			}
		});
		usePointField.setText("0");
		usePointField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn=sqliteConnection.dbConnection();
					ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM customerTable;");
					while(rs.next())
					{
						if(nameTextField.getText().toString().equals(rs.getString("name")) && cellTextField.getText().toString().equals(rs.getString("cellNumber")))
						{
							conn.createStatement().executeUpdate("UPDATE customerTable SET points='"+Integer.toString(Integer.parseInt(rs.getString("points").toString())-Integer.parseInt(usePointField.getText().toString()))+"' WHERE cellNumber='"+cellTextField.getText().toString()+"';");
						}
					}
					pointField.setText(Integer.toString(Integer.parseInt(pointField.getText())-Integer.parseInt(usePointField.getText())));
					rs.close();
					conn.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
				}
			}
		});
		
		usePointField.setFont(new Font("Tahoma", Font.BOLD, 14));
		usePointField.setHorizontalAlignment(SwingConstants.CENTER);
		usePointField.setBounds(127, 11, 125, 25);
		usePointField.setEnabled(false);
		panel_13.add(usePointField);
		usePointField.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(28, 505, 262, 75);
		panel_24.add(panel_9);
		panel_9.setBackground(new Color(206,171,179));
		panel_9.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Print");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nilAchal.isSelected())
				{
					nilAchal.setSelected(false);
					if(rcvdTextField.getText().toString().equals(null)||rcvdTextField.getText().toString().equals("")||rcvdTextField.getText().toString().equals("0"))
					{
						int dialogResult = JOptionPane.showConfirmDialog (frmBirdsEye, "Would You Like to print without cash received?","Warning",JOptionPane.YES_NO_OPTION);
						if(dialogResult == JOptionPane.YES_OPTION){
							if(totalAmount.getText().equals(null)||totalAmount.getText().equals("0"))
							{
								JOptionPane.showMessageDialog(GuestWindow.frmBirdsEye, "No products have been added");
							}
							else{
								if(!nameTextField.getText().equals(null)||!cellTextField.getText().equals(null))
								{
									conn=sqliteConnection.dbConnection();
									conn1=nilAchalConnection.dbConnection();
									try {
										ResultSet point=conn.createStatement().executeQuery("SELECT * FROM customerTable;");
										while(point.next())
										{
											if(cellTextField.getText().equals(point.getString("cellNumber"))&& Integer.parseInt(totalAmount.getText())>=500)
											{
												conn.createStatement().execute("UPDATE customerTable SET purchasedAmounts='"+totalAmount.getText()+"',points='"+Integer.toString(Integer.parseInt(totalAmount.getText())/500+Integer.parseInt(point.getString("points")))+"' WHERE cellNumber='"+cellTextField.getText().toString()+"';");
											}
										}
										point.close();
										for(int i=0;i<soldItems.size();i++)
										{
											
											conn1.createStatement().execute("INSERT INTO salesTable VALUES('"+invoiceField.getText().toString()+"','"+soldItems.elementAt(i).barcode+"','"+soldItems.elementAt(i).name+"','"+soldItems.elementAt(i).brand+"','"+soldItems.elementAt(i).volume+"','"+soldItems.elementAt(i).quantity+"','"+soldItems.elementAt(i).buyPrice+"','"+soldItems.elementAt(i).sellPrice+"','"+soldItems.elementAt(i).totalSellPrice+"','"+nameTextField.getText()+"','"+cellTextField.getText()+"','"+Login.user+"','"+getCurrentDate()+"','"+getCurrentTime()+"');");
										}
										ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM itemDataTable;");
										int saleTotal=0;
										int profit=0;
										int newQuan=0;
										for(int n=0;n<soldItems.size();n++)
										{
											if(Integer.parseInt(soldItems.elementAt(n).buyPrice.toString())!=0)
											{
												profit+=Integer.parseInt(soldItems.elementAt(n).totalSellPrice.toString())-(Integer.parseInt(soldItems.elementAt(n).quantity)*Integer.parseInt(soldItems.elementAt(n).buyPrice));
											}
											saleTotal+=Integer.parseInt(soldItems.elementAt(n).totalSellPrice);
											while(rs.next())
											{
												if(soldItems.elementAt(n).barcode.equals(rs.getString("barcode"))||soldItems.elementAt(n).name.equals(rs.getString("itemName")))
												{
													if(rs.getInt("quantity")!=0)
													{
														newQuan=rs.getInt("quantity")-Integer.parseInt(soldItems.elementAt(n).quantity);
														conn.createStatement().executeUpdate("UPDATE itemDataTable SET quantity='"+newQuan+"' WHERE barcode='"+soldItems.elementAt(n).barcode+"';");
													}
													else
													{
														break;
													}
												}
											}
										}
										conn1.createStatement().execute("INSERT INTO saleInfo VALUES('"+invoiceField.getText().toString()+"','"+Integer.toString(saleTotal)+"','"+getCurrentDate()+"','"+Integer.toString(profit)+"');");
										rs.close();
										conn.close();
										conn1.close();
									} catch (SQLException e2) {
										JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs "+e2.getMessage());
									}
									GuestNilAchalPrint.main(null);
									totalAmount.setText("0");
									rcvdTextField.setText(null);
									changeField.setText(null);
									DefaultTableModel re=(DefaultTableModel) salesTable.getModel();
									re.setRowCount(0);
									soldItems.clear();
									rdbtnExistingCustomer.setSelected(false);
									rdbtnNewCustomer.setSelected(false);
									nameTextField.setText(null);
									cellTextField.setText(null);
									pointField.setText(null);
									invoiceField.setText("0");
									barcodeField.grabFocus();
								}
								
								else
								{
									conn=sqliteConnection.dbConnection();
									conn1=nilAchalConnection.dbConnection();
									int saleTotal=0;
									for(int i=0;i<soldItems.size();i++)
									{
										saleTotal+=Integer.parseInt(soldItems.elementAt(i).totalSellPrice);
										
										try {
											conn1.createStatement().execute("INSERT INTO salesTable VALUES('"+invoiceField.getText().toString()+"','"+soldItems.elementAt(i).barcode+"','"+soldItems.elementAt(i).name+"','"+soldItems.elementAt(i).brand+"','"+soldItems.elementAt(i).volume+"','"+soldItems.elementAt(i).quantity+"','"+soldItems.elementAt(i).buyPrice+"','"+soldItems.elementAt(i).sellPrice+"','"+soldItems.elementAt(i).totalSellPrice+"','null','null','"+Login.user+"','"+getCurrentDate()+"','"+getCurrentTime()+"');");
											ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM itemDataTable;");
											int profit=0;
											for(int n=0;n<soldItems.size();n++)
											{
												if(Integer.parseInt(soldItems.elementAt(n).buyPrice.toString())!=0)
												{
													profit+=Integer.parseInt(soldItems.elementAt(n).totalSellPrice.toString())-(Integer.parseInt(soldItems.elementAt(n).quantity)*Integer.parseInt(soldItems.elementAt(n).buyPrice));
												}
												while(rs.next())
												{
													if(soldItems.elementAt(n).barcode.equals(rs.getString("barcode"))||soldItems.elementAt(n).name.equals(rs.getString("itemName")))
													{
														if(rs.getInt("quantity")!=0)
														{
															int newQuan=rs.getInt("quantity")-Integer.parseInt(soldItems.elementAt(n).quantity);
															String update="UPDATE itemDataTable SET quantity='"+newQuan+"' WHERE barcode='"+soldItems.elementAt(n).barcode+"';";
															Statement upst=conn.createStatement();
															upst.executeUpdate(update);
															upst.close();
														}
														else
														{
															break;
														}
													}
												}
											}
											conn1.createStatement().execute("INSERT INTO saleInfo VALUES('"+invoiceField.getText().toString()+"','"+Integer.toString(saleTotal)+"','"+getCurrentDate()+"','"+Integer.toString(profit)+"');");
											rs.close();
											conn.close();
											conn1.close();
										} catch (SQLException e) {JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");}
									}
									GuestNilAchalPrint.main(null);
									totalAmount.setText("0");
									DefaultTableModel re=(DefaultTableModel) salesTable.getModel();
									re.setRowCount(0);
									soldItems.clear();
									rcvdTextField.setText(null);
									changeField.setText(null);
									rdbtnExistingCustomer.setSelected(false);
									rdbtnNewCustomer.setSelected(false);
									nameTextField.setText(null);
									cellTextField.setText(null);
									pointField.setText(null);
									invoiceField.setText("0");
									barcodeField.grabFocus();
									barcodeField.requestFocus();
								}
							}
						}
					}
					else{
						if(totalAmount.getText().equals(null)||totalAmount.getText().equals("0"))
						{
							JOptionPane.showMessageDialog(GuestWindow.frmBirdsEye, "No products have been added");
						}
						else{
							if(!nameTextField.getText().equals(null)||!cellTextField.getText().equals(null))
							{
								conn=sqliteConnection.dbConnection();
								conn1=nilAchalConnection.dbConnection();
								try {
									ResultSet point=conn.createStatement().executeQuery("SELECT * FROM customerTable;");
									while(point.next())
									{
										if(cellTextField.getText().equals(point.getString("cellNumber"))&& Integer.parseInt(totalAmount.getText())>=500)
										{
											conn.createStatement().execute("UPDATE customerTable SET purchasedAmounts='"+totalAmount.getText()+"',points='"+Integer.toString(Integer.parseInt(totalAmount.getText())/500+Integer.parseInt(point.getString("points")))+"' WHERE cellNumber='"+cellTextField.getText().toString()+"';");
										}
									}
									point.close();
									ResultSet rs= conn.createStatement().executeQuery("SELECT * FROM itemDataTable;");
									int saleTotal=0;
									int profit=0;
									for(int n=0;n<soldItems.size();n++)
									{
										conn1.createStatement().execute("INSERT INTO salesTable VALUES('"+invoiceField.getText().toString()+"','"+soldItems.elementAt(n).barcode+"','"+soldItems.elementAt(n).name+"','"+soldItems.elementAt(n).brand+"','"+soldItems.elementAt(n).volume+"','"+soldItems.elementAt(n).quantity+"','"+soldItems.elementAt(n).buyPrice+"','"+soldItems.elementAt(n).sellPrice+"','"+soldItems.elementAt(n).totalSellPrice+"','"+nameTextField.getText()+"','"+cellTextField.getText()+"','"+Login.user+"','"+getCurrentDate()+"','"+getCurrentTime()+"');");
										if(Integer.parseInt(soldItems.elementAt(n).buyPrice.toString())!=0)
										{
											profit+=Integer.parseInt(soldItems.elementAt(n).totalSellPrice.toString())-(Integer.parseInt(soldItems.elementAt(n).quantity)*Integer.parseInt(soldItems.elementAt(n).buyPrice));
										}
										saleTotal+=Integer.parseInt(soldItems.elementAt(n).totalSellPrice);
										while(rs.next())
										{
											if(soldItems.elementAt(n).barcode.equals(rs.getString("barcode"))||soldItems.elementAt(n).name.equals(rs.getString("itemName")))
											{
												if(rs.getInt("quantity")!=0)
												{
													int newQuan=rs.getInt("quantity")-Integer.parseInt(soldItems.elementAt(n).quantity);
													String update="UPDATE itemDataTable SET quantity='"+newQuan+"' WHERE barcode='"+soldItems.elementAt(n).barcode+"';";
													Statement upst=conn.createStatement();
													upst.executeUpdate(update);
													upst.close();
												}
												else
												{
													break;
												}
											}
										}
									}
									conn1.createStatement().execute("INSERT INTO saleInfo VALUES('"+invoiceField.getText().toString()+"','"+Integer.toString(saleTotal)+"','"+getCurrentDate()+"','"+Integer.toString(profit)+"');");
									rs.close();
									conn.close();
									conn1.close();
								} catch (SQLException e2) {
									JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
								}
								
								GuestNilAchalPrint.main(null);
								totalAmount.setText("0");
								DefaultTableModel re=(DefaultTableModel) salesTable.getModel();
								re.setRowCount(0);
								rdbtnExistingCustomer.setSelected(false);
								rdbtnNewCustomer.setSelected(false);
								nameTextField.setText(null);
								cellTextField.setText(null);
								pointField.setText(null);
								soldItems.clear();
								invoiceField.setText("0");
								barcodeField.grabFocus();
							}
							
							else
							{
								try {
									conn=sqliteConnection.dbConnection();
									conn1=nilAchalConnection.dbConnection();
									int saleTotal=0;
									int newQuan=0;
									ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM itemDataTable;");
									int profit=0;
									for(int n=0;n<soldItems.size();n++)
									{
										saleTotal+=Integer.parseInt(soldItems.elementAt(n).totalSellPrice);
										conn1.createStatement().execute("INSERT INTO salesTable VALUES('"+invoiceField.getText().toString()+"','"+soldItems.elementAt(n).barcode+"','"+soldItems.elementAt(n).name+"','"+soldItems.elementAt(n).brand+"','"+soldItems.elementAt(n).volume+"','"+soldItems.elementAt(n).quantity+"','"+soldItems.elementAt(n).buyPrice+"','"+soldItems.elementAt(n).sellPrice+"','"+soldItems.elementAt(n).totalSellPrice+"','null','null','"+Login.user+"','"+getCurrentDate()+"','"+getCurrentTime()+"');");
										if(Integer.parseInt(soldItems.elementAt(n).buyPrice.toString())!=0)
										{
											profit+=Integer.parseInt(soldItems.elementAt(n).totalSellPrice.toString())-(Integer.parseInt(soldItems.elementAt(n).quantity)*Integer.parseInt(soldItems.elementAt(n).buyPrice));
										}
										while(rs.next())
										{
											if(soldItems.elementAt(n).barcode.equals(rs.getString("barcode"))||soldItems.elementAt(n).name.equals(rs.getString("itemName")))
											{
												if(rs.getInt("quantity")!=0)
												{
													newQuan=rs.getInt("quantity")-Integer.parseInt(soldItems.elementAt(n).quantity);
													conn.createStatement().executeUpdate("UPDATE itemDataTable SET quantity='"+newQuan+"' WHERE barcode='"+soldItems.elementAt(n).barcode+"';");
												}
												else
												{
													break;
												}
											}
										}
									}
									conn1.createStatement().execute("INSERT INTO saleInfo VALUES('"+invoiceField.getText().toString()+"','"+Integer.toString(saleTotal)+"','"+getCurrentDate()+"','"+Integer.toString(profit)+"');");
									conn.close();
									conn1.close();
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
								}
								GuestNilAchalPrint.main(null);
								totalAmount.setText("0");
								DefaultTableModel re=(DefaultTableModel) salesTable.getModel();
								re.setRowCount(0);
								soldItems.clear();
								rdbtnExistingCustomer.setSelected(false);
								rdbtnNewCustomer.setSelected(false);
								nameTextField.setText(null);
								cellTextField.setText(null);
								pointField.setText(null);
								rcvdTextField.setText("0");
								changeField.setText("0");
								invoiceField.setText("0");
								barcodeField.grabFocus();
								barcodeField.requestFocus();
							}
						}
					}
					
				}
				else
				{
					if(rcvdTextField.getText().toString().equals(null)||rcvdTextField.getText().toString().equals("")||rcvdTextField.getText().toString().equals("0"))
					{
						int dialogResult = JOptionPane.showConfirmDialog (frmBirdsEye, "Would You Like to print without cash received?","Warning",JOptionPane.YES_NO_OPTION);
						if(dialogResult == JOptionPane.YES_OPTION){
							if(totalAmount.getText().equals(null)||totalAmount.getText().equals("0"))
							{
								JOptionPane.showMessageDialog(GuestWindow.frmBirdsEye, "No products have been added");
							}
							else{
								if(!nameTextField.getText().equals(null)||!cellTextField.getText().equals(null))
								{
									conn=sqliteConnection.dbConnection();
									try {
										ResultSet point=conn.createStatement().executeQuery("SELECT * FROM customerTable;");
										while(point.next())
										{
											if(cellTextField.getText().equals(point.getString("cellNumber"))&& Integer.parseInt(totalAmount.getText())>=500)
											{
												conn.createStatement().execute("UPDATE customerTable SET purchasedAmounts='"+totalAmount.getText()+"',points='"+Integer.toString(Integer.parseInt(totalAmount.getText())/500+Integer.parseInt(point.getString("points")))+"' WHERE cellNumber='"+cellTextField.getText().toString()+"';");
											}
										}
										point.close();
										for(int i=0;i<soldItems.size();i++)
										{
											String save="INSERT INTO salesTable VALUES('"+invoiceField.getText().toString()+"','"+soldItems.elementAt(i).barcode+"','"+soldItems.elementAt(i).name+"','"+soldItems.elementAt(i).brand+"','"+soldItems.elementAt(i).volume+"','"+soldItems.elementAt(i).quantity+"','"+soldItems.elementAt(i).buyPrice+"','"+soldItems.elementAt(i).sellPrice+"','"+soldItems.elementAt(i).totalSellPrice+"','"+nameTextField.getText()+"','"+cellTextField.getText()+"','"+Login.user+"','"+getCurrentDate()+"','"+getCurrentTime()+"');";
											conn.createStatement().execute(save);
										}
										String select="SELECT * FROM itemDataTable;";
										Statement st = conn.createStatement();
										ResultSet rs=st.executeQuery(select);
										int saleTotal=0;
										int profit=0;
										int newQuan=0;
										for(int n=0;n<soldItems.size();n++)
										{
											if(Integer.parseInt(soldItems.elementAt(n).buyPrice.toString())!=0)
											{
												profit+=Integer.parseInt(soldItems.elementAt(n).totalSellPrice.toString())-(Integer.parseInt(soldItems.elementAt(n).quantity)*Integer.parseInt(soldItems.elementAt(n).buyPrice));
											}
											saleTotal+=Integer.parseInt(soldItems.elementAt(n).totalSellPrice);
											while(rs.next())
											{
												if(soldItems.elementAt(n).barcode.equals(rs.getString("barcode"))||soldItems.elementAt(n).name.equals(rs.getString("itemName")))
												{
													if(rs.getInt("quantity")!=0)
													{
														newQuan=rs.getInt("quantity")-Integer.parseInt(soldItems.elementAt(n).quantity);
														conn.createStatement().executeUpdate("UPDATE itemDataTable SET quantity='"+newQuan+"' WHERE barcode='"+soldItems.elementAt(n).barcode+"';");
													}
													else
													{
														break;
													}
												}
											}
										}
										conn.createStatement().execute("INSERT INTO saleInfo VALUES('"+invoiceField.getText().toString()+"','"+Integer.toString(saleTotal)+"','"+getCurrentDate()+"','"+Integer.toString(profit)+"');");
										rs.close();
										conn.close();
									} catch (SQLException e2) {
										JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
									}
									GuestPrintWithoutCash.main(null);
									totalAmount.setText("0");
									rcvdTextField.setText(null);
									changeField.setText(null);
									DefaultTableModel re=(DefaultTableModel) salesTable.getModel();
									re.setRowCount(0);
									soldItems.clear();
									rdbtnExistingCustomer.setSelected(false);
									rdbtnNewCustomer.setSelected(false);
									nameTextField.setText(null);
									cellTextField.setText(null);
									pointField.setText(null);
									invoiceField.setText("0");
									barcodeField.grabFocus();
								}
								
								else
								{
									conn=sqliteConnection.dbConnection();
									int saleTotal=0;
									for(int i=0;i<soldItems.size();i++)
									{
										saleTotal+=Integer.parseInt(soldItems.elementAt(i).totalSellPrice);
										
										try {
											conn.createStatement().execute("INSERT INTO salesTable VALUES('"+invoiceField.getText().toString()+"','"+soldItems.elementAt(i).barcode+"','"+soldItems.elementAt(i).name+"','"+soldItems.elementAt(i).brand+"','"+soldItems.elementAt(i).volume+"','"+soldItems.elementAt(i).quantity+"','"+soldItems.elementAt(i).buyPrice+"','"+soldItems.elementAt(i).sellPrice+"','"+soldItems.elementAt(i).totalSellPrice+"','null','null','"+Login.user+"','"+getCurrentDate()+"','"+getCurrentTime()+"');");
											ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM itemDataTable;");
											int profit=0;
											for(int n=0;n<soldItems.size();n++)
											{
												if(Integer.parseInt(soldItems.elementAt(n).buyPrice.toString())!=0)
												{
													profit+=Integer.parseInt(soldItems.elementAt(n).totalSellPrice.toString())-(Integer.parseInt(soldItems.elementAt(n).quantity)*Integer.parseInt(soldItems.elementAt(n).buyPrice));
												}
												while(rs.next())
												{
													if(soldItems.elementAt(n).barcode.equals(rs.getString("barcode"))||soldItems.elementAt(n).name.equals(rs.getString("itemName")))
													{
														if(rs.getInt("quantity")!=0)
														{
															int newQuan=rs.getInt("quantity")-Integer.parseInt(soldItems.elementAt(n).quantity);
															String update="UPDATE itemDataTable SET quantity='"+newQuan+"' WHERE barcode='"+soldItems.elementAt(n).barcode+"';";
															Statement upst=conn.createStatement();
															upst.executeUpdate(update);
															upst.close();
														}
														else
														{
															break;
														}
													}
												}
											}
											conn.createStatement().execute("INSERT INTO saleInfo VALUES('"+invoiceField.getText().toString()+"','"+Integer.toString(saleTotal)+"','"+getCurrentDate()+"','"+Integer.toString(profit)+"');");
											rs.close();
											conn.close();
										} catch (SQLException e) {JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");}
									}
									GuestPrintWithoutCash.main(null);
									totalAmount.setText("0");
									DefaultTableModel re=(DefaultTableModel) salesTable.getModel();
									re.setRowCount(0);
									soldItems.clear();
									rcvdTextField.setText(null);
									changeField.setText(null);
									rdbtnExistingCustomer.setSelected(false);
									rdbtnNewCustomer.setSelected(false);
									nameTextField.setText(null);
									cellTextField.setText(null);
									pointField.setText(null);
									invoiceField.setText("0");
									barcodeField.grabFocus();
									barcodeField.requestFocus();
								}
							}
						}
					}
					else{
						if(totalAmount.getText().equals(null)||totalAmount.getText().equals("0"))
						{
							JOptionPane.showMessageDialog(GuestWindow.frmBirdsEye, "No products have been added");
						}
						else{
							if(!nameTextField.getText().equals(null)||!cellTextField.getText().equals(null))
							{
								conn=sqliteConnection.dbConnection();
								try {
									ResultSet point=conn.createStatement().executeQuery("SELECT * FROM customerTable;");
									while(point.next())
									{
										if(cellTextField.getText().equals(point.getString("cellNumber"))&& Integer.parseInt(totalAmount.getText())>=500)
										{
											conn.createStatement().execute("UPDATE customerTable SET purchasedAmounts='"+totalAmount.getText()+"',points='"+Integer.toString(Integer.parseInt(totalAmount.getText())/500+Integer.parseInt(point.getString("points")))+"' WHERE cellNumber='"+cellTextField.getText().toString()+"';");
										}
									}
									point.close();
									ResultSet rs= conn.createStatement().executeQuery("SELECT * FROM itemDataTable;");
									int saleTotal=0;
									int profit=0;
									for(int n=0;n<soldItems.size();n++)
									{
										conn.createStatement().execute("INSERT INTO salesTable VALUES('"+invoiceField.getText().toString()+"','"+soldItems.elementAt(n).barcode+"','"+soldItems.elementAt(n).name+"','"+soldItems.elementAt(n).brand+"','"+soldItems.elementAt(n).volume+"','"+soldItems.elementAt(n).quantity+"','"+soldItems.elementAt(n).buyPrice+"','"+soldItems.elementAt(n).sellPrice+"','"+soldItems.elementAt(n).totalSellPrice+"','"+nameTextField.getText()+"','"+cellTextField.getText()+"','"+Login.user+"','"+getCurrentDate()+"','"+getCurrentTime()+"');");
										if(Integer.parseInt(soldItems.elementAt(n).buyPrice.toString())!=0)
										{
											profit+=Integer.parseInt(soldItems.elementAt(n).totalSellPrice.toString())-(Integer.parseInt(soldItems.elementAt(n).quantity)*Integer.parseInt(soldItems.elementAt(n).buyPrice));
										}
										saleTotal+=Integer.parseInt(soldItems.elementAt(n).totalSellPrice);
										while(rs.next())
										{
											if(soldItems.elementAt(n).barcode.equals(rs.getString("barcode"))||soldItems.elementAt(n).name.equals(rs.getString("itemName")))
											{
												if(rs.getInt("quantity")!=0)
												{
													int newQuan=rs.getInt("quantity")-Integer.parseInt(soldItems.elementAt(n).quantity);
													String update="UPDATE itemDataTable SET quantity='"+newQuan+"' WHERE barcode='"+soldItems.elementAt(n).barcode+"';";
													Statement upst=conn.createStatement();
													upst.executeUpdate(update);
													upst.close();
												}
												else
												{
													break;
												}
											}
										}
									}
									conn.createStatement().execute("INSERT INTO saleInfo VALUES('"+invoiceField.getText().toString()+"','"+Integer.toString(saleTotal)+"','"+getCurrentDate()+"','"+Integer.toString(profit)+"');");
									rs.close();
									conn.close();
								} catch (SQLException e2) {
									JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
								}
								
								GuestPrint.main(null);
								totalAmount.setText("0");
								DefaultTableModel re=(DefaultTableModel) salesTable.getModel();
								re.setRowCount(0);
								rdbtnExistingCustomer.setSelected(false);
								rdbtnNewCustomer.setSelected(false);
								nameTextField.setText(null);
								cellTextField.setText(null);
								pointField.setText(null);
								soldItems.clear();
								invoiceField.setText("0");
								barcodeField.grabFocus();
							}
							
							else
							{
								try {
									conn=sqliteConnection.dbConnection();
									int saleTotal=0;
									int newQuan=0;
									ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM itemDataTable;");
									int profit=0;
									for(int n=0;n<soldItems.size();n++)
									{
										saleTotal+=Integer.parseInt(soldItems.elementAt(n).totalSellPrice);
										conn.createStatement().execute("INSERT INTO salesTable VALUES('"+invoiceField.getText().toString()+"','"+soldItems.elementAt(n).barcode+"','"+soldItems.elementAt(n).name+"','"+soldItems.elementAt(n).brand+"','"+soldItems.elementAt(n).volume+"','"+soldItems.elementAt(n).quantity+"','"+soldItems.elementAt(n).buyPrice+"','"+soldItems.elementAt(n).sellPrice+"','"+soldItems.elementAt(n).totalSellPrice+"','null','null','"+Login.user+"','"+getCurrentDate()+"','"+getCurrentTime()+"');");
										if(Integer.parseInt(soldItems.elementAt(n).buyPrice.toString())!=0)
										{
											profit+=Integer.parseInt(soldItems.elementAt(n).totalSellPrice.toString())-(Integer.parseInt(soldItems.elementAt(n).quantity)*Integer.parseInt(soldItems.elementAt(n).buyPrice));
										}
										while(rs.next())
										{
											if(soldItems.elementAt(n).barcode.equals(rs.getString("barcode"))||soldItems.elementAt(n).name.equals(rs.getString("itemName")))
											{
												if(rs.getInt("quantity")!=0)
												{
													newQuan=rs.getInt("quantity")-Integer.parseInt(soldItems.elementAt(n).quantity);
													conn.createStatement().executeUpdate("UPDATE itemDataTable SET quantity='"+newQuan+"' WHERE barcode='"+soldItems.elementAt(n).barcode+"';");
												}
												else
												{
													break;
												}
											}
										}
									}
									conn.createStatement().execute("INSERT INTO saleInfo VALUES('"+invoiceField.getText().toString()+"','"+Integer.toString(saleTotal)+"','"+getCurrentDate()+"','"+Integer.toString(profit)+"');");
									conn.close();
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
								}
								GuestPrint.main(null);
								totalAmount.setText("0");
								DefaultTableModel re=(DefaultTableModel) salesTable.getModel();
								re.setRowCount(0);
								soldItems.clear();
								rdbtnExistingCustomer.setSelected(false);
								rdbtnNewCustomer.setSelected(false);
								nameTextField.setText(null);
								cellTextField.setText(null);
								pointField.setText(null);
								rcvdTextField.setText("0");
								changeField.setText("0");
								invoiceField.setText("0");
								barcodeField.grabFocus();
								barcodeField.requestFocus();
							}
						}
					}
				}
			}
		});
		btnNewButton_2.setForeground(Color.DARK_GRAY);
		btnNewButton_2.setBackground(new Color(25, 25, 112));
		btnNewButton_2.setOpaque(true);
		btnNewButton_2.setFont(new Font("Constantia", Font.BOLD, 20));
		btnNewButton_2.setBounds(10, 11, 117, 53);
		panel_9.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cancel");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model=(DefaultTableModel) salesTable.getModel();
				model.setRowCount(0);
				totalAmount.setText("0");
				rdbtnExistingCustomer.setSelected(false);
				rdbtnNewCustomer.setSelected(false);
				nameTextField.setText(null);
				cellTextField.setText(null);
				rcvdTextField.setText("0");
				changeField.setText("0");
				soldItems.removeAllElements();
				pointField.setText(null);
				invoiceField.setText("0");
				barcodeField.requestFocus();
			}
		});
		btnNewButton_3.setForeground(Color.DARK_GRAY);
		btnNewButton_3.setBackground(new Color(153, 0, 0));
		btnNewButton_3.setFont(new Font("Constantia", Font.BOLD, 20));
		btnNewButton_3.setBounds(137, 11, 115, 53);
		panel_9.add(btnNewButton_3);
		
		JPanel panel_25 = new JPanel();
		panel_25.setBorder(new LineBorder(SystemColor.window, 1, true));
		panel_25.setBackground(new Color(0,0,0,0));
		panel_25.setOpaque(false);
		panel_25.setBounds(28, 25, 262, 27);
		panel_24.add(panel_25);
		panel_25.setLayout(null);
		
		rdbtnExistingCustomer = new JRadioButton("Existing  Customer");
		rdbtnExistingCustomer.setBounds(0, 0, 136, 26);
		panel_25.add(rdbtnExistingCustomer);
		rdbtnExistingCustomer.setForeground(SystemColor.textHighlightText);
		rdbtnExistingCustomer.setBackground(new Color(0,0,0,0));
		rdbtnExistingCustomer.setOpaque(false);
		rdbtnExistingCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		rdbtnNewCustomer = new JRadioButton("New Customer");
		rdbtnNewCustomer.setForeground(SystemColor.textHighlightText);
		rdbtnNewCustomer.setBackground(new Color(0,0,0,0));
		rdbtnNewCustomer.setOpaque(false);
		rdbtnNewCustomer.setBounds(152, 0, 110, 26);
		panel_25.add(rdbtnNewCustomer);
		rdbtnNewCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnExistingCustomer.isSelected()==true)
				{
					usePointField.setEnabled(false);
					rdbtnExistingCustomer.setSelected(false);
					nameTextField.setText(null);
					cellTextField.setText(null);
					pointField.setText(null);
					GuestAddCustomer window = new GuestAddCustomer();
					window.frmAddCustomer.setVisible(true);
				}
				else{
					GuestAddCustomer window = new GuestAddCustomer();
					window.frmAddCustomer.setVisible(true);
				}
			}
		});
		rdbtnNewCustomer.setSelected(false);
		
		nilAchal = new JRadioButton("Print with Nil Achal Fashion House");
		nilAchal.setForeground(new Color(255, 127, 80));
		nilAchal.setFont(new Font("Tahoma", Font.BOLD, 13));
		nilAchal.setOpaque(false);
		nilAchal.setBackground(new Color(0,0,0,0));
		nilAchal.setHorizontalAlignment(SwingConstants.CENTER);
		nilAchal.setBounds(28, 588, 262, 23);
		panel_24.add(nilAchal);
		rdbtnExistingCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usePointField.setEnabled(true);
				rdbtnNewCustomer.setSelected(false);
				GuestCustomerCheck window;
				try {
					nameTextField.setText(null);
					cellTextField.setText(null);
					pointField.setText(null);
					window = new GuestCustomerCheck();
					window.frmCustomers.setVisible(true);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
				}
				
			}
		});
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(UIManager.getColor("ToolBar.floatingForeground"), 2, true));
		panel_7.setBounds(10, 11, 976, 603);
		panel_7.setBackground(new Color(0,0,0,0));
		salesPanel.add(panel_7);
		panel_7.setLayout(null);
		
		barcodeField = new JTextField();
		barcodeField.setBackground(SystemColor.window);
		barcodeField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					GuestManualSearch window;
					try {
						window = new GuestManualSearch();
						window.frmManualSearch.setVisible(true);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
					}
					
				}
			}
		});
		barcodeField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		barcodeField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) salesTable.getModel();
				String givenBarcode=barcodeField.getText();
				try {
					conn=sqliteConnection.dbConnection();
					String barQuery="SELECT * FROM itemDataTable;";
					Statement st=conn.createStatement();
					ResultSet rs=st.executeQuery(barQuery);
					boolean matched=false;
					ResultSet rs1=conn.createStatement().executeQuery("SELECT * FROM saleInfo;");
					int ic=1000;
					while(rs1.next())
					{
						ic++;
					}
					invoiceField.setText(Integer.toString(ic));
					
					rs1.close();
					while(rs.next())
					{	
						if(givenBarcode.equalsIgnoreCase(rs.getString("barcode")))
						{
							if(model.getRowCount()==0)
							{
								model.addRow(new Object[]{rs.getString("barcode"),rs.getString("itemName"),rs.getString("brand"),rs.getString("volume"),1,rs.getString("vat"),rs.getString("sellPrice"),rs.getString("sellPrice")});
								salesTable.getColumn("Barcode Number").setCellRenderer( centerRenderer );
								salesTable.getColumn("Products").setCellRenderer( centerRenderer );
								salesTable.getColumn("Brand").setCellRenderer( centerRenderer );
								salesTable.getColumn("Volume").setCellRenderer( centerRenderer );
								salesTable.getColumn("Quantity").setCellRenderer( centerRenderer );
								salesTable.getColumn("VAT").setCellRenderer( centerRenderer );
								salesTable.getColumn("Price").setCellRenderer( centerRenderer );
								salesTable.getColumn("Net Price").setCellRenderer( centerRenderer );
								SoldProductInfo sp=new SoldProductInfo(invoiceField.getText(),givenBarcode,rs.getString("itemName"),rs.getString("brand"),rs.getString("volume"),"1",rs.getString("buyPrice"),rs.getString("sellPrice"),Integer.toString(1*Integer.parseInt(rs.getString("sellPrice"))));
								soldItems.add(sp);
								totalAmount.setText(rs.getString("sellPrice"));
								matched=true;
								
								break;
							}
							else{
								int row=0;
								boolean exist=false;
								for(int i=0;i<salesTable.getRowCount();i++)
								{
									if(salesTable.getValueAt(i, 0).equals(givenBarcode)){
										row=i;
										exist=true;
									}
								}
								if(exist==true)
								{
									salesTable.setValueAt((1+Integer.parseInt(model.getValueAt(row, 4).toString())), row, 4);
									salesTable.setValueAt(Integer.toString(Integer.parseInt(model.getValueAt(row, 6).toString())*Integer.parseInt(model.getValueAt(row, 4).toString())), row, 7);
									salesTable.getColumn("Barcode Number").setCellRenderer( centerRenderer );
									salesTable.getColumn("Products").setCellRenderer( centerRenderer );
									salesTable.getColumn("Brand").setCellRenderer( centerRenderer );
									salesTable.getColumn("Volume").setCellRenderer( centerRenderer );
									salesTable.getColumn("Quantity").setCellRenderer( centerRenderer );
									salesTable.getColumn("VAT").setCellRenderer( centerRenderer );
									salesTable.getColumn("Price").setCellRenderer( centerRenderer );
									salesTable.getColumn("Net Price").setCellRenderer( centerRenderer );
									vectorSetter(givenBarcode,soldItems);
									int a=0;
									for(int z=0;z<salesTable.getRowCount();z++)
									{
										a+=Integer.parseInt(salesTable.getValueAt(z, 7).toString());
										
									}
									totalAmount.setText(Integer.toString(a));
									
									matched=true;
									break;
								}
								else{
									model.addRow(new Object[]{rs.getString("barcode"),rs.getString("itemName"),rs.getString("brand"),rs.getString("volume"),1,rs.getInt("vat"),rs.getInt("sellPrice"),rs.getInt("sellPrice")});
									salesTable.getColumn("Barcode Number").setCellRenderer( centerRenderer );
									salesTable.getColumn("Products").setCellRenderer( centerRenderer );
									salesTable.getColumn("Brand").setCellRenderer( centerRenderer );
									salesTable.getColumn("Volume").setCellRenderer( centerRenderer );
									salesTable.getColumn("Quantity").setCellRenderer( centerRenderer );
									salesTable.getColumn("VAT").setCellRenderer( centerRenderer );
									salesTable.getColumn("Price").setCellRenderer( centerRenderer );
									salesTable.getColumn("Net Price").setCellRenderer( centerRenderer );
									//totalAmount.setText(Integer.toString(Integer.parseInt(totalAmount.getText())+rs.getInt("sellPrice")));
									SoldProductInfo sp=new SoldProductInfo(invoiceField.getText(),givenBarcode,rs.getString("itemName"),rs.getString("brand"),rs.getString("volume"),"1",rs.getString("buyPrice"),rs.getString("sellPrice"),Integer.toString(1*Integer.parseInt(rs.getString("sellPrice"))));
									soldItems.add(sp);
									int a=0;
									for(int z=0;z<salesTable.getRowCount();z++)
									{
										a+=Integer.parseInt(salesTable.getValueAt(z, 7).toString());
										
									}
									totalAmount.setText(Integer.toString(a));
									matched=true;
									break;
								}
							}
						}
						
					}
					if(!matched)
					{
						JOptionPane.showMessageDialog(GuestWindow.frmBirdsEye,"Product Not Found");
						if(salesTable.getRowCount()==0)
						{
							invoiceField.setText("0");
						}
					}
					
					matched=false;
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
				}
				barcodeField.setText(null);
				barcodeField.requestFocus();
				salesTable.clearSelection();
			}
		});
		
		barcodeField.setHorizontalAlignment(SwingConstants.CENTER);
		barcodeField.setBounds(13, 13, 377, 28);
		//barcodeField.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
		barcodeField.setForeground(Color.black);
		barcodeField.requestFocus();
		
		JPanel panel_14 = new JPanel();
		panel_14.setBounds(606, 13, 356, 28);
		panel_7.add(panel_14);
		panel_14.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Invoice No.");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(0, 0, 147, 28);
		panel_14.add(lblNewLabel_4);
		
		invoiceField = new JTextField();
		invoiceField.setBackground(SystemColor.text);
		invoiceField.setEnabled(false);
		invoiceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		invoiceField.setHorizontalAlignment(SwingConstants.CENTER);
		invoiceField.setBounds(157, 0, 199, 28);
		panel_14.add(invoiceField);
		invoiceField.setColumns(10);
		panel_7.add(barcodeField);
		barcodeField.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 50, 956, 542);
		scrollPane_2.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Added Products",TitledBorder.CENTER,TitledBorder.CENTER,new Font("times new roman",Font.BOLD,18), Color.black));
		panel_7.add(scrollPane_2);
		
		
		
		salesTable = new JTable(){  
		      public boolean isCellEditable(int row, int column){  
		          if(salesTable.getSelectedColumn()==4 || salesTable.getSelectedColumn()==8||salesTable.getSelectedColumn()==6)
		          {
		        	  return true;
		          }
		    	  return false;  
		        }  
		      };
		salesTable.setBorder(new LineBorder(new Color(0, 139, 139), 1, true));
		//salesTable = new JTable();
		salesTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		//salesTable.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
	
		salesTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Barcode Number", "Products", "Brand", "Volume", "Quantity", "VAT", "Price", "Net Price", "Delete"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		salesTable.getColumnModel().getColumn(0).setResizable(false);
		salesTable.getColumnModel().getColumn(0).setPreferredWidth(131);
		salesTable.getColumnModel().getColumn(1).setResizable(false);
		salesTable.getColumnModel().getColumn(1).setPreferredWidth(205);
		salesTable.getColumnModel().getColumn(1).setMinWidth(25);
		salesTable.getColumnModel().getColumn(2).setResizable(false);
		salesTable.getColumnModel().getColumn(2).setPreferredWidth(154);
		salesTable.getColumnModel().getColumn(3).setResizable(false);
		salesTable.getColumnModel().getColumn(3).setPreferredWidth(81);
		salesTable.getColumnModel().getColumn(4).setResizable(false);
		salesTable.getColumnModel().getColumn(4).setPreferredWidth(54);
		salesTable.getColumnModel().getColumn(5).setResizable(false);
		salesTable.getColumnModel().getColumn(5).setPreferredWidth(58);
		salesTable.getColumnModel().getColumn(6).setResizable(false);
		salesTable.getColumnModel().getColumn(7).setResizable(false);
		salesTable.getColumnModel().getColumn(7).setPreferredWidth(81);
		salesTable.getColumnModel().getColumn(8).setResizable(false);
		salesTable.getColumnModel().getColumn(8).setPreferredWidth(43);
		salesTable.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	            	
	            	if(!totalAmount.getText().equals(null)||!totalAmount.getText().equals("0")){
		            	  
	            		int row = salesTable.getSelectedRow();
	            		int givenQuantity = Integer.parseInt(salesTable.getValueAt(row, 4).toString());
	            		int sellPrice=Integer.parseInt(salesTable.getValueAt(row, 6).toString());
	            		salesTable.setValueAt(Integer.toString(givenQuantity*sellPrice), row, 7);
	            		int a=0;
	            		for(int n=0;n<salesTable.getRowCount();n++)
	            		{
	            			a=a+Integer.parseInt(salesTable.getValueAt(n,7).toString());
		            	}
		             
	            		for(int i=0;i<soldItems.size();i++)
	            		{
		            		  
	            			if(soldItems.elementAt(i).barcode==salesTable.getValueAt(row, 0)){
		            			  
	            				soldItems.elementAt(i).quantity=Integer.toString(givenQuantity);
	            				soldItems.elementAt(i).sellPrice=Integer.toString(sellPrice);
	            				soldItems.elementAt(i).totalSellPrice=Integer.toString(givenQuantity*sellPrice);
		            		  }
		            	  }
		            	  
		            	 totalAmount.setText(Integer.toString(a));
		            	 barcodeField.setText(null);
		            	 barcodeField.requestFocus();
		            	 salesTable.clearSelection();
		            	  
		               }
	                
	            }
	        }
	    });
		final TableCellRenderer saleTableRenderer = salesTable.getTableHeader().getDefaultRenderer();
		salesTable.getTableHeader().setDefaultRenderer(new TableCellRenderer() 
	    	{
	            @Override
	           public Component getTableCellRendererComponent(JTable table, 
	                Object value, boolean isSelected, boolean hasFocus, 
	                int row, int column) {
	                JLabel lbl = (JLabel) saleTableRenderer.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, column);
	                lbl.setForeground(Color.lightGray);
	                lbl.setBorder(BorderFactory.createCompoundBorder(lbl.getBorder(), 
	                      BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	                lbl.setHorizontalAlignment(SwingConstants.CENTER);
	                if (isSelected) {
	                    lbl.setForeground(Color.red);
	                    lbl.setBackground(Color.lightGray);
	                } else {
	                    lbl.setForeground(Color.black);
	                    lbl.setBackground(Color.white);
	                }
	                return lbl;
	            }
	        });
		salesTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
		salesTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox()));
		salesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel listModel=salesTable.getSelectionModel();
		scrollPane_2.setViewportView(salesTable);
		listModel.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e){
				if(e.getValueIsAdjusting()){
					return;
				}
				ListSelectionModel lsm=(ListSelectionModel)e.getSource();
				if(!lsm.isSelectionEmpty()){
					lsm.getMinSelectionIndex();
				}
				
			}
		});
		
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setBackground(new Color(46,238,237));
		inventoryPanel.setBackground(SystemColor.info);
		tabbedPane.addTab("Inventory", null, inventoryPanel, null);
		inventoryPanel.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setFont(new Font("Segoe UI Historic", Font.BOLD, 12));
		tabbedPane_1.setBounds(24, 11, 1259, 599);
		tabbedPane_1.setBackground(new Color(0,0,0,0));
		inventoryPanel.add(tabbedPane_1);
		
		JPanel addItem = new JPanel();
		addItem.setBackground(new Color(221,210,205));
		tabbedPane_1.addTab("Add Items", null, addItem, null);
		addItem.setLayout(null);
		
		JPanel panel_26 = new JPanel();
		panel_26.setBorder(new LineBorder(SystemColor.window, 4, true));
		panel_26.setBackground(new Color(181,171,167));
		panel_26.setBounds(53, 195, 1152, 274);
		addItem.add(panel_26);
		panel_26.setLayout(null);
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setBounds(70, 21, 153, 44);
		panel_26.add(lblItemName);
		lblItemName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItemName.setFont(new Font("Constantia", Font.BOLD, 18));
		
		ItemNameTextField = new JTextField();
		ItemNameTextField.setBounds(268, 26, 200, 36);
		panel_26.add(ItemNameTextField);
		ItemNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		ItemNameTextField.setFont(new Font("Constantia", Font.PLAIN, 15));
		ItemNameTextField.setColumns(10);
		ItemNameTextField.setText(null);
		
		JLabel lblBrandName = new JLabel("Brand Name");
		lblBrandName.setBounds(94, 81, 129, 44);
		panel_26.add(lblBrandName);
		lblBrandName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBrandName.setFont(new Font("Constantia", Font.BOLD, 18));
		
		brandNameTextField = new JTextField();
		brandNameTextField.setBounds(268, 86, 200, 36);
		panel_26.add(brandNameTextField);
		brandNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		brandNameTextField.setFont(new Font("Constantia", Font.PLAIN, 15));
		brandNameTextField.setColumns(10);
		brandNameTextField.setText(null);
		
		JLabel lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setBounds(70, 148, 153, 44);
		panel_26.add(lblExpiryDate);
		lblExpiryDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExpiryDate.setFont(new Font("Constantia", Font.BOLD, 18));
		
		dayComboBox = new JComboBox();
		dayComboBox.setBounds(268, 153, 58, 36);
		panel_26.add(dayComboBox);
		dayComboBox.setMaximumRowCount(16);
		dayComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dayComboBox.setFont(new Font("Constantia", Font.PLAIN, 15));
		dayComboBox.setSelectedIndex(-1);
		
		 monthComboBox = new JComboBox();
		monthComboBox.setBounds(339, 153, 58, 36);
		panel_26.add(monthComboBox);
		monthComboBox.setMaximumRowCount(12);
		monthComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		monthComboBox.setFont(new Font("Constantia", Font.PLAIN, 15));
		monthComboBox.setSelectedIndex(-1);
		
		 yearComboBox = new JComboBox();
		yearComboBox.setBounds(410, 153, 58, 36);
		panel_26.add(yearComboBox);
		yearComboBox.setMaximumRowCount(10);
		yearComboBox.setModel(new DefaultComboBoxModel(gettingYearComBox()));
		yearComboBox.setFont(new Font("Constantia", Font.PLAIN, 15));
		yearComboBox.setSelectedIndex(-1);
		
		sellPriceTextField = new JTextField();
		sellPriceTextField.setBounds(268, 213, 200, 36);
		panel_26.add(sellPriceTextField);
		sellPriceTextField.setHorizontalAlignment(SwingConstants.CENTER);
		sellPriceTextField.setFont(new Font("Constantia", Font.PLAIN, 15));
		sellPriceTextField.setColumns(10);
		sellPriceTextField.setText(null);
		
		JLabel lblSellingPrice = new JLabel("Selling Price");
		lblSellingPrice.setBounds(70, 208, 153, 44);
		panel_26.add(lblSellingPrice);
		lblSellingPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSellingPrice.setFont(new Font("Constantia", Font.BOLD, 18));
		
		JLabel lblVat = new JLabel("VAT");
		lblVat.setBounds(616, 208, 166, 44);
		panel_26.add(lblVat);
		lblVat.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVat.setFont(new Font("Constantia", Font.BOLD, 18));
		
		vatTextField = new JTextField();
		vatTextField.setBounds(827, 213, 136, 36);
		panel_26.add(vatTextField);
		vatTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(vatTextField.getText().length()>=3)
			    {
					vatTextField.setText(vatTextField.getText().substring(0, 2));
			    }
			}
		});
		vatTextField.setHorizontalAlignment(SwingConstants.CENTER);
		vatTextField.setFont(new Font("Constantia", Font.PLAIN, 15));
		vatTextField.setColumns(10);
		vatTextField.setText("0");
		
		buyPriceTextField = new JTextField();
		buyPriceTextField.setBounds(827, 153, 200, 36);
		panel_26.add(buyPriceTextField);
		buyPriceTextField.setHorizontalAlignment(SwingConstants.CENTER);
		buyPriceTextField.setFont(new Font("Constantia", Font.PLAIN, 15));
		buyPriceTextField.setColumns(10);
		buyPriceTextField.setText(null);
		
		JLabel lblBuyingPrice = new JLabel("Buying Price");
		lblBuyingPrice.setBounds(616, 148, 166, 44);
		panel_26.add(lblBuyingPrice);
		lblBuyingPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBuyingPrice.setFont(new Font("Constantia", Font.BOLD, 18));
		
		quantityTextField = new JTextField();
		quantityTextField.setBounds(827, 86, 200, 36);
		panel_26.add(quantityTextField);
		quantityTextField.setHorizontalAlignment(SwingConstants.CENTER);
		quantityTextField.setFont(new Font("Constantia", Font.PLAIN, 15));
		quantityTextField.setColumns(10);
		quantityTextField.setText(null);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(612, 81, 170, 44);
		panel_26.add(lblQuantity);
		lblQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantity.setFont(new Font("Constantia", Font.BOLD, 18));
		
		vwTextField = new JTextField();
		vwTextField.setBounds(827, 26, 200, 36);
		panel_26.add(vwTextField);
		vwTextField.setHorizontalAlignment(SwingConstants.CENTER);
		vwTextField.setFont(new Font("Constantia", Font.PLAIN, 15));
		vwTextField.setColumns(10);
		vwTextField.setText(null);
		
		JLabel lblVolumeweight = new JLabel("Volume/Weight");
		lblVolumeweight.setBounds(612, 21, 170, 44);
		panel_26.add(lblVolumeweight);
		lblVolumeweight.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVolumeweight.setFont(new Font("Constantia", Font.BOLD, 18));
		
		fixedVatText = new JTextField();
		fixedVatText.setBounds(977, 213, 50, 36);
		panel_26.add(fixedVatText);
		fixedVatText.setEditable(false);
		fixedVatText.setText("%");
		fixedVatText.setHorizontalAlignment(SwingConstants.CENTER);
		fixedVatText.setFont(new Font("Constantia", Font.PLAIN, 15));
		fixedVatText.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(155,145,142));
		panel_1.setBorder(new LineBorder(new Color(255, 255, 255), 5, true));
		panel_1.setBounds(441, 29, 414, 149);
		addItem.add(panel_1);
		panel_1.setLayout(null);
		
		genBarTextField = new JTextField();
		genBarTextField.setHorizontalAlignment(SwingConstants.CENTER);
		genBarTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		genBarTextField.setEditable(true);
		
		genBarTextField.setBackground(SystemColor.text);
		genBarTextField.setBounds(46, 33, 322, 33);
		panel_1.add(genBarTextField);
		genBarTextField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Generate Barcode");
		btnNewButton_1.setBackground(SystemColor.controlDkShadow);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					conn=sqliteConnection.dbConnection();
					String query="SELECT barcode FROM itemDataTable;";
					Statement st=conn.createStatement();
					ResultSet rs=st.executeQuery(query);
					while(rs.next())
					{
						
						long number = 0l;
						Random rand = new Random();
						number = (rand.nextInt(1000000)+1000000000l) * (rand.nextInt(900)+100);
						
						if(rs.getInt("barcode")!=number)
						{
							genBarTextField.setText(""+number+"");
							break;
						}
						else continue;
					}
					
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
				}
				
			}
		});
		btnNewButton_1.setToolTipText("Click to generate barcode");
		btnNewButton_1.setFont(new Font("Constantia", Font.BOLD, 13));
		btnNewButton_1.setBounds(134, 84, 147, 42);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBackground(SystemColor.controlDkShadow);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(genBarTextField.getText().equals(null)||ItemNameTextField.getText().equals("") || ItemNameTextField.getText().equals(null) || sellPriceTextField.getText().equals(null) || sellPriceTextField.getText().equals("")){
					JOptionPane.showMessageDialog(GuestWindow.frmBirdsEye, "Nothing to save");
				}
				else{
					try {
						conn=sqliteConnection.dbConnection();
						String expiryDate=dayComboBox.getSelectedItem()+"-"+monthComboBox.getSelectedItem()+"-"+yearComboBox.getSelectedItem();
						boolean match=false;
						ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM itemDataTable;");
						while(rs.next())
						{
							if(rs.getString("barcode").equals(genBarTextField.getText()))
							{
								match=true;
							}
						}
						if(match==true)
						{
							JOptionPane.showMessageDialog(frmBirdsEye, "This product have been already added!");
						}
						else{
							conn.createStatement().executeUpdate("INSERT INTO itemDataTable VALUES('"+genBarTextField.getText()+"','"+ItemNameTextField.getText()+"','"+brandNameTextField.getText()+"','"+vwTextField.getText()+"','"+quantityTextField.getText()+"','"+expiryDate+"','"+buyPriceTextField.getText()+"','"+vatTextField.getText()+"','"+sellPriceTextField.getText()+"');");
							JOptionPane.showMessageDialog(GuestWindow.frmBirdsEye, "Successfully Saved");
							genBarTextField.setText(null);
							ItemNameTextField.setText(null);
							brandNameTextField.setText(null);
							vwTextField.setText(null);
							quantityTextField.setText(null);
							dayComboBox.setSelectedIndex(-1);
							monthComboBox.setSelectedIndex(-1);
							yearComboBox.setSelectedIndex(-1);
							buyPriceTextField.setText(null);
							vatTextField.setText(null);
							sellPriceTextField.setText(null);
						}
						rs.close();
						conn.close();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
					}
				}
			}
			
		});
		
		btnNewButton.setFont(new Font("Constantia", Font.BOLD, 20));
		btnNewButton.setBounds(515, 495, 99, 44);
		addItem.add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(SystemColor.controlDkShadow);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				genBarTextField.setText(null);
				ItemNameTextField.setText(null);
				brandNameTextField.setText(null);
				vwTextField.setText(null);
				quantityTextField.setText(null);
				dayComboBox.setSelectedIndex(-1);
				monthComboBox.setSelectedIndex(-1);
				yearComboBox.setSelectedIndex(-1);
				buyPriceTextField.setText(null);
				vatTextField.setText(null);
				sellPriceTextField.setText(null);
			}
		});
		btnClear.setFont(new Font("Constantia", Font.BOLD, 20));
		btnClear.setBounds(706, 495, 99, 44);
		addItem.add(btnClear);;
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setIcon(new ImageIcon("image_lib\\landing_window.png"));
		lblNewLabel_8.setBounds(0, 0, 1366, 748);
		panel.add(lblNewLabel_8);
	}
	
	
	
	public static void customerSet(String s, String p)
	{
		nameTextField.setText(s);
		cellTextField.setText(p);
	}
	public void vectorSetter(String s,Vector<SoldProductInfo> v){
		for(int i=0;i<v.size();i++)
		{
			if(s.equals(v.elementAt(i).barcode.toString()))
			{
				v.elementAt(i).quantity=Integer.toString(Integer.parseInt(v.elementAt(i).quantity.toString())+1);
				v.elementAt(i).totalSellPrice=Integer.toString(Integer.parseInt(v.elementAt(i).quantity)*Integer.parseInt(v.elementAt(i).sellPrice));
			}
		}
	}
	public void vectorItemRemove(String s,Vector<SoldProductInfo> v){
		for(int i=0;i<v.size();i++)
		{
			if(s.equals(v.elementAt(i).barcode))
			{
				v.remove(i);
				
			}
		}
	}
	public static String[] gettingYearComBox(){
		
		int year= Calendar.getInstance().get(Calendar.YEAR);
		Calendar.getInstance().get(Calendar.HOUR);
		String[] yearArr=new String[21];
		for(int i=0;i<=20;i++)
		{
			yearArr[i]=Integer.toString(year++);
		}
		return yearArr;
	}
	public static String getCurrentTime(){
		String time= Integer.toString(Calendar.getInstance().get(Calendar.HOUR))+":"+Integer.toString(Calendar.getInstance().get(Calendar.MINUTE))+":"+Integer.toString(Calendar.getInstance().get(Calendar.SECOND));
		return time;
	}
	public static String getCurrentDate(){
		//String time= Integer.toString(Calendar.getInstance().get(Calendar.YEAR))+"-"+Integer.toString(Calendar.getInstance().get(Calendar.MONTH))+"-"+Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String s=sdf.format(date);
		return s;
	}
	
	class RowRenderer extends JLabel implements ListCellRenderer<Object>{
		
		private static final long serialVersionUID = 1L;
		public RowRenderer(JTable table){
		JTableHeader jd=table.getTableHeader();
		setOpaque(true);
		setBorder(BorderFactory.createEmptyBorder());
		setHorizontalAlignment(CENTER);
		setForeground(jd.getForeground());
		setBackground(jd.getBackground());
		setFont(jd.getFont());
		}
		@Override
		public Component getListCellRendererComponent(JList<?> arg0, Object obj, int index, boolean selected, boolean focused) {
			setText ((obj==null)?"":obj.toString());
			return this;
		}

	}
	

	public class ButtonRenderer extends JButton implements TableCellRenderer {
		  
		  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ButtonRenderer() {
		    setOpaque(true);
		  }
		   
		  public Component getTableCellRendererComponent(JTable table, Object value,
		                   boolean isSelected, boolean hasFocus, int row, int column) {
		    if (isSelected) {
		      setForeground(table.getSelectionForeground());
		      setBackground(table.getSelectionBackground());
		    } else{
		      setForeground(table.getForeground());
		      setBackground(UIManager.getColor("Button.background"));
		    }
		    //setText( (value ==null) ? "" : value.toString() );
		    setIcon(new ImageIcon("image_lib\\close.PNG"));
		    return this;
		  }
		}
	
	
	public class ButtonEditor extends DefaultCellEditor {
		  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		protected JButton button;
		  private String    label;
		  private boolean   isPushed;
		  
		  public ButtonEditor(JCheckBox checkBox) {
		    super(checkBox);
		    button = new JButton();
		    button.setOpaque(true);
		    button.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  	fireEditingStopped();
		    		DefaultTableModel model = (DefaultTableModel) salesTable.getModel();
		    		vectorItemRemove(model.getValueAt(salesTable.getSelectedRow(),0).toString(),soldItems);
		    		model.removeRow(salesTable.getSelectedRow());
		    		int a=0;
		    		for(int z=0;z<model.getRowCount();z++)
		    		{
		    			a+=Integer.parseInt(model.getValueAt(z, 7).toString());
		    		}
		    		if(model.getRowCount()==0)
		    		{
		    			invoiceField.setText("0");
		    		}
		    		totalAmount.setText(Integer.toString(a));
			        barcodeField.requestFocus();	
			        salesTable.clearSelection();
		      }
		    });
		  }
		  
		  public Component getTableCellEditorComponent(JTable table, Object value,
		                   boolean isSelected, int row, int column) {
		    if (isSelected) {
		      button.setForeground(table.getSelectionForeground());
		      button.setBackground(table.getSelectionBackground());
		      
		    } else{
		      button.setForeground(table.getForeground());
		      button.setBackground(table.getBackground());
		    }
		    label = (value ==null) ? "" : value.toString();
		    button.setText( label );
		    button.setIcon(new ImageIcon("image_lib\\close.PNG"));
		    isPushed = true;
		    return button;
		  }
		  
		  public Object getCellEditorValue() {
		    if (isPushed)  {
		     //JOptionPane.showMessageDialog(button ,label + ": Ouch!");
		    	
		    }
		    isPushed = false;
		    return new String( label ) ;
		  }
		    
		  public boolean stopCellEditing() {
		    isPushed = false;
		    return super.stopCellEditing();
		  }
		  
		  protected void fireEditingStopped() {
		    super.fireEditingStopped();
		  }
		}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setComboModel(String s,JComboBox comboBox) throws SQLException{
		ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM itemDataTable;");
		conn=sqliteConnection.dbConnection();
		ArrayList<String> arr = new ArrayList<String>();
		
		while(rs.next())
		{
			arr.add(rs.getString(s));
		}
		rs.close();
		conn.close();
		comboBox.setModel(new DefaultComboBoxModel(arr.toArray()));
		comboBox.setSelectedIndex(-1);
	}
	@SuppressWarnings({ "rawtypes" })
	public void setCustomerSearchModel(JComboBox comboBox) throws SQLException{
		conn=sqliteConnection.dbConnection();
		ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM customerTable;");
		ArrayList<String> arr = new ArrayList<String>();
		while(rs.next())
		{
			arr.add(rs.getString("name"));
		}
		rs.close();
		conn.close();
	}
}



