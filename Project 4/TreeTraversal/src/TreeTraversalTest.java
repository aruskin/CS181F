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
											"left 1", "leftimg1.gif",
											"right 1", "rightimg2.gif"));
		testTree1.setLeft(new BinaryTree(new KeyTriple("Answer A")));
		testTree1.setRight(new BinaryTree(new KeyTriple("Answer B")));
	
		assertEquals(testTree1, tester.fileToTree("src/test2.tree"));
	}
}
