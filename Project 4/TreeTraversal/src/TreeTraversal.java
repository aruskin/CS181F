import java.io.*;
import structure5.*;

public class TreeTraversal {
	private static FakeComponent2 comp2 = new FakeComponent2();
	
	public void setComponent2Input(String[] newInput){
		comp2.setInput(newInput);
	}
	
	/* Helper method for converting file to binary tree; recursively
	 * creates nodes based on lines from text file.
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
		if(line == null){
			throw new RuntimeException("fileToTree: file parse error");
		}else if(line.startsWith("Q")){
			/* There's an indication that the line is not a leaf,
			 * so return tree with value from this line and call
			 * method again for each of the children
			 */
			String[] subStrings = line.split("@"); //parses line into question and answers
			if(subStrings.length != 5){
				//this is the case where the line either doesn't have all
				//of the necessary information or is incorrectly partitioned
				// by "@" characters
				throw new RuntimeException("fileToTree: file parse error");
			}
			return new BinaryTree<KeyTriple>
			(new KeyTriple(subStrings[0].substring(1), subStrings[1], 
					subStrings[2], subStrings[3], subStrings[4]), 
					readFileHelper(reader), readFileHelper(reader)); 
		
		}else if(line.startsWith("A")){
			/* There's an indication that the line is a leaf, so
			 * return a tree with the value from the line and no
			 * children.
			 */
			return new BinaryTree<KeyTriple>(new KeyTriple(line.substring(1)));
		}else{
			// Indication that input file is in incorrect format.
			throw new RuntimeException("fileToTree: file parse error");
		}
	}
	
	
	/* Method to convert file to binary tree, takes in name of file
	 * to be converted, return completed tree.
	 * @pre filename refers to a valid file
	 * @param filename is the name of the file to be converted
	 * @return the binary tree representing the file
	 */
	public static BinaryTree<KeyTriple> fileToTree(String filename){
		try{
			// readFileHelper will recursively create tree, using BufferedReader
			// to read in input file line by line.
			return readFileHelper(new BufferedReader(new FileReader(filename)));
		}
		catch(IOException e){
			// Error handling for invalid input files.
			throw new RuntimeException("Problems reading file: " + filename);
		}
	}
	
	/*
	 * Method to traverse tree based on user input, takes in a binary tree with
	 * dichotomous key data, returns list of characteristics indicated by the 
	 * user.
	 */
	public static Queue<Association<String, String>> 
		traverse(BinaryTree<KeyTriple> tree){
		
		BinaryTree<KeyTriple> finger = tree; //node we're currently considering
		Boolean done = false; //whether or not we're finished traversing the tree
		Queue<Association<String, String>> characteristicsList =
				new QueueVector<Association<String, String>>();
		if(tree.isEmpty()){ //so there's nothing to traverse
			throw new RuntimeException("traverse: passed an empty tree");
		}
		
		while(!done){ //user has not yet reached leaf or elected to skip
			//send value of current node to Component 2
			KeyTriple currValue = finger.value();
			if(finger.left().isEmpty() || finger.right().isEmpty()){
				Association<String, String> plantChar = 
						new Association("Name", currValue.getQuestion());
				characteristicsList.add(plantChar);
				done = true;
			}else{
				String result = comp2.userQuery(currValue);
				// case where we've hit a leaf

				if(result == "SKIP"){
					done = true;
				}else if(result == "LEFT"){
					Association<String, String> plantChar = new Association(currValue.getQuestion(),
							currValue.getLeftAnswer());
					characteristicsList.add(plantChar);
					finger = finger.left();
					done = finger.isEmpty();
				}else if(result == "RIGHT"){
					Association<String, String> plantChar = new Association(currValue.getQuestion(),
							currValue.getRightAnswer());
					characteristicsList.add(plantChar);
					finger = finger.right();
					done = finger.isEmpty();				
				}
				else{
					throw new RuntimeException("traverse: received invalid user input");
				}
			}
		}
		return characteristicsList;
	}
}
