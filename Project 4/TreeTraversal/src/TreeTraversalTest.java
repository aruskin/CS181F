import static org.junit.Assert.*;
import structure5.BinaryTree;

import org.junit.Test;


public class TreeTraversalTest {
	private TreeTraversal tester;
	
	public void setUp(){
		tester = new TreeTraversal();
	}

	@Test
	/* 1. Converting (correctly formatted) text input to binary tree
	 * Requirement: Must read in text file representation of binary tree
	 * in a specific format and correctly convert it into a binary tree.
	 */
	public void testFileToTreeGoodInput() {
		
		//build simple tree with two leaves to test equivalency against
		BinaryTree<KeyTriple> testTree1 = new BinaryTree(new KeyTriple("First question?",
											"left", "leftimg.gif",
											"right", "rightimg.gif"), 
											new BinaryTree(new KeyTriple("Answer A")),
											new BinaryTree(new KeyTriple("Answer B")));
		
		//toString makes sure that we're just testing to see if the trees contain the
		//same values, rather than that the trees are the same object
		assertEquals(testTree1.toString(), tester.fileToTree("src/test01.tree").toString());
		
		//build slightly more complicated tree to test equivalency against
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
		
		//build tree with only one leaf to test equivalency
		BinaryTree<KeyTriple> testTree3 = new BinaryTree(new KeyTriple("Answer A"));
		
		assertEquals(testTree3.toString(), tester.fileToTree("src/test03.tree").toString());
	}
	
	@Test
	/* 2. Handling incorrectly formatted text input
	 * Requirement: In the event that the component receives invalid input for
	 * the representation of the dichotomous key, return an error message.
	 */
	public void testFileToTreeBadInput(){
		try{
			//test input has a question but no answers
			tester.fileToTree("src/test04.tree");
			fail("Should have thrown an exception");
		}catch(RuntimeException e){
			assertEquals("fileToTree: file parse error", e.getMessage());
		}try{
			//clearly incorrect test input
			tester.fileToTree("src/test05.tree");
			fail("Should have thrown an exception");
		}catch(RuntimeException e){
			assertEquals("fileToTree: file parse error", e.getMessage());
		}try{
			//more subtly incorrect test input
			tester.fileToTree("src/test06.tree");
			fail("Should have thrown an exception");
		}catch(RuntimeException e){
			assertEquals("fileToTree: file parse error", e.getMessage());
		}	
	}
	
	@Test
	/*
	 * 8. Non-existent file passed in as parameter to fileToTree method
	 * Requirement: Only valid files should be used as input; if an invalid
	 * file name is passed in, we should return an error message.
	 */
	public void testFileToTreeNonexistentInput(){
		try{
			tester.fileToTree("not_a_real_file.txt");
			fail("Should have thrown an exception");
		}catch(RuntimeException e){
			assertEquals("Problems reading file: not_a_real_file.txt",
					e.getMessage());
		}
	}
}
