
package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectMySQL 
{   
    public Connection con; //
    public void createConnection()
    {
        try {
            //For Local host
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eg-service","root","root");

            //For Online
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection
//                    ("jdbc:mysql://db4free.net:3306/database_fordb4","emranhosen50","emranhosen50");

             System.out.println("Database Connection Done");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
