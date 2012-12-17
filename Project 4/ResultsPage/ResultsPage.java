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


public class ResultsPage extends JFrame implements ActionListener {


// Note: this is the list of strings of search results returned by Component 3
// the first string will be a plant's name and the second string will be its percentage match
static public ArrayList<Association<String, String>> results;

/* Creating a user interface window. 
 * This will create the frame (including a scrollbar) and add a button for each plant 
 * from the results list.
 * Will display an error message if no result is returned (or returned 0 result).
 * The methods to perform these actions are all in the JFrame and Event libraries.
 */
public ResultsPage(ArrayList<Association<String, String>> resultsFromDB) {
  
  getResults(resultsFromDB);
  // setting up frame
  JFrame resultsPage = new JFrame("Floradex Search Results");
  resultsPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  resultsPage.setSize(320,480);
  resultsPage.setResizable(false);
  resultsPage.setBackground(new Color(255,255,255));
  resultsPage.setLayout(new FlowLayout(FlowLayout.LEFT));

  // If there are results to display
  if (!results.isEmpty())
  {
    // setting up buttons
    List<JButton> items = new ArrayList<JButton>();
    JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
  
    for (int i = 0; i < results.size(); ++i)
    {
      int percentage = Integer.parseInt(results.get(i).getValue());
      if (percentage >= 50)
      {
        // Creating a button to contain this item
        JButton item = new JButton(results.get(i).getKey());
        item.setFont(new Font("Helvetica", Font.BOLD, 25));
        item.setLayout(new GridLayout(1,5));
        item.setPreferredSize(new Dimension(280, 85));
        
        // getting factsheet (jpg) from file
        java.net.URL imgURL = ResultsPage.class.getResource(results.get(i).getKey()  + ".jpg");
        ImageIcon image = new ImageIcon();
        
        // If path is valid, display the thumbnail
        if (imgURL != null) 
        {
          image = new ImageIcon(imgURL);
          // Resizing image to 65*65
          Image img = image.getImage();  
          Image newimg = img.getScaledInstance(65, 65,  java.awt.Image.SCALE_SMOOTH);  
          ImageIcon newIcon = new ImageIcon(newimg);
          
          // Creating icon
          JLabel icon = new JLabel(newIcon, JLabel.LEADING);
          item.add(icon);
        }
        // If path is not valid, use a default icon instead
        else 
        {
          image = new ImageIcon("default.jpg");
          JLabel icon = new JLabel(image, JLabel.LEADING);
          item.add(icon);
        }
        
        // Matching Percentage
        JLabel match = new JLabel(percentage + "% ", JLabel.RIGHT);
        match.setFont(new Font("Helvetica", Font.PLAIN, 15));
        item.add(match);
        
        // Adding this item to the list of buttons and items
        items.add(item);
        items.get(i).addActionListener(this);
        buttons.add(items.get(i));
      }
    }
    
    buttons.setPreferredSize(new Dimension(290, 91 * items.size()));
    
    // Making the results scrollable
    JScrollPane scroll = new JScrollPane(buttons);
    scroll.setPreferredSize(new Dimension(310,450));
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scroll.getVerticalScrollBar().setUnitIncrement(10);
    resultsPage.add(scroll);
  }
  // Otherwise, display error message
  else 
  {
    ImageIcon image = new ImageIcon("noresult.jpg");
    JLabel message = new JLabel("No results found!", image, JLabel.CENTER);
    message.setFont(new Font("Helvetica", Font.BOLD, 20));
    message.setForeground(new Color(50, 50, 25));
    
    resultsPage.setLayout(new GridLayout (3,3));
    resultsPage.add(message);
  }
  
  resultsPage.setVisible(true);
}


/* This function handles the mouseclicks. It detects which plant is selected (button pressed)
 * then open a new window displaying the according factsheet
 */
public void actionPerformed(ActionEvent event) {
  // Case: plant1 button, display factSheet(plant1)
  // Case: plant2 button, display factSheet(plant2)
  // Case: plant3 button, display factSheet(plant3)
  // ...
  
  String source = event.getActionCommand();
  for (int i = 0; i < results.size(); ++i)
  {
    if (source == results.get(i).getKey())
    {
      factSheet(results.get(i).getKey());
    } 
  }
}


/* This function gets the results passed from Component 3
 */
static public void getResults(ArrayList<Association<String, String>> resultsFromDB) {
  results = resultsFromDB;
}


/* Creating a user interface window. 
 * This will create the frame and add image (plantName.jpg), add JTextarea(description).
 * The methods to perform these actions are all in the JFrame.
 */
public void factSheet(String plantName) {
  // setting up frame
  JFrame factsheet = new JFrame("Plant Factsheet: " + plantName);
  factsheet.setSize(320,480);
  factsheet.setBackground(new Color(255,255,255));
  factsheet.setLayout(new FlowLayout(FlowLayout.LEFT));
 
  // getting factsheet (jpg) from file
  java.net.URL imgURL = ResultsPage.class.getResource(plantName+"_factsheet.jpg");
  // If path is valid, display factsheet and enable scrolling
  if (imgURL != null) 
  {
      ImageIcon image = new ImageIcon(imgURL);
      JLabel factsheetImage = new JLabel(image);
      factsheet.add(factsheetImage);
      
      JScrollPane scroll = new JScrollPane(factsheetImage);
      scroll.setPreferredSize(new Dimension(310,450));
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      scroll.getVerticalScrollBar().setUnitIncrement(10);
      factsheet.add(scroll);
  }
  // If path is not valid, display error message
  else 
  {
      ImageIcon image = new ImageIcon("noresult.jpg");
      JLabel message = new JLabel("Factsheet Unavailable.",image, JLabel.CENTER);
      message.setFont(new Font("Helvetica", Font.BOLD, 20));
      factsheet.setLayout(new GridLayout (3,3));

      factsheet.add(message);
  }

  factsheet.setVisible(true);
  } 


/* The main fucntion will:
 * - Run the tests
 * - Set up the user interface window
 * - Waits for button pressed and perform the actions specified in actionPerformed
 */
 public static void main(String[] args) {

  /*
  Test Setup 1:
  Simulates empty list of results returned by component 3
  Passing criterias:
  An error message saying "No results found." should be displayed (along with a picture of a sad bunny)

  ***Insturction: uncomment the next line and comment line 235 to run Test 1
  */
  emptyResultsTest();

  /*
  Test Setup 2:
  Simulates a list of results returned by component 3, including one plant that has actual thumbnail
  and factsheet files available in the local source folder and 14 other fake(test) plants that has
  no thumbnail or factsheet.
  
  Passing criterias:
  - Results with a matching percentage over or equal to 50% (in this case, 11 items out of 15 returned)
  should be displayed in the order from the most relevant to the least relevant.
  - The first item in the list should be Avocado, and a thumbnail image of avocado should be displayed.
  - The other 10 items in the list should have fake names (plant1 to plant10), the thumbnails displayed
  should be an default image (green square).
  - Should be able to scroll up and down using keyboard command "UP" and "DOWN" or scroll with mouse or
  touchpad.
  - After clicking on the first plant Avocado, a new window displaying the factsheet of avocado should
  pop up.
  - When clicking on any of the other 14 plants, a new window displaying an error message saying 
  "Factsheet Unavailable." should pop up.

  ***Insturction: uncomment the next line and comment line 212 to run Test 2
  */
  //displayResultsTest();
 }

static public void emptyResultsTest() {
  ArrayList<Association<String,String>> resultsFromDB  = new ArrayList<Association<String,String>>();
  JFrame resultsPage = new ResultsPage(resultsFromDB);
}

static public void displayResultsTest() {
  ArrayList<Association<String,String>> resultsFromDB  = new ArrayList<Association<String,String>>();

  // the first plant is avocado
  Association<String,String> avocado = new Association<String, String>("Avocado", "100");
  resultsFromDB.add(avocado);

  // the rest of plants are just placeholders
  for (int i = 1; i < 15; ++i)
  {
    String percentage = Integer.toString((100 - (i*5)));
    Association<String,String> plant = new Association<String, String>("plant"+i, percentage);
    resultsFromDB.add(plant);
  }

  JFrame resultsPage = new ResultsPage(resultsFromDB);
}


}
 
