import structure5.Vector;

public class FakeComponent2{
	//representation of the user's input at each step
	private String[] userChoices;
	
	//where in the array of input we are
	private int currentIndex;
	
	//list of node values received from traversal;
	//used for testing correctness of output from traversal
	private Vector<KeyTriple> valuesReceived;
	
	//create instance of component
	public FakeComponent2(){
		currentIndex = 0;
	}

	//change the "user input" to newInput and reset index and
	// list of values
	public void setInput(String[] newInput){
		userChoices = newInput;
		currentIndex = 0;
		valuesReceived = new Vector<KeyTriple>();
	}
	
	// This simulates the main interaction between the
	// two components. TreeTraversal will pass Component 2
	// the value of the node it's currently on, and Component 2
	// will return the user's choice for which branch to go down.
	public String userQuery(KeyTriple nodeValue){
		//avoid array access issues; not relevant to real Component 2
		if(currentIndex >= (userChoices.length + 1)){
			return "TERRIBLE USER INPUT";
		}else{
			valuesReceived.add(nodeValue);
			String choice = userChoices[currentIndex];
			currentIndex++;
			return choice;
		}
	}
	
	// This does not simulate Component 2's actual functionality,
	// but will be used for testing. Returns list of values received
	// from TreeTraversal.
	public Vector<KeyTriple> retrieveValues(){
		return valuesReceived;
	}
}
