import structure5.Vector;

public class FakeComponent2{
	private String[] userChoices;
	private int currentIndex;
	private Vector<KeyTriple> valuesReceived;
	
	public FakeComponent2(){
		currentIndex = 0;
	}
	public FakeComponent2(String[] input){
		userChoices = input;
		currentIndex = 0;
		valuesReceived = new Vector<KeyTriple>();
	}
	public void setInput(String[] newInput){
		userChoices = newInput;
		currentIndex = 0;
		valuesReceived = new Vector<KeyTriple>();
	}
	public String userQuery(KeyTriple nodeValue){
		if(currentIndex >= (userChoices.length + 1)){
			return "TERRIBLE USER INPUT";
		}else{
			valuesReceived.add(nodeValue);
			String choice = userChoices[currentIndex];
			currentIndex++;
			return choice;
		}
	}
	/* This does not simulate Component 2's actual functionality,
	 * but will be used for testing.
	 */
	public Vector<KeyTriple> retrieveValues(){
		return valuesReceived;
	}
}
