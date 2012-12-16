import structure5.Association;

/* Provides a structure for the data stored in each node of the 
 * binary tree to allow easier access to each individual part
 * (question, left answer, etc.). This has a private constructor
 * because this component is the only one that should be creating
 * KeyTriples.
 */
public class KeyTriple{
	//Question at this node in the tree
	private String question;
	//Key will be answer for left branch, 
	//value will be image file associated w/ answer
	private Association<String, String> left;
	//Key will be answer for right branch,
	//value will be image file associated w/ answer
	private Association<String, String> right;
	
	//Creating an instance of the class using all of the values
	public KeyTriple(String q, String lftAns, String lftImg, 
				String rgtAns, String rgtImg){
		question = q;
		left = new Association(lftAns, lftImg);
		right = new Association(rgtAns, rgtImg);			
	}
	
	//Creating an instance of the class for a leaf of the tree
	// so the question will actually be the name of a plant
	public KeyTriple(String a){
		question = a;
		left = new Association("NOTHING","NOTHING");
		right = new Association("NOTHING","NOTHING");
	}
	
	//Accessor methods for all of the values
	public String getQuestion(){
		return question;
	}
	public String getLeftAnswer(){
		return left.getKey();
	}
	public String getLeftImage(){
		return left.getValue();
	}
	public String getRightAnswer(){
		return right.getKey();
	}
	public String getRightImage(){
		return right.getValue();
	}
	
	//Returns string representation of a KeyTriple
	public String toString(){
		return "(" + question + "," + left.toString() + "," + right.toString() + ")";
	}
}
