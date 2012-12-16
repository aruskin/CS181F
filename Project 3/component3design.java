/*Component 3—Database Search
 * Robin Momii
 * CS181F


General description & role in system:


This component takes in a list of associations of questions and answers that the user has specified from Component 1 and searches through the plant database to find the top ten matches.  It creates a list of plants that have the highest matching percentage, which is determined by how many of the associations match a plant.  Because some characteristics of the plants might not be obvious nor easy to identify for some users, we want to be able to return the highest matches from the database, even if none of them exactly match the list of associations.  This list is passed on to Component 4 where the user can look through the top ten results.


Interface:


The database search component takes in the list of associations of questions and answers from Component 1 and runs through the database question by question, as it filters out the plants that best match the given answer.  Since the database is organized as a table with the questions as column lables and the values as answers, all this component needs to do is to look up each question within the table and check which plants match the answer that is in the given list.  A list will be compiled that will contain the top matches that have a matching percentage of 50% or better, based on how many associations of that plant match the user's answers (i.e. a certain plant “P” in the table shows that it has 4 of the 6 given associations in the list; therefore it has a 67% match).  This list of top matching plants as well as their percentage match will be passed on to Component 4, which will properly display the returned results to the user. 


Requirements:


-Must read in list of associations in a specific format (List<Association<String,String>>) for easy iteration to look up in the database
-Must have a working database that can be searched through by association
-Database must be large enough to be able to return plants that match the given associations (using a dummy database for this project) as well as their percentage match
-Must have the ability to look up each association within the database and return a list of plants that have that feature
-Must be able to return a list of top ten results and their associated matching percentages

*/
package database_console;

import java.sql.Connection;         // SQL imports
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.*;                   
import structure5.*;                // using Association

public class DBConnect {

    public static void main(String[] args) {
        /* can't access javaDB without a main function
        */
        List<Association<String, Integer>> finalList = getResultSet(test1, length);

    }
    
    /*
     * function that calculates size of the given Queue
     * if the entry is empty, it does not include it in the total length
     */
    
    public static int getSize(Queue<Association<String, String>> list) {

        return length;
    }
    
    /*
     * function that queries the database for the best matches for the given
     * associations
     */
    public static List<Association<String, Integer>> getResultSet(Queue<Association<String, String>> list, int length) {
        try {
            //given list is empty, exit the program
            if (list.isEmpty()) {
                System.out.println("Error: Empty list; can't perform query");
                System.exit(0);
            }
            
            //database access information
            /*String host = "jdbc:derby://localhost:1527/FINAL";
            String uName = "RM";
            String uPass = "RM";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, 
                    ResultSet.CONCUR_READ_ONLY);*/

            ResultSet rs = null;
            
            //list of associations with the name of the plant and the number
            //of characteristics it matches in the given queue
            List<Association<String, Integer>> count = new Vector<>();
            
            //query the database based off of the first and most broad
            //characteristic (is it a tree or a bush?)
            //if not either then still query to get results

            /*if (a.getKey().equals("TREEORBUSH")) {
                if (a.getValue().equals("bush")) {
                    String SQL = "SELECT * FROM RM.PLANTS WHERE TREEORBUSH = 'bush'";
                    rs = stmt.executeQuery(SQL);
                } else if (a.getValue().equals("tree")) {
                    String SQL = "SELECT * FROM RM.PLANTS WHERE TREEORBUSH = 'tree'";
                    rs = stmt.executeQuery(SQL);
                } else {
                    String SQL = "SELECT * FROM RM.PLANTS";
                    rs = stmt.executeQuery(SQL);
                }*/
                
                //go through the rest of the characteristics in the given
                //Queue and keeps tally of how many match the plants in the
                //result set

                Queue<Association<String, String>> temp = list;
                while (rs.next()) {
                }
                
                
            return finalListFinished;
        }
        catch ( SQLException e ) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /*
     * function that takes in the list of plant names and their count of how
     * many characteristics they match and sorts them from best match the worst
     */
    public static List<Association<String, Integer>> sort(List<Association<String, Integer>> count) {
        List<Association<String, Integer>> finalList = new Vector<>();

        return finalList;
    }
    
    /*
     * calculates the percentage matches by dividing by the total number of 
     * characteristics in the given Queue
     * returns a list that has the plant names and their associated percentage
     * matche
     */
    public static List<Association<String, Integer>> calculatePercentages
            (List<Association<String, Integer>> finalList, int length) {
        return finalList;
    }
}
