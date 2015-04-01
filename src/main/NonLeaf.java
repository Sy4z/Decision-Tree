package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Node in the decision tree
 * @author Jarred Hone
 *
 */
public class NonLeaf extends Node{

	
	private Node left;
	private Node right;
	private String categoryType;
	//private boolean isTraining = false;
	int order = 0; //The order in which they were added. this is used only for populating other lists. It has nothing to do with the actual decision tree.
	List<Boolean> purityValues = new ArrayList<Boolean>(); //this will hold all the true/false values. Purity will be checked later.
	
	public NonLeaf(String category, Node left, Node right){
		//isTraining = training;
		categoryType = category;
		this.order = order;	
		
	}

	@Override
	boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	//Below are the methods for returning both the child nodes
	/**
	 * Returns the right child node
	 * @return
	 */
	public Node getRight(){
		return right;
	}
	
	/**
	 * Returns the left child node
	 * @return
	 */
	public Node getLeft(){
		return left;
	}
	
	
	
	
}
