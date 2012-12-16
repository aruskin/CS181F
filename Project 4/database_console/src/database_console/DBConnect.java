package database_console;

/**
 *
 * @author Robin Momii
 * Component 3
 * Initial Code
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
            int length = getSize(test1);
            List<Association<String, Integer>> finalList = getResultSet(test1, length);
            System.out.println(finalList);

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

            length = getSize(test2);
            finalList = getResultSet(test2, length);
            System.out.println(finalList);
            
            /*
             * test3: read in empty list of associations
             * should send error message back
             */
            Queue<Association<String, String>> test3 = new QueueVector<>();
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
    
    /*
     * calculates size of the given Queue
     * if the entry is empty, it does not include it in the total length
     */
    
    public static int getSize(Queue<Association<String, String>> list) {
        int length = 0;
        int original = list.size();
        for (int i = 0; i < original; i ++) {           
            if (! (list.peek().getValue().equals("")) && ! (list.peek().getKey().equals(""))) {
                length ++;
            }
            list.add(list.remove());
            
        }
        return length;
    }
    
    /*
     * function that queries the database for the best matches for the given
     * associations
     * 
     * assumes that TREEORBUSH is the first given association for the purposes
     * of this project (it's the most broad)
     */
    public static List<Association<String, Integer>> getResultSet(Queue<Association<String, String>> list, int length) {
        try {
            if (list.isEmpty()) {
                System.out.println("Error: Empty list; can't perform query");
                System.exit(0);
            }
            String host = "jdbc:derby://localhost:1527/FINAL";
            String uName = "RM";
            String uPass = "RM";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, 
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = null;
            int i = 0;
            List<Association<String, Integer>> count = new Vector<>();
            Association<String, String> a = list.remove();

            if (a.getKey().equals("TREEORBUSH")) {
                if (a.getValue().equals("bush")) {
                    String SQL = "SELECT * FROM RM.PLANTS WHERE TREEORBUSH = 'bush'";
                    rs = stmt.executeQuery(SQL);
                } else if (a.getValue().equals("tree")) {
                    String SQL = "SELECT * FROM RM.PLANTS WHERE TREEORBUSH = 'tree'";
                    rs = stmt.executeQuery(SQL);
                } else {
                    String SQL = "SELECT * FROM RM.PLANTS";
                    rs = stmt.executeQuery(SQL);
                }
                
                int j;
                Queue<Association<String, String>> temp = list;
                while (rs.next()) {
                    Association<String, Integer> assc = new Association(rs.getString("NAME"), 1);
                    for (int len = 0; len < list.size(); len ++ ) {
                        Association<String, String> temp2 = temp.peek();
                        if (temp2.getKey() != null) {
                            if (temp2.getValue().equals(rs.getString(temp2.getKey()))) {
                                j= assc.getValue();
                                j ++;
                                assc.setValue(j);
                            }
                            
                        }
                        temp.add(temp.remove());
                    }
                    count.add(assc);
                }
                                  
            }

            List<Association<String, Integer>> finalList = sort(count);
            

            List<Association<String, Integer>> finalListFinished = calculatePercentages(finalList, length);
            return finalListFinished;
        }
        catch ( SQLException e ) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static List<Association<String, Integer>> sort(List<Association<String, Integer>> count) {
        List<Association<String, Integer>> finalList = new Vector<>();
        Association<String,Integer> z = new Association("", 0);
        Association<String,Integer> y = new Association("", 0);
        int max = 0;
        for (int m = 0; m < count.size(); m ++) {
            if (count.get(m).getValue() > max) {
                z = count.get(m);
                max = count.get(m).getValue();
            }
            for (int n = m; n < count.size(); n ++) {
                if (count.get(n).getValue() > max) {
                    y = count.get(n);
                    max = count.get(m).getValue();
                }
            }
            if (y.getValue() > z.getValue()){
                finalList.add(y);
                finalList.add(z);
            } else if (!finalList.contains(z)) {
                finalList.add(z);
            }
            max = 0;
        }
        return finalList;
    }
    
    /*
     * calculates the percentage matches
     */
    public static List<Association<String, Integer>> calculatePercentages
            (List<Association<String, Integer>> finalList, int length) {
        
        for (int x = 0; x <finalList.size(); x++) {
            int tempPercent = finalList.get(x).getValue();
            double tp = (double) tempPercent / (double) length;
            int finPercent = (int) (tp *  100);
            finalList.get(x).setValue(finPercent);
        }
        return finalList;
    }
}
