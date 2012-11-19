/*
 * TEAM: Floradex
 * COMPONENT 4 DESIGN
 * OWNER: Siyao Xie
 * 
 * Component 4 displays the search results returned by Component 3 as well as 
 * the factsheets of the results. It will create the user interaction window,
 * displays the list of results, handles mouse-click selection by the user,
 * and displays the selected plant's factsheet in a new window.
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

// JFrame allows us to create the window and buttons, and by implementing ActionLister
// we will be able to listen for mouse clicks 
class resultsPage extends JFrame implements ActionListener {
  
  // Note: this is the list of strings of search results returned by Component 3
  public List<string> results;

  
  /* Creating a user interface window. 
   * This will create the frame and add a button for each plant from the results list
   * The methods to perform these actions are all in the JFrame and Event libraries.
   */
  public void resultsPage() { 
  }
  
  
  /* This function handles the mouseclicks. It detects which plant is selected (button pressed)
   * then open a new window displaying the according factsheet
   */
  public void actionPerformed(ActionEvent event) {
    // Case: plant1 button, display factSheet(plant1)
    // Case: plant2 button, display factSheet(plant2)
    // Case: plant3 button, display factSheet(plant3)
    // ...
  }
  
  
  /* The main fucntion will:
   * - Set up the user interface window
   * - Waits for button pressed and perform the actions specified in actionPerformed
   */
   public static void main(String[] args) {
   }
  
}

// creates factSheet using the passed in plantName
class factSheet extends JFrame {
  
  /* read in the plant description from the file (plantName.txt) stored in a local folder
   * and return a string containing the description
   */
   public string readFromFile(plantName) {
   }

  /* Creating a user interface window. 
   * This will create the frame and add image (plantName.jpg), add JTextarea(description).
   * The methods to perform these actions are all in the JFrame.
   */
  public void factSheet(plantName) { 
  }

}





