package project2;

/*
* Arthur Dooner
* Arun Donti
* Program 2
* CS 2223
*/

//Binary Search Tree to hold for search_pre_to_post
public class BST extends BinaryTree {
	
	public BST(String value, BST Left, BST Right)
	{
		super(value, Left, Right);
	}
	
	public BST(String value)
	{
		super(value);
	}
	
	
	public void addpre(String string) // adding Strings to a BST
	{
		if(string.compareTo(getValue())<0) // add to left if the value is less than values already in the tree
		{
			if (Left instanceof MTBinaryTree) 
			{	
				setLeft(new BST(string));
			}
			else
			{
				BST tempLeft;
				tempLeft=((BST)getLeft());
				tempLeft.addpre(string);
				setLeft(tempLeft);
			}
		}
		
		else if(string.compareTo(getValue())>0) // add to right if the value is greater than values already in the tree
		{
			if (Right instanceof MTBinaryTree)
			{	
				setRight(new BST(string));
			}
			else
			{
				BST tempRight;
				tempRight=((BST)getRight());
				tempRight.addpre(string);
				setRight(tempRight);
			}
		}
	}
	
	

}
