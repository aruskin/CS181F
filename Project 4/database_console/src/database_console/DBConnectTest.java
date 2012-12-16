
/*
 * IGNORE THIS COMPONENT: STORING NOTES HERE
 */
package database_console;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.ResultSet;
import java.io.*;
import structure5.*;
/**
 *
 * @author Robin
 */
public class DBConnectTest {
    private DBConnect tester;
    public void test1() {
        /*
         * test1: passed in associations for Burning Bush
         * should return Burning Bush as a top choice
         */
            /*Queue<Association<String,String>> test1 = new QueueVector<>();
            Association<String,String> temp1 = new Association("TREEORBUSH", "bush");
            test1.add(temp1);
            Association<String,String> temp2 = new Association("LEAVESORNEEDLES", "leaves");
            test1.add(temp2);
            Association<String,String> temp3 = new Association("FLOWERS", "y");
            test1.add(temp3);
            Association<String,String> temp4 = new Association("COLOR", "red");
            test1.add(temp4);
            int length = getSize(test1);
            List<Association<String, Integer>> finalList = getResultSet(test1, length);
            System.out.println(finalList);*/

            /*
             * test2: passed in associations with missing component
             * should still calculate top results
             */
            /*Queue<Association<String,String>> test2 = new QueueVector<>();
            temp1 = new Association("TREEORBUSH", "bush");
            test2.add(temp1);
            temp2 = new Association("LEAVESORNEEDLES", "");
            test2.add(temp2);
            temp3 = new Association("COLOR", "red");
            test2.add(temp3);

            length = getSize(test2);
            finalList = getResultSet(test2, length);
            System.out.println(finalList);*/
            
            /*
             * test3: read in empty list of associations
             * should send error message back
             */
            //Queue<Association<String, String>> test3 = new QueueVector<>();
            //length = getSize(test3);
            //finalList = getResultSet(test3, length);
            
            /*
             * test4: read in list with invalid associations that don't exist
             * in the database
             * should still query other associations and return top results
             */
            /*Queue<Association<String,String>> test4 = new QueueVector<>();
            temp1 = new Association("TREEORBUSH", "bush");
            test4.add(temp1);
            temp2 = new Association("", "leaves");
            test4.add(temp2);
            temp3 = new Association("FLOWERS", "y");
            test4.add(temp3);
            temp4 = new Association("COLOR", "red");
            test4.add(temp4);
            length = getSize(test4);
            finalList = getResultSet(test4, length);
            System.out.println(finalList);*/
            
    }
}
