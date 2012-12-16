package database_console;

/**
 *
 * @author Robin Momii
 * Component 3
 * 
 * The purpose of this component is to connect to the local database, run a query
 * based off of the given Queue of associations, and then return a list of the top matches
 * and their associated percentage matches to Component 4.
 */

import java.sql.Connection;         // SQL imports
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.*;                   
import structure5.*;                // using Association

public class DBConnect {

    public static void main(String[] args) {
        /*
         * main required in order to use javaDB
         */
        testSuite();
    }
    
    public static void testSuite() {
        /*
         * test1: passed in associations for Burning Bush
         * should return Burning Bush as a top choice
         */
        Queue<Association<String,String>> test1 = new QueueVector<>();
        Association<String,String> temp1 = new Association("TREEORBUSH", "bush");
        test1.add(temp1);
        Association<String,String> temp2 = new Association("LEAVESORNEEDLES", "leaves");
        test1.add(temp2);
        Association<String,String> temp3 = new Association("FLOWERS", "y");
        test1.add(temp3);
        Association<String,String> temp4 = new Association("COLOR", "red");
        test1.add(temp4);
        int length = test1.size();
        List<Association<String, Integer>> finalList = getResultSet(test1, length);

        Association<String, Integer> plant1 = new Association("Burning Bush", 100);
        Association<String, Integer> plant2 = new Association("Rhododendron", 75);
        Association<String, Integer> plant3 = new Association("Juniper Bush", 50);
        
        if (finalList.contains(plant1) && finalList.contains(plant2) && finalList.contains(plant3)) {
            System.out.println("test1 passed");
        } else {
            System.out.println("test1 failed");
        }

        /*
        * test2: passed in associations with missing component
        * should still calculate top results
        */
        Queue<Association<String,String>> test2 = new QueueVector<>();
        temp1 = new Association("TREEORBUSH", "bush");
        test2.add(temp1);
        temp2 = new Association("LEAVESORNEEDLES", "");
        test2.add(temp2);
        temp3 = new Association("COLOR", "red");
        test2.add(temp3);
        length = test2.size();
        finalList = getResultSet(test2, length);
        
        plant1 = new Association("Burning Bush", 66);
        plant2 = new Association("Juniper Bush", 33);
        plant3 = new Association("Rhododendron", 33);

        
        if (finalList.contains(plant1) && finalList.contains(plant2) && finalList.contains(plant3)) {
            System.out.println("test2 passed");
        } else {
            System.out.println("test2 failed");
        }

            
        /*
        * test3: read in empty list of associations
        * should send error message back
        */
        Queue<Association<String, String>> test3 = new QueueVector<>();
        length = test3.size();
        finalList = getResultSet(test3, length);
        if (finalList == null) {
            System.out.println("test3 passed");
        } else {
            System.out.println("test3 failed");
        }
            
        /*
        * test4A: test for Juniper Bush
        */
        Queue<Association<String,String>> test4A = new QueueVector<>();
        temp1 = new Association("TREEORBUSH", "bush");
        test4A.add(temp1);
        temp2 = new Association("LEAVESORNEEDLES", "leaves");
        test4A.add(temp2);
        temp3 = new Association("FLOWERS", "n");
        test4A.add(temp3);
        temp4 = new Association("COLOR", "green");
        test4A.add(temp4);
        length = test4A.size();
        finalList = getResultSet(test4A, length);
        
        plant1 = new Association("Juniper Bush", 100);
        if (finalList.contains(plant1)) {
            System.out.println("test4A passed");
        } else {
            System.out.println("test4A failed");
        }

        /*
        * test4B: test for Rhododendron
        */
        Queue<Association<String,String>> test4B = new QueueVector<>();
        temp1 = new Association("TREEORBUSH", "bush");
        test4B.add(temp1);
        temp2 = new Association("LEAVESORNEEDLES", "leaves");
        test4B.add(temp2);
        temp3 = new Association("FLOWERS", "y");
        test4B.add(temp3);
        temp4 = new Association("COLOR", "pink");
        test4B.add(temp4);
        length = test4B.size();
        finalList = getResultSet(test4B, length);
        
        plant1 = new Association("Rhododendron", 100);
        if (finalList.contains(plant1)) {
            System.out.println("test4B passed");
        } else {
            System.out.println("test4B failed");
        }

        /*
         * test4C: test for Avocado && Evergreen
         */
        Queue<Association<String,String>> test4C = new QueueVector<>();
        temp1 = new Association("TREEORBUSH", "tree");
        test4C.add(temp1);
        temp2 = new Association("LEAVESORNEEDLES", "leaves");
        test4C.add(temp2);
        temp3 = new Association("FLOWERS", "n");
        test4C.add(temp3);
        temp4 = new Association("COLOR", "green");
        test4C.add(temp4);
        length = test4C.size();
        finalList = getResultSet(test4C, length);
        
        plant1 = new Association("Evergreen Tree", 100);
        plant2 = new Association("Avocado", 100);
        if (finalList.contains(plant1) && finalList.contains(plant2)) {
            System.out.println("test4C passed");
        } else {
            System.out.println("test4C failed");
        }
        /*
         * test4D: test for Pine tree
         */
        Queue<Association<String,String>> test4D = new QueueVector<>();
        temp1 = new Association("TREEORBUSH", "tree");
        test4D.add(temp1);
        temp2 = new Association("LEAVESORNEEDLES", "needles");
        test4D.add(temp2);
        temp3 = new Association("FLOWERS", "n");
        test4D.add(temp3);
        temp4 = new Association("COLOR", "green");
        test4D.add(temp4);
        length = test4D.size();
        finalList = getResultSet(test4D, length);
        
        plant1 = new Association("Pine Tree", 100);
        if (finalList.contains(plant1)) {
            System.out.println("test4D passed");
        } else {
            System.out.println("test4D failed");
        }      
    }
    
