import java.io.*;
import structure5.BinaryTree;
import structure5.Association;

public class TreeTraversal {
	
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
			throw new RuntimeException("Problems reading file: " + filename +"\n" + e);
		}
	}
}
