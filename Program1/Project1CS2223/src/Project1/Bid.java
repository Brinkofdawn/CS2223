package Project1;

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
	public String toString(){
		return "Bid object that is "+price+" "+quantity+" "+order;
	}
	
	public String printOrders(){
		return this.getPrice()+","+this.getQuantity();
	}
	//Takes a value to decrement Quantity by, returning true if the Quantity is 0.
	public boolean decrementQuantity(int amount){
		quantity-=amount;
		if (quantity == 0){
			return true;
		}
		return false;
	}
	@Override
	public int compareTo(Bid y){
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
