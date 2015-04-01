package main;

import java.util.List;
import java.util.Set;

public class Main {


	
/**
 * Method which builds the tree	
 * @param instances
 * @param attributes
 */
public void BuildTree(Set instances, List attributes){
	
	if(instances.isEmpty()){
		//Return leaf containing name and probability of most likely class (Baseline Predictor)
	}
	//If Instances are pure, return a leaf node containing the name of the class of the instances in the node and probability 1
	
	if(attributes.isEmpty()){
		//return leaf node containing the name and probability of the majority class of the instances in the node(Choose randomly if classes are equal)
	}
	else{ //find best attribute
		//for each attribute
		  //seperate instances into two sets:
		     //instances for which the attribute is true, and
		     //instances for which the attribute is false
		  //compute purity of each set
		  //if weighted average purity of these sets is best so far
		     //bestAtt = this attribute
		     //bestInstsTrue = set of true instances
		     //bestInstsFalse = set of false instances
		
		//build subtrees using the remaining attributes:
		  //left = Buildtree(bestInstsTrue, attributes - bestAtt)
		  //right = BuildTree(bestInstsTrue, attributes - bestAtt)
		//return node containing(bestAtt, left, right)
	}
	
	
	
	
}

}