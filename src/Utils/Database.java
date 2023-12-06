package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import Repository.*;
public class Database
{
    
    private static Connection connection;


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
            EntityRepository e1 = new UserRepository();
            e1.createTable();
            e1 = new ProfileRepository();
            e1.createTable();
            //e1 = new MessageRepository();
            //e1.createTable();
            //e1 = new DiscussionRepository();
            //e1.createTable();
            
        }catch(SQLException e)
        {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
        }
        
    }

    public void DeleteTables()
    {
        try{
            EntityRepository e1 = new UserRepository();
            e1.DeleteTable();
            e1 = new ProfileRepository();
            e1.DeleteTable();
            /*
            e1 = new MessageRepository();
            e1.DeleteTable();
            e1 = new DiscussionRepository();
            e1.DeleteTable();
            */
            
        }catch(SQLException e)
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
