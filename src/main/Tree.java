package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Tree {
	List<String> categories = new ArrayList<String>();
	List<String> attributes = new ArrayList<String>();
	List<Person> people = new ArrayList<Person>();
	int numAttributes = 0;
	public Tree(File trainingSetFile){
		buildTrainingSetFromFile(trainingSetFile);
	}





	public static void main(String[] args){
		new Tree(new File(args[0]));
	}

	/**
	 * Method which builds the tree	
	 * @param instances
	 * @param attributes
	 */
	public Node BuildTree(List<Person> instances, List<String> attributes){
		//Set is pure enough, so print leaves
		if(instances.isEmpty()){
			//Return leaf containing name and probability of most likely class (Baseline Predictor)
		}
		if(pureCheck(instances)){//If Instances are pure, return a leaf node containing the name of the class of the instances in the node and probability 1
		return new Leaf(instances.get(0).getCategory(), 1); //gets the category of the first instance in the list(Since its pure, can just grab any instance out)
		}
		if(attributes.isEmpty()){
			//return leaf node containing the name and probability of the majority class of the instances in the node(Choose randomly if classes are equal)
		}
		//Set is not pure enough, so construct subsets of instances in the subnodes, then compute average purity
		else{ 
			String bestAttribute = null;//find best attribute

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

	public void buildTrainingSetFromFile(File trainingFile){
		try {
			Scanner sc = new Scanner(trainingFile);
			if(sc.hasNext()){ //Fill the list with the first line
				categories.add(sc.next());
				categories.add(sc.next());
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

			//Deals with the data for each person (Instances)
			while(sc.hasNextLine()){
				List<Boolean> tempList = new ArrayList<Boolean>();
				String tempValue = null;
				if(sc.hasNext() && !sc.hasNextBoolean()){ //get information about whether the person lived or died. Only useful with the training set
					//is the start of a new line i.e a new person.
					tempValue = sc.next();
				}
				while(sc.hasNext()){
					if(sc.hasNextBoolean()){
						String saveBoolean;
						saveBoolean = sc.next();
						if(saveBoolean.equals("true")){
							tempList.add(true);
						}
						else{
							tempList.add(false);
						}
					}
					people.add(new Person(tempValue, tempList)); //Add new person to the list of people
				}

			}





			sc.close();//efficiency
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Builds the values for the test set from the file
	 */
	public void buildTestSetFromFile(File testFile){

		try {
			Scanner sc = new Scanner(testFile);
			//dont need the first two pieces















		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Leaf buildLeaf(){
		return null;

	}

	/**
	 * Returns true iff the list of instances is pure
	 * Else returns false
	 * @param instance
	 * @return
	 */
	public boolean pureCheck(List<Person> instance){
		String category = instance.get(0).getCategory();

		for(Person i : instance){
			if(!i.getCategory().equals(category)){
				return false;
			}
		}
		return true;

	}
	
	/**
	 * Computes the weighted purity of the two input sets.
	 * Impurity Formula: P(A)P(B) = ((m/(m+n)) * (n/(m+n)))
	 * m = number of A's, n = number of B's
	 */
	public double computePurity(List<Person> left, List<Person> right){
		
		
		
	}


}