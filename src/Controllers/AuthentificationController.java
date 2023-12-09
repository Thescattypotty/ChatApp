package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Models.User;
import Utils.AlertMessage;
import Utils.AbstractController.AbstractController;
import Utils.User.PasswordAuthenticatedUserInterface;

public class AuthentificationController extends AbstractController {

    private AlertMessage alert;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LoginButton;

    @FXML
    private AnchorPane LoginForm;

    @FXML
    private PasswordField LoginForm_password;

    @FXML
    private TextField LoginForm_username;

    @FXML
    private Button OpenLogin;

    @FXML
    private Button OpenRegister;

    @FXML
    private Button RegisterButton;

    @FXML
    private AnchorPane RegisterForm;

    @FXML
    private CheckBox Register_CheckBox;

    @FXML
    private PasswordField register_comfirmPassword;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_username;

    public AuthentificationController() {
        super();
        alert = new AlertMessage();
    }

    @FXML
    private void handleLogin() {
        String username = LoginForm_username.getText();
        String password = LoginForm_password.getText();


        if (this.authenticate(username, password) == true) {
            alert.successMessage("Login Successfully");
            clearLogin();
            this.redirectTo((Stage)LoginButton.getScene().getWindow(), "/home/senshi/Desktop/ChatApp/src/Views/mainappinterface.fxml");
        } else {
            alert.errorMessage("Login Failed. Check your username or password");
            clearLogin();
        }
    }

    @FXML
    private void handleRegister() throws SQLException {
        String username = register_username.getText();
        String password = register_password.getText();
        String ComfirmedPassword = register_comfirmPassword.getText();

        if (this.userRepository.getUser(username) != null) {
            alert.errorMessage("Username already exist change it");
            clearRegister();
            return;
        }
        if (!password.equals(ComfirmedPassword)) {
            alert.errorMessage("Password does not match");
            clearRegister();
            return;
        }

        PasswordAuthenticatedUserInterface newUser = new User(username, password);
        this.userRepository.addUser(newUser);
        RegisterForm.setVisible(false);
        LoginForm.setVisible(true);
        alert.comfirmationMessage("Registration successful !");

    }

    @FXML
    public void switchToLogin()
    {
        RegisterForm.setVisible(false);
        LoginForm.setVisible(true);
    }

    @FXML 
    public void switchToRegister()
    {
        RegisterForm.setVisible(true);
        LoginForm.setVisible(false);
    }

    public void clearRegister() {
        register_username.clear();
        register_password.clear();
        register_comfirmPassword.clear();
    }

    public void clearLogin() {
        LoginForm_password.clear();
        LoginForm_username.clear();
    }

    @FXML
    void initialize() {
        assert LoginButton != null : "fx:id=\"LoginButton\" was not injected: check your FXML file 'register.fxml'.";
        assert LoginForm != null : "fx:id=\"LoginForm\" was not injected: check your FXML file 'register.fxml'.";
        assert LoginForm_password != null
                : "fx:id=\"LoginForm_password\" was not injected: check your FXML file 'register.fxml'.";
        assert LoginForm_username != null
                : "fx:id=\"LoginForm_username\" was not injected: check your FXML file 'register.fxml'.";
        assert OpenLogin != null : "fx:id=\"OpenLogin\" was not injected: check your FXML file 'register.fxml'.";
        assert OpenRegister != null : "fx:id=\"OpenRegister\" was not injected: check your FXML file 'register.fxml'.";
        assert RegisterButton != null
                : "fx:id=\"RegisterButton\" was not injected: check your FXML file 'register.fxml'.";
        assert RegisterForm != null : "fx:id=\"RegisterForm\" was not injected: check your FXML file 'register.fxml'.";
        assert Register_CheckBox != null
                : "fx:id=\"Register_CheckBox\" was not injected: check your FXML file 'register.fxml'.";
        assert register_comfirmPassword != null
                : "fx:id=\"register_comfirmPassword\" was not injected: check your FXML file 'register.fxml'.";
        assert register_password != null
                : "fx:id=\"register_password\" was not injected: check your FXML file 'register.fxml'.";
        assert register_username != null
                : "fx:id=\"register_username\" was not injected: check your FXML file 'register.fxml'.";

    }
}
