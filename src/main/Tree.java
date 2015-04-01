package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Tree {
	public  Node decisionTreeRoot;
	public  List<String> categories = new ArrayList<String>(); //This program assumes categories will be binary (I.e it has only two possibilities)
	public  List<String> attributesList = new ArrayList<String>(); 
	public  List<Instance> people = new ArrayList<Instance>();
	public String majorityCategory;

	public  int numAttributes = 0;
	public Tree(File trainingSetFile){
		buildTrainingSetFromFile(trainingSetFile);

		baseLineClassifier();

		decisionTreeRoot = buildTree(people, attributesList);
		System.out.println("");
		decisionTreeRoot.report("");
		
		//buildTestSetFromFile(testSetFile); not implemented
	}





	public static void main(String[] args){
		new Tree(new File(args[0]));
	}

	// identify the most frequency class and its probability
	private void baseLineClassifier(){
		int [] counts = new int[categories.size()];

		for(Instance i : people)
			counts[categories.indexOf(i.getCategory())]++;

		int max = 0;
		for(int i = 0; i < counts.length; i++){
			if(counts[i] > max){
				max = counts[i];
				majorityCategory = categories.get(i);
			}
		}

	}





	/**
	 * Method which builds the tree	
	 * @param instances
	 * @param attributesList
	 */
	public  Node buildTree(List<Instance> instances, List<String> attributes){
		//Set is pure enough, so print leaves
		if(instances.isEmpty()){

			return buildLeaf(people);//Return leaf containing name and probability of most likely class (Baseline Predictor)

		}
		if(pureCheck(instances)){//If Instances are pure, return a leaf node containing the name of the class of the instances in the node and probability 1

			return new Leaf(instances.get(0).getCategory(), 1); //gets the category of the first instance in the list(Since its pure, can just grab any instance out)
		}
		if(attributes.isEmpty()){
			return buildLeaf(instances);//return leaf node containing the name and probability of the majority class of the instances in the node(Choose randomly if classes are equal)
		}
		//Set is not pure enough, so construct subsets of instances in the subnodes, then compute average purity
		else{ 
			String bestAttribute = null;//find best attribute

			//for each attribute
			List<Instance> bestTrue = null;//seperate instances into two sets:
			List<Instance> bestFalse = null;

			double trackPurity = 1;//Set initial best purity as 1 (100%). This will keep track of the best purity as the algorithm goes down
			for(String attribute: attributes){
				List<Instance> attTrue = new ArrayList<Instance>();//instances for which the attribute is true, and
				List<Instance> attFalse = new ArrayList<Instance>();//instances for which the attribute is false
				for(Instance i : instances){
					if(i.getValues().get(attributes.indexOf(attribute)) == true){
						attTrue.add(i);
					}
					else{
						attFalse.add(i);
					}
				}
				System.out.println("trackPurity = " + trackPurity);
				double averagePurity = computePurity(attTrue, attFalse);//compute purity of each set
				if(trackPurity > averagePurity){//if weighted average purity of these sets is best so far
					trackPurity = averagePurity;
					bestAttribute = attribute;//bestAtt = this attribute
					bestTrue = attTrue;//bestInstsTrue = set of true instances
					bestFalse = attFalse;//bestInstsFalse = set of false instances


				}
			}
			List<String> minusBestAtt = new ArrayList<String>();//build subtrees using the remaining attributes: i.e add the attribute subtrees minus the best attribute
			for(int i=0; i<attributes.size();i++){ //loop through the list of attributes and make a new temp list that does not contain the best attribute
				if(!attributes.get(i).equals(bestAttribute)){
					minusBestAtt.add(attributes.get(i));
				}

			}


			Node left = buildTree(bestTrue, minusBestAtt);//left = Buildtree(bestInstsTrue, attributes - bestAtt)
			Node right = buildTree(bestFalse, minusBestAtt);//right = BuildTree(bestInstsTrue, attributes - bestAtt)
			
			return new NonLeaf(bestAttribute, left, right);//return node containing(bestAtt, left, right)
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
						attributesList.add(scn.next()); //Gets the line of attributes, splits them, and puts them all in a list.
						numAttributes++;
						System.out.println(attributesList.get(numAttributes-1));

					}
					scn.close();//efficiency
				}
			}

			//Deals with the data for each person (Instances)
			while(sc.hasNextLine() && sc.hasNext()){
				//System.out.println("Gets Inside the while loop");
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
					else{
						break;
					}


				}
				people.add(new Instance(tempValue, tempList)); //Add new person to the list of people
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
		

	}
























	/**
	 * Returns true iff the list of instances is pure
	 * Else returns false
	 * @param instance
	 * @return
	 */
	public boolean pureCheck(List<Instance> instance){
		String category = instance.get(0).getCategory();

		for(Instance i : instance){
			if(!i.getCategory().equals(category)){
				return false;
			}
		}
		return true;

	}

	/**
	 * Computes the weighted purity of the two input sets (Unstructured Subtrees)
	 * Impurity Formula: P(A)P(B) = ((m/(m+n)) * (n/(m+n)))
	 * m = number of A's, n = number of B's
	 */
	public double computePurity(List<Instance> left, List<Instance> right){
		double totalInstances = left.size() + right.size();//First figure out how many instances of person there are total
		String category = categories.get(0);//get one of the two category names
		double leftSize = left.size();
		double rightSize = right.size();
		int mLeft = 0;
		int nLeft = 0;
		int mRight = 0;
		int nRight = 0;
		//now figure out how many of the category are correct for each tree
		//left subtree
		for(Instance i : left){
			if(i.getCategory().equals(category)){
				mLeft++;
			}
		}
		//right subtree
		for(Instance z : right){
			if(z.getCategory().equals(category)){
				mRight++;
			}
		}
		System.out.println("mLeft = " + mLeft + "mRight = " + mRight);
		//now we know how many instances are one category. The other category is the absolute value of totalInstances - (leftVote+rightVote)
		nLeft = left.size() - mLeft; //Any left over that arent classified as the category here, are the other category.
		nRight = right.size() - mRight;

		//Time for the impurity formula. Low impurity means high purity.
		double leftImpurity = leftSize > 0 ?(mLeft/leftSize) * ((leftSize-mLeft)/leftSize) : 0;
		double rightImpurity = rightSize > 0 ? (mRight/rightSize) * ((rightSize-mRight)/rightSize) : 0;

		//Now to get the weighted impurities of the nodes
		// Weighted average impurity of subnodes = SUM(P(node) * impurity(node))
		double purity = leftImpurity*(leftSize/totalInstances) + rightImpurity *(rightSize/totalInstances);

		return purity;

	}


	/**
	 * Method which returns the leaf with the majority category from the list of instances
	 * @param leafInstances
	 * @return Leaf
	 */
	public Leaf buildLeaf(List<Instance> leafInstances){
		//We're assuming there are always going to be only two categories in this program. A third category would add another level of complexity
		int category1 = 0; //votes for the first category (Index 0 in the list of categories)
		int category2 = 0; //votes for the second category (Index 1 in the list of categories)
		for(Instance c: people){
			if(c.getCategory().equals(categories.get(0))){
				category1++;
			}
			else{
				category2++;
			}
		}
		String majority;
		int highest;
		//if there is a tie: we'll choose a node at random. In this case, pseudorandom considering it wont matter which is returned, we'll return the first one
		if(category1 == category2){
			majority = categories.get(0); //Was a bit lazy, instead of true randomness, am just taking the first category if its a tie.
			highest = category1;
		}
		else if(category1 > category2){
			majority = categories.get(0);
			highest = category1;
		}
		else{
			majority = categories.get(1);
			highest = category2;
		}


		return new Leaf(majority, (double)(highest/leafInstances.size()));

	}


}