
public class SoldProductInfo {
	String invoice;
	String barcode;
	String name;
	String brand;
	String volume;
	String quantity;
	String buyPrice;
	String sellPrice;
	String totalSellPrice;
	
	SoldProductInfo(String inv,String bar,String nam,String brnd,String vol,String quan,String buyP,String sellP,String total){
		invoice=inv;
		barcode=bar;
		name=nam;
		brand=brnd;
		volume=vol;
		quantity=quan;
		buyPrice=buyP;
		sellPrice=sellP;
		totalSellPrice=total;
	}
}
