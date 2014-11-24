package project2;

import java.io.*;
import java.util.*;

/*
* Arthur Dooner
* Arun Donti
* Program 2
* CS 2223
*/


public class Traversals {
	
	static boolean error = false; //Program-wide handler for any errors in the program 
	static boolean error2 = false;
	
		
	public static void main(String[] args) throws IOException {
	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); //Read-in for input provided by base program
	        String[] traversal1 = new String[100]; //Could be very long arrays
	        String[] traversal2 = new String[100];
	        boolean readAtLeastOneLine = false; //Checks for one line of input at least 
	        boolean readTwoLines = false; //Checks for one
	
	        // whitespace will separate our strings in a given input line
	        // this doesn't work for leading/trailing whitespace, so we use trim() below.
	        String delims = "[ ]+";
	
	        String s1 = (in.readLine()).trim();
	        if  (s1 !=null && s1.length() != 0) {
	            traversal1 = s1.split(delims);
	            readAtLeastOneLine = true;
	        }
	        /* at this point the first line of input has been read into an array of Strings, namely 
	           traversal1 */
	
	        String s2 = (in.readLine()).trim();
	        if  (s2 !=null && s2.length() != 0) {
	            traversal2 = s2.split(delims);
	            readTwoLines = true;
	        }
	
	        /* at this point one or two lines of input have been read into an array or two of Strings, namely 
	           traversal1  and traversal2.  The boolean variables readAtLeastOneLine and readTwoLines
	           capture how much was read.
	        */
	
	        
	        /* Testing */
	        System.out.println(readAtLeastOneLine);            
	        if (readAtLeastOneLine)
	            System.out.println("traversal1 length was : " + traversal1.length);
	
	        System.out.println(readTwoLines);            
	        if (readTwoLines)
	            System.out.println("traversal2 length was : " + traversal2.length);
	
	        if(readTwoLines){
	        	
	        	ArrayList<String> result= pre_in_to_post(traversal1, traversal2,traversal2.length);
	        	if (error==false)
	        	{
	        	System.out.println("The pre in to post array is " + result.toString());
	        	}
	        	if (error==true)
	        	{
	        		System.out.println("This tree does not work for Preorder and Inorder to Postorder to create a full tree");
	        	}
	        	ArrayList<String> result2 = pre_post_to_in(traversal1, traversal2);
	        	
	        }
	        
	        else if (readAtLeastOneLine)
	        {	
	        	ArrayList<String> finalPOSTArray = new ArrayList<String>();
	        	finalPOSTArray = search_pre_to_post(traversal1);
	        	System.out.println("The pre to post array is " + finalPOSTArray.toString());
	        }
	        
	        else
	        {
	        	System.out.println("Invalid Input");
	        }
	        
	    } //main
	

	//Implementation of Pre_To_Post provided it is coming from a BST with NO USE of a TREE. 
	//Returns an ArrayList of String in the post order.
	static ArrayList<String> search_pre_to_post(String[] stringarray){
		boolean invalid = false;
		ArrayList<String> result = new ArrayList<String>();
		if (stringarray.length > 2){ //checks to see if we have an appropriately sized array
			int indexToDivide = getDivisionIndex(stringarray); //Gives the index where we should divide the array
			String[] tempLeft = Arrays.copyOfRange(stringarray, 1, indexToDivide); //Makes the left half and appends to the arraylist
			String[] tempRight = Arrays.copyOfRange(stringarray, indexToDivide, stringarray.length); //Makes the right and does the same
			result.addAll(search_pre_to_post(tempLeft));
			result.addAll(search_pre_to_post(tempRight));
		}
		else if (stringarray.length == 2){ //In the case we have only 2 elements and should recurse only once.
				result.addAll(search_pre_to_post(Arrays.copyOfRange(stringarray, 1, 2)));
		}
		else if (stringarray.length == 0){ //Catches if we go too far
			return result;
		}
		result.add(stringarray[0]);
		return result;
	}
	
	static int getDivisionIndex(String[] StringArray){
		boolean greaterthanRoot = false;
		int i = 0;
		while (greaterthanRoot == false && (i <= StringArray.length)){
			i++;
			if (StringArray[i].compareTo(StringArray[0]) > 0){
				greaterthanRoot = true;
			}
		}
		return i;
	}
	
	
	static ArrayList<String> pre_in_to_post(String[] PreList, String[] InList, int n){

		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> wrong = new ArrayList<String>();

		int root = search(InList,PreList[0],n);	// find where the tree root is in the Inorder traversal
		boolean visited1 = false; // using booleans to check whether or not trees have either 0 or 2 subtrees in order to make sure outcome is of a valid full tree 
		boolean	visited2 = false;
		if (root != 0){ //if the root is not at the beginning of the tree, therefore we can search the left subtree
			
			String [] newpre = Arrays.copyOfRange(PreList, 1, PreList.length+1);
			result.addAll(pre_in_to_post(newpre, InList,root));
			visited1 = true; // left node has been visited
			
		}
		
		if (root != n-1) 
		{
			String [] newpre = Arrays.copyOfRange(PreList, root+1, PreList.length+1);
			String [] newin = Arrays.copyOfRange(InList, root+1, InList.length+1);
			result.addAll(pre_in_to_post(newpre, newin, n-root-1));
			
			visited2 = true; // right node has been visited
		}
		
		
		if (error){
			return wrong; // returning an empty array because it is not a valid tree
		}
		
		if ((visited1 && visited2) || (visited1 ==false && visited2 == false)) {// if it is a full tree	
		result.add(PreList[0]);
		return result;
		}
		
		else {// throw error because it isnt a full tree
		error=true;
		return wrong; 
		}
	}
	
	
	
	
	static int search(String[] s, String x, int n) // search for the specified character in the array and return the location
	{
		for (int i=0; i< n; i++)
		{
			if (s[i].equals(x))
			{
				return i;
			}
		}
		return -1;
	}
	

	static ArrayList<String> pre_post_to_in(String[] stringarray1, String[] stringarray2){
		ArrayList<String> mT = new ArrayList<String>();
		ArrayList<String> PreList = new ArrayList<String>(Arrays.asList(stringarray1)); //Converts the PreList to an ArrayList<String>
		ArrayList<String> PostList = new ArrayList<String>(Arrays.asList(stringarray2)); //Converts the PostList to an ArrayList<String>
		
		
		int PostListLength = PostList.size(); //Reused the variable quite a bit 
		
		if (PreList.size() == PostListLength && PostListLength%2==1 ){ //Checks to ensure same length and odd number of elements are in the tree since we are looking for a full tree
			
			if(PreList.get(0).equals(PostList.get((PostListLength-1)))){  //checks to ensure the first and last are equal
				BinaryTree ourBT = new BinaryTree(PreList.get(0)); //Start the Binary Tree
				ArrayList <String> InOrder = new ArrayList<String>(); //Starts the result list
				
				if (PostListLength == 1){ //checks to ensure that wasn't the only value in the tree
					InOrder.add(ourBT.getValue());
					return InOrder;		
				}
				
				PreList.remove(0);
				PostList.remove(PostListLength-1);
				boolean found = false;
				int i = 0;
				while (!found){ //While we haven't found the actual value it matches
					if (PreList.get(0).equals(PostList.get(i))) { //finds the actual value that matches with the pre-list in the postlist
						found = true; 
						try
						{
						ourBT.setLeft(helperpre_post_to_in(new ArrayList<String>(PreList.subList(0, i+1)), new ArrayList<String>(PostList.subList(0, i+1))));
						ourBT.setRight(helperpre_post_to_in(new ArrayList<String>(PreList.subList(i + 1, PreList.size())), new ArrayList<String>(PostList.subList(i + 1, PostList.size()))));
						}
						
						catch(IndexOutOfBoundsException e) 
						{
							System.out.println("This does not work for Preorder and Postorder to Inorder to create a full tree");
							return mT;
							
						}
					}
					i++;
				}
				ArrayList<String> DONE= ourBT.toInOrder();
		
				System.out.println("The pre post to in array is" +DONE); 
				return DONE;
			} 
			else{
				System.out.println("This tree does not work for Preorder and Postorder to Inorder to create a full tree");
				return mT;
				
				
			}
		}
		else {
			System.out.println("This tree does not work for Preorder and Postorder to Inorder to create a full tree");
			return mT;
		}
	}
	
	static BinaryTree helperpre_post_to_in(ArrayList<String> PreListPart, ArrayList<String> PostListPart){
		MTBinaryTree MTBT =  new MTBinaryTree();
		if (PreListPart.isEmpty()){
			return MTBT;
		}
		BinaryTree ourBT = new BinaryTree(PreListPart.get(0));
		if (PostListPart.size() == 1){ //checks to ensure that wasn't the only value in the tree
			return ourBT;		
		}
		
		
		PreListPart.remove(0);
		PostListPart.remove(PostListPart.size()-1);
		boolean found = false;
		int i = 0;

		while (!found){ //While we haven't found the actual value it matches
	
				if (PreListPart.get(0).equals(PostListPart.get(i))) { //finds the actual value that matches with the pre-list in the postlist
					found = true; 
					ourBT.setLeft(helperpre_post_to_in(new ArrayList<String>(PreListPart.subList(0, i+1)), new ArrayList<String>(PostListPart.subList(0, i+1)))); //Set all parts on the left subtree
					ourBT.setRight(helperpre_post_to_in(new ArrayList<String>(PreListPart.subList(i + 1, PreListPart.size())), new ArrayList<String>(PostListPart.subList(i + 1, PostListPart.size())))); //Set all parts on the right subtree
					}
			i++;
				
			
		}	
		return ourBT;
	}
	
	
	
} // class Traversals