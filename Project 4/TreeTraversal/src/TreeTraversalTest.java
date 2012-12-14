import static org.junit.Assert.*;
import structure5.BinaryTree;

import org.junit.Before;
import org.junit.Test;


public class TreeTraversalTest {

	@Test
	//Converting (correctly formatted) text input to binary tree
	public void testFileToTree() {
		TreeTraversal tester = new TreeTraversal();
		
		//build simple tree to test equality against
		BinaryTree<KeyTriple> testTree1 = new BinaryTree(new KeyTriple("First question?",
											"left", "leftimg.gif",
											"right", "rightimg.gif"), 
											new BinaryTree(new KeyTriple("Answer A")),
											new BinaryTree(new KeyTriple("Answer B")));
		
		//toString makes sure that we're just testing to see if the trees contain the
		//same values, rather than that the trees are the same object
		assertEquals(testTree1.toString(), tester.fileToTree("src/test01.tree").toString());
		
		//build slightly more complicated tree to test equality against
		BinaryTree<KeyTriple> right = new BinaryTree(new KeyTriple("Second right question?", 
										"left 2r", "leftimg2r.gif",
										"right 2r", "rightimg2r.gif"),
										new BinaryTree(new KeyTriple("Answer D")),
										new BinaryTree(new KeyTriple("Answer E")));
		
		BinaryTree<KeyTriple> lright =  new BinaryTree(new KeyTriple("Third lr question?", 
											"left 3lr", "leftimg3lr.gif",
											"right 3lr", "rightimg3lr.gif"),
											new BinaryTree(new KeyTriple("Answer B")),
											new BinaryTree(new KeyTriple("Answer C")));
		
		BinaryTree<KeyTriple> left = new BinaryTree(new KeyTriple("Second left question?", 
										"left 2l", "leftimg2l.gif",
										"right 2l", "rightimg2l.gif"),
										new BinaryTree(new KeyTriple("Answer A")),
										lright);
		BinaryTree<KeyTriple> testTree2 = new BinaryTree(new KeyTriple("First question?",
											"left 1", "leftimg1.gif", 
											"right 1", "rightimg1.gif"),
											left, right);
		
		assertEquals(testTree2.toString(), tester.fileToTree("src/test02.tree").toString());
	}
}