    /*
     * function that queries the database for the best matches for the given
     * associations
     * 
     * assumes that TREEORBUSH is the first given association for the purposes
     * of this project (it's the most broad) since I don't know how to grab the
     * appropriate generic name using javaDB
     * 
     * hard to break this function apart into helper functions since it requires the SQL information
     * that was declared within this function
     */
    public static List<Association<String, Integer>> getResultSet(Queue<Association<String, String>> list, int length) {
        try {
            //given list is empty, exit the program
            if (list.isEmpty()) {
                System.out.println("Error: Empty list, can't perform query");
                return null;
            } else {
            
                //local database access information
                String host = "jdbc:derby://localhost:1527/FINAL";
                String uName = "RM";
                String uPass = "RM";
                Connection con = DriverManager.getConnection(host, uName, uPass); //establish connection
                //want a result set that is read only and can only be viewed from
                //front to back
                Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = null;

                //list of associations with the name of the plant and the number
                //of characteristics it matches in the given queue
                List<Association<String, Integer>> count = new Vector<>();

                //query the database based off of the first and most broad
                //characteristic (is it a tree or a bush? - don't know how to make
                //it generic because can't access column names of DB)
                //if not either then still query to get results
                Association<String, String> characteristic = list.remove();
                if (characteristic.getKey().equals("TREEORBUSH")) {
                    if (characteristic.getValue().equals("bush")) {
                        String SQL = "SELECT * FROM RM.PLANTS WHERE TREEORBUSH = 'bush'";
                        rs = stmt.executeQuery(SQL);
                    } else if (characteristic.getValue().equals("tree")) {
                        String SQL = "SELECT * FROM RM.PLANTS WHERE TREEORBUSH = 'tree'";
                        rs = stmt.executeQuery(SQL);
                    } else {
                        String SQL = "SELECT * FROM RM.PLANTS";
                        rs = stmt.executeQuery(SQL);
                    }

                    //goes through the rest of the characteristics in the given
                    //Queue and keeps tally of how many match the plants in the
                    //result set
                    int counter;  //temporary counter
                    Queue<Association<String, String>> temp = list;
                    while (rs.next()) {
                        Association<String, Integer> assc = new Association(rs.getString("NAME"), 1);
                        for (int len = 0; len < list.size(); len ++ ) {
                            characteristic = temp.peek();
                            if (characteristic.getKey() != null) {
                                if (characteristic.getValue().equals(rs.getString(characteristic.getKey()))) {    //there is amatch
                                    counter= assc.getValue();
                                    counter ++;                   //increase the tally
                                    assc.setValue(counter);
                                }

                            }
                            temp.add(temp.remove());        //maintain order of characteristics in queue
                        }
                        count.add(assc);                    //add new association of plant and it's count to the list
                    }

                }

                List<Association<String, Integer>> finalList = sort(count); //sort the list
                finalList = calculatePercentages(finalList, length); //calculate the percentages
                return finalList;   //return final list
            }
        }
        catch ( SQLException e ) {      //SQL Exception if invalid
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
        Association<String,Integer> temp1 = new Association("", 0); //temporary for sorting
        Association<String,Integer> temp2 = new Association("", 0); //temporary for sorting
        int max = 0;       
        //finds a max value and compares all other values to it to sort properly
        for (int i = 0; i < count.size(); i ++) {
            if (count.get(i).getValue() > max) {    //initialize new max
                temp1 = count.get(i);
                max = count.get(i).getValue();
            }
            for (int j = i; j < count.size(); j ++) {   //check that this is indeed the max, update otherwise
                if (count.get(j).getValue() > max) {
                    temp2 = count.get(j);
                    max = count.get(i).getValue();
                }
            }
            if (!(finalList.contains(temp1)) && !(finalList.contains(temp2)) && 
                    (temp2.getValue() > temp1.getValue())){ //if second element found is greater add it first
                finalList.add(temp2);
                finalList.add(temp1);
            } else if (!finalList.contains(temp1)) {    //else just add temp1
                finalList.add(temp1);
            }
            max = 0;    //reset max
        }
        return finalList;   //return final list with names sorted in correct order
    }
    
    /*
     * calculates the percentage matches by dividing by the total number of 
     * characteristics in the given Queue
     * returns a list that has the plant names and their associated percentage
     * matches (between 0 and 100)
     */
    public static List<Association<String, Integer>> calculatePercentages
            (List<Association<String, Integer>> finalList, int length) {
        //goes through each Association<String, Integer> and calculates the
        //percentage match based on how many feature the given plant had over
        //the total number of features that were given in the Queue
        for (int i = 0; i <finalList.size(); i++) {
            int temp1 = finalList.get(i).getValue();
            double temp2 = (double) temp1 / (double) length;
            int finPercent = (int) (temp2 *  100);
            finalList.get(i).setValue(finPercent);  //set the percentage as the value
        }
        return finalList; //return final list with plants and their percent matches
    }
}
