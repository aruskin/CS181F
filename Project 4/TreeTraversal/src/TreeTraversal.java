import java.io.*;
import structure5.*;

/* The purpose of this module is to:
 * 1) Create a binary tree representation of the dichotomous key
 * by reading in a text representation of the key.
 * 2) Provide a method for traversing the tree based on the user's
 * input and returning list of characteristics of the plant compiled
 * during the traversal.
 */

public class TreeTraversal {
	//Placeholder for the actual Component 2
	private static FakeComponent2 comp2 = new FakeComponent2();
	
	/* This method should not exist after integration, but is
	 * necessary to simulate Component 2 and run tests. Changes
	 * the placeholder Component 2's "user input" to newInput.
	 */
	public void setComponent2Input(String[] newInput){
		comp2.setInput(newInput);
	}
	
	/* This method also shouldn't exist after integration.
	 * Necessary to test that we're sending the correct
	 * information to Component 2 in the traverse method.
	 */
	public Vector<KeyTriple> getOutputToComponent2(){
		return comp2.retrieveValues();
	}
	
	/* Helper method for converting file to binary tree; recursively
	 * creates nodes based on lines from text file. BufferedReader is
	 * a class that allows efficient reading of lines from a stream.
	 */
	private static BinaryTree<KeyTriple> readFileHelper(BufferedReader reader){
		
		//stores the current line of the file
		String line = null;
		
		try{
			//reader reads in line from the file
			line = reader.readLine();
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		
		if(line == null){//file is empty at this point and shouldn't be
			throw new RuntimeException("fileToTree: file parse error");
		}
		// there's an indication that the line is not a leaf,
		// so return tree with value from this line and call
		// method again for each of the children
		else if(line.startsWith("Q")){
			
			//divides line into question and answers
			String[] subStrings = line.split("@"); 
			
			//this is the case where the line either doesn't have all
			//of the necessary information or is incorrectly partitioned
			// by "@" characters
			if(subStrings.length != 5){
				throw new RuntimeException("fileToTree: file parse error");
			}
			return new BinaryTree<KeyTriple>
							(new KeyTriple(subStrings[0].substring(1),
									subStrings[1], subStrings[2], 
									subStrings[3], subStrings[4]), 
							readFileHelper(reader), readFileHelper(reader)); 
		
		}
		// there's an indication that the line is a leaf, so
		// return a tree with the value from the line and no
		// children.
		else if(line.startsWith("A")){
			return new BinaryTree<KeyTriple>(new KeyTriple(line.substring(1)));
		}
		else{// Indication that input file is in incorrect format.
			throw new RuntimeException("fileToTree: file parse error");
		}
	}
	
	
	/* Method to convert file to binary tree, takes in name of file
	 * to be converted, returns completed tree.
	 */
	public static BinaryTree<KeyTriple> fileToTree(String filename){
		try{
			// readFileHelper will recursively create tree, using BufferedReader
			// to read in input file line by line.
			return readFileHelper(new BufferedReader(new FileReader(filename)));
		}
		catch(IOException e){// Error handling for invalid input files.
			throw new RuntimeException("Problems reading file: " + filename);
		}
	}
	
	/* Method to traverse tree based on user input, takes in a binary tree with
	 * dichotomous key data, returns list of characteristics indicated by the 
	 * user.
	 */
	public static Queue<Association<String, String>> 
		traverse(BinaryTree<KeyTriple> tree){
		
		BinaryTree<KeyTriple> finger = tree; //node we're currently considering
		
		Boolean done = false; //whether or not we're finished traversing the tree
		
		// list of characteristics of plant which will be used by Component 3
		// to search the database
		Queue<Association<String, String>> characteristicsList =
				new QueueVector<Association<String, String>>();
		
		if(tree.isEmpty()){ //so there's nothing to traverse
			throw new RuntimeException("traverse: passed an empty tree");
		}
		while(!done){ //user has not yet reached leaf or elected to skip
			
			KeyTriple currValue = finger.value();
			
			//case where we've reached a leaf; no question for Comp 2 to display
			if(finger.left().isEmpty() || finger.right().isEmpty()){
				
				Association<String, String> plantChar = 
						new Association<String, String>
								("Name", currValue.getQuestion());
				characteristicsList.add(plantChar);
				
				done = true; //nowhere else to traverse
			}else{
				//need to send node value to Component 2 to display
				//userQuery returns the user selection (LEFT, RIGHT, or SKIP)
				String result = comp2.userQuery(currValue);

				if(result == "SKIP"){
					done = true;
				}else if(result == "LEFT"){
					Association<String, String> plantChar = 
							new Association<String, String>
									(currValue.getQuestion(),
									currValue.getLeftAnswer());
					characteristicsList.add(plantChar);
					
					finger = finger.left(); //go down left branch
				}else if(result == "RIGHT"){
					Association<String, String> plantChar = 
							new Association<String, String>
									(currValue.getQuestion(),
									currValue.getRightAnswer());
					characteristicsList.add(plantChar);
					
					finger = finger.right(); //go down right branch			
				}else{ //received something other than LEFT, RIGHT, SKIP
					throw new RuntimeException
									("traverse: received invalid user input");
				}
			}
		}
		return characteristicsList;
	}
}
