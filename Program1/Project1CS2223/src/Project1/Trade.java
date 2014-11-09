package Project1;

public class Trade {
	private int price;
	private int quantity;


	public Trade ( int price, int quantity) {
		this.price = price;
		this.quantity= quantity;
	}
	
	
	public int getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	
}
	
	public String toString(){
		return quantity+" units of the stock were sold at the price of "+price+".";
	}
}
