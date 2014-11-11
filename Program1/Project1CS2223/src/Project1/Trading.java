package Project1;

import java.util.*;
import java.io.*;
import java.math.*;


public class Trading {
	
	public static ArrayList<Trade> makeTrades(PriorityQueue<Buy> BuyPQ, PriorityQueue<Sell> SellPQ){
		ArrayList<Trade> TradeList = new ArrayList<Trade>();
		//System.out.println("We have made it to makeTrades");
		if (BuyPQ.isEmpty() || SellPQ.isEmpty()){
			//System.out.println("The system is in equilibrium because BuyPQ or SellPQ are empty");
			return TradeList;
		}
		int comparison = SellPQ.peek().compareTo(BuyPQ.peek()); //compares the two values of buy and sell
		Trade ourTrade;
		int quantityToTrade;
		while (comparison < 1){ //If the buying price is greater than or equal to the selling price
			
			quantityToTrade = Math.min(SellPQ.peek().getQuantity(), BuyPQ.peek().getQuantity());
			ourTrade = new Trade(SellPQ.peek().getPrice(), quantityToTrade);
			TradeList.add(ourTrade);
			System.out.println(ourTrade.toString());
			boolean boo1 = SellPQ.peek().decrementQuantity(quantityToTrade);
			boolean boo2 = BuyPQ.peek().decrementQuantity(quantityToTrade);
			if (boo1 == true){
				SellPQ.poll();
			}
			if (boo2 == true){
				BuyPQ.poll();
			}
			if (BuyPQ.isEmpty() || SellPQ.isEmpty()){
				return TradeList;
			}
			comparison = SellPQ.peek().compareTo(BuyPQ.peek());
		}
		return TradeList;
	}
	
	public static void main(String[] args){
		ArrayList<Buy> BuyOffers = new ArrayList<Buy>();
		ArrayList<Sell> SellOffers = new ArrayList<Sell>();
		
		

		int order = 0; //keep track of the order
		Buy BuyObject; //Might be a buy object
		Sell SellObject; //Might be a sell object
		System.out.println("On Input:");
		for (int x=0; x< args.length; x=x+3){ //iterate across the inputs of buy and sell orders
			if (args[x].equals("buy")){ //Check if buy object, then find the two integers following it
				int price = Integer.parseInt(args[(x+1)]);
				int quantity = Integer.parseInt(args[(x+2)]);
				BuyObject = new Buy(price, quantity, order); //Create the object
				System.out.println("(Buy,"+BuyObject.printOrders()+")");
				order++;
				BuyOffers.add(BuyObject);
				//System.out.println(BuyObject.toString());
			}
			
			else if (args[x].equals("sell")){ //Check if sell object, then find the two integers following it
				int price = Integer.parseInt(args[(x+1)]);
				int quantity = Integer.parseInt(args[(x+2)]);
				SellObject = new Sell(price, quantity, order); //Create the object
				System.out.println("(Sell,"+SellObject.printOrders()+")");
				order++;
				//System.out.println(SellObject.toString());
				SellOffers.add(SellObject);
			}
			else{ //In case the input was incorrect
				System.out.println("You failed to format your buy and sell objects properly. Please try again.");
				return;
			}
		}
		PriorityQueue<Buy> BuyPQ= new PriorityQueue<Buy>(BuyOffers.size()+1,Collections.reverseOrder()); // +1 for size  in case of empty lists
		PriorityQueue<Sell> SellPQ= new PriorityQueue<Sell>(SellOffers.size()+1);// +1 for size  in case of empty lists
		boolean b1 = BuyOffers.isEmpty();
		boolean b2 = SellOffers.isEmpty();
		System.out.println("\n The sequence of sales is:");
		while (!b1 || !b2){ //Change this later on to a while loop with a bunch of conditions that we don't yet fully understand
			if (b1){
				SellPQ.add(SellOffers.remove(0));
			}
			else if (b2){
				BuyPQ.add(BuyOffers.remove(0));
			}
			else if (BuyOffers.get(0).getOrder() < SellOffers.get(0).getOrder()){
				BuyPQ.add(BuyOffers.remove(0));
			}
			else{
				SellPQ.add(SellOffers.remove(0));
			}
			makeTrades(BuyPQ, SellPQ);
			b1 = BuyOffers.isEmpty();
			b2 = SellOffers.isEmpty();
		}
		
		System.out.println(" \n The outstanding sell orders are:");
		while (SellPQ.isEmpty()==false){ // Printing out the outstanding Sell orders
			
			Sell printobject;
			printobject = SellPQ.peek();
			System.out.println("("+printobject.printOrders()+")");
			SellPQ.poll();
		}
		
		System.out.println(" \n The outstanding buy orders are:");
		while (BuyPQ.isEmpty()==false){ // printing out the outstanding Buy orders
			Buy printobject;
			printobject = BuyPQ.peek();
			System.out.println("("+printobject.printOrders()+")");
			BuyPQ.poll();
		}
		
	}

}
