import structure5.*;

// This class shows off the basic functionality of this component.

public class Demo {
	
	public static void main(String[] args){
		//create instance of TreeTraversal class
		TreeTraversal demo = new TreeTraversal();
		
		//converts a dichotomous key file to tree
		BinaryTree<KeyTriple> tree = demo.fileToTree("src/test02.tree");
		
		//sets up "user input" (would get this from Comp 2 if integrated)
		String[] userInput = {"LEFT", "RIGHT", "LEFT"};
		demo.setComponent2Input(userInput);
		
		// the list of characteristics compiled by the traversal
		// (would be passed to Component 3)
		Queue<Association<String, String>> results = demo.traverse(tree);
		
		System.out.println(results.toString());
		
		// same thing with different "user input"
		String[] userInput2 = {"RIGHT", "SKIP"};
		demo.setComponent2Input(userInput2);
		
		results = demo.traverse(tree);
		
		System.out.println(results.toString());			
	}

}
