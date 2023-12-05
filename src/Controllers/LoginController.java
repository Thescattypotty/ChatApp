package Controllers;

import java.sql.Connection;
import java.sql.SQLException;

import Models.User;
import Utils.AlertMessage;
import Utils.Database;
import Utils.DbModels.UserDb;
import Utils.Exceptions.LoginException;

public class LoginController {
    
    private AlertMessage alert = new AlertMessage();
    private Connection connect;
    private UserDb user;


    public LoginController()
    {
        try {
            connect = Database.Connect();
            user = new UserDb(connect);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        
    }

    public void Login(String username , String password) throws LoginException, SQLException
    {
        if(username.isEmpty() || password.isEmpty())
        {
            throw new LoginException("Please Fill All blank Fields !");
        }
        else
        {
            User u = new User();
            u = user.getObjectByUsername(username);
            if(u.getPlainPassword().matches(password))
            {
                //redirect to new page;
                // but before create the services responsable to manage user-client
            }
            else
            {
                throw new LoginException("Password or username are inccorect");
            }

        }
    }


}
