package main;

public class Leaf extends Node {

	
	private String category;
	private double probability;
	
	public Leaf(String category, double prob){
		this.category = category;
		this.probability = prob;
	}
	
	/**
	 * Returns what attribute this node is.
	 * 
	 * @return String
	 */
	public String getCategory(){
		return this.category;
	}
	
	/**
	 * Returns true for this. This method was taken from the abstract class node
	 * It is needed because the algorithm will only distinguish nodes, and this is the only way for it to tell leaves, and normal nodes apart
	 */
	public boolean isLeaf() {
		return true;
	}

}
