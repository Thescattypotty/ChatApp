package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import Repository.*;

public class Database {

    private static Connection connection;

    public static Connection Connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Database.db";

            connection = DriverManager.getConnection(dbURL);

            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void CreateTables() {
        try {
            EntityRepository e1 = new UserRepository();
            e1.createTable();
            e1 = new ProfileRepository();
            e1.createTable();
            e1 = new MessageRepository();
            e1.createTable();
            e1 = new DiscussionRepository();
            e1.createTable();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
        }

    }

    public void DeleteTables() {
        try {
            EntityRepository e1 = new UserRepository();
            e1.DeleteTable();
            e1 = new ProfileRepository();
            e1.DeleteTable();
            e1 = new MessageRepository();
            e1.DeleteTable();
            e1 = new DiscussionRepository();
            e1.DeleteTable();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
        }

    }

    public static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please Provide an argument !");
            return;
        } else {
            try {
                Database d = new Database();
                Database.Connect();

                if (args[0].equals("create")) {
                    d.CreateTables();
                    System.out.println("Table Created Successfully !");
                } else if (args[0].equals("delete")) {
                    d.DeleteTables();
                    System.out.println("Table Deleted Successfully !");
                } else {
                    System.out.println("Provide a valid argument create or delete !");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally
            {
                Database.close();
            }

            

        }

    }

}
