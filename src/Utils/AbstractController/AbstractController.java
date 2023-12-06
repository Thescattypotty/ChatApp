package Utils.AbstractController;

import java.net.URL;
import java.util.ResourceBundle;

import Models.User;
import Utils.User.UserProvider;

import javafx.fxml.Initializable;

public class AbstractController implements Initializable
{
    protected UserProvider userProvider;

    public AbstractController(UserProvider userProvider)
    {
        this.userProvider = userProvider;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

    protected User getUser(String username)
    {
        return new User();
    }
    
}
