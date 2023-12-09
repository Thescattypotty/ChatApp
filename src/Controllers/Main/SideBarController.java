package Controllers.Main;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Utils.AbstractController.AbstractController;
import Utils.User.PasswordAuthenticatedUserInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import Utils.FXMLUtils.UserListCell;

public class SideBarController extends AbstractController
{

    @FXML
    private ListView<PasswordAuthenticatedUserInterface> UserListView;

    private ObservableList<PasswordAuthenticatedUserInterface> users = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try {
            List<PasswordAuthenticatedUserInterface> userList = this.userRepository.getAllUsers();
            users.addAll(userList);
            UserListView.setItems(users);
            UserListView.setCellFactory(param -> new UserListCell());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
}
