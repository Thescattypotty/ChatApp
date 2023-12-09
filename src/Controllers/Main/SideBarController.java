package Controllers.Main;

import java.net.URL;
import java.util.ResourceBundle;

import Utils.AbstractController.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SideBarController extends AbstractController
{

    @FXML
    private Label time; // get Time from Server i will do it latter // server not initialize yet

    @FXML
    private Label username;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String usernameuser = (getUser() != null) ? getUser().getUsername() : "N/A";
        username.setText(usernameuser);

    }


    
}
