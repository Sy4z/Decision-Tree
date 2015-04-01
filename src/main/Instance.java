package main;

import java.util.ArrayList;
import java.util.List;
/**
 * This class represents a single instance on the dataset, in this case it is a person
 * @author Jarred Hone
 *
 */
public class Instance {

	String category;
	private List<Boolean> values = new ArrayList<Boolean>();



	public Instance(String category, List<Boolean> values){
		this.category =  category;
		this.values = values;

	}


	/**
	 * Returns the list of true/false values for outside access
	 * @return
	 */
	public List<Boolean> getValues(){
		return this.values;
	}

	/**
	 * Getter to return private field category
	 * @return String category
	 */
	public String getCategory(){
		return category;
	}


}
