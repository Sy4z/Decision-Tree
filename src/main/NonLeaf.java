package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Node in the decision tree
 * @author Jarred Hone
 *
 */
public class NonLeaf extends Node{

	
	private Node leftNode;
	private Node rightNode;
	public String attributeType;
	//private boolean isTraining = false;
	int order = 0; //The order in which they were added. this is used only for populating other lists. It has nothing to do with the actual decision tree.
	List<Boolean> purityValues = new ArrayList<Boolean>(); //this will hold all the true/false values. Purity will be checked later.
	
	public NonLeaf(String attribute, Node left, Node right){
		//isTraining = training;
		attributeType = attribute;
		//this.order = order;	
		this.leftNode = left;
		this.rightNode = right;
		
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
		return rightNode;
	}
	
	/**
	 * Returns the left child node
	 * @return
	 */
	public Node getLeft(){
		return leftNode;
	}
	
	public void report(String indent){
		System.out.format("%s%s = True:\n",indent, attributeType);
		if(leftNode == null){
			System.out.println("left node is null");
		}
		if(rightNode == null){
			System.out.println("right node is null");
		}
		leftNode.report(indent+" ");
		System.out.format("%s%s = False:\n",indent, attributeType);
		rightNode.report(indent+" ");
		}

	
	
	
}
