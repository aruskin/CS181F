import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

public class UserInterface extends JFrame implements ActionListener {
	
	// Buttons and labels to display in the window
	private JButton leftButton, rightButton, skipButton;
	private JLabel questionText, leftImageLabel, rightImageLabel;
	
	// Information passed to and from component 1
	private String question;
	private String leftAnswer, rightAnswer;
	private String leftPicture, rightPicture;
	private String nextNode;
	
	// Button identifiers
	private static final String ACTION_LEFT = "LEFT";
	private static final String ACTION_RIGHT = "RIGHT";
	private static final String ACTION_SKIP = "SKIP";
	
	
	/* This method creates the GUI window with the 
	 * correct display values based on the values held
	 * in the variables of the class. It sets up the JFrame,
	 * adds buttons and images, and then displays it to the 
	 * user.
	 */
	public UserInterface(KeyTriple x) {
		
		// Set up button and label values
		question = x.getQuestion();		
		leftAnswer = x.getLeftAnswer();
		rightAnswer = x.getRightAnswer();
		
		if (x.getLeftImage() == "NOTHING") {
			leftPicture = "default.png";
		}
		else {
			leftPicture = x.getLeftImage();
		}
		
		if (x.getRightImage() == "NOTHING") {
			rightPicture = "default.png";
		}
		else {
			rightPicture = x.getRightImage();
		}
		
		// Set up window layout
		Container pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setTitle("Dichotomous Key Search");
		setSize(500,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		// QUESTION TEXT
		this.questionText = new JLabel();
		questionText.setText("<html>" + question + "</html>");
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1.0;
		c.gridwidth = 3;
		pane.add(questionText, c);
		//Reset to default width
		c.gridwidth = 1;
		
		// LEFT BUTTON
		this.leftButton = new JButton();
		leftButton.setText(leftAnswer);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		leftButton.setActionCommand(UserInterface.ACTION_LEFT);
		leftButton.addActionListener(this);
		pane.add(leftButton, c);
		
		//LEFT IMAGE
		final ImageIcon leftImage = new ImageIcon(leftPicture);
		this.leftImageLabel = new JLabel(leftImage);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(leftImageLabel, c);
		
	
		// RIGHT BUTTON
		this.rightButton = new JButton();
		rightButton.setText(rightAnswer);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		rightButton.setActionCommand(UserInterface.ACTION_RIGHT);
		rightButton.addActionListener(this);
		pane.add(rightButton, c);
		
		//RIGHT IMAGE
		final ImageIcon rightImage = new ImageIcon(rightPicture);
		this.rightImageLabel = new JLabel(rightImage);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		pane.add(rightImageLabel, c);
		
		
		// SKIP BUTTON
		this.skipButton = new JButton();
		skipButton.setText("SKIP");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		skipButton.setActionCommand(UserInterface.ACTION_SKIP);
		skipButton.addActionListener(this);
		pane.add(skipButton, c);


		// Show to the user!
		setVisible(true);	
	}
	
	
	/* A required method for the ActionListener class. This tells
	 * the ActionListener what to do when each of the different buttons
	 * is clicked. 
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals(UserInterface.ACTION_LEFT) ) {
			passChoice("LEFT");
		}
		else if (event.getActionCommand().equals(UserInterface.ACTION_RIGHT) ) {
			passChoice("RIGHT");
		}
		else if (event.getActionCommand().equals(UserInterface.ACTION_SKIP) ) {
			passChoice("SKIP");
		}
    }
	
	
	/* To be called by component 1.
	 * This method takes in a new KeyTriple to display within the
	 * window for the user to respond to and creates the new window.
	 */
	public void userQuery(KeyTriple x) {
		
		new UserInterface(x);
	}
	

	/* Changes the value of this.nextNode based on what the
	 * user chose with their button click. It is called within
	 * the actionPerformed method to set the user's choice.
	 */
	private void passChoice(String S) {
	
		this.nextNode = S;
		
	}
	
	
	/* To be called by the main method.
	 * This closes the GUI window and stops it from running in the
	 * background. To be called when the user chooses "SKIP" or reaches
	 * a leaf in the tree traversal.
	 */
	public void closeWindow() {
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}

	
	
	/* THE FOLLOWING METHODS ARE FOR TESTING PURPOSES ONLY
	 * They are written so the testing class has access to the variable
	 * values to ensure that they hold the correct contents. They are not 
	 * used in the functioning of this component.
	 */
	
	public String getQuestion() {
		return question;
	}
	
	public String getLeftAnswer() {
		return leftAnswer;
	}
	
	public String getRightAnswer() {
		return rightAnswer;
	}
	
	public String getLeftPicture() {
		return leftPicture;
	}
	
	public String getRightPicture() {
		return rightPicture;
	}

	public String getNextNode() {
		return nextNode;
	}
	
	
	/* THE FOLLOWING METHODS ARE FOR TESTING PURPOSES ONLY
	 * They are written so the testing class can simulate button clicks
	 * to ensure that the correct output is passed after a button click.
	 * They are not used in the functioning of this component.
	 */
	
	public void leftClick() {
		leftButton.doClick();
	}
	
	public void rightClick() {
		rightButton.doClick();
	}
	
	public void skipClick() {
		skipButton.doClick();
	}
	
	/* END OF TESTING METHODS
	 * The remaining methods are used for the functionality of
	 * this component. 
	 */
	
}


