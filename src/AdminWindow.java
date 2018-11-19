import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.border.LineBorder;

public class AdminWindow {
	SimpleAttributeSet attribs = new SimpleAttributeSet();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
	public JButton btnView;
	public JRadioButton rdbtnExistingCustomer;
	public JRadioButton rdbtnBirdseye;
	public JRadioButton rdbtnNilAchal;
	public static JRadioButton nilAchalBtn;
	public static JRadioButton multiSelection;
	public JLabel rcptLabel;
	public JRadioButton radioButton;
	public JRadioButton radioButton_1;
	@SuppressWarnings("rawtypes")
	public JList rowHeader;
	@SuppressWarnings("rawtypes")
	public static JComboBox findBox;
	@SuppressWarnings("rawtypes")
	public static JComboBox comboBox;
	Connection conn;
	Connection conn1;
	@SuppressWarnings("rawtypes")
	JComboBox dayComboBox,monthComboBox,yearComboBox;
	JScrollPane scrollPane,scrollPane_3,scrollPane_1;
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	public static Vector <SoldProductInfo> soldItems=new Vector<SoldProductInfo>();
	public static JTable viewItemTable;
	private JTextField genBarTextField;
	private JTextField customerNameText;
	private JTextField customerCellText;
	public static JTable customerTable;
	public static JTable salesTable;
	private JTextField barcodeField;
	public static JTextField totalAmount;
	public static JTextField nameTextField;
	public static JTextField cellTextField;
	public static JTextField rcvdTextField;
	public static JTextField changeField;
	public JTable importTable;
	private JTextField usePointField;
	public static JTextField pointField;
	public static JTextField invoiceField;
	private JTextField dateField;
	private JTextField timeField;
	private JTextField userField;
	private JTable rcptTable;
	private JTextField totalField;
	private JTextField salesManField;
	private JTextField invoiceSearch;
	private JTextField desigText;
	
	@SuppressWarnings("rawtypes")
	public static JComboBox fromDay;
	@SuppressWarnings("rawtypes")
	public static JComboBox fromMonth;
	@SuppressWarnings("rawtypes")
	public static JComboBox fromYear;
	@SuppressWarnings("rawtypes")
	public static JComboBox toDay;
	@SuppressWarnings("rawtypes")
	public static JComboBox toMonth;
	@SuppressWarnings("rawtypes")
	public static JComboBox toYear;
	@SuppressWarnings("rawtypes")
	public static JComboBox customerSearch;
	private JTable invoiceTable;
	private JTextField sellField;
	private JTextField profitField;
	public static JTable employeeTable;
	private JTextField newName;
	private JTextField newPass;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					AdminWindow window = new AdminWindow();
					window.frmBirdsEye.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public AdminWindow() throws IOException, SQLException,ParseException {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws SQLException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private void initialize() throws IOException, SQLException, ParseException {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);
		

		frmBirdsEye = new JFrame();
		frmBirdsEye.setBackground(Color.WHITE);
		frmBirdsEye.setTitle("Birds Eye");
		frmBirdsEye.setIconImage(Toolkit.getDefaultToolkit().getImage("image_lib\\besof.png"));
		//frame.setBounds(100, 100,screenSize.width,screenSize.height);
		frmBirdsEye.setResizable(false);
		frmBirdsEye.getContentPane().setPreferredSize(new Dimension(screenSize.width, screenSize.height));
		frmBirdsEye.pack();
		frmBirdsEye.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmBirdsEye.setLocationRelativeTo(null);
		frmBirdsEye.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        barcodeField.requestFocus();
		       
		    }
			@Override
			public void windowClosing(WindowEvent arg0) {
				String[] options = new String[2];
				options[0] = new String("Yes");
				options[1] = new String("No!");
				int choice=JOptionPane.showOptionDialog(frmBirdsEye.getContentPane(),"Are you sure to exit?!","Confirmation to proceed", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,null);
				if(choice==0)
				{
					frmBirdsEye.dispose();
					System.exit(0);
				}
				else frmBirdsEye.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
			
		});
		frmBirdsEye.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(84,122,130));
		frmBirdsEye.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
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
		panel_10.setBorder(new LineBorder(Color.WHITE, 2, true));
		panel_10.setBackground(new Color(0,0,0,0));
		panel_10.setBounds(28, 63, 262, 131);
		panel_24.add(panel_10);
		panel_10.setLayout(null);
		
		JLabel lblPoints = new JLabel("Points");
		lblPoints.setForeground(SystemColor.textHighlightText);
		lblPoints.setFont(new Font("Constantia", Font.BOLD, 14));
		lblPoints.setHorizontalAlignment(SwingConstants.LEFT);
		lblPoints.setBounds(20, 89, 94, 28);
		panel_10.add(lblPoints);
		
		pointField = new JTextField();
		pointField.setBackground(SystemColor.text);
		pointField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pointField.setHorizontalAlignment(SwingConstants.CENTER);
		pointField.setEditable(false);
		pointField.setColumns(10);
		pointField.setBounds(124, 89, 128, 28);
		panel_10.add(pointField);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setForeground(SystemColor.textHighlightText);
		lblNewLabel_2.setFont(new Font("Constantia", Font.BOLD, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setBounds(20, 11, 94, 28);
		panel_10.add(lblNewLabel_2);
		
		nameTextField = new JTextField();
		nameTextField.setBackground(SystemColor.text);
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameTextField.setEditable(false);
		nameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		nameTextField.setBounds(124, 11, 128, 28);
		panel_10.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblCellNumber_1 = new JLabel("Cell Number");
		lblCellNumber_1.setForeground(SystemColor.textHighlightText);
		lblCellNumber_1.setFont(new Font("Constantia", Font.BOLD, 14));
		lblCellNumber_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCellNumber_1.setBounds(20, 50, 94, 28);
		panel_10.add(lblCellNumber_1);
		
		cellTextField = new JTextField();
		cellTextField.setBackground(SystemColor.text);
		cellTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cellTextField.setEditable(false);
		cellTextField.setHorizontalAlignment(SwingConstants.CENTER);
		cellTextField.setColumns(10);
		cellTextField.setBounds(124, 50, 128, 28);
		panel_10.add(cellTextField);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(28, 205, 262, 125);
		panel_24.add(panel_8);
		panel_8.setBackground(new Color(221,210,205));
		panel_8.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_8.setLayout(null);
		
		totalAmount = new JTextField();
		totalAmount.setBackground(SystemColor.window);
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
		//panel_11.setBackground(new Color(171,161,161));
		panel_11.setBounds(28, 341, 262, 92);
		panel_24.add(panel_11);
		panel_11.setLayout(null);
		
		rcvdTextField = new JTextField();
		rcvdTextField.setBackground(SystemColor.text);
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
		changeField.setText("0");
		changeField.setForeground(new Color(178, 34, 34));
		changeField.setBackground(SystemColor.window);
		changeField.setEditable(false);
		changeField.setHorizontalAlignment(SwingConstants.CENTER);
		changeField.setFont(new Font("Tahoma", Font.BOLD, 18));
		changeField.setColumns(10);
		changeField.setBounds(135, 44, 117, 37);
		panel_11.add(changeField);
		
		JLabel lblCashReceived = new JLabel("Cash Received");
		lblCashReceived.setForeground(SystemColor.window);
		lblCashReceived.setHorizontalAlignment(SwingConstants.CENTER);
		lblCashReceived.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCashReceived.setBounds(10, 11, 117, 29);
		panel_11.add(lblCashReceived);
		
		JLabel lblChange = new JLabel("Change");
		lblChange.setForeground(SystemColor.window);
		lblChange.setHorizontalAlignment(SwingConstants.CENTER);
		lblChange.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChange.setBounds(135, 11, 117, 29);
		panel_11.add(lblChange);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel_13.setBackground(new Color(0,0,0,0));
		panel_13.setBounds(28, 444, 262, 47);
		panel_24.add(panel_13);
		panel_13.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Use Points");
		lblNewLabel_3.setForeground(SystemColor.window);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 11, 107, 25);
		panel_13.add(lblNewLabel_3);
		
		usePointField = new JTextField();
		usePointField.setBackground(SystemColor.text);
		usePointField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				usePointField.setText(null);
			}
		});
		usePointField.setText("0");
		usePointField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!pointField.getText().equals(Integer.toString(0)) && !pointField.getText().equals("") && !pointField.getText().equals(null))
				{
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
				else
				{
					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"This Customer has no point to use");
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
				if(nilAchalBtn.isSelected())
				{
					nilAchalBtn.setSelected(false);
					if(rcvdTextField.getText().toString().equals(null)||rcvdTextField.getText().toString().equals("")||rcvdTextField.getText().toString().equals("0"))
					{
						int dialogResult = JOptionPane.showConfirmDialog (frmBirdsEye, "Would You Like to print without cash received?","Warning",JOptionPane.YES_NO_OPTION);
						if(dialogResult == JOptionPane.YES_OPTION){
							if(totalAmount.getText().equals(null)||totalAmount.getText().equals("0"))
							{
								JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye, "No products have been added");
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
									AdminNilAchalPrint.main(null);
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
										} catch (SQLException e) {JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");}
									}
									AdminNilAchalPrint.main(null);
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
							JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye, "No products have been added");
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
													conn.createStatement().executeUpdate(update);
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
								
								AdminNilAchalPrint.main(null);
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
								AdminNilAchalPrint.main(null);
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
					nilAchalBtn.setSelected(false);
					if(rcvdTextField.getText().toString().equals(null)||rcvdTextField.getText().toString().equals("")||rcvdTextField.getText().toString().equals("0"))
					{
						int dialogResult = JOptionPane.showConfirmDialog (frmBirdsEye, "Would You Like to print without cash received?","Warning",JOptionPane.YES_NO_OPTION);
						if(dialogResult == JOptionPane.YES_OPTION){
							if(totalAmount.getText().equals(null)||totalAmount.getText().equals("0"))
							{
								JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye, "No products have been added");
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
										conn.createStatement().execute("INSERT INTO saleInfo VALUES('"+invoiceField.getText().toString()+"','"+Integer.toString(saleTotal)+"','"+getCurrentDate()+"','"+Integer.toString(profit)+"');");
										rs.close();
										conn.close();
									} catch (SQLException e2) {
										JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
									}
									EidReceiptWithoutCash.main(null);
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
										} catch (SQLException e) {JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");}
									}
									EidReceiptWithoutCash.main(null);
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
							JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye, "No products have been added");
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
													conn.createStatement().executeUpdate(update);
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
								
								EidReceipt.main(null);
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
								EidReceipt.main(null);
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
				invoiceField.setText("0");
				barcodeField.requestFocus();
				pointField.setText(null);
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
		rdbtnExistingCustomer.setBounds(0, 0, 133, 26);
		panel_25.add(rdbtnExistingCustomer);
		rdbtnExistingCustomer.setForeground(SystemColor.window);
		rdbtnExistingCustomer.setOpaque(false);
		rdbtnExistingCustomer.setBackground(new Color(0,0,0,0));
		//rdbtnExistingCustomer.setBackground(new Color(230, 230, 250));
		rdbtnExistingCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		rdbtnNewCustomer = new JRadioButton("New Customer");
		rdbtnNewCustomer.setForeground(SystemColor.window);
		rdbtnNewCustomer.setOpaque(false);
		rdbtnNewCustomer.setBackground(new Color(0,0,0,0));
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
					AddCustomer window = new AddCustomer();
					window.frmAddCustomer.setVisible(true);
				}
				else{
					AddCustomer window = new AddCustomer();
					window.frmAddCustomer.setVisible(true);
				}
			}
		});
		rdbtnNewCustomer.setSelected(false);
		
		nilAchalBtn = new JRadioButton("Print with Nil Achal Fashion House");
		nilAchalBtn.setForeground(new Color(255, 127, 80));
		nilAchalBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		nilAchalBtn.setOpaque(false);
		nilAchalBtn.setBackground(new Color(0,0,0,0));
		nilAchalBtn.setHorizontalAlignment(SwingConstants.CENTER);
		nilAchalBtn.setBounds(28, 588, 262, 23);
		panel_24.add(nilAchalBtn);
		rdbtnExistingCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usePointField.setEnabled(true);
				rdbtnNewCustomer.setSelected(false);
				CustomerCheck window;
				try {
					nameTextField.setText(null);
					cellTextField.setText(null);
					pointField.setText(null);
					window = new CustomerCheck();
					window.frmCustomers.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
				}
				
			}
		});
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_7.setBorder(new LineBorder(UIManager.getColor("ToolBar.floatingForeground"), 2, true));
		panel_7.setBounds(10, 11, 976, 603);
		//panel_7.setBackground(new Color(0,0,0,0));
		salesPanel.add(panel_7);
		panel_7.setLayout(null);
		
		barcodeField = new JTextField();
		barcodeField.setBackground(SystemColor.text);
		barcodeField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					ManualSearch window;
					try {
						window = new ManualSearch();
						window.frmManualSearch.setVisible(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
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
					ResultSet rs=conn.createStatement().executeQuery(barQuery);
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
						JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Product Not Found");
						if(salesTable.getRowCount()==0)
						{
							invoiceField.setText("0");
						}
					}
					
					matched=false;
					rs.close();
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
		barcodeField.setBounds(13, 11, 378, 31);
		barcodeField.setBorder(new LineBorder(new Color(165, 42, 42), 2));
		barcodeField.setForeground(Color.black);
		barcodeField.requestFocus();
		
		JPanel panel_14 = new JPanel();
		panel_14.setBounds(602, 14, 356, 28);
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
		scrollPane_2.setBounds(13, 50, 950, 542);
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
		
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		inventoryPanel.setBackground(new Color(46,238,237));
		inventoryPanel.setBackground(SystemColor.info);
		tabbedPane.addTab("Inventory", null, inventoryPanel, null);
		inventoryPanel.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setFont(new Font("Segoe UI Historic", Font.BOLD, 12));
		tabbedPane_1.setBounds(10, 18, 1287, 592);
		tabbedPane_1.setBackground(new Color(0,0,0,0));
		inventoryPanel.add(tabbedPane_1);
		
		JPanel addItem = new JPanel();
		addItem.setBackground(new Color(221,210,205));
		tabbedPane_1.addTab("Add Items", null, addItem, null);
		addItem.setLayout(null);
		
		JPanel panel_26 = new JPanel();
		panel_26.setBorder(new LineBorder(SystemColor.text, 4, true));
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
					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye, "Nothing to save");
				}
				else{
					try {
						conn=sqliteConnection.dbConnection();
						String expiryDate=dayComboBox.getSelectedItem()+"-"+monthComboBox.getSelectedItem()+"-"+yearComboBox.getSelectedItem();
						String query="SELECT * FROM itemDataTable;";
						Statement st=conn.createStatement();
						boolean match=false;
						ResultSet rs=st.executeQuery(query);
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
							conn.createStatement().executeUpdate("INSERT INTO itemDataTable VALUES('"+genBarTextField.getText().toString().trim()+"','"+ItemNameTextField.getText().toString().trim()+"','"+brandNameTextField.getText().toString().trim()+"','"+vwTextField.getText().toString().trim()+"','"+quantityTextField.getText().toString().trim()+"','"+expiryDate+"','"+buyPriceTextField.getText().toString().trim()+"','"+vatTextField.getText().toString().trim()+"','"+sellPriceTextField.getText().toString().trim()+"');");
							JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye, "Successfully Saved");
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
						st.close();
						conn.close();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
					}
				}
			}
			
		});
		
		btnNewButton.setFont(new Font("Constantia", Font.BOLD, 20));
		btnNewButton.setBounds(506, 492, 99, 44);
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
		btnClear.setBounds(697, 492, 99, 44);
		addItem.add(btnClear);
        
        JPanel viewItemPanel = new JPanel();
        viewItemPanel.setToolTipText("Manage stored Items");
        viewItemPanel.setForeground(Color.WHITE);
        viewItemPanel.setBackground(new Color(15,52,81));
        tabbedPane_1.addTab("View Items", null, viewItemPanel, null);
        viewItemPanel.setLayout(null);
        
        multiSelection = new JRadioButton("Multi Selection ");
        multiSelection.setForeground(SystemColor.window);
        multiSelection.setOpaque(false);
        multiSelection.setBackground(new Color(0,0,0,0));   
        multiSelection.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(multiSelection.isSelected())
        		{
        			viewItemTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        		}
        		else{
        			viewItemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        		}
        	}
        });
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 992, 542);
        scrollPane.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Stocked Products",TitledBorder.CENTER,TitledBorder.TOP,new Font("times new roman",Font.BOLD,20), Color.black));
        viewItemPanel.add(scrollPane);
        
        viewItemTable = new JTable();
        viewItemTable.setBorder(new LineBorder(new Color(0, 51, 102), 1, true));
        viewItemTable.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        viewItemTable.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Barcode Number", "Item Name", "Brand", "Volume", "Quantity", "Expiry Date", "Buy Price", "VAT", "Sell Price"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, String.class, String.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        viewItemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        viewItemTable.setAutoCreateRowSorter(true);
        final TableCellRenderer tcrOs = viewItemTable.getTableHeader().getDefaultRenderer();
        viewItemTable.getTableHeader().setDefaultRenderer(new TableCellRenderer() 
        	{
                @Override
               public Component getTableCellRendererComponent(JTable table, 
                    Object value, boolean isSelected, boolean hasFocus, 
                    int row, int column) {
                    JLabel lbl = (JLabel) tcrOs.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, column);
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
        
        scrollPane.setViewportView(viewItemTable);
        final TableCellRenderer tcr1s = viewItemTable.getTableHeader().getDefaultRenderer();
        multiSelection.setFont(new Font("Tahoma", Font.BOLD, 14));
        multiSelection.setHorizontalAlignment(SwingConstants.CENTER);
        multiSelection.setBounds(1012, 11, 232, 23);
        viewItemPanel.add(multiSelection);
        
        JPanel panel_32 = new JPanel();
        panel_32.setBorder(new LineBorder(new Color(255, 250, 240), 2, true));
        panel_32.setBounds(1059, 283, 140, 252);
        panel_32.setBackground(new Color (0,0,0,0));
        viewItemPanel.add(panel_32);
        panel_32.setLayout(null);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBackground(SystemColor.controlDkShadow);
        btnUpdate.setBounds(16, 16, 109, 37);
        panel_32.add(btnUpdate);
        btnUpdate.setFont(new Font("Gabriola", Font.BOLD, 22));
        
        JButton btnClear_2 = new JButton("Clear");
        btnClear_2.setBackground(SystemColor.controlDkShadow);
        btnClear_2.setBounds(16, 62, 109, 37);
        panel_32.add(btnClear_2);
        btnClear_2.setFont(new Font("Gabriola", Font.BOLD, 22));
        
        
        JButton btnShowData = new JButton("Show");
        btnShowData.setBackground(SystemColor.controlDkShadow);
        btnShowData.setBounds(16, 108, 109, 37);
        panel_32.add(btnShowData);
        btnShowData.setFont(new Font("Gabriola", Font.BOLD, 22));
        
        JButton btnExport = new JButton("Export");
        btnExport.setBackground(SystemColor.controlDkShadow);
        btnExport.setBounds(16, 154, 109, 37);
        panel_32.add(btnExport);
        btnExport.setFont(new Font("Gabriola", Font.BOLD, 22));
        
        JButton btnDelete = new JButton("Delete!");
        btnDelete.setBackground(SystemColor.controlDkShadow);
        btnDelete.setBounds(16, 200, 109, 37);
        panel_32.add(btnDelete);
        btnDelete.setFont(new Font("Gabriola", Font.BOLD, 22));
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(multiSelection.isSelected())
        		{
        			JOptionPane.showMessageDialog(frmBirdsEye, "Multiple Selection is on!");
        		}
        		else{
        			if(viewItemTable.getRowCount()==0)
        			{
        				JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye, "Nothing to delete!");
        			}
        			else
        			{
        				try {
        					conn=sqliteConnection.dbConnection();
        					Statement delete=conn.createStatement();
        					ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM itemDataTable;");
        					DefaultTableModel model=(DefaultTableModel) viewItemTable.getModel();
        					
        					
        					for(int i=0;i<model.getRowCount();i++)
        					{
        						if(viewItemTable.isRowSelected(i))
        						{
        							while(rs.next())
        							{
        								if(model.getValueAt(i, 0).toString().equals(rs.getString("barcode")))
        								{
        									delete.execute("DELETE FROM itemDataTable WHERE itemName='"+model.getValueAt(viewItemTable.getSelectedRow(), 1)+"';");
        								}
        							}
        						}
        					}
        					model.removeRow(viewItemTable.getSelectedRow());
        					delete.close();
        					rs.close();
        					conn.close();
        					JOptionPane.showMessageDialog(frmBirdsEye, "Successfully Deleted!");
        					
        				} catch (SQLException e1) {
        					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
        				}
        				
        			}
        		}
        	}
        });
        btnExport.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(multiSelection.isSelected()==true)
        		{
        			multiSelection.setSelected(false);
        			if(viewItemTable.getRowCount()!=0 && viewItemTable.getSelectedRowCount()!=0)
        			{
        				JFileChooser chooser=new JFileChooser("C:\\Users\\Public");
        				chooser.setDialogTitle("Select to save a workbook file");
        				chooser.isOpaque();
        				chooser.setApproveButtonText("Save");
        				// Set the mnemonic
        				//chooser.setApproveButtonMnemonic('a');
        			    // Set the tool tip
        			    chooser.setApproveButtonToolTipText("Click to save here");
        				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        				chooser.setAcceptAllFileFilterUsed(false);
        				chooser.showOpenDialog(frmBirdsEye);
        				XSSFWorkbook wb = new XSSFWorkbook();
        				XSSFSheet sheet=wb.createSheet();
        				int sheetRowNo=0;
        				XSSFRow row0 = sheet.createRow(sheetRowNo);
        				XSSFCell r0c0 = row0.createCell(0);
        				r0c0.setCellValue("Barcode");
        				XSSFCell r0c1 = row0.createCell(1);
        				r0c1.setCellValue("Item Name");
        				XSSFCell r0c2 = row0.createCell(2);
        				r0c2.setCellValue("Brand");
        				XSSFCell r0c3 = row0.createCell(3);
        				r0c3.setCellValue("Volume");
        				XSSFCell r0c4 = row0.createCell(4);
        				r0c4.setCellValue("Quantity");
        				XSSFCell r0c5 = row0.createCell(5);
        				r0c5.setCellValue("Expiry Date");
        				XSSFCell r0c6 = row0.createCell(6);
        				r0c6.setCellValue("Buy Price");
        				XSSFCell r0c7 = row0.createCell(7);
        				r0c7.setCellValue("VAT");
        				XSSFCell r0c8 = row0.createCell(8);
        				r0c8.setCellValue("Sell Price");
        				DefaultTableModel model=(DefaultTableModel) viewItemTable.getModel();
        				for(int row=0;row<model.getRowCount();row++)
        				{
        					if(viewItemTable.isRowSelected(row)){
        						sheetRowNo++;
        						XSSFRow r = sheet.createRow(sheetRowNo);
        						XSSFCell c0 = r.createCell(0);
        						c0.setCellValue(viewItemTable.getValueAt(row, 0).toString());
        						XSSFCell c1 = r.createCell(1);
        						c1.setCellValue(viewItemTable.getValueAt(row, 1).toString());
        						XSSFCell c2 = r.createCell(2);
        						c2.setCellValue(viewItemTable.getValueAt(row, 2).toString());
        						XSSFCell c3 = r.createCell(3);
        						c3.setCellValue(viewItemTable.getValueAt(row, 3).toString());
        						XSSFCell c4 = r.createCell(4);
        						c4.setCellValue(viewItemTable.getValueAt(row, 4).toString());
        						XSSFCell c5 = r.createCell(5);
        						c5.setCellValue(viewItemTable.getValueAt(row, 5).toString());
        						XSSFCell c6 = r.createCell(6);
        						c6.setCellValue(viewItemTable.getValueAt(row, 6).toString());
        						XSSFCell c7 = r.createCell(7);
        						c7.setCellValue(viewItemTable.getValueAt(row, 7).toString());
        						XSSFCell c8 = r.createCell(8);
        						c8.setCellValue(viewItemTable.getValueAt(row, 8).toString());
        					}
        				}
        				try {
        					wb.write(new FileOutputStream(new File(chooser.getSelectedFile().getAbsolutePath()+"\\exported workbook.xlsx")));
        					wb.close();
        					JOptionPane.showMessageDialog(frmBirdsEye, "Successfully exported to choosed Directory");
        				} catch (FileNotFoundException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				} catch (IOException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
        			}
        			else{
        				JOptionPane.showMessageDialog(frmBirdsEye, "Nothing to Export!");
        			}
        		}
        		
        	}
        });
        btnShowData.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(viewItemTable.getRowCount()==0)
        		{
        			try {
        				JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye, "Collecting Data....");
        				conn=sqliteConnection.dbConnection();
        				String query="SELECT * FROM itemDataTable;" ;
        				Statement st=conn.createStatement();
        				ResultSet rs=st.executeQuery(query);
        				DefaultTableModel model = (DefaultTableModel) viewItemTable.getModel();
        				while(rs.next())
        				{
        					model.addRow(new Object[]{rs.getString("barcode"),rs.getString("itemName"),rs.getString("brand"),rs.getString("volume"),rs.getInt("quantity"),rs.getString("expiryDate"),rs.getInt("buyPrice"),rs.getInt("vat"),rs.getInt("sellPrice")});
        				}
        				
        				viewItemTable.getColumn("Barcode Number").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("Item Name").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("Brand").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("Volume").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("Quantity").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("Expiry Date").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("Buy Price").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("VAT").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("Sell Price").setCellRenderer( centerRenderer );
        				viewItemTable.setAutoCreateRowSorter(true);
        				int no=viewItemTable.getRowCount();
        				String [] rowNo = new String[no];
        				for(int i=1;i<=no;i++){
        					rowNo[i-1]=""+i;
        				}
        				rowHeader= new JList(rowNo);
        				rowHeader.setFixedCellWidth(50);
        				rowHeader.setFixedCellHeight(viewItemTable.getRowHeight());
        				rowHeader.setBackground(new Color(0,0,0,0));
        				rowHeader.setCellRenderer(new RowRenderer(viewItemTable));
        				
        				scrollPane.setRowHeaderView(rowHeader);
        				rs.close();
        				st.close();
        				conn.close();
        				
        			} catch (SQLException e) {
        				JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
        			}
        		}
        		else{
        			DefaultTableModel model0 = (DefaultTableModel) viewItemTable.getModel();
        			model0.setRowCount(0);
        			scrollPane.setRowHeaderView(null);
        			try {
        				JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye, "Collecting Data....");
        				conn=sqliteConnection.dbConnection();
        				String query="SELECT * FROM itemDataTable;" ;
        				Statement st=conn.createStatement();
        				ResultSet rs=st.executeQuery(query);
        				DefaultTableModel model = (DefaultTableModel) viewItemTable.getModel();
        				while(rs.next())
        				{
        					model.addRow(new Object[]{rs.getString("barcode"),rs.getString("itemName"),rs.getString("brand"),rs.getString("volume"),rs.getInt("quantity"),rs.getString("expiryDate"),rs.getInt("buyPrice"),rs.getInt("vat"),rs.getInt("sellPrice")});
        				}
        				
        				viewItemTable.getColumn("Barcode Number").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("Item Name").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("Brand").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("Volume").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("Quantity").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("Expiry Date").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("Buy Price").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("VAT").setCellRenderer( centerRenderer );
        				viewItemTable.getColumn("Sell Price").setCellRenderer( centerRenderer );
        				viewItemTable.setAutoCreateRowSorter(true);
        				int no=viewItemTable.getRowCount();
        				String [] rowNo = new String[no];
        				for(int i=1;i<=no;i++){
        					rowNo[i-1]=""+i;
        				}
        				rowHeader= new JList(rowNo);
        				rowHeader.setFixedCellWidth(50);
        				rowHeader.setFixedCellHeight(viewItemTable.getRowHeight());
        				rowHeader.setBackground(new Color(0,0,0,0));
        				rowHeader.setCellRenderer(new RowRenderer(viewItemTable));
        				
        				scrollPane.setRowHeaderView(rowHeader);
        				rs.close();
        				st.close();
        				conn.close();
        				
        			} catch (SQLException e) {
        				JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
        			}
        		}
        	}
        });
        btnClear_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		DefaultTableModel model=(DefaultTableModel) viewItemTable.getModel();
        		model.setRowCount(0);
        		scrollPane.setRowHeaderView(null);
        	}
        });
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if (viewItemTable.getRowCount()!=0)
        		{
        			try {
            			DefaultTableModel model = (DefaultTableModel) viewItemTable.getModel();
            			conn=sqliteConnection.dbConnection();
            			String select="SELECT * FROM itemDataTable;";
            			Statement st=conn.createStatement();
            			Statement update=conn.createStatement();
            			ResultSet rs;
            			rs=st.executeQuery(select);
            			for(int i=0;i<viewItemTable.getRowCount();i++)
            			{
            				if(viewItemTable.isRowSelected(i))
            				{
            					while(rs.next())
            					{
            						if(rs.getString("barcode").equals(viewItemTable.getValueAt(i, 0)))
            						{
            							update.executeUpdate("UPDATE itemDataTable SET barcode='"+viewItemTable.getValueAt(i, 0).toString().trim()+"',itemName='"+viewItemTable.getValueAt(i, 1).toString().trim()+"',brand='"+viewItemTable.getValueAt(i, 2).toString().trim()+"',volume='"+viewItemTable.getValueAt(i, 3).toString().trim()+"',quantity='"+viewItemTable.getValueAt(i, 4).toString().trim()+"',expiryDate='"+viewItemTable.getValueAt(i, 5).toString().trim()+"',buyPrice='"+viewItemTable.getValueAt(i, 6).toString().trim()+"',vat='"+viewItemTable.getValueAt(i, 7).toString().trim()+"',sellPrice='"+viewItemTable.getValueAt(i, 8).toString().trim()+"' WHERE barcode='"+viewItemTable.getValueAt(i, 0).toString().trim()+"';");
            						}
            					}
            				}
            			}
            			rs.close();
            			model.setRowCount(0);
            			rs=st.executeQuery(select);
            			while(rs.next())
            			{
            				model.addRow(new Object[]{rs.getString("barcode"),rs.getString("itemName"),rs.getString("brand"),rs.getString("volume"),rs.getInt("quantity"),rs.getString("expiryDate"),rs.getInt("buyPrice"),rs.getInt("vat"),rs.getInt("sellPrice")});
            			}
            			
            			viewItemTable.getColumn("Barcode Number").setCellRenderer( centerRenderer );
            			viewItemTable.getColumn("Item Name").setCellRenderer( centerRenderer );
            			viewItemTable.getColumn("Brand").setCellRenderer( centerRenderer );
            			viewItemTable.getColumn("Volume").setCellRenderer( centerRenderer );
            			viewItemTable.getColumn("Quantity").setCellRenderer( centerRenderer );
            			viewItemTable.getColumn("Expiry Date").setCellRenderer( centerRenderer );
            			viewItemTable.getColumn("Buy Price").setCellRenderer( centerRenderer );
            			viewItemTable.getColumn("VAT").setCellRenderer( centerRenderer );
            			viewItemTable.getColumn("Sell Price").setCellRenderer( centerRenderer );
            			viewItemTable.setAutoCreateRowSorter(true);
            			int no=viewItemTable.getRowCount();
            			String [] rowNo = new String[no];
            			for(int i=1;i<=no;i++){
            				rowNo[i-1]=""+i;
            			}
            			rowHeader= new JList(rowNo);
            			rowHeader.setFixedCellWidth(50);
            			rowHeader.setFixedCellHeight(viewItemTable.getRowHeight());
            			rowHeader.setBackground(new Color(0,0,0,0));
            			rowHeader.setCellRenderer(new RowRenderer(viewItemTable));
            			scrollPane.setRowHeaderView(rowHeader);
            			
            			rs.close();
            			update.close();
            			st.close();
            			conn.close();
            			JOptionPane.showMessageDialog(frmBirdsEye, "Successfully Updated!");
            		} catch (SQLException e) {
            			JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
            		}
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Nothing to update");
        		}
        	}
        });
        
        JPanel panel_15 = new JPanel();
        panel_15.setBackground(new Color(245, 245, 245));
        panel_15.setBorder(new LineBorder(new Color(245, 255, 250), 3, true));
        panel_15.setOpaque(false);
        panel_15.setBackground(new Color(0,0,0,0));
        panel_15.setBounds(1012, 48, 232, 209);
        viewItemPanel.add(panel_15);
        panel_15.setLayout(null);
        
        JLabel lblNewLabel_5 = new JLabel("Find");
        lblNewLabel_5.setForeground(Color.WHITE);
        lblNewLabel_5.setBounds(49, 11, 124, 38);
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setFont(new Font("Gabriola", Font.BOLD, 39));
        panel_15.add(lblNewLabel_5);
        
        JPanel panel_17 = new JPanel();
        panel_17.setLayout(null);
        panel_17.setBorder(new LineBorder(SystemColor.window, 1, true));
        panel_17.setBackground(new Color(255, 255, 255));
        panel_17.setBounds(0, 58, 232, 45);
        panel_15.add(panel_17);
        
        comboBox = new JComboBox();
        comboBox.setBounds(10, 11, 212, 23);
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Barcode", "Name", "Brand"}));
        comboBox.setSelectedIndex(-1);
        comboBox.setOpaque(false);
        comboBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			if(comboBox.getSelectedItem().toString().equals("Barcode"))
        			{
        				setComboModel("barcode",findBox);
        				findBox.setSelectedIndex(-1);
        			}
        			else if(comboBox.getSelectedItem().toString().equals("Name"))
        			{
        				setComboModel("itemName",findBox);
        				findBox.setSelectedIndex(-1);
        			}
        			else if(comboBox.getSelectedItem().toString().equals("Brand"))
        			{
        				setComboModel("brand",findBox);
        				findBox.setSelectedIndex(-1);
        			}
        		} catch (SQLException e1) {
        			JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
        		}
        	}
        });
        panel_17.add(comboBox);
        
        JPanel panel_18 = new JPanel();
        panel_18.setLayout(null);
        panel_18.setBorder(new LineBorder(SystemColor.window, 1, true));
        panel_18.setBackground(new Color(255, 255, 255));
        panel_18.setBounds(0, 111, 232, 45);
        panel_15.add(panel_18);
        
        findBox = new JComboBox();
        findBox.setBounds(10, 11, 212, 23);
        AutoCompleteFind.enable(findBox);
        findBox.setSelectedIndex(-1);
        panel_18.add(findBox);
        
        JButton btnNewButton_4 = new JButton("Clear ");
        btnNewButton_4.setBackground(SystemColor.controlDkShadow);
        btnNewButton_4.setFont(new Font("Gabriola", Font.BOLD, 20));
        btnNewButton_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		findBox.setSelectedIndex(-1);
        		viewItemTable.clearSelection();
        	}
        });
        btnNewButton_4.setBounds(70, 167, 89, 29);
        panel_15.add(btnNewButton_4);
        
        JPanel panel_12 = new JPanel();
        panel_12.setToolTipText("import excel files to application's database");
        panel_12.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        panel_12.setBackground(new Color(15,52,81));
        tabbedPane_1.addTab("Import", null, panel_12, null);
        panel_12.setLayout(null);
        
        scrollPane_3 = new JScrollPane();
        scrollPane_3.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"New Products",TitledBorder.CENTER,TitledBorder.TOP,new Font("times new roman",Font.BOLD,20), Color.black));
        scrollPane_3.setBounds(10, 11, 1016, 542);
        panel_12.add(scrollPane_3);
        
        importTable = new JTable();
        importTable.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Barcode", "Item Name", "Brand", "Volume", "Quantity", "Expiry Date", "Buy Price", "VAT", "Sell Price"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, String.class, String.class, String.class, Integer.class, String.class, Integer.class, Integer.class, Integer.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        importTable.getColumnModel().getColumn(0).setResizable(false);
        importTable.getColumnModel().getColumn(0).setPreferredWidth(90);
        importTable.getColumnModel().getColumn(1).setResizable(false);
        importTable.getColumnModel().getColumn(1).setPreferredWidth(133);
        importTable.getColumnModel().getColumn(2).setResizable(false);
        importTable.getColumnModel().getColumn(2).setPreferredWidth(83);
        importTable.getColumnModel().getColumn(3).setResizable(false);
        importTable.getColumnModel().getColumn(4).setResizable(false);
        importTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        importTable.getColumnModel().getColumn(5).setResizable(false);
        importTable.getColumnModel().getColumn(6).setResizable(false);
        importTable.getColumnModel().getColumn(7).setResizable(false);
        importTable.getColumnModel().getColumn(7).setPreferredWidth(64);
        importTable.getColumnModel().getColumn(8).setResizable(false);
        importTable.getColumnModel().getColumn(8).setPreferredWidth(70);
        importTable.getTableHeader().setDefaultRenderer(new TableCellRenderer() 
	    	{
	            @Override
	           public Component getTableCellRendererComponent(JTable table, 
	                Object value, boolean isSelected, boolean hasFocus, 
	                int row, int column) {
	                JLabel lbl = (JLabel) tcr1s.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, column);
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
        importTable.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        scrollPane_3.setViewportView(importTable);
        
        	JPanel panel_27 = new JPanel();
        	
        	panel_27.setBorder(new LineBorder(SystemColor.text, 3, true));
        	panel_27.setBounds(1036, 11, 208, 157);
        	panel_12.add(panel_27);
        	panel_27.setLayout(null);
        	
        	JButton btnNewButton_7 = new JButton();
        	btnNewButton_7.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent arg0) {
        			DefaultTableModel model=(DefaultTableModel) importTable.getModel();
        			model.setRowCount(0);
        			scrollPane_3.setRowHeaderView(null);
        		}
        	});
        	JButton button = new JButton(new ImageIcon("image_lib\\import.png"));
        	button.setBounds(32, 30, 68, 45);
        	panel_27.add(button);
        	button.setFont(new Font("Constantia", Font.BOLD, 15));
        	button.setBorder(BorderFactory.createEmptyBorder());
        	button.setBorderPainted(true);
        	button.setContentAreaFilled(false);
        	
        	button.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent arg0) {
        			DefaultTableModel model=(DefaultTableModel) importTable.getModel();
        			JFileChooser chooser=new JFileChooser("C:\\Users\\Public");
        			chooser.setDialogTitle("Select a workbook file");
        			chooser.setAcceptAllFileFilterUsed(false);
        			FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx", "xlx");
        			chooser.addChoosableFileFilter(filter);
        			chooser.showOpenDialog(null);
        			try {
        				FileInputStream file = new FileInputStream(chooser.getSelectedFile().getAbsolutePath().toString());
        				XSSFWorkbook workbook = new XSSFWorkbook(file);
        			    XSSFSheet sheet = workbook.getSheetAt(0);
        			    for(int row=1;row<sheet.getPhysicalNumberOfRows();row++)
        			    {
        			    	model.addRow(new Object[]{});
        			    	for(int cell=0;cell<=8;cell++)
        			    	{
        			    		Cell c=sheet.getRow(row).getCell(cell);
        			    		switch(cell){
        			    		case 0:{
        			    			if(c==null){
        			    				model.setValueAt("", row-1, 0);
        			    			}
        			    			else if(c.getCellTypeEnum()==CellType.STRING){
        			    				model.setValueAt(sheet.getRow(row).getCell(0).getStringCellValue().toString(), row-1, 0);
        		    				}
        			    			else if(c.getCellTypeEnum()==CellType.NUMERIC){
        			    				model.setValueAt(new BigDecimal(sheet.getRow(row).getCell(0).getNumericCellValue()).toPlainString(), row-1, 0);
            		    			}
        			    			break;
        			    			/*else{
        			    				model.setValueAt(new BigDecimal(sheet.getRow(row).getCell(0).getStringCellValue()).toPlainString(), row-1, 0);break;
        			    			}*/
        			    		}
        			    		case 1:{
        			    			if(c==null||c.equals("")){
        			    				model.setValueAt("", row-1, 1);
        			    			}
        			    			else if(c.getCellTypeEnum()==CellType.STRING){
            		    				model.setValueAt(sheet.getRow(row).getCell(1).getStringCellValue().toString(), row-1, 1);
            		    			}
            			    		else if(c.getCellTypeEnum()==CellType.NUMERIC){
                		    			model.setValueAt(sheet.getRow(row).getCell(1).getNumericCellValue(), row-1, 1);
                		    		}
        			    			break;
        			    		}
        			    		case 2:{
        			    			if(c==null||c.equals("")){
        			    				model.setValueAt("not specifeid", row-1, 2);
        			    			}
        			    			else if(c.getCellTypeEnum()==CellType.STRING){
            		    				model.setValueAt(sheet.getRow(row).getCell(2).getStringCellValue().toString(), row-1, 2);
            		    			}
            			    		else if(c.getCellTypeEnum()==CellType.NUMERIC){
                		    			model.setValueAt(sheet.getRow(row).getCell(2).getNumericCellValue(), row-1, 2);
                		   			}
        			    			break;
        			    		}
        			    		case 3:{
        			    			if(c==null||c.equals("")){
        			    				model.setValueAt("not specifeid", row-1, 3);
        			    			}
        			    			else if(c.getCellTypeEnum()==CellType.STRING){
            		    				model.setValueAt(sheet.getRow(row).getCell(3).getStringCellValue().toString(), row-1, 3);
            		    			}
            			    		else if(c.getCellTypeEnum()==CellType.NUMERIC){
                		    			model.setValueAt(sheet.getRow(row).getCell(3).getNumericCellValue(), row-1, 3);
                		   			}
        			    			break;
        			    		}
        			    		case 4:{
        			    			if(c==null||c.equals("")){
        			    				model.setValueAt("0", row-1, 4);
        			    			}
        			    			else if(c.getCellTypeEnum()==CellType.STRING){
        			    				model.setValueAt(sheet.getRow(row).getCell(4).getStringCellValue().toString(), row-1, 4);
            		    			}
            			    		else if(c.getCellTypeEnum()==CellType.NUMERIC){
                		    			model.setValueAt(sheet.getRow(row).getCell(4).getNumericCellValue(), row-1, 4);
                		    		}
        			    			break;
        			    		}
        			    		case 5:{
        			    			if(c==null||c.equals("")){
        			    				model.setValueAt("not specifeid", row-1, 5);
        			    			}
        			    			else if(c.getCellTypeEnum()==CellType.STRING){
            		    				model.setValueAt(sheet.getRow(row).getCell(5).getStringCellValue().toString(), row-1, 5);
            		    			}
            			    		else if(c.getCellTypeEnum()==CellType.NUMERIC){
                		   				model.setValueAt(sheet.getRow(row).getCell(5).getNumericCellValue(), row-1, 5);
                		    		}
        			    			break;
        			    		}
        			    		case 6:{
        			    			if(c==null||c.equals("")){
        			    				model.setValueAt("0", row-1, 6);
        			    			}
        			    			else if(c.getCellTypeEnum()==CellType.STRING){
            		    				model.setValueAt(sheet.getRow(row).getCell(6).getStringCellValue().toString(), row-1, 6);
            		    			}
            			    		else if(c.getCellTypeEnum()==CellType.NUMERIC){
                		   				model.setValueAt(sheet.getRow(row).getCell(6).getNumericCellValue(), row-1, 6);
                		    		}
        			    			break;
        			    		}
        			    		case 7:{
        			    			if(c==null||c.equals("")){
        			    				model.setValueAt("0", row-1, 7);
        			    			}
        			    			else if(c.getCellTypeEnum()==CellType.STRING){
            		    				model.setValueAt(sheet.getRow(row).getCell(7).getStringCellValue().toString(), row-1, 7);
            		    			}
            			    		else if(c.getCellTypeEnum()==CellType.NUMERIC){
                		   				model.setValueAt(sheet.getRow(row).getCell(7).getNumericCellValue(), row-1, 7);
                		    		}
        			    			break;
        			    		}
        			    		case 8:{
        			    			if(c==null||c.equals("")){
        			    				model.setValueAt("0", row-1, 7);
        			    			}
        			    			else if(c.getCellTypeEnum()==CellType.STRING){
            		    				model.setValueAt(sheet.getRow(row).getCell(8).getStringCellValue().toString(), row-1, 8);
            		    			}
            			    		else if(c.getCellTypeEnum()==CellType.NUMERIC){
                		    			model.setValueAt(sheet.getRow(row).getCell(8).getNumericCellValue(), row-1, 8);
                		   			}
        			    			break;
        			    		}
        			    		}
        			    	}
        			    }
        			    workbook.close();
        			    file.close();
        			} catch (FileNotFoundException e) {
        				e.printStackTrace();
        			} catch (IOException e) {
        				e.printStackTrace();
        			}
        			importTable.getColumn("Barcode").setCellRenderer( centerRenderer );
        			importTable.getColumn("Item Name").setCellRenderer( centerRenderer );
        			importTable.getColumn("Brand").setCellRenderer( centerRenderer );
        			importTable.getColumn("Volume").setCellRenderer( centerRenderer );
        			importTable.getColumn("Quantity").setCellRenderer( centerRenderer );
        			importTable.getColumn("Expiry Date").setCellRenderer( centerRenderer );
        			importTable.getColumn("Buy Price").setCellRenderer( centerRenderer );
        			importTable.getColumn("VAT").setCellRenderer( centerRenderer );
        			importTable.getColumn("Sell Price").setCellRenderer( centerRenderer );
        			importTable.setAutoCreateRowSorter(true);
        			int no=importTable.getRowCount();
        			String [] rowNo = new String[no];
        			for(int i=1;i<=no;i++){
        				rowNo[i-1]=""+i;
        			}
        			rowHeader= new JList(rowNo);
        			rowHeader.setFixedCellWidth(50);
        			rowHeader.setFixedCellHeight(importTable.getRowHeight());
        			rowHeader.setBackground(new Color(0,0,0,0));
        			rowHeader.setCellRenderer(new RowRenderer(importTable));
        			
        			scrollPane_3.setRowHeaderView(rowHeader);
        	       
        		}
        	});
        	btnNewButton_7.setIcon(new ImageIcon("image_lib\\cancel.png"));
        	btnNewButton_7.setBorderPainted(false);
        	btnNewButton_7.setContentAreaFilled(false);
        	btnNewButton_7.setBounds(110, 30, 68, 45);
        	panel_27.add(btnNewButton_7);
        	
        	JButton btnSave_1 = new JButton("Save");
        	btnSave_1.setBackground(SystemColor.controlDkShadow);
        	btnSave_1.setBounds(52, 97, 109, 37);
        	panel_27.add(btnSave_1);
        	btnSave_1.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent arg0) {
        			
        			if(importTable.getRowCount()!=0)
        			{
        				try {
        					conn=sqliteConnection.dbConnection();
        					String select="SELECT * FROM itemDataTable;";
        					Statement st=conn.createStatement();
        					Statement update=conn.createStatement();
        					ResultSet rs=st.executeQuery(select);
        					DefaultTableModel model=(DefaultTableModel) importTable.getModel();
        					int b=0;
        					boolean match=false;
        					for(int row=0;row<model.getRowCount();row++)
        					{
        						while(rs.next())
        						{
        							if(importTable.getValueAt(row, 0).toString().equals(rs.getString("barcode")))
        							{
        								System.out.println("matched");
        								match=true;
        								int newQuan=Integer.parseInt(rs.getString("quantity"))+Integer.parseInt(importTable.getValueAt(row, 4).toString());
        								update.executeUpdate("UPDATE itemDataTable SET itemName='"+importTable.getValueAt(row, 1).toString()+"',brand='"+importTable.getValueAt(row, 2).toString()+"',volume='"+importTable.getValueAt(row, 3).toString()+"',quantity='"+Integer.toString(newQuan)+"',expiryDate='"+importTable.getValueAt(row, 5).toString()+"',buyPrice='"+importTable.getValueAt(row, 6).toString()+"',vat='"+importTable.getValueAt(row, 7).toString()+"',sellPrice='"+importTable.getValueAt(row, 8).toString()+"' WHERE barcode='"+importTable.getValueAt(row, 0).toString()+"';");
        								break;
        							}
        						}
        						if(match==false)
        						{
        							update.execute("INSERT INTO itemDataTable VALUES('"+model.getValueAt(row, 0).toString()+"','"+model.getValueAt(row, 1).toString()+"','"+model.getValueAt(row, 2).toString()+"','"+model.getValueAt(row, 3).toString()+"','"+model.getValueAt(row, 4).toString()+"','"+model.getValueAt(row, 5).toString()+"','"+model.getValueAt(row, 6).toString()+"','"+model.getValueAt(row, 7).toString()+"','"+model.getValueAt(row, 8).toString()+"');");
        						}
        						
        						if(row==model.getRowCount()-1)
        						{
        							System.out.println("Save");
	
        						}
        						else{
        							
        							System.out.println("done "+b);
        							b++;
        						}
        					}
        					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Saved to database!");
        					st.close();
        					update.close();
        					rs.close();
        					conn.close();
        				} catch (SQLException e) {
        					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
        				}
        			}
        			
        		}
        	});
        	btnSave_1.setFont(new Font("Constantia", Font.BOLD, 15));
        	
        	JPanel accntsPanel = new JPanel();
        	accntsPanel.setBackground(new Color(15,52,81));
        	tabbedPane.addTab("Accounts", null, accntsPanel, null);
        	accntsPanel.setLayout(null);
        	
        	JPanel panel_16 = new JPanel();
        	panel_16.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
        	panel_16.setBounds(0, 0, 864, 625);
        	panel_16.setBackground(new Color(246,238,237));
        	accntsPanel.add(panel_16);
        	panel_16.setLayout(null);
        	
        	JPanel panel_22 = new JPanel();
        	panel_22.setBorder(new LineBorder(new Color(0, 139, 139), 3, true));
        	panel_22.setOpaque(false);
        	panel_22.setBackground(new Color(0,0,0,0));
        	panel_22.setBounds(15, 81, 243, 450);
        	panel_16.add(panel_22);
        	panel_22.setLayout(null);
        	
        	fromYear = new JComboBox();
        	fromYear.setBackground(SystemColor.controlDkShadow);
        	fromYear.setModel(new DefaultComboBoxModel(new String[] {"2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070"}));
        	fromYear.setMaximumRowCount(10);
        	fromYear.setSelectedIndex(-1);
        	fromYear.setBounds(173, 156, 51, 26);
        	panel_22.add(fromYear);
        	
        	JLabel lblFrom = new JLabel("From");
        	lblFrom.setForeground(Color.DARK_GRAY);
        	lblFrom.setHorizontalAlignment(SwingConstants.CENTER);
        	lblFrom.setFont(new Font("Gabriola", Font.BOLD, 28));
        	lblFrom.setBounds(79, 121, 72, 28);
        	panel_22.add(lblFrom);
        	
        	fromMonth = new JComboBox();
        	fromMonth.setBackground(SystemColor.controlDkShadow);
        	fromMonth.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
        	fromMonth.setMaximumRowCount(12);
        	fromMonth.setSelectedIndex(-1);
        	fromMonth.setBounds(96, 156, 51, 26);
        	panel_22.add(fromMonth);
        	
        	toDay = new JComboBox();
        	toDay.setBackground(SystemColor.controlDkShadow);
        	toDay.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
        	toDay.setMaximumRowCount(16);
        	toDay.setSelectedIndex(-1);
        	toDay.setBounds(20, 234, 51, 26);
        	panel_22.add(toDay);
        	
        	JLabel label = new JLabel("/");
        	label.setHorizontalAlignment(SwingConstants.CENTER);
        	label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        	label.setBounds(70, 156, 28, 28);
        	panel_22.add(label);
        	
        	toYear = new JComboBox();
        	toYear.setBackground(SystemColor.controlDkShadow);
        	toYear.setModel(new DefaultComboBoxModel(new String[] {"2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070"}));
        	toYear.setMaximumRowCount(10);
        	toYear.setSelectedIndex(-1);
        	toYear.setBounds(173, 234, 51, 26);
        	panel_22.add(toYear);
        	
        	JLabel lblTo = new JLabel("To");
        	lblTo.setForeground(Color.DARK_GRAY);
        	lblTo.setHorizontalAlignment(SwingConstants.CENTER);
        	lblTo.setFont(new Font("Gabriola", Font.BOLD, 28));
        	lblTo.setBounds(83, 199, 72, 28);
        	panel_22.add(lblTo);
        	
        	JLabel label_1 = new JLabel("/");
        	label_1.setHorizontalAlignment(SwingConstants.CENTER);
        	label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        	label_1.setBounds(70, 234, 28, 28);
        	panel_22.add(label_1);
        	
        	JLabel label_2 = new JLabel("/");
        	label_2.setHorizontalAlignment(SwingConstants.CENTER);
        	label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        	label_2.setBounds(146, 234, 28, 28);
        	panel_22.add(label_2);
        	
        	JLabel label_3 = new JLabel("/");
        	label_3.setHorizontalAlignment(SwingConstants.CENTER);
        	label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        	label_3.setBounds(146, 156, 28, 28);
        	panel_22.add(label_3);
        	
        	toMonth = new JComboBox();
        	toMonth.setBackground(SystemColor.controlDkShadow);
        	toMonth.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
        	toMonth.setMaximumRowCount(12);
        	toMonth.setSelectedIndex(-1);
        	toMonth.setBounds(96, 234, 51, 26);
        	panel_22.add(toMonth);
        	
        	fromDay = new JComboBox();
        	fromDay.setBackground(new Color(0,0,0,0));
        	//fromDay.setBackground(SystemColor.controlDkShadow);
        	fromDay.setOpaque(false);
        	fromDay.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
        	fromDay.setMaximumRowCount(16);
        	fromDay.setSelectedIndex(-1);
        	fromDay.setBounds(20, 156, 51, 26);
        	panel_22.add(fromDay);
        	
        	JButton btnNewButton_6 = new JButton("Show");
        	btnNewButton_6.setForeground(new Color(0, 139, 139));
        	btnNewButton_6.setBackground(SystemColor.controlDkShadow);
        	btnNewButton_6.setFont(new Font("Constantia", Font.BOLD, 13));
        	btnNewButton_6.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent arg0) {
        			if(rdbtnBirdseye.isSelected())
        			{
        				if(fromDay.getSelectedIndex()!=-1 && fromMonth.getSelectedIndex()!=-1 && fromYear.getSelectedIndex()!=-1 && toDay.getSelectedIndex()!=-1 && toMonth.getSelectedIndex()!=-1 && toYear.getSelectedIndex()!=-1)
            			{
            				DefaultTableModel model=(DefaultTableModel) invoiceTable.getModel();
            				model.setRowCount(0);
            				sellField.setText(null);
            				profitField.setText(null);
            				String startDate=fromDay.getSelectedItem().toString()+"/"+fromMonth.getSelectedItem().toString()+"/"+fromYear.getSelectedItem().toString();
            				String endDate=toDay.getSelectedItem().toString()+"/"+toMonth.getSelectedItem().toString()+"/"+toYear.getSelectedItem().toString();
            				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            				try {
            					Date start = (Date)formatter.parse(startDate);
            					Date end= (Date)formatter.parse(endDate);
            					long diff = Math.abs(start.getTime() - end.getTime());
            					int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
            					conn=sqliteConnection.dbConnection();
            					System.out.println(diffDays);
            					if(diffDays!=0)
            					{
            						for(int i=0;i<=diffDays;i++)
            						{
            							ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM saleInfo;");
            							Calendar c = Calendar.getInstance(); 
            							c.setTime(start);
            							c.add(Calendar.DATE, i);
            							String temp=formatter.format(c.getTime());
            							while(rs.next())
            							{
            								if(rs.getString("date").toString().equals(temp))
            								{
            									model.addRow(new Object[]{rs.getString("date"),rs.getString("invoice"),rs.getString("totalAmount"),rs.getString("profit")});
            								}
            							}
            							rs.close();
            							
            						}
            						
            						conn.close();
            					}
            					else
            					{
            						ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM saleInfo;");
            						while(rs.next())
            						{
            							if(rs.getString("date").toString().equals(startDate))
            							{
            								model.addRow(new Object[]{rs.getString("date"),rs.getString("invoice"),rs.getString("totalAmount"),rs.getString("profit")});
            							}
            						}
            						rs.close();
            					}
            					
            					conn.close();
            				} catch (ParseException e) {
            					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Parsing "+2651+" problem occurs");
            				} catch (SQLException e) {
            					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
            				}
            				invoiceTable.getColumn("Date").setCellRenderer( centerRenderer );
            				invoiceTable.getColumn("Invoices").setCellRenderer( centerRenderer );
            				invoiceTable.getColumn("Total Sell").setCellRenderer( centerRenderer );
            				invoiceTable.getColumn("Profit").setCellRenderer( centerRenderer );
            				int totalSell=0;
            				int totalProfit=0;
            				for(int i=0;i<model.getRowCount();i++)
            				{
            					totalSell+=Integer.parseInt(model.getValueAt(i, 2).toString());
            					totalProfit+=Integer.parseInt(model.getValueAt(i, 3).toString());
            				}
            				sellField.setText(totalSell+" tk");
            				profitField.setText(totalProfit+" tk");
            			}
            			else
            			{
            				JOptionPane.showMessageDialog(frmBirdsEye, "Please check the both dates!");
            			}
        			}
        			else if(rdbtnNilAchal.isSelected())
        			{
        				if(fromDay.getSelectedIndex()!=-1 && fromMonth.getSelectedIndex()!=-1 && fromYear.getSelectedIndex()!=-1 && toDay.getSelectedIndex()!=-1 && toMonth.getSelectedIndex()!=-1 && toYear.getSelectedIndex()!=-1)
            			{
            				DefaultTableModel model=(DefaultTableModel) invoiceTable.getModel();
            				model.setRowCount(0);
            				sellField.setText(null);
            				profitField.setText(null);
            				String startDate=fromDay.getSelectedItem().toString()+"/"+fromMonth.getSelectedItem().toString()+"/"+fromYear.getSelectedItem().toString();
            				String endDate=toDay.getSelectedItem().toString()+"/"+toMonth.getSelectedItem().toString()+"/"+toYear.getSelectedItem().toString();
            				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            				try {
            					Date start = (Date)formatter.parse(startDate);
            					Date end= (Date)formatter.parse(endDate);
            					long diff = Math.abs(start.getTime() - end.getTime());
            					int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
            					conn1=nilAchalConnection.dbConnection();
            					if(diffDays!=0)
            					{
            						for(int i=0;i<=diffDays;i++)
            						{
            							ResultSet rs=conn1.createStatement().executeQuery("SELECT * FROM saleInfo;");
            							Calendar c = Calendar.getInstance(); 
            							c.setTime(start);
            							c.add(Calendar.DATE, i);
            							String temp=formatter.format(c.getTime());
            							while(rs.next())
            							{
            								if(rs.getString("date").toString().equals(temp))
            								{
            									model.addRow(new Object[]{rs.getString("date"),rs.getString("invoice"),rs.getString("totalAmount"),rs.getString("profit")});
            								}
            							}
            							rs.close();
            							
            						}
            						
            						conn1.close();
            					}
            					else
            					{
            						ResultSet rs=conn1.createStatement().executeQuery("SELECT * FROM saleInfo;");
            						while(rs.next())
            						{
            							if(rs.getString("date").toString().equals(startDate))
            							{
            								model.addRow(new Object[]{rs.getString("date"),rs.getString("invoice"),rs.getString("totalAmount"),rs.getString("profit")});
            							}
            						}
            						rs.close();
            					}
            					conn1.close();
            				} catch (ParseException e) {
            					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Parsing "+2651+" problem occurs");
            				} catch (SQLException e) {
            					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
            				}
            				invoiceTable.getColumn("Date").setCellRenderer( centerRenderer );
            				invoiceTable.getColumn("Invoices").setCellRenderer( centerRenderer );
            				invoiceTable.getColumn("Total Sell").setCellRenderer( centerRenderer );
            				invoiceTable.getColumn("Profit").setCellRenderer( centerRenderer );
            				int totalSell=0;
            				int totalProfit=0;
            				for(int i=0;i<model.getRowCount();i++)
            				{
            					totalSell+=Integer.parseInt(model.getValueAt(i, 2).toString());
            					totalProfit+=Integer.parseInt(model.getValueAt(i, 3).toString());
            				}
            				sellField.setText(totalSell+" tk");
            				profitField.setText(totalProfit+" tk");
            			}
            			else
            			{
            				JOptionPane.showMessageDialog(frmBirdsEye, "Please check the both dates!");
            			}
        			}
        		}
        	});
        	btnNewButton_6.setBounds(20, 388, 89, 26);
        	panel_22.add(btnNewButton_6);
        	
        	JButton btnClear_1 = new JButton("Clear");
        	btnClear_1.setForeground(new Color(0, 139, 139));
        	btnClear_1.setBackground(SystemColor.controlDkShadow);
        	btnClear_1.setFont(new Font("Constantia", Font.BOLD, 13));
        	btnClear_1.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent arg0) {
        			fromDay.setSelectedIndex(-1);
        			fromMonth.setSelectedIndex(-1);
        			fromYear.setSelectedIndex(-1);
        			toDay.setSelectedIndex(-1);
        			toMonth.setSelectedIndex(-1);
        			toYear.setSelectedIndex(-1);
        			DefaultTableModel model=(DefaultTableModel) invoiceTable.getModel();
        			model.setRowCount(0);
        		}
        	});
        	btnClear_1.setBounds(135, 388, 89, 26);
        	panel_22.add(btnClear_1);
        	
        	rdbtnBirdseye = new JRadioButton("Birds Eye");
        	rdbtnBirdseye.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent arg0) {
        				rdbtnNilAchal.setSelected(false);
        				rdbtnBirdseye.setSelected(true);
        		}
        	});
        	rdbtnBirdseye.setBackground(new Color(0,0,0,0));
        	rdbtnBirdseye.setOpaque(false);
        	rdbtnBirdseye.setFont(new Font("Gadugi", Font.PLAIN, 14));
        	rdbtnBirdseye.setHorizontalAlignment(SwingConstants.CENTER);
        	rdbtnBirdseye.setBounds(70, 305, 109, 23);
        	panel_22.add(rdbtnBirdseye);
        	
        	rdbtnNilAchal = new JRadioButton("Nil Achal Fashion House");
        	rdbtnNilAchal.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			rdbtnNilAchal.setSelected(true);
    				rdbtnBirdseye.setSelected(false);
        		}
        	});
        	rdbtnNilAchal.setBackground(new Color(0,0,0,0));
        	rdbtnNilAchal.setOpaque(false);
        	rdbtnNilAchal.setFont(new Font("Gadugi", Font.PLAIN, 14));
        	rdbtnNilAchal.setHorizontalAlignment(SwingConstants.CENTER);
        	rdbtnNilAchal.setBounds(30, 331, 184, 23);
        	panel_22.add(rdbtnNilAchal);
        	
        	JLabel lblSearch = new JLabel("Search");
        	lblSearch.setForeground(new Color(0, 139, 139));
        	lblSearch.setFont(new Font("Gabriola", Font.BOLD, 59));
        	lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
        	lblSearch.setBounds(10, 23, 223, 62);
        	panel_22.add(lblSearch);
        	
        	JScrollPane scrollPane_6 = new JScrollPane();
        	scrollPane_6.setBounds(270, 81, 584, 450);
        	panel_16.add(scrollPane_6);
        	
        	invoiceTable = new JTable();
        	invoiceTable.setBorder(new LineBorder(new Color(0, 139, 139), 2, true));
        	invoiceTable.setModel(new DefaultTableModel(
        		new Object[][] {
        		},
        		new String[] {
        			"Date", "Invoices", "Total Sell", "Profit"
        		}
        	) {
        		Class[] columnTypes = new Class[] {
        			String.class, String.class, String.class, String.class
        		};
        		public Class getColumnClass(int columnIndex) {
        			return columnTypes[columnIndex];
        		}
        		boolean[] columnEditables = new boolean[] {
        			false, false, false, false
        		};
        		public boolean isCellEditable(int row, int column) {
        			return columnEditables[column];
        		}
        	});
        	final TableCellRenderer invoiceTableRnderer = invoiceTable.getTableHeader().getDefaultRenderer();
        	invoiceTable.getTableHeader().setDefaultRenderer(new TableCellRenderer() 
	    	{
	            @Override
	           public Component getTableCellRendererComponent(JTable table, 
	                Object value, boolean isSelected, boolean hasFocus, 
	                int row, int column) {
	                JLabel lbl = (JLabel) invoiceTableRnderer.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, column);
	                lbl.setForeground(Color.lightGray);
	                lbl.setBorder(BorderFactory.createCompoundBorder (lbl.getBorder(),     //createCompoundBorder 
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
        	invoiceTable.getColumnModel().getColumn(0).setPreferredWidth(118);
        	invoiceTable.getColumnModel().getColumn(1).setPreferredWidth(123);
        	invoiceTable.getColumnModel().getColumn(2).setPreferredWidth(88);
        	invoiceTable.getColumnModel().getColumn(3).setPreferredWidth(83);
        	scrollPane_6.setViewportView(invoiceTable);
        	
        	JPanel panel_19 = new JPanel();
        	panel_19.setBorder(new LineBorder(new Color(0, 139, 139), 3, true));
        	panel_19.setBackground(new Color(0,0,0,0));
        	panel_19.setBounds(270, 542, 274, 72);
        	panel_16.add(panel_19);
        	panel_19.setLayout(null);
        	
        	JLabel lblNewLabel = new JLabel("Total Sell");
        	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        	lblNewLabel.setBounds(10, 11, 87, 50);
        	panel_19.add(lblNewLabel);
        	
        	sellField = new JTextField();
        	sellField.setFont(new Font("Times New Roman", Font.BOLD, 19));
        	sellField.setHorizontalAlignment(SwingConstants.CENTER);
        	sellField.setEditable(false);
        	sellField.setBounds(115, 11, 148, 50);
        	panel_19.add(sellField);
        	sellField.setColumns(10);
        	
        	JPanel panel_23 = new JPanel();
        	panel_23.setBackground(new Color(0,0,0,0));
        	panel_23.setBorder(new LineBorder(new Color(0, 139, 139), 3, true));
        	panel_23.setLayout(null);
        	panel_23.setBounds(573, 542, 281, 72);
        	panel_16.add(panel_23);
        	
        	JLabel lblTotalProfit = new JLabel("Total Profit");
        	lblTotalProfit.setHorizontalAlignment(SwingConstants.CENTER);
        	lblTotalProfit.setFont(new Font("Tahoma", Font.BOLD, 15));
        	lblTotalProfit.setBounds(10, 11, 98, 50);
        	panel_23.add(lblTotalProfit);
        	
        	profitField = new JTextField();
        	profitField.setFont(new Font("Times New Roman", Font.BOLD, 19));
        	profitField.setHorizontalAlignment(SwingConstants.CENTER);
        	profitField.setEditable(false);
        	profitField.setColumns(10);
        	profitField.setBounds(118, 11, 149, 50);
        	panel_23.add(profitField);
        	
        	JLabel lblNewLabel_6 = new JLabel("Accounts");
        	lblNewLabel_6.setForeground(new Color(0, 139, 139));
        	lblNewLabel_6.setFont(new Font("Gabriola", Font.BOLD, 60));
        	lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
        	lblNewLabel_6.setBounds(15, 11, 839, 59);
        	panel_16.add(lblNewLabel_6);
        	
        	radioButton = new JRadioButton("Birds Eye");
        	radioButton.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			
        			rcptLabel.setText("BirdsEye");
        		}
        	});
        	radioButton.setForeground(Color.WHITE);
        	radioButton.setOpaque(false);
        	radioButton.setHorizontalAlignment(SwingConstants.CENTER);
        	radioButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        	radioButton.setBackground(new Color(0, 0, 0, 0));
        	radioButton.setBounds(931, 19, 109, 23);
        	accntsPanel.add(radioButton);
        	
        	JPanel panel_20 = new JPanel();
        	panel_20.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
        	panel_20.setOpaque(false);
        	panel_20.setBackground(new Color(0,0,0,0));
        	panel_20.setBounds(874, 49, 423, 563);
        	accntsPanel.add(panel_20);
        	panel_20.setLayout(null);
        	
        	rcptLabel = new JLabel("Reciept");
        	rcptLabel.setFont(new Font("Gabriola", Font.BOLD, 31));
        	rcptLabel.setHorizontalAlignment(SwingConstants.CENTER);
        	rcptLabel.setForeground(Color.WHITE);
        	rcptLabel.setOpaque(false);
        	rcptLabel.setBounds(140, 53, 140, 59);
        	panel_20.add(rcptLabel);
        	
        	JPanel panel_34 = new JPanel();
        	panel_34.setBounds(74, 524, 280, 28);
        	panel_20.add(panel_34);
        	panel_34.setLayout(null);
        	
        	JLabel lblNewLabel_7 = new JLabel("Total");
        	lblNewLabel_7.setBounds(0, 0, 114, 28);
        	panel_34.add(lblNewLabel_7);
        	lblNewLabel_7.setForeground(Color.DARK_GRAY);
        	lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
        	lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
        	
        	totalField = new JTextField();
        	totalField.setBounds(142, 0, 138, 28);
        	panel_34.add(totalField);
        	totalField.setText("0 tk");
        	totalField.setHorizontalAlignment(SwingConstants.CENTER);
        	totalField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        	totalField.setEditable(false);
        	totalField.setColumns(10);
        	
        	dateField = new JTextField();
        	dateField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        	dateField.setHorizontalAlignment(SwingConstants.CENTER);
        	dateField.setEditable(false);
        	dateField.setBounds(27, 69, 111, 28);
        	panel_20.add(dateField);
        	dateField.setColumns(10);
        	
        	timeField = new JTextField();
        	timeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        	timeField.setHorizontalAlignment(SwingConstants.CENTER);
        	timeField.setEditable(false);
        	timeField.setColumns(10);
        	timeField.setBounds(284, 69, 111, 28);
        	panel_20.add(timeField);
        	
        	userField = new JTextField();
        	userField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        	userField.setHorizontalAlignment(SwingConstants.CENTER);
        	userField.setEditable(false);
        	userField.setBounds(27, 112, 368, 28);
        	panel_20.add(userField);
        	userField.setColumns(10);
        	
        	JScrollPane scrollPane_5 = new JScrollPane();
        	scrollPane_5.setBounds(29, 189, 366, 324);
        	panel_20.add(scrollPane_5);
        	
        	rcptTable = new JTable();
        	rcptTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        	rcptTable.setModel(new DefaultTableModel(
        		new Object[][] {
        		},
        		new String[] {
        			"barcode", "Name", "Qty", "net price"
        		}
        	) {
        		Class[] columnTypes = new Class[] {
        			String.class, String.class, String.class, String.class
        		};
        		public Class getColumnClass(int columnIndex) {
        			return columnTypes[columnIndex];
        		}
        		boolean[] columnEditables = new boolean[] {
        			false, false, false, false
        		};
        		public boolean isCellEditable(int row, int column) {
        			return columnEditables[column];
        		}
        	});
        	rcptTable.getColumnModel().getColumn(1).setPreferredWidth(134);
        	rcptTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        	scrollPane_5.setViewportView(rcptTable);
        	
        	salesManField = new JTextField();
        	salesManField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        	salesManField.setHorizontalAlignment(SwingConstants.CENTER);
        	salesManField.setEditable(false);
        	salesManField.setColumns(10);
        	salesManField.setBounds(27, 150, 368, 28);
        	panel_20.add(salesManField);
        	
        	JPanel panel_21 = new JPanel();
        	panel_21.setBounds(27, 25, 368, 27);
        	panel_20.add(panel_21);
        	panel_21.setLayout(null);
        	
        	invoiceSearch = new JTextField();
        	invoiceSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
        	invoiceSearch.addKeyListener(new KeyAdapter() {
        		@Override
        		public void keyPressed(KeyEvent e) {
        			if(e.getKeyCode()==KeyEvent.VK_ENTER)
        			{
        				if(radioButton.isSelected())
            			{
            				try {
                				DefaultTableModel model=(DefaultTableModel) rcptTable.getModel();
                				model.setRowCount(0);
                				conn=sqliteConnection.dbConnection();
                				ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM salesTable;");
                				int total=0;
                				boolean match=false;
                				while(rs.next())
                				{
                					if(rs.getString("invoice").equals(invoiceSearch.getText().toString()))
                					{match=true;
                						total+=Integer.parseInt(rs.getString("totalPrice"));
                						dateField.setText(rs.getString("date"));
                						timeField.setText(rs.getString("time"));
                						userField.setText(rs.getString("customer")+"    "+rs.getString("customerCell"));
                						model.addRow(new Object[]{rs.getString("barcode"),rs.getString("name"),rs.getString("quantity"),rs.getString("totalPrice")});
                					}
                				}
                				if(match==false)
                				{
                					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye, "This Invoice doesn't exist!");
                					totalField.setText("0");
                				}
                				else{
                					totalField.setText(Integer.toString(total)+"  tk");
                				}
                				rcptTable.getColumn("barcode").setCellRenderer( centerRenderer );
                				rcptTable.getColumn("Name").setCellRenderer( centerRenderer );
                				rcptTable.getColumn("Qty").setCellRenderer( centerRenderer );
                				rcptTable.getColumn("net price").setCellRenderer( centerRenderer );
                				totalField.setText(Integer.toString(total)+"  tk");
                				rs.close();
                				conn.close();
                			} catch (SQLException e1) {
                				JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
                			}
            			}
            			else if(radioButton_1.isSelected())
            			{
            				try {
                				DefaultTableModel model=(DefaultTableModel) rcptTable.getModel();
                				model.setRowCount(0);
                				conn1=nilAchalConnection.dbConnection();
                				ResultSet rs=conn1.createStatement().executeQuery("SELECT * FROM salesTable;");
                				int total=0;
                				boolean match=false;
                				while(rs.next())
                				{
                					if(rs.getString("invoice").equals(invoiceSearch.getText().toString()))
                					{match=true;
                						total+=Integer.parseInt(rs.getString("totalPrice"));
                						dateField.setText(rs.getString("date"));
                						timeField.setText(rs.getString("time"));
                						userField.setText(rs.getString("customer")+"    "+rs.getString("customerCell"));
                						model.addRow(new Object[]{rs.getString("barcode"),rs.getString("name"),rs.getString("quantity"),rs.getString("totalPrice")});
                					}
                				}
                				if(match==false)
                				{
                					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye, "This Invoice doesn't exist!");
                					totalField.setText("0");
                				}
                				else{
                					totalField.setText(Integer.toString(total)+"  tk");
                				}
                				rcptTable.getColumn("barcode").setCellRenderer( centerRenderer );
                				rcptTable.getColumn("Name").setCellRenderer( centerRenderer );
                				rcptTable.getColumn("Qty").setCellRenderer( centerRenderer );
                				rcptTable.getColumn("net price").setCellRenderer( centerRenderer );
                				totalField.setText(Integer.toString(total)+"  tk");
                				rs.close();
                				conn1.close();
                			} catch (SQLException e1) {
                				JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
                			}
            			}
            			else
            			{
            				JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"No option is selected!");
            			}
        			}
        		}
        	});
        	invoiceSearch.setHorizontalAlignment(SwingConstants.CENTER);
        	invoiceSearch.setBounds(0, 0, 275, 27);
        	panel_21.add(invoiceSearch);
        	invoiceSearch.setColumns(10);
        	
        	JButton btnNewButton_5 = new JButton("Search");
        	btnNewButton_5.setForeground(new Color(0, 139, 139));
        	btnNewButton_5.setBackground(SystemColor.controlDkShadow);
        	btnNewButton_5.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			if(radioButton.isSelected())
        			{
        				try {
            				DefaultTableModel model=(DefaultTableModel) rcptTable.getModel();
            				model.setRowCount(0);
            				conn=sqliteConnection.dbConnection();
            				ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM salesTable;");
            				int total=0;
            				boolean match=false;
            				while(rs.next())
            				{
            					if(rs.getString("invoice").equals(invoiceSearch.getText().toString()))
            					{match=true;
            						total+=Integer.parseInt(rs.getString("totalPrice"));
            						dateField.setText(rs.getString("date"));
            						timeField.setText(rs.getString("time"));
            						userField.setText(rs.getString("customer")+"    "+rs.getString("customerCell"));
            						model.addRow(new Object[]{rs.getString("barcode"),rs.getString("name"),rs.getString("quantity"),rs.getString("totalPrice")});
            					}
            				}
            				if(match==false)
            				{
            					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye, "This Invoice doesn't exist!");
            					totalField.setText("0");
            				}
            				else{
            					totalField.setText(Integer.toString(total)+"  tk");
            				}
            				rcptTable.getColumn("barcode").setCellRenderer( centerRenderer );
            				rcptTable.getColumn("Name").setCellRenderer( centerRenderer );
            				rcptTable.getColumn("Qty").setCellRenderer( centerRenderer );
            				rcptTable.getColumn("net price").setCellRenderer( centerRenderer );
            				totalField.setText(Integer.toString(total)+"  tk");
            				rs.close();
            				conn.close();
            			} catch (SQLException e1) {
            				JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
            			}
        			}
        			else if(radioButton_1.isSelected())
        			{
        				try {
            				DefaultTableModel model=(DefaultTableModel) rcptTable.getModel();
            				model.setRowCount(0);
            				conn1=nilAchalConnection.dbConnection();
            				ResultSet rs=conn1.createStatement().executeQuery("SELECT * FROM salesTable;");
            				int total=0;
            				boolean match=false;
            				while(rs.next())
            				{
            					if(rs.getString("invoice").equals(invoiceSearch.getText().toString()))
            					{match=true;
            						total+=Integer.parseInt(rs.getString("totalPrice"));
            						dateField.setText(rs.getString("date"));
            						timeField.setText(rs.getString("time"));
            						userField.setText(rs.getString("customer")+"    "+rs.getString("customerCell"));
            						model.addRow(new Object[]{rs.getString("barcode"),rs.getString("name"),rs.getString("quantity"),rs.getString("totalPrice")});
            					}
            				}
            				if(match==false)
            				{
            					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye, "This Invoice doesn't exist!");
            					totalField.setText("0");
            				}
            				else{
            					totalField.setText(Integer.toString(total)+"  tk");
            				}
            				rcptTable.getColumn("barcode").setCellRenderer( centerRenderer );
            				rcptTable.getColumn("Name").setCellRenderer( centerRenderer );
            				rcptTable.getColumn("Qty").setCellRenderer( centerRenderer );
            				rcptTable.getColumn("net price").setCellRenderer( centerRenderer );
            				totalField.setText(Integer.toString(total)+"  tk");
            				rs.close();
            				conn1.close();
            			} catch (SQLException e1) {
            				JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
            			}
        			}
        		}
        	});
        	btnNewButton_5.setFont(new Font("Constantia", Font.BOLD, 15));
        	btnNewButton_5.setBounds(273, 0, 95, 27);
        	panel_21.add(btnNewButton_5);
        	
        	radioButton_1 = new JRadioButton("Nil Achal Fashion House");
        	radioButton_1.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			radioButton.setSelected(false);
        			rcptLabel.setText("Nil Achal");
        		}
        	});
        	radioButton_1.setForeground(Color.WHITE);
        	radioButton_1.setOpaque(false);
        	radioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
        	radioButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        	radioButton_1.setBackground(new Color(0, 0, 0, 0));
        	radioButton_1.setBounds(1042, 19, 204, 23);
        	accntsPanel.add(radioButton_1);
        	
        	JPanel panel_2 = new JPanel();
        	panel_2.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
        	panel_2.setBackground(new Color(246,238,237));
        	tabbedPane.addTab("Users", null, panel_2, null);
        	panel_2.setLayout(null);
        	
        	JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
        	tabbedPane_2.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
        	tabbedPane_2.addChangeListener(new ChangeListener() {
        		public void stateChanged(ChangeEvent arg0) {
        			if(employeeTable!=null)
        			{
        				DefaultTableModel model=(DefaultTableModel) employeeTable.getModel();
        				model.setRowCount(0);
        			}
        		}
        	});
        	tabbedPane_2.setBounds(34, 26, 1243, 575);
        	panel_2.add(tabbedPane_2);
        	
        	JPanel panel_3 = new JPanel();
        	panel_3.setBorder(new LineBorder(Color.GRAY, 1, true));
        	panel_3.setBackground(Color.WHITE);
        	tabbedPane_2.addTab("Customer Subscription", null, panel_3, null);
        	panel_3.setLayout(null);
        	
        	scrollPane_1 = new JScrollPane();
        	scrollPane_1.setBounds(175, 189, 793, 302);
        	scrollPane_1.setBackground(Color.WHITE);
        	panel_3.add(scrollPane_1);
        	scrollPane_1.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Informations",TitledBorder.CENTER,TitledBorder.CENTER,new Font("times new roman",Font.BOLD,18), Color.black));
        	
        	customerTable = new JTable(){  
  		      public boolean isCellEditable(int row, int column){  
  		          if(customerTable.getSelectedColumn()==0 || customerTable.getSelectedColumn()==2||customerTable.getSelectedColumn()==3||customerTable.getSelectedColumn()==4)
  		          {
  		        	  return true;
  		          }
  		    	  return false;  
  		        }  
  		      };  
        	customerTable.setBackground(Color.WHITE);
        	customerTable.setModel(new DefaultTableModel(
        		new Object[][] {
        		},
        		new String[] {
        			"Name", "Cell Number", "Designation", "Purchased Amounts", "Points"
        		}
        	) {
        		Class[] columnTypes = new Class[] {
        			Object.class, Object.class, String.class, Object.class, Object.class
        		};
        		public Class getColumnClass(int columnIndex) {
        			return columnTypes[columnIndex];
        		}
        	});
        	customerTable.getColumnModel().getColumn(0).setPreferredWidth(108);
        	customerTable.getColumnModel().getColumn(1).setPreferredWidth(172);
        	customerTable.getColumnModel().getColumn(2).setPreferredWidth(121);
        	customerTable.getColumnModel().getColumn(3).setPreferredWidth(139);
        	customerTable.setAutoCreateRowSorter(false);
        	final TableCellRenderer customerTableRenderer = customerTable.getTableHeader().getDefaultRenderer();
        	customerTable.getTableHeader().setDefaultRenderer(new TableCellRenderer() 
	    	{
	            @Override
	           public Component getTableCellRendererComponent(JTable table, 
	                Object value, boolean isSelected, boolean hasFocus, 
	                int row, int column) {
	                JLabel lbl = (JLabel) customerTableRenderer.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, column);
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
        	scrollPane_1.setViewportView(customerTable);
        	
        	customerSearch = new JComboBox();
        	customerSearch.setBounds(700, 149, 268, 29);
        	panel_3.add(customerSearch);
        	customerSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
        	customerSearch.setBackground(SystemColor.controlDkShadow);
        	setCustomerSearchModel(customerSearch);
        	AutoCompleteCustomerSearch.enable(customerSearch);
        	
        	JPanel panel_31 = new JPanel();
        	panel_31.setBounds(35, 253, 108, 171);
        	panel_3.add(panel_31);
        	panel_31.setBorder(new LineBorder(new Color(47, 79, 79), 3, true));
        	panel_31.setBackground(new Color(0,0,0,0));
        	panel_31.setLayout(null);
        	
        	btnView = new JButton("View");
        	btnView.setForeground(SystemColor.infoText);
        	btnView.setBackground(SystemColor.controlDkShadow);
        	btnView.setBounds(10, 11, 89, 29);
        	panel_31.add(btnView);
        	btnView.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent arg0) {
        			try {
        				conn=sqliteConnection.dbConnection();
        				ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM customerTable;");
        				DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        				while(rs.next())
        				{
        					model.addRow(new Object[]{rs.getString("name"),rs.getString("cellNumber"),rs.getString("designation"), rs.getString("purchasedAmounts"),rs.getString("points")});
        				}
        				//cell allignment
        				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        				centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        				customerTable.getColumn("Name").setCellRenderer( centerRenderer );
        				customerTable.getColumn("Cell Number").setCellRenderer( centerRenderer );
        				customerTable.getColumn("Designation").setCellRenderer( centerRenderer );
        				customerTable.getColumn("Purchased Amounts").setCellRenderer( centerRenderer );
        				customerTable.getColumn("Points").setCellRenderer( centerRenderer );
        				//row header
        				int no=customerTable.getRowCount();
        				String [] rowNo = new String[no];
        				for(int i=1;i<=no;i++){
        					rowNo[i-1]=Integer.toString(i);
        				}
        				rowHeader= new JList(rowNo);
        				rowHeader.setFixedCellWidth(30);
        				rowHeader.setFixedCellHeight(customerTable.getRowHeight());
        				rowHeader.setBackground(new Color(0,0,0,0));
        				rowHeader.setCellRenderer(new RowRenderer(customerTable));
        				scrollPane_1.setRowHeaderView(rowHeader);
        				//row header
        				btnView.setEnabled(false);
        				rs.close();
        				conn.close();
        				customerTable.grabFocus();
        			} catch (SQLException e) {
        				JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
        			}
        		}
        	});
        	btnView.setFont(new Font("Gabriola", Font.BOLD, 20));
        	
        	JButton btnClear_3 = new JButton("Clear");
        	btnClear_3.setForeground(SystemColor.infoText);
        	btnClear_3.setBackground(SystemColor.controlDkShadow);
        	btnClear_3.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent arg0) {
        			customerSearch.setSelectedIndex(-1);
        			DefaultTableModel model=(DefaultTableModel) customerTable.getModel();
        			model.setRowCount(0);
        			scrollPane_1.setRowHeaderView(null);
        			btnView.setEnabled(true);
        		}
        	});
        	btnClear_3.setBounds(10, 51, 89, 29);
        	panel_31.add(btnClear_3);
        	btnClear_3.setFont(new Font("Gabriola", Font.BOLD, 20));
        	
        	JButton btnUpdate_1 = new JButton("Update");
        	btnUpdate_1.setForeground(SystemColor.infoText);
        	btnUpdate_1.setBackground(SystemColor.controlDkShadow);
        	btnUpdate_1.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent arg0) {
        			if(customerTable.getRowCount()!=0)
        			{
        				try {
        					conn=sqliteConnection.dbConnection();
							
							DefaultTableModel model=(DefaultTableModel) customerTable.getModel();
							for(int row=0;row<model.getRowCount();row++)
							{
								ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM customerTable;");
								while(rs.next())
								{
									if(model.getValueAt(row, 1).toString().equals(rs.getString("cellNumber")))
									{
										conn.createStatement().executeUpdate("UPDATE customerTable SET name='"+model.getValueAt(row, 0).toString().trim()+"', purchasedAmounts='"+model.getValueAt(row, 3).toString()+"', points='"+model.getValueAt(row, 4).toString().trim()+"',designation='"+model.getValueAt(row, 2).toString().trim()+"' WHERE cellNumber='"+model.getValueAt(row, 1).toString().trim()+"';");
									}
								}
								rs.close();
							}
							model.setRowCount(0);
							ResultSet rs1=conn.createStatement().executeQuery("SELECT * FROM customerTable;");
							while(rs1.next())
							{
								model.addRow(new Object[]{rs1.getString("name"),rs1.getString("cellNumber"),rs1.getString("designation"),rs1.getString("purchasedAmounts"),rs1.getString("points")});
							}
							conn.close();
							JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Updated successfully!");
						} catch (SQLException e) {
						
							JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs!"+e.getMessage());
						}
        			}
        			else
        			{
        				JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Nothing to update!");
        			}
        		}
        	});
        	btnUpdate_1.setBounds(10, 91, 89, 29);
        	panel_31.add(btnUpdate_1);
        	btnUpdate_1.setFont(new Font("Gabriola", Font.BOLD, 20));
        	
        	JButton btnDelete_2 = new JButton("Delete");
        	btnDelete_2.setForeground(SystemColor.infoText);
        	btnDelete_2.setBackground(SystemColor.controlDkShadow);
        	btnDelete_2.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent arg0) {
        			if(customerTable.getRowCount()!=0)
        			{
        				try {
        					conn=sqliteConnection.dbConnection();
							ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM customerTable;");
							while(rs.next())
							{
								if(rs.getString("cellNumber").equals(customerTable.getValueAt(customerTable.getSelectedRow(), 1).toString()))
								{
									conn.createStatement().execute("DELETE FROM customerTable WHERE cellNumber='"+customerTable.getValueAt(customerTable.getSelectedRow(), 1).toString()+"';");
								}
							}
							rs.close();
							DefaultTableModel model=(DefaultTableModel) customerTable.getModel();
							model.removeRow(customerTable.getSelectedRow());
							scrollPane_1.setRowHeaderView(null);
							int no=customerTable.getRowCount();
	        				String [] rowNo = new String[no];
	        				for(int i=1;i<=no;i++){
	        					rowNo[i-1]=Integer.toString(i).trim();
	        				}
	        				rowHeader= new JList(rowNo);
	        				rowHeader.setFixedCellWidth(30);
	        				rowHeader.setFixedCellHeight(customerTable.getRowHeight());
	        				rowHeader.setBackground(new Color(0,0,0,0));
	        				rowHeader.setCellRenderer(new RowRenderer(customerTable));
	        				scrollPane_1.setRowHeaderView(rowHeader);
							conn.close();
							JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Successfully deleted selected Customer");
						} 
        				catch (SQLException e) 
        				{
        					e.printStackTrace();
        					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs!");
						}
        			}
        			else
        			{
        				JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Nothing to delete!");
        			}
        		}
        	});
        	btnDelete_2.setBounds(10, 131, 89, 29);
        	panel_31.add(btnDelete_2);
        	btnDelete_2.setFont(new Font("Gabriola", Font.BOLD, 20));
        	
        	JPanel panel_5 = new JPanel();
        	panel_5.setBorder(new LineBorder(new Color(47,79,79), 3, true));
        	panel_5.setBounds(988, 195, 226, 292);
        	panel_3.add(panel_5);
        	panel_5.setLayout(null);
        	
        	customerNameText = new JTextField();
        	customerNameText.setForeground(UIManager.getColor("textHighlight"));
        	customerNameText.setBounds(41, 58, 151, 25);
        	panel_5.add(customerNameText);
        	customerNameText.setHorizontalAlignment(SwingConstants.CENTER);
        	customerNameText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        	customerNameText.setColumns(10);
        	
        	customerCellText = new JTextField();
        	customerCellText.setForeground(UIManager.getColor("textHighlight"));
        	customerCellText.setBounds(41, 120, 151, 25);
        	panel_5.add(customerCellText);
        	customerCellText.setHorizontalAlignment(SwingConstants.CENTER);
        	customerCellText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        	customerCellText.setColumns(10);
        	
        	desigText = new JTextField();
        	desigText.setForeground(UIManager.getColor("textHighlight"));
        	desigText.setBounds(41, 182, 151, 25);
        	panel_5.add(desigText);
        	desigText.setHorizontalAlignment(SwingConstants.CENTER);
        	desigText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        	desigText.setColumns(10);
        	
        	JButton btnSave = new JButton("Add");
        	btnSave.setBounds(62, 235, 108, 29);
        	panel_5.add(btnSave);
        	btnSave.setBackground(SystemColor.controlDkShadow);
        	btnSave.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			try {
        				conn=sqliteConnection.dbConnection();
        				ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM customerTable;");
        				int count=0;
        				while(rs.next()){
        					if(rs.getString("cellNumber").equals(customerCellText.getText())){
        						count++;
        						break;
        					}
        				}
        				if(count==0){
        					conn.createStatement().execute("INSERT INTO customerTable VALUES('"+customerNameText.getText()+"','"+customerCellText.getText()+"','0','0','"+desigText.getText()+"');");
        					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"New Customer Successfully Added");
        					customerNameText.setText(null);
                			customerCellText.setText(null);
                			desigText.setText(null);
        				}
        				else{
        					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Customer is already added");
        					customerNameText.setText(null);
                			customerCellText.setText(null);
                			desigText.setText(null);
        				}
        				rs.close();
        				conn.close();
        				
        			} catch (SQLException e1) {
        				JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
        			}
        			
        		}
        	});
        	btnSave.setFont(new Font("Constantia", Font.BOLD, 18));
        	
        	JLabel lblDesignation = new JLabel("Designation");
        	lblDesignation.setForeground(Color.DARK_GRAY);
        	lblDesignation.setBounds(41, 156, 151, 25);
        	panel_5.add(lblDesignation);
        	lblDesignation.setHorizontalAlignment(SwingConstants.CENTER);
        	lblDesignation.setFont(new Font("Constantia", Font.BOLD, 17));
        	
        	JLabel lblCellNumber = new JLabel("Cell Number");
        	lblCellNumber.setForeground(Color.DARK_GRAY);
        	lblCellNumber.setBounds(41, 94, 151, 25);
        	panel_5.add(lblCellNumber);
        	lblCellNumber.setFont(new Font("Constantia", Font.BOLD, 17));
        	lblCellNumber.setHorizontalAlignment(SwingConstants.CENTER);
        	
        	JLabel lblName = new JLabel("Name");
        	lblName.setForeground(Color.DARK_GRAY);
        	lblName.setBounds(41, 32, 151, 25);
        	panel_5.add(lblName);
        	lblName.setFont(new Font("Constantia", Font.BOLD, 17));
        	lblName.setHorizontalAlignment(SwingConstants.CENTER);
        	
        	JPanel panel_6 = new JPanel();
        	panel_6.setBorder(new LineBorder(new Color(0, 139, 139), 3, true));
        	panel_6.setBounds(391, 36, 460, 83);
        	panel_3.add(panel_6);
        	panel_6.setLayout(null);
        	
        	JLabel lblCustomerManager = new JLabel("Customer Manager");
        	lblCustomerManager.setBounds(10, 11, 439, 59);
        	panel_6.add(lblCustomerManager);
        	lblCustomerManager.setHorizontalAlignment(SwingConstants.CENTER);
        	lblCustomerManager.setForeground(new Color(0, 139, 139));
        	lblCustomerManager.setFont(new Font("Gabriola", Font.BOLD, 60));
        	
        	JPanel panel_4 = new JPanel();
        	panel_4.setBorder(new LineBorder(Color.GRAY, 1, true));
        	panel_4.setBackground(Color.WHITE);
        	tabbedPane_2.addTab("Employee Management", null, panel_4, null);
        	panel_4.setLayout(null);
        	
        	JPanel panel_35 = new JPanel();
        	panel_35.setBounds(429, 84, 394, 83);
        	panel_4.add(panel_35);
        	panel_35.setLayout(null);
        	panel_35.setBackground(new Color(0,0,0,0));
        	panel_35.setBorder(new LineBorder(new Color(47, 79, 79), 4, true));
        	
        	JLabel lblEmployeemanager = new JLabel("Employee Manager");
        	lblEmployeemanager.setHorizontalAlignment(SwingConstants.CENTER);
        	lblEmployeemanager.setForeground(new Color(47, 79, 79));
        	lblEmployeemanager.setFont(new Font("Gabriola", Font.BOLD, 50));
        	lblEmployeemanager.setBounds(10, 11, 372, 59);
        	panel_35.add(lblEmployeemanager);
        	
        	JScrollPane scrollPane_4 = new JScrollPane();
        	scrollPane_4.setBackground(Color.WHITE);
        	scrollPane_4.setBounds(265, 203, 601, 216);
        	panel_4.add(scrollPane_4);
        	scrollPane_4.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Users",TitledBorder.CENTER,TitledBorder.CENTER,new Font("times new roman",Font.BOLD,18), Color.black));
        	
        	
        	employeeTable = new JTable()
        	{  
        	      public boolean isCellEditable(int row, int column){  
        	          if(employeeTable.getSelectedColumn()==2)
        	          {
        	        	  return true;
        	          }
        	    	  return false;  
        	        }  
        	      };
        	employeeTable.setBackground(Color.WHITE);
        	employeeTable.setAutoCreateRowSorter(false);
        	employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        	employeeTable.setModel(new DefaultTableModel(
        		new Object[][] {
        		},
        		new String[] {
        			"Type", "User Name", "Password"
        		}
        	) {
        		Class[] columnTypes = new Class[] {
        			String.class, String.class, String.class
        		};
        		public Class getColumnClass(int columnIndex) {
        			return columnTypes[columnIndex];
        		}
        	});
        	final TableCellRenderer employeeTableRenderer = employeeTable.getTableHeader().getDefaultRenderer();
        	employeeTable.getTableHeader().setDefaultRenderer(new TableCellRenderer() 
	    	{
	            @Override
	           public Component getTableCellRendererComponent(JTable table, 
	                Object value, boolean isSelected, boolean hasFocus, 
	                int row, int column) {
	                JLabel lbl = (JLabel) employeeTableRenderer.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, column);
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
        	employeeTable.getColumnModel().getColumn(0).setPreferredWidth(98);
        	employeeTable.getColumnModel().getColumn(1).setPreferredWidth(137);
        	employeeTable.getColumnModel().getColumn(2).setPreferredWidth(114);
        	scrollPane_4.setViewportView(employeeTable);
        	
        	JPanel panel_29 = new JPanel();
        	panel_29.setBounds(121, 203, 134, 216);
        	panel_4.add(panel_29);
        	panel_29.setBorder(new LineBorder(new Color(47, 79, 79), 6, true));
        	panel_29.setBackground(new Color(0,0,0,0));
        	panel_29.setLayout(null);
        	
        	JButton btnNewButton_8 = new JButton("View ");
        	btnNewButton_8.setBackground(SystemColor.controlDkShadow);
        	btnNewButton_8.setForeground(new Color(47, 79, 79));
        	btnNewButton_8.setFont(new Font("Gabriola", Font.BOLD, 19));
        	btnNewButton_8.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent arg0) {
        			DefaultTableModel model=(DefaultTableModel) employeeTable.getModel();
        			model.setRowCount(0);
        			try {
        				conn=sqliteConnection.dbConnection();
        				ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM loginDB;");
        				while(rs.next())
        				{
        					if(rs.getString("type").equals("guest"))
        					{
        						model.addRow(new Object[]{rs.getString("type"),rs.getString("name"),rs.getString("password")});
        					}
        				}
        				employeeTable.getColumn("Type").setCellRenderer( centerRenderer );
        				employeeTable.getColumn("User Name").setCellRenderer( centerRenderer );
        				employeeTable.getColumn("Password").setCellRenderer( centerRenderer );
        				rs.close();
        				conn.close();
        			} catch (SQLException e) {
        				JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
        			}
        			
        		}
        	});
        	btnNewButton_8.setBounds(16, 71, 102, 33);
        	panel_29.add(btnNewButton_8);
        	
        	JButton btnDelete_1 = new JButton("Delete!");
        	btnDelete_1.setForeground(new Color(47, 79, 79));
        	btnDelete_1.setBackground(SystemColor.controlDkShadow);
        	btnDelete_1.setFont(new Font("Gabriola", Font.BOLD, 19));
        	btnDelete_1.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			if(employeeTable.getSelectedRowCount()!=0)
        			{
        				try {
        					conn=sqliteConnection.dbConnection();
        					DefaultTableModel model=(DefaultTableModel) employeeTable.getModel();
        					conn.createStatement().execute("DELETE FROM loginDB WHERE name='"+employeeTable.getValueAt(employeeTable.getSelectedRow(), 1)+"';");
        					model.removeRow(employeeTable.getSelectedRow());
        					conn.close();
        				} catch (SQLException e1) {
        					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
        				}
        			}
        		}
        	});
        	btnDelete_1.setBounds(16, 115, 102, 33);
        	panel_29.add(btnDelete_1);
        	
        	JPanel panel_30 = new JPanel();
        	panel_30.setBackground(Color.WHITE);
        	panel_30.setBorder(new LineBorder(new Color(47, 79, 79), 4, true));
        	panel_30.setBounds(876, 203, 252, 216);
        	panel_4.add(panel_30);
        	panel_30.setLayout(null);
        	
        	JLabel lblAddNewUser = new JLabel("Add New User");
        	lblAddNewUser.setFont(new Font("Tahoma", Font.BOLD, 15));
        	lblAddNewUser.setHorizontalAlignment(SwingConstants.CENTER);
        	lblAddNewUser.setBounds(10, 11, 232, 46);
        	panel_30.add(lblAddNewUser);
        	
        	newName = new JTextField();
        	newName.setHorizontalAlignment(SwingConstants.CENTER);
        	newName.setBounds(119, 68, 107, 24);
        	panel_30.add(newName);
        	newName.setColumns(10);
        	
        	newPass = new JTextField();
        	newPass.setHorizontalAlignment(SwingConstants.CENTER);
        	newPass.setBounds(119, 103, 107, 24);
        	panel_30.add(newPass);
        	newPass.setColumns(10);
        	
        	JButton btnAdd = new JButton("Add");
        	btnAdd.setForeground(new Color(47, 79, 79));
        	btnAdd.setFont(new Font("Gabriola", Font.BOLD, 19));
        	btnAdd.setBackground(SystemColor.controlDkShadow);
        	btnAdd.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			if(newName.getText().equals(null) && newPass.getText().equals(null) && newName.getText().equalsIgnoreCase("") && newPass.getText().equals(""))
        			{
        				JOptionPane.showMessageDialog(frmBirdsEye, "Nothing to save");
        			}
        			else
        			{
        				try {
        					conn=sqliteConnection.dbConnection();
        					ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM loginDB;");
        					boolean match=false;
        					while(rs.next())
        					{
        						if(rs.getString("name").equalsIgnoreCase(newName.getText()))
        						{
        							match=true;
        							break;
        						}
        					}
        					if(match==true)
        					{
        						JOptionPane.showMessageDialog(frmBirdsEye, "User exists with this name\n please select another name");
        						newName.setText(null);
        						newPass.setText(null);
        					}
        					else
        					{
        						conn.createStatement().execute("INSERT INTO loginDB VALUES('guest','"+newName.getText()+"','"+newPass.getText()+"');");
        						JOptionPane.showMessageDialog(frmBirdsEye, "New Guest user Created");
        						newName.setText(null);
        						newPass.setText(null);
        					}
        				} catch (SQLException e1) {
        					JOptionPane.showMessageDialog(AdminWindow.frmBirdsEye,"Database problem occurs");
        				}
        			}
        		}
        	});
        	btnAdd.setBounds(78, 153, 102, 33);
        	panel_30.add(btnAdd);
        	
        	JLabel lblName_1 = new JLabel("Name");
        	lblName_1.setFont(new Font("Constantia", Font.PLAIN, 14));
        	lblName_1.setHorizontalAlignment(SwingConstants.CENTER);
        	lblName_1.setBounds(20, 68, 89, 24);
        	panel_30.add(lblName_1);
        	
        	JLabel lblPassword = new JLabel("Password");
        	lblPassword.setFont(new Font("Constantia", Font.PLAIN, 14));
        	lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        	lblPassword.setBounds(20, 103, 89, 24);
        	panel_30.add(lblPassword);
		
		JPanel panel_33 = new JPanel();
		panel_33.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		panel_33.setBounds(1237, 11, 89, 23);
		panel.add(panel_33);
		panel_33.setLayout(null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBackground(SystemColor.controlDkShadow);
		btnLogout.setBounds(0, 0, 89, 23);
		panel_33.add(btnLogout);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login window=new Login();
				window.frmLogin.setVisible(true);
				frmBirdsEye.dispose();
			}
		});
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
		});;
		
		JLabel lblNewLabel_8 = new JLabel("");
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
		    			a+=Integer.parseInt(model.getValueAt(z, 7).toString().trim());
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
		conn=sqliteConnection.dbConnection();
		ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM itemDataTable;");
		ArrayList<String> arr = new ArrayList<String>();
		Set<String> hs = new HashSet<>();
		while(rs.next())
		{
			arr.add(rs.getString(s).toString().trim());		
		}
		hs.addAll(arr);
		arr.clear();
		arr.addAll(hs);
		rs.close();
		conn.close();
		comboBox.setModel(new DefaultComboBoxModel(arr.toArray()));
		comboBox.setSelectedIndex(-1);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		comboBox.setModel(new DefaultComboBoxModel(arr.toArray()));
		comboBox.setSelectedIndex(-1);
	}
}



