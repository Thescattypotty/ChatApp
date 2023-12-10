package Controllers.Main;

import java.net.URL;
import java.util.ResourceBundle;

import Utils.AbstractController.AbstractController;
import Utils.User.PasswordAuthenticatedUserInterface;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MessagerieController extends AbstractController {
    
    
    
    private PasswordAuthenticatedUserInterface receiver;
    private static MessagerieController instance ;

    public MessagerieController()
    {
        instance = this;
    }

    public static MessagerieController getInstance() {
        return instance;
    }


    @FXML
    private Label receiverusername;

    @FXML
    private AnchorPane messagerieAnchorPane;

    public void SetReceiver(String username) {
        this.receiver = this.getUser(username);
        if(receiver != null)
        {
            this.receiverusername.setText(username);
        }
        else
        {
            System.out.println("Big problem");
        }
    }

    @FXML
    public void initialize(URL url , ResourceBundle resourceBundle) {
        assert messagerieAnchorPane != null : "fx:id=\"messagerieAnchorPane\" was not injected: check your FXML file 'CenterAndMainMessagerie.fxml'.";
        assert receiverusername != null : "fx:id=\"receiverusername\" was not injected: check your FXML file 'CenterAndMainMessagerie.fxml'.";

    }

}
