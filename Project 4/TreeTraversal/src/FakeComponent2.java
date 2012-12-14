
public class FakeComponent2{
	private String[] userChoices;
	private int currentIndex;
	public FakeComponent2(String[] input){
		userChoices = input;
		currentIndex = 0;
	}
	public String userQuery(KeyTriple nodeValue){
		if(currentIndex >= (userChoices.length + 1)){
			return "TERRIBLE USER INPUT";
		}else{
			String choice = userChoices[currentIndex];
			currentIndex++;
			return choice;
		}
	}	
}