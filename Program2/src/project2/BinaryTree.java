package project2;

import java.util.ArrayList;

/*
 * Arthur Dooner
 * Arun Donti
 * Program 2
 * CS 2223
 */
public class BinaryTree {
	String value;
	BinaryTree Left;
	BinaryTree Right;

	
	
	public BinaryTree(String value, BinaryTree Left, BinaryTree Right){
		this.value = value;
		this.Left = Left;
		this.Right = Right; 
	}
	
	public BinaryTree(String value){
		this.value = value;
		this.Left = new MTBinaryTree();
		this.Right = new MTBinaryTree();
	}
	//Getter for Value
	public String getValue() {
		return value;
	}
	//Setter for Value
	public void setValue(String value) {
		this.value = value;
	}
	//Getter for Left
	public BinaryTree getLeft() {
		return Left;
	}
	//Setter for Left
	public void setLeft(BinaryTree left) {
		Left = left;
	}
	//Getter for Right
	public BinaryTree getRight() {
		return Right;
	}
	//Setter for Right
	public void setRight(BinaryTree right) {
		Right = right;
	}
	//Converts this Tree to an ArrayList of the elements in post-order form
	public ArrayList<String> topost()
	{	ArrayList<String> thing = new ArrayList<String>();
		
		if (this instanceof MTBinaryTree) // if the subtree is empty don't need to do anything to it
		{
			return thing; 
		}
		
		 thing.addAll(getLeft().topost()); // recursively call on Left and right trees to get correct post-order traversal
		 thing.addAll(getRight().topost());
		 thing.add(value);
		 return thing;
	}
	//Converts this tree to an ArrayList of the elements in in-order form
	public ArrayList<String> toInOrder(){
		ArrayList<String> thing = new ArrayList<String>();
		if (this instanceof MTBinaryTree){  // if the subtree is empty don't need to do anything to it
			return thing; 
		}
		
		thing.addAll(getLeft().toInOrder());
		thing.add(value);						; // recursively call on Left and right trees to get correct post-order traversal
		thing.addAll(getRight().toInOrder());
		return thing;
	}
}
