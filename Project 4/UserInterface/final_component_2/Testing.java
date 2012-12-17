
/*
 * This is the testing class for component 2.
 * If the main method is run without any assert statements
 * throwing a failure message, then the tests have all passed.
 * The GUI windows pop up briefly during the testing portion
 * but are closed after the tests are run.
 * Each of the test methods runs a single test case from the testing
 * plan.
 */

public class Testing {
	
	/*
	 * Test that the GUI window's buttons and labels created match
	 * the inputs it was given for the following:
	 * question, left answer, left image, right answer, right image
	 * In addition, test that the window size is correct.
	 * There is no need to check the button locations within the window because
	 * the JFrame is implemented with the GridBagLayout, which automatically
	 * arranges the buttons and labels based on the space provided and their
	 * text sizes.
	 */
	public void test1() {
		
		String quest = "This is the question.";
		String leftAns = "This is the left answer.";
		String rightAns = "This is the right answer.";
		String leftPic = "NOTHING";
		String rightPic = "NOTHING";
		
		KeyTriple test1 = new KeyTriple(quest, leftAns, leftPic, rightAns, rightPic);
		UserInterface GUI = new UserInterface(test1);		
		
		// Check the button and label texts, and the images
		assert(GUI.getQuestion() == quest);
		assert(GUI.getLeftAnswer() == leftAns);
		assert(GUI.getRightAnswer() == rightAns);
		assert(GUI.getLeftPicture() == "default.png");
		assert(GUI.getRightPicture() == "default.png");
		
		// Check window size
		assert(GUI.getWidth() == 500);
		assert(GUI.getHeight() == 400);

	}
	
	
	/*
	 * Tests that a left button click from the user passes the correct information
	 * to the rest of the component and component 1.
	 */
	public void test2() {
		
		Placeholder ph = new Placeholder();
		
		KeyTriple test1 = new KeyTriple("Question", "Left", "left.png", "Right", "right.png");
		UserInterface GUI = new UserInterface(test1);

		GUI.leftClick();
		ph.setNext(GUI.getNextNode());
		
		assert(GUI.getNextNode() == "LEFT");
		assert(ph.getNext() == "LEFT");
		
	}
	
	/*
	 * Tests that a right button click from the user passes the correct information
	 * to the rest of the component and component 1.
	 */
	public void test3() {
		
		Placeholder ph = new Placeholder();
		
		KeyTriple test1 = new KeyTriple("Question", "Left", "left.png", "Right", "right.png");
		UserInterface GUI = new UserInterface(test1);

		GUI.rightClick();
		ph.setNext(GUI.getNextNode());
		
		assert(GUI.getNextNode() == "RIGHT");
		assert(ph.getNext() == "RIGHT");
	
	}
	
	/*
	 * Tests that a skip button click from the user passes the correct information
	 * to the rest of the component and component 1.
	 */
	public void test4() {
		
		Placeholder ph = new Placeholder();
		
		KeyTriple test1 = new KeyTriple("Question", "Left", "left.png", "Right", "right.png");
		UserInterface GUI = new UserInterface(test1);

		GUI.skipClick();
		ph.setNext(GUI.getNextNode());
		
		assert(GUI.getNextNode() == "SKIP");
		assert(ph.getNext() == "SKIP");
	}
	
	/*
	 * Closes the GUI window after testing is done.
	 *
	 */
	public void close() {
		KeyTriple test1 = new KeyTriple("Question", "Left", "left.png", "Right", "right.png");
		UserInterface GUI = new UserInterface(test1);
		
		GUI.closeWindow();
	}
			
	
	/*
	 * Runs all of the test methods.
	 * If completed without any thrown exceptions, all the tests 
	 * have passed.
	 */
	public static void main(String[] args) {
		
		Testing test = new Testing();
		test.test1();
		System.out.println("Passed test 1.");
		
		test = new Testing();
		test.test2();
		System.out.println("Passed test 2.");
		
		test = new Testing();
		test.test3();
		System.out.println("Passed test 3.");
		
		test = new Testing();
		test.test4();
		System.out.println("Passed test 4.");
		
		test.close();
		
		System.out.println("All tests passed!");
		
		
	}

}
