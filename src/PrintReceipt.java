import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;





public class PrintReceipt implements Printable,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	Date nDate=new Date();
	SimpleDateFormat nDtFormat=new SimpleDateFormat("dd.MMM.yyyy");
	SimpleDateFormat monthFormat=new SimpleDateFormat("MMMMMMMMMM");
	SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm a");
	
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

	      if (pageIndex < 0 || pageIndex >= 1) {
	            return Printable.NO_SUCH_PAGE;
	        }

	       
	        	Graphics2D g2d = (Graphics2D) graphics;
		        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		        Font header = new Font("Viner Hand ITC",Font.BOLD, 22);
		        Font total = new Font("Arial Unicode MS",Font.PLAIN, 8);
		        Font item = new Font("Courier",Font.PLAIN, 6);
		        Font coders = new Font("Chiller",Font.BOLD, 10);
		        Font mail = new Font("lucidia bright",Font.BOLD, 8);
		        
		        g2d.setFont(header);
		        g2d.setColor(Color.BLACK);
		        //g2d.drawLine(10, 20,170,20);
		        g2d.drawString("Birds Eye" ,50, 15);
		        g2d.setFont(total);
		        g2d.drawString("Roton Corner, Malotinagar High School Lane, Bogra" ,10, 34);
		        g2d.drawString("cell: 01732127666" ,70, 44);
		        g2d.drawString("Invoice: "+AdminWindow.invoiceField.getText() ,76, 54);
		        g2d.drawString(nDtFormat.format(new Date()),10, 54);
		        g2d.drawString(dateFormat.format(new Date()),162,54);
		        g2d.drawRect(10, 58, 186, 24);
		        if(!AdminWindow.nameTextField.getText().equals(null) && !AdminWindow.nameTextField.getText().equals(""))
		        {
		        	g2d.drawString(AdminWindow.nameTextField.getText(), 12, 68);
		        	//g2d.drawString(AdminWindow.cellTextField.getText(), 135, 68);
		        }
		        else{
		        	g2d.drawString("Default", 12, 68);
		        }
		        g2d.drawString("Sold by "+Login.user, 12,78);
		        g2d.setFont(item);
		        g2d.drawString("MUSAK-11 KA (694988710826)", 102,78);
		        g2d.setFont(total);
		        g2d.drawLine(10,88,196,88);
		        g2d.drawString("No |", 12,98);
		        g2d.drawString("Product Desription", 60,98);
		        g2d.drawString("Amount", 147,98);
		        g2d.drawLine(10,103,196,103);
		        int y=113;
		        g2d.setColor(Color.BLACK);
		        for(int i=0;i<AdminWindow.soldItems.size();i++)
		        {
		        	g2d.drawString("1.", 15, y);
			       	g2d.drawString(AdminWindow.soldItems.elementAt(i).barcode, 29,y);
			       	g2d.drawString(AdminWindow.soldItems.elementAt(i).name, 23,y+9);
			       	g2d.drawString(AdminWindow.soldItems.elementAt(i).quantity+" ps",100,y);
			    	g2d.drawString(AdminWindow.soldItems.elementAt(i).totalSellPrice,180,y);
			    	y+=22;
		        }
		       	
		        
		        g2d.setFont(total);
		        g2d.drawLine(10,(y-3),196,(y-3));
		       
		        g2d.drawString("Total (VAT included)", 35,(y+8));
		        g2d.drawString(":", 122,(y+8));
		        g2d.drawString(AdminWindow.totalAmount.getText(), 150,(y+8));
		        g2d.drawString("tk", 185, y+8);
		        g2d.drawString("Cash Received", 35,(y+18));
		        g2d.drawString(":", 122,(y+18));
		        g2d.drawString(AdminWindow.rcvdTextField.getText(), 150, y+18);
		        g2d.drawString("tk", 185, y+18);
		        g2d.drawString("Change", 35,(y+28));
		        g2d.drawString(":", 122,(y+28));
		        g2d.drawString(AdminWindow.changeField.getText(), 150, y+28);
		        g2d.drawString("tk", 185, y+28);
		        g2d.setFont(mail);
		        //g2d.drawString("lab Symbiotic™", 104, y+51);
		        g2d.setFont(total);
		        g2d.drawString("Thanks for shopping!",12,y+48);
		        g2d.setFont(mail);
		        g2d.drawString("labsymbiotic@gmail.com",104,y+48);
		        //fD.delete();
		        return Printable.PAGE_EXISTS;
	        
	       
	    }
    public static void main(String[] args) {
    	
    	   PageFormat format = new PageFormat();
    	   Paper paper = new Paper();

    	   double paperWidth = 4.00;
    	   double paperHeight = 11.69;
    	   double leftMargin = 0.10;
    	   double rightMargin = 0.10;
    	   double topMargin = 0;
    	   double bottomMargin = 0.00;

    	   paper.setSize(paperWidth * 72.0, paperHeight * 72.0);
    	   paper.setImageableArea(leftMargin * 72.0, topMargin * 72.0,(paperWidth - leftMargin - rightMargin) * 72.0,(paperHeight - topMargin - bottomMargin) * 72.0);

    	   format.setPaper(paper);

    	   PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
    	   aset.add(OrientationRequested.PORTRAIT);

    	   PrinterJob printerJob = PrinterJob.getPrinterJob();
    	   Printable printable = new PrintReceipt();
    	   format = printerJob.validatePage(format);
    	   printerJob.setPrintable(printable, format);
    	   try {
    	      printerJob.print(aset);
    	   }
    	   catch (Exception e) {
    		   e.printStackTrace();
    	       JOptionPane.showMessageDialog(null,"Printing error !!");
    	   }
    	}

}