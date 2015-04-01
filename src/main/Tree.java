package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Tree {
	List<String> outcomes = new ArrayList<String>();
	List<String> attributes = new ArrayList<String>();
	int numAttributes = 0;
	public Tree(File trainingSetFile){
		buildListFromFile(trainingSetFile);
	}





	public static void main(String[] args){
		new Tree(new File(args[0]));
	}

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

	public void buildListFromFile(File file){
		try {
			Scanner sc = new Scanner(file);
			if(sc.hasNext()){ //Fill the list with the first line
				outcomes.add(sc.next());
				outcomes.add(sc.next());
				if(sc.hasNextLine()){ //Deals with the attribute names

					sc.nextLine(); //Apparantly there is a line of whitespace. The more you know.
					String attributesLine = sc.nextLine(); //Grabs the next line. Still needs to be split into individual attributes.

					Scanner scn = new Scanner(attributesLine);
					while(scn.hasNext()){
						attributes.add(scn.next()); //Gets the line of attributes, splits them, and puts them all in a list.
						numAttributes++;
						System.out.println(attributes.get(numAttributes-1));

					}
					scn.close();//efficiency
				}
			}
			while(sc.hasNext()){
				if(sc.hasNext() && !sc.hasNextBoolean()){ //get information about whether the person lived or died. Only useful with the training set
					//is the start of a new line i.e a new person.
				}
				else if(sc.hasNextBoolean()){
					
				}


			}






		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




}