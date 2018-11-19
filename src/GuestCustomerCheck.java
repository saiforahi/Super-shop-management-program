import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.MatteBorder;

public class GuestCustomerCheck {

	public JFrame frmCustomers;
	public static JTable table;
	public static JComboBox<?> comboBox;
	public static JComboBox<?> comboBox_1;
	public static Connection conn=sqliteConnection.dbConnection();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuestCustomerCheck window = new GuestCustomerCheck();
						window.frmCustomers.setVisible(true);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public GuestCustomerCheck() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	@SuppressWarnings("rawtypes")
	private void initialize() throws SQLException {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
		
		
		frmCustomers = new JFrame();
		frmCustomers.setIconImage(Toolkit.getDefaultToolkit().getImage("image_lib\\besof.png"));
		frmCustomers.setResizable(false);
		frmCustomers.setTitle("Customers");
		frmCustomers.setBounds(100, 100, 812, 458);
		frmCustomers.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCustomers.setLocationRelativeTo(GuestWindow.frmBirdsEye);
		frmCustomers.getContentPane().setLayout(null);
		frmCustomers.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		    	comboBox.requestFocus();
		    }
		});
		
		JPanel panel = new JPanel();
		panel.setBounds(41, 24, 712, 86);
		frmCustomers.getContentPane().add(panel);
		panel.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 45, 208, 29);
		setNameComboModel();
		comboBox.setMaximumRowCount(10);
		comboBox.setFont(new Font("Constantia", Font.PLAIN, 14));
		GuestAutoCompletion.enable(comboBox);
		comboBox.setSelectedIndex(-1);
		panel.add(comboBox);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Constantia", Font.BOLD, 15));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(10, 11, 203, 29);
		panel.add(lblName);
		
		JLabel lblCellNumber = new JLabel("Cell Number");
		lblCellNumber.setFont(new Font("Constantia", Font.BOLD, 15));
		lblCellNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblCellNumber.setBounds(268, 11, 194, 29);
		panel.add(lblCellNumber);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setSelectedIndex(-1);
		setCellComboModel();
		GuestAutoCompletion.enable(comboBox_1);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_1.setBounds(259, 45, 208, 29);
		
		panel.add(comboBox_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 128, 720, 256);
		scrollPane.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"Customers",TitledBorder.CENTER,TitledBorder.CENTER,new Font("times new roman",Font.BOLD,18), Color.black));
		frmCustomers.getContentPane().add(scrollPane);
		

		table= new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					DefaultTableModel model=(DefaultTableModel) table.getModel();
					System.out.println(table.getSelectedRow());
					GuestWindow.nameTextField.setText(model.getValueAt(table.getSelectedRow(), 0).toString());
					GuestWindow.cellTextField.setText(model.getValueAt(table.getSelectedRow(), 1).toString());
					GuestWindow.pointField.setText(model.getValueAt(table.getSelectedRow(), 4).toString());
					frmCustomers.setVisible(false);
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Cell Number", "Designation", "Purchased Amounts", "Points"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, String.class, Object.class, Object.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(96);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(106);
		table.getColumnModel().getColumn(4).setResizable(false);
		final TableCellRenderer tcrOs = table.getTableHeader().getDefaultRenderer();
		table.getTableHeader().setDefaultRenderer(new TableCellRenderer() 
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
		final TableCellRenderer saleTableRenderer = table.getTableHeader().getDefaultRenderer();
		table.getTableHeader().setDefaultRenderer(new TableCellRenderer() 
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
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(table);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setNameComboModel() throws SQLException{
		ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM customerTable;");
		ArrayList<String> arr = new ArrayList<String>();
		
		while(rs.next())
		{
			arr.add(rs.getString("name"));
		}
		rs.close();
		comboBox.setModel(new DefaultComboBoxModel(arr.toArray()));
		comboBox.setSelectedIndex(-1);
	}
	public void setCellComboModel() throws SQLException{
		ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM customerTable;");
		ArrayList<String> arr = new ArrayList<String>();
		
		while(rs.next())
		{
			if(rs.getString("cellNumber").equals(null)||rs.getString("cellNumber").equals(""))
			{
				continue;
			}
			else arr.add(rs.getString("cellNumber"));
		}
		rs.close();
		comboBox_1.setModel(new DefaultComboBoxModel(arr.toArray()));
		comboBox_1.setSelectedIndex(-1);
	}
	public static void setTable(){
		DefaultTableModel model=(DefaultTableModel) table.getModel();
		model.setRowCount(0);
		try {
			ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM customerTable;");
			while(rs.next())
			{
				if(rs.getString("name").equals(comboBox.getSelectedItem()))
				{
					model.addRow(new Object[]{rs.getString("name"),rs.getString("cellNumber"),rs.getString("designation"),rs.getString("purchasedAmounts"),rs.getString("points")});
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
