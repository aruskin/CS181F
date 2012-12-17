/*
 * TEAM: Floradex
 * COMPONENT 4 Final DESIGN
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
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import structure5.Association;
import java.lang.Integer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.net.URLConnection;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


// JFrame allows us to create the window and buttons, and by implementing ActionLister
// we will be able to listen for mouse clicks 
class resultsPage extends JFrame implements ActionListener {
  
  // Note: this is the list of strings of search results returned by Component 3
  // the first string will be a plant's name and the second string will be its percentage match
  public List<Assocation<String, String>> results;

  
  /* Creating a user interface window. 
   * This will create the frame (including a slider) and add a button for each plant 
   * from the results list.
   * Will display an error message if no result is returned (or returned 0 result).
   * The methods to perform these actions are all in the JFrame and Event libraries.
   */
  public void resultsPage() {
    // if (results.size() > 0)
    //  setting up buttons
    //  for (i = 0; i < results.size(), ++i)
    //    JButton button1 = new JButton(results[i].first, results[i].first.jpg);
    // else display error message
  }
  
  
  /* This function handles the mouseclicks. It detects which plant is selected (button pressed)
   * then open a new window displaying the according factsheet
   */
  public void actionPerformed(ActionEvent event) {
    // Case: keyboard input "UP", resultsPage scroll up
    // Case: keyboard input "DOWN", resultsPage scroll down
    // Case: plant1 button, display factSheet(plant1)
    // Case: plant2 button, display factSheet(plant2)
    // Case: plant3 button, display factSheet(plant3)
    // ...
  }
  
  
  /* The main fucntion will:
   * - Run tests
   * - Set up the user interface window
   * - Waits for button pressed and perform the actions specified in actionPerformed
   */
   public static void main(String[] args) {
   }
  

  /* Creating a user interface window. 
   * This will create the frame and add the factsheet(plantNameFactsheet.jpg) to display.
   * Will display an error message if the factsheet is not accessible.
   * The methods to perform these actions are all in the JFrame.
   */
  public void factSheet(plantName) {
    }

  /* This function gets the results passed from Component 3
  */
  static public void getResults(ArrayList<Association<String, String>> resultsFromDB) {
    results = resultsFromDB;    
  }

  // Set up test 1
  static public void emptyResultsTest() {
  }

  // Set up test 2
  static public void displayResultsTest() {
  }


}


