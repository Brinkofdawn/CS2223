package Project1;

/* Arun Donti
 * Arthur Dooner 
 * CS2223
 */

public abstract class Bid implements Comparable<Bid> {
	private int price;
	private int quantity;
	private int order;

	public Bid(int price, int quantity, int order){
		this.price=price;
		this.quantity=quantity;
		this.order=order;
	}
	public int getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getOrder() {
		return order;
	}
	/*public String toString(){     // used for debugging purposes
		return "Bid object that is "+price+" "+quantity+" "+order;
	} */
	
	public String printOrders(){ // To print out the price and quantity of a buy or sell order
		return this.getPrice()+","+this.getQuantity();
	}
	
	public boolean decrementQuantity(int amount){ // //Takes a value to decrement Quantity by, returning true if the Quantity is 0 (should we remove the item or not).
		quantity-=amount;
		if (quantity == 0){
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Bid y){   // Overriding Priority Queue compare to method in order to sort heaps by price
		if (this.price < y.getPrice()){
			return -1;
		}
		else if (this.price == y.getPrice()){
			return 0;
		}
		else {
			return 1;
		}
	}
}
