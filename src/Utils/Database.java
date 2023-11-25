package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database 
{
    
    private static Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;

    private Statement stmt = null;


    public static Connection Connect()
    {
        if(connection != null)
        {
            return connection;
        }
        else 
        {
            try{
                Class.forName("org.sqlite.JDBC");
                String dbURL = "jbdc:sqlite:Database.db";

                connection = DriverManager.getConnection(dbURL);

                return connection;

            }catch(ClassNotFoundException | SQLException e)
            {
                e.printStackTrace();
                return null;
            }
        }
    }
    public void CreateTables()
    {
        try{

        }catch(Exception e)
        {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
        }
        
    }

    public void DeleteTables()
    {
        try{

        }catch(Exception e)
        {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
        }
        
    }

    public static void close() {
        if (connection != null) {
            try{
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    

}
