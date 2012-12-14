import structure5.Association;

/* Provides a structure for the data stored in each node of the 
 * binary tree to allow easier access to each individual part
 * (question, left answer, etc.). This has a private constructor
 * because this component is the only one that should be creating
 * KeyTriples.
 */
public class KeyTriple{
	private String question;
	private Association<String, String> left;
	private Association<String, String> right;
	public KeyTriple(String q, String lftAns, String lftImg, 
				String rgtAns, String rgtImg){
		question = q;
		left = new Association(lftAns, lftImg);
		right = new Association(rgtAns, rgtImg);			
	}
	public KeyTriple(String a){
		question = a;
		left = new Association("","");
		right = new Association("","");
	}
}
