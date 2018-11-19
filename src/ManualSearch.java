import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class ManualSearch {
	
	public JFrame frmManualSearch;
	public static JTable table;
	public JList<?> rowHeader;
	private JButton btnNewButton;
    public static JComboBox<Object> comboBox;
    public static Connection conn=sqliteConnection.dbConnection();
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManualSearch window = new ManualSearch();
					window.frmManualSearch.setVisible(true);
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
	public ManualSearch() throws SQLException {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
		
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		frmManualSearch = new JFrame();
		frmManualSearch.setTitle("Manual Search ");
		frmManualSearch.setIconImage(Toolkit.getDefaultToolkit().getImage("image_lib\\besof.png"));
		frmManualSearch.setBounds(100, 100, 1024, 556);
		frmManualSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmManualSearch.setLocationRelativeTo(null);
		frmManualSearch.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        comboBox.requestFocus();
		       
		    }
		});
		frmManualSearch.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		panel.setBounds(0, 0, 1008, 517);
		frmManualSearch.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 988, 455);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
				{
					DefaultTableModel model=(DefaultTableModel) table.getModel();
					DefaultTableModel model1=(DefaultTableModel) AdminWindow.salesTable.getModel();
					if(table.getRowCount()!=0)
					{
						if(AdminWindow.invoiceField.getText().toString().equals("0")||AdminWindow.invoiceField.getText().toString().equals(""))
						{
							try {
								ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM saleInfo;");
								int inv=1000;
								while(rs.next()){
									inv++;
								}
								AdminWindow.invoiceField.setText(Integer.toString(inv));
								rs.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						model1.addRow(new Object[]{model.getValueAt(table.getSelectedRow(),0).toString(),model.getValueAt(table.getSelectedRow(),1).toString(),model.getValueAt(table.getSelectedRow(),2).toString(),model.getValueAt(table.getSelectedRow(),3).toString(),"1",model.getValueAt(table.getSelectedRow(),6).toString(),model.getValueAt(table.getSelectedRow(),8).toString(),model.getValueAt(table.getSelectedRow(),8).toString()});
						String bar=model.getValueAt(table.getSelectedRow(), 0).toString();
						String name=model.getValueAt(table.getSelectedRow(), 1).toString();
						String brand=model.getValueAt(table.getSelectedRow(), 2).toString();
						String volume=model.getValueAt(table.getSelectedRow(), 3).toString();
						int buy=0;
						if(model.getValueAt(table.getSelectedRow(), 8).toString().equals(null))
						{
							buy=0;
						}
						else{
							buy=Integer.parseInt(model.getValueAt(table.getSelectedRow(), 7).toString());
						}
						int sell=Integer.parseInt(model.getValueAt(table.getSelectedRow(), 8).toString());
						SoldProductInfo sp=new SoldProductInfo(AdminWindow.invoiceField.getText().toString(),bar,name,brand,volume,"1",Integer.toString(buy),Integer.toString(sell),Integer.toString(sell));
						AdminWindow.soldItems.add(sp);
						AdminWindow.totalAmount.setText(Integer.toString(Integer.parseInt(AdminWindow.totalAmount.getText())+sell));
					}
					AdminWindow.salesTable.getColumn("Barcode Number").setCellRenderer( centerRenderer );
					AdminWindow.salesTable.getColumn("Products").setCellRenderer( centerRenderer );
					AdminWindow.salesTable.getColumn("Brand").setCellRenderer( centerRenderer );
					AdminWindow.salesTable.getColumn("Volume").setCellRenderer( centerRenderer );
					AdminWindow.salesTable.getColumn("Quantity").setCellRenderer( centerRenderer );
					AdminWindow.salesTable.getColumn("VAT").setCellRenderer( centerRenderer );
					AdminWindow.salesTable.getColumn("Price").setCellRenderer( centerRenderer );
					AdminWindow.salesTable.getColumn("Net Price").setCellRenderer( centerRenderer );
					frmManualSearch.setVisible(false);
				}
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Barcode", "Name", "Brand", "Volume", "Quantity", "Expiry Date", "VAT", "price", "Sell Price"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(83);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(174);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(65);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(54);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(62);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(8).setResizable(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		scrollPane.setViewportView(table);
		
		
		
		btnNewButton = new JButton("Select");
		btnNewButton.setBackground(SystemColor.controlDkShadow);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				DefaultTableModel model1=(DefaultTableModel) AdminWindow.salesTable.getModel();
				if(table.getRowCount()!=0)
				{
					if(AdminWindow.invoiceField.getText().toString().equals("0")||AdminWindow.invoiceField.getText().toString().equals(""))
					{
						try {
							ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM saleInfo;");
							int inv=1000;
							while(rs.next()){
								inv++;
							}
							AdminWindow.invoiceField.setText(Integer.toString(inv));
							rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					model1.addRow(new Object[]{model.getValueAt(table.getSelectedRow(),0).toString(),model.getValueAt(table.getSelectedRow(),1).toString(),model.getValueAt(table.getSelectedRow(),2).toString(),model.getValueAt(table.getSelectedRow(),3).toString(),"1",model.getValueAt(table.getSelectedRow(),6).toString(),model.getValueAt(table.getSelectedRow(),8).toString(),model.getValueAt(table.getSelectedRow(),8).toString()});
					String bar=model.getValueAt(table.getSelectedRow(), 0).toString();
					String name=model.getValueAt(table.getSelectedRow(), 1).toString();
					String brand=model.getValueAt(table.getSelectedRow(), 2).toString();
					String volume=model.getValueAt(table.getSelectedRow(), 3).toString();
					int buy=0;
					if(model.getValueAt(table.getSelectedRow(), 8).toString().equals(null))
					{
						buy=0;
					}
					else{
						buy=Integer.parseInt(model.getValueAt(table.getSelectedRow(), 7).toString());
					}
					int sell=Integer.parseInt(model.getValueAt(table.getSelectedRow(), 8).toString());
					SoldProductInfo sp=new SoldProductInfo(AdminWindow.invoiceField.getText().toString(),bar,name,brand,volume,"1",Integer.toString(buy),Integer.toString(sell),Integer.toString(sell));
					//soldProductInfo sp=new soldProductInfo(model.getValueAt(table.getSelectedRow(),0).toString(),model.getValueAt(table.getSelectedRow(),1).toString(),model.getValueAt(table.getSelectedRow(),2).toString(),model.getValueAt(table.getSelectedRow(),3).toString(),1,(int)model.getValueAt(table.getSelectedRow(),8),(int)model.getValueAt(table.getSelectedRow(),7));
					AdminWindow.soldItems.add(sp);
					
					AdminWindow.totalAmount.setText(Integer.toString(Integer.parseInt(AdminWindow.totalAmount.getText())+sell));
					
				}
				AdminWindow.salesTable.getColumn("Barcode Number").setCellRenderer( centerRenderer );
				AdminWindow.salesTable.getColumn("Products").setCellRenderer( centerRenderer );
				AdminWindow.salesTable.getColumn("Brand").setCellRenderer( centerRenderer );
				AdminWindow.salesTable.getColumn("Volume").setCellRenderer( centerRenderer );
				AdminWindow.salesTable.getColumn("Quantity").setCellRenderer( centerRenderer );
				AdminWindow.salesTable.getColumn("VAT").setCellRenderer( centerRenderer );
				AdminWindow.salesTable.getColumn("Price").setCellRenderer( centerRenderer );
				AdminWindow.salesTable.getColumn("Net Price").setCellRenderer( centerRenderer );
				frmManualSearch.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Constantia", Font.BOLD, 14));
		btnNewButton.setBounds(725, 11, 89, 30);
		panel.add(btnNewButton);
		
		comboBox = new JComboBox<Object>();
		comboBox.setBackground(SystemColor.text);
		setComboModel(comboBox);
		AutoCompleteSearch.enable(comboBox);
		comboBox.setBounds(271, 11, 445, 29);
		panel.add(comboBox);
	}
	public void setComboModel(JComboBox<Object> comboBox) throws SQLException{
		ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM itemDataTable;");
		ArrayList<String> arr = new ArrayList<String>();
		while(rs.next())
		{
			arr.add(rs.getString("itemName"));
		}
		rs.close();
		comboBox.setModel(new DefaultComboBoxModel<Object>(arr.toArray()));
		comboBox.setSelectedIndex(-1);
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
}
