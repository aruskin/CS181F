import static org.junit.Assert.*;
import structure5.BinaryTree;

import org.junit.Before;
import org.junit.Test;


public class TreeTraversalTest {

	@Test
	public void testFileToTree() {
		TreeTraversal tester = new TreeTraversal();
		
		//build tree to test equality against
		BinaryTree<KeyTriple> testTree1 = new BinaryTree(new KeyTriple("First question?",
											"left", "leftimg.gif",
											"right", "rightimg.gif"), 
											new BinaryTree(new KeyTriple("Answer A")),
											new BinaryTree(new KeyTriple("Answer B")));
		
		//toString makes sure that we're just testing to see if the trees contain the
		//same values, rather than that the trees are the same object
		assertEquals(testTree1.toString(), tester.fileToTree("src/test2.tree").toString());
	}
}
