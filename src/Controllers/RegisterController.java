package Controllers;

import utils.AlertMessage;
import java.sql.Connection;
import utils.Database;
import utils.DbModels.UserDb;
import Models.User;
import utils.Exception.RegisterException;

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

    public register(String username, String password, String ComfirmedPassword) throws RegisterException
    {
        if(username.getText().isEmpty() || password.getText().isEmpty() || ComfirmedPassword.getText().isEmpty())
        {
           throw new RegisterException("Please Fill All blank Fields !");
        }
        else
        {
            if(password.getText() == ComfirmedPassword.getText())
            {
                User u = new User();
                u.setUsername(username.getText());
                u.setPlainPassword(new PlainPassword(password.getText()));
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
