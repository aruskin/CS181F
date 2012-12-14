import static org.junit.Assert.*;
import structure5.*;

import org.junit.Test;
import org.junit.Before;


public class TreeTraversalTest {
	private TreeTraversal tester; //instance of TreeTraversal class to run tests on
	private BinaryTree<KeyTriple> fakeKey; //tree representation of key that we will
										   // use for several of the tests
	
	@Before
	public void setUp(){
		tester = new TreeTraversal();
		
		//build tree to use as our input for traversal tests
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
		
		fakeKey = new BinaryTree(new KeyTriple("First question?",
					"left 1", "leftimg1.gif", 
					"right 1", "rightimg1.gif"),
					left, right);
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
		
		//a slightly more complicated tree to test equivalency against
		BinaryTree<KeyTriple> testTree2 = fakeKey;
		
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
	 * 3. Traversing the tree (stopping at leaves)
	 * Requirement: Must correctly traverse tree based on user input and
	 * stop traversing when it hits a leaf. The returned list must contain
	 * the correct information and be in the correct format to be used by
	 * Component 3.
	 */
	public void testTraverseStopAtLeaves(){
		String[] testInput1 = {"RIGHT", "RIGHT"}; //simulating user input
		tester.setComponent2Input(testInput1);
		
		//the expected list of characteristics, given testTree and testInput1
		Queue<Association<String, String>> resultsList1 = 
						new QueueVector<Association<String, String>>();
		resultsList1.add(new Association
					("First question?", "right 1"));
		resultsList1.add(new Association
					("Second right question?", "right 2r"));
		resultsList1.add(new Association
					("Name", "Answer E"));

		assertEquals(resultsList1.toString(), 
				tester.traverse(fakeKey).toString());
		
		
		String[] testInput2 = {"LEFT", "RIGHT", "LEFT"};
		tester.setComponent2Input(testInput2);
		
		Queue<Association<String, String>> resultsList2 = 
				new QueueVector<Association<String, String>>();
		resultsList2.add(new Association("First question?", "left 1"));
		resultsList2.add(new Association("Second left question?", "right 2l"));
		resultsList2.add(new Association("Third lr question?", "left 3lr"));
		resultsList2.add(new Association("Name", "Answer B"));
		
		assertEquals(resultsList2.toString(), 
				tester.traverse(fakeKey).toString());		
	}
	
	@Test
	/* 4. Traversing the tree (skipping)
	 * Requirement: Must correctly traverse tree based on user input 
	 * and stop traversing when the user has elected to skip. The returned
	 * must contain the correct information and be in the correct format
	 * to be used by Component 3.
	 */
	public void testTraverseSkipping(){
		String[] testInput1 = {"LEFT", "SKIP"};
		tester.setComponent2Input(testInput1);
		Queue<Association<String, String>> resultsList1 = 
				new QueueVector<Association<String, String>>();
		resultsList1.add(new Association("First question?", "left 1"));
		
		assertEquals(resultsList1.toString(),
				tester.traverse(fakeKey).toString());		
	}
	
	@Test
	/* 5. Handling unexpected input from Component 2
	 * 	Requirement: In the event that Component 2 sends anything other than
	 * LEFT, RIGHT, or SKIP, return an error message.
	 */
	public void testTraverseBadInput(){
		try{
			String[] testInput1 = {"LEFT", "YES", "RIGHT"};
			tester.setComponent2Input(testInput1);
			tester.traverse(fakeKey);
			fail("Should have thrown an exception");
		}catch(RuntimeException e){
			assertEquals("traverse: received invalid user input",
					e.getMessage());
		}
	}
	
	@Test
	/* 6. Passing correct information to Component 2
	 * Requirement: Must be able to send value to the display component
	 * at each node.
	 */
	public void testTraverseOutputToComponent2(){
		String[] testInput1 = {"RIGHT", "RIGHT"};
		tester.setComponent2Input(testInput1);
		Vector<KeyTriple> valuesSent = new Vector<KeyTriple>();
		valuesSent.add(new KeyTriple("First question?",
									"left 1", "leftimg1.gif",
									"right 1", "rightimg1.gif"));
		valuesSent.add(new KeyTriple("Second right question?",
									"left 2r", "leftimg2r.gif",
									"right 2r", "rightimg2r.gif"));
		assertEquals(valuesSent, tester.getOutputToComponent2());
		
	}
	
	@Test
	/* 7. Accessor methods for KeyTriple work correctly
	 * Requirement: The accessor methods for KeyTriple should return the 
	 * correct types and correct elements of the class.
	 */
	public void testKeyTriple(){
		KeyTriple testKey1 = new KeyTriple("question", "leftAns", "leftImg",
											"rightAns", "rightImg");
		assertEquals("question", testKey1.getQuestion());
		assertEquals("leftAns", testKey1.getLeftAnswer());
		assertEquals("leftImg", testKey1.getLeftImage());
		assertEquals("rightAns", testKey1.getRightAnswer());
		assertEquals("rightImg", testKey1.getRightImage());
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
