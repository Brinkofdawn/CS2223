package Project1;

public class Buy extends Bid{
	
	public Buy(int price, int quantity, int order){
		super(price, quantity, order);
	}
	
	public String printBuy(){
		return "Buy order of price "+this.getPrice()+ " and quantity of "+this.getQuantity();
	}
}
