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

package database_console;    	//import for the DB

import java.sql.*;                      //imports for database
import java.util.List;                  //using a List
import java.util.ArrayList;

//import Association object

class Query {
    
        /* Commands for database:
        SELECT * FROM RM.PLANTS;                                //select all records from the table called Plants
        SELECT * FROM RM.PLANTS WHERE JOB_TITLE='Programmer'    //searches for specific entry
        SELECT * FROM RM.PLANTS WHERE SALARY > 1000
        SELECT * FROM RM.PLANTS WHERE JOB_TITLE LIKE '%er'
        */
    
        /* Commands for connecting to the database
        Connection con = DriverManager.getConnection(host, uName, uPass);                           //connect
        Statement s = con.createStatement(RecordSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); //only scroll forward               
        String SQL = "SELECT* FROM PLANTS";                                             //selects records from Plants
        ResultSet rs = s.executeQuery(SQL); //returns records */
    
    public static void main(String[] args) {
        //method that connects us to the database that we have used (using Java DB)
        
        try {
            String host;
            String uName;
            String uPass;
            
            
            
            /* read in the list of associations of questions and the users given answers from Component 3
            /*
            
            //List<Association<String, String>> l;

            /* Query the database feature by feature, which will reduce the database through
            each query. The query will terminate once there are no more associations to query with
            */
            
            // while (L != empty) {
            //     
            //     rs = queryPlant(L[a]);
            //     a++;
            // }
            
            /*pull the plants that have a 50% match or better and return them in a list with their percentage match to Component 4
            */
            
            // List<Association<String,String>> final = makeList(rs);

        }
        catch ( SQLException err ) {
            System.out.println(err.getMessage());
        }
    }
    
    public ResultSet queryPlant(Assocation<String, String> association) {
        // takes in an Association object and looks up the association in the database
        // and queries the database for the users choice to return a smaller set of plants
        // that will be returned back to main
        ResultSet rs;
        //iterate through each association in the given stack and narrow down the DB
        return rs;
    }
   
    
    public List<Association<String, String>> makeList(ResultSet rs) {
        // takes the rest of the result set and turns it into a usable ArrayList
        List<Association<String, String>> L = new ArrayList<Association<String,String>>();
        return L;
    }
}