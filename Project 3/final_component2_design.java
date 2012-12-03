/*
 * TEAM: Floradex
 * COMPONENT 2 - Initial Design
 * OWNER: Skyler Lipscomb
 * 
 * The purpose of component 2 is to create the dichotomous key's GUI and
 * handle interactions with the user during the input portion of the application.
 * It will create the user interaction window, handle all mouse-clicks by the 
 * user within the application, and send information back and forth to 
 * component 1, the search tree traversal portion.
 * 
 * The functionality of this component will rely on the Abstract Window Toolkit
 * (java.awt) from the Java standard library to create frames and buttons
 * and register user interactions. For complete documentation of the java.awt
 * package please see:
 * http://docs.oracle.com/javase/1.4.2/docs/api/java/awt/package-summary.html
 * 
 * For extended functionality the Swing (javax.swing) package will be used to
 * give extra frame layout and design capabilities. For complete documentation
 * of the javax.swing package please see:
 * http://docs.oracle.com/javase/1.4.2/docs/api/javax/swing/package-summary.html
 */

// Libraries used for GUI setup and interaction
import javax.swing.*;
import java.awt.event.*;

// JFrame allows us to create the window and buttons, and ActionLister will give us the
// ability to listen for mouse-clicks
class UserInterface extends JFrame implements ActionListener {
  
  // Note: these variables are public because they must interact with the BST component
  // Question text from the dichotomous key
  private String question;
  // Left answer text from the dichotomous key
  private String leftAnswer;
  // Left picture from the dichotomous key
  private String leftPicture;
  // Right answer text from the dichotomous key
  private String rightAnswer;
  // Right picture from the dichotomous key
  private String rightPicture;
  // Next node direction, for the dichotomous key tree
  public String nextNode;
  
  /* Creating a user interface window. This will create the frame, add the question text,
   * add pictures for both the left and right answers, and add the following buttons:
   * leftAns, rightAns, skip
   * The methods to perform these actions are all in the JFrame and Event libraries.
   */
  public void userInterface() { 
  }
  
  
  /* After the user is done inputting information, the window should be closed by the main 
   * program controlling the components. Rather than simply moving the window into the
   * background, the window should be closed and removed from memory to save space. This
   * can be done with the JFrame library.
   */
  public void closeWindow() {
  }
  
  
  /* THIS IS A STANDARD METHOD REQUIRED BY THE ActionEvent CLASS. It cannot set
   * the strings in the class, which is why "clicked" is another method provided 
   * to be called by this function.
   * 
   * This function handles the mouseclicks. It detects which button in the window
   * produced the event, then will call the appropriate method to perform the
   * information passing between the GUI and the dichotomous key tree. */
  public void actionPerformed(ActionEvent event) {
    // Case: skip button
    // Case: left button
    // Case: right button
  }
  
  
  /* This function will set the String value of nextNode based on which button was pressed,
   * to be used in the query() function. Strings are: "LEFT", "RIGHT", or "SKIP". */
  private void clicked(ActionEvent event) {
    // Sets the String nextNode
  }
  
  
  /* This is the method that will send and retrieve data from the dichotomous key tree.
   * Its input is the value of what the current node's content is (question, left
   * answer, left picture, right answer, right picture) and sets the appropriate Strings
   * with this information to display the text to the user. (Also refreshes the window so
   * the new information shows up.)
   * It then waits until the user clicks a button, after which the choice of "LEFT", "RIGHT",
   * or "SKIP" is then returned from the function for the tree's information.
   * The KeyTriple class is a self-designed structure that is used to hold the information in
   * each node of the BST. It is defined in the dichotomous key tree component. */
  public String query(KeyTriple x) {
    return nextNode; // Placeholder for compilation's sake
  }
  
  
}






