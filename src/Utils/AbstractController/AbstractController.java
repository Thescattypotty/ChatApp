package Utils.AbstractController;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Utils.User.PasswordAuthenticatedUserInterface;
import Utils.User.UserProvider;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AbstractController extends UserProvider implements Initializable {

    public AbstractController() {

        super();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    protected PasswordAuthenticatedUserInterface getUser() {
        if (this.userService.getUser() != null) {
            return this.userService.getUser();
        }
        return null;

    }

    protected PasswordAuthenticatedUserInterface getUser(String username) {

        PasswordAuthenticatedUserInterface user = null;
        try {
            user = this.userRepository.getUser(username);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    protected void redirectTo(Stage currentStage, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(new File(fxmlPath).toURI().toURL());
            Stage newStage = new Stage();
            newStage.setTitle("ChatApp - Welcome");
            newStage.setScene(new Scene(root, 1024, 600));

            if (currentStage != null) {
                currentStage.close();
            }
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
