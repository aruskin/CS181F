
/*
 * This class is a simulated placeholder for component 1.
 * It contains none of the tree-traversal functionality, but
 * simply has the method which sets its next traversal method
 * based on input from component 2. 
 */

public final class Placeholder {
	
	private String next;
	
	/*
	 * To be called by component 2.
	 * Takes in the user's input based on their button press and 
	 * sets the tree traversal's next direction accordingly.
	 */
	public void setNext(String S) {
		next = S;
	}
	
	/*
	 * THE FOLLOWING METHOD IS FOR TESTING PURPOSES ONLY.
	 * It is written so the testing class can assert that the
	 * next node to traverse matches the user's input that was
	 * passed.
	 */
	public String getNext() {
		return next;
	}

}
