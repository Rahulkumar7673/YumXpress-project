/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YumXpress.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



//      <===================THIS CLASS USE CONNECTION OPEN&CLOSE===================>

public class DBConnection {
    private static Connection conn;
    
     //      <===================THIS BLOCK OPEN THE CONNECTION===================>
           static{
                try{
                  conn=DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","yumxpress","foodie");
                    JOptionPane.showMessageDialog(null, "Connection open!");
                }
                catch(SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, "Can not open the connection!");
                    ex.printStackTrace();
//                    System.exit(0);
                }
                 }
           
    public static Connection getConn() {
        return conn;
    }
    
    //      <===================THIS METHOD CLOSE THE CONNECTION===================>
           public static void closeConnection(){
    try{
        conn.close();
    } catch(SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, "Can not close the connection!");
                    ex.printStackTrace();
                }
}
    
}
