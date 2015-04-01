package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Node in the decision tree
 * @author Jarred Hone
 *
 */
public class NonLeaf extends Node{

	private String attributeType;
	private boolean isTraining = false;
	int order = 0; //The order in which they were added. this is used only for populating other lists. It has nothing to do with the actual decision tree.
	List<Boolean> purityValues = new ArrayList<Boolean>(); //this will hold all the true/false values. Purity will be checked later.
	
	public NonLeaf(String attribute, int order, boolean training){
		isTraining = training;
		attributeType = attribute;
		this.order = order;	
		
	}

	@Override
	boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
	
}
