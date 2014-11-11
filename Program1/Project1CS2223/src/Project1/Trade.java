package Project1;

/* Arun Donti
 * Arthur Dooner 
 * CS2223
 */

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
	
	public String toString(){ // makes string containing what happened in the trades
		return "("+this.getPrice()+","+this.getQuantity()+")";
	}
}
