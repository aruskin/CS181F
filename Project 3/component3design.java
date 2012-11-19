/*Component 3—Database Search
 * Robin Momii
 * CS181F


* Description of the purpose of this module:
* This component takes in a list of associations of a plant that the user is searching for and searches 
* through the plant database to find the top ten matches.  It creates a list of plants that have the highest 
* matching percentage, which is determined by how many of the associations match a plant.  Because some 
* characteristics of the plants might not be obvious nor easy to identify for some users, we want to be able 
* to return the highest matches from the database, even if none of them exactly match the list of associations.
* This list is passed on to Component 4 where the user can look through the top ten results.

* Interface:
* The database search component takes in the list of associations from Component 2 and runs through the database
* feature by feature, as it filters out the plants that best match the given associations.  Since the database is
* organized as a table with the values filled in, all this component needs to do is to look up each association 
* within the table and see which plants have that feature.  A list will be compiled that will contain the top ten
* matches, based on a percentage of how many associations match the given list (i.e. a certain plant “P” in the 
* table shows that it has 4 of the 6 given associations in the list; therefore it has a 67% match).  This list will
* be passed on to Component 4 which will properly display the returned results to the user.

* Requirements:
* -Must read in list of associations in a specific format for easy iteration to look up in the database
* -Must have a working database that can be searched through by association
* -Database must be large enough to be able to return plants that match the given associations (using a dummy 
* database for this project)
* -Must have the ability to look up each association within the database and return a list of plants that have that feature
* -Must be able to return a list of top ten results

*/

package database_console;		//import for the DB

import java.sql.*;                      //imports for database
import java.util.List;                  //using a List
import java.util.ArrayList;

//import Association object

class Query {
    
    public static void main(String[] args) {
        //method that connects us to the database that we have used (using Java DB)
        
        // Commands for database:
        // SELECT * FROM RM.PLANTS;                                //select all records from the table called Plants
        // SELECT * FROM RM.PLANTS WHERE JOB_TITLE='Programmer'    //searches for specific entry
        // SELECT * FROM RM.PLANTS WHERE SALARY > 1000
        // SELECT * FROM RM.PLANTS WHERE JOB_TITLE LIKE '%er'
        
        try {
            String host;
            String uName;
            String uPass;
            
            /* Commands for connecting to the database
            Connection con = DriverManager.getConnection(host, uName, uPass);                                   //connect
            Statement s = con.createStatement(RecordSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);     //only scroll forward               
            String SQL = "SELECT* FROM PLANTS";                                                                 //selects records from Plants
            ResultSet rs = s.executeQuery(SQL); //returns records */
            
            // read in the list of Association objects which match an association with the users choice
            // List<Association<String, String>> l;

            // Query the database feature by feature, which will reduce the database through
            // each query. Check the size of the database, if <11 then stop searching and return
            // the list of 10, else keep qeurying.
            
            // while (l != null) {
            //     
            //     rs = queryPlant(l[a]);
            //     if (rs.size() < 11) { //stop searching
            //     }
            //     a++;
            // }
            
            // pull the top results and returns a list
            // List<String> final = makeList(rs);

        }
        catch ( SQLException err ) {
            System.out.println(err.getMessage());
        }
    }
    
    public ResultSet queryPlant(List<Assocation<String, String>> associations) {
        // takes in an Association object and looks up the association in the database
        // and queries the database for the users choice to return a smaller set of plants
        // that will be returned back to main
        ResultSet rs;
        //iterate through each association in the given stack and narrow down the DB
        return rs;
    }
   
    
    public List<String> makeList(ResultSet rs) {
        // takes the rest of the result set and turns it into a usable ArrayList
        List<String> l = new ArrayList();
        return l;
    }
}