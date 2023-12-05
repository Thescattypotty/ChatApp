package Controllers;

import Utils.AlertMessage;
import java.sql.Connection;
import java.sql.SQLException;

import Utils.Database;
import Utils.DbModels.UserDb;
import Models.User;
import Utils.Exceptions.RegisterException;
import Utils.Security.PlainPassword;

public class RegisterController {

    private AlertMessage alert = new AlertMessage();
    private Connection connect;
    private UserDb user;

    public RegisterController() {
        try {
            connect = Database.Connect();
            user = new UserDb(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void register(String username, String password, String ComfirmedPassword) throws RegisterException, SQLException
    {
        if(username.isEmpty() || password.isEmpty() || ComfirmedPassword.isEmpty())
        {
           throw new RegisterException("Please Fill All blank Fields !");
        }
        else
        {
            if(password.equals(ComfirmedPassword))
            {
                User u = new User();
                u.setUsername(username);
                u.setPlainPassword(new PlainPassword(password));
                
                this.user.insertObject(u);
                alert.successMessage("Register Succesfully !");
                // redirect vers main window
            }
            else
            {
                throw new RegisterException("Passwords does not match !");
            }
        }
    }
}
