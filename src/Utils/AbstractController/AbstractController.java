package Utils.AbstractController;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Controllers.LauncherController;
import Models.Profile;
import Repository.ProfileRepository;
import Utils.AlertMessage;
import Utils.User.PasswordAuthenticatedUserInterface;
import Utils.User.UserProvider;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractController extends UserProvider implements Initializable {

    protected AlertMessage alert;
    private ProfileRepository profileRepository;
    public AbstractController() {
        super();
        profileRepository = new ProfileRepository();
        alert = new AlertMessage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    protected Profile getProfile()
    {
        Profile profile = null;
        try {
            profile = profileRepository.GetProfile(this.getUser().getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(profile == null)
        {
            // here need to fill profile information show an alet message
            alert.successMessage("Please Complete your profile !");
        }
        return profile; 
    }

    protected void redirectTo(Stage currentStage, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(new File(fxmlPath).toURI().toURL());
            Parent root = loader.load();
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
    protected void CloseSystem()
    {
        this.logout(this.getUser().getUsername());
        Platform.exit();
        System.exit(0);
    }

    public void minimizeWindow(){
        LauncherController.getPrimaryStageObj().setIconified(true);
    }
   

}
