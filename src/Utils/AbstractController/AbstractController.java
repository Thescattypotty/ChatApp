package Utils.AbstractController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Utils.User.PasswordAuthenticatedUserInterface;
import Utils.User.UserProvider;

import javafx.fxml.Initializable;

public class AbstractController extends UserProvider implements Initializable
{

    public AbstractController()
    {
        super();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

    protected PasswordAuthenticatedUserInterface getUser(String username)
    {

        PasswordAuthenticatedUserInterface user = null;
        try{
            user = this.userRepository.getUser(username);
            return user;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }

    
}
