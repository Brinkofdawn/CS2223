package Project1;

public class Sell extends Bid{
	
	public Sell(int price, int quantity, int order){
		super(price, quantity, order);
	}
	
	public String printSell(){
		return "Sell order of price "+this.getPrice()+ " and quantity of "+this.getQuantity();
	}
}
