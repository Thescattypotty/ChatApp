package Controllers;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Models.User;
import Utils.AlertMessage;
import Utils.Services.UserService;
import Utils.User.PasswordAuthenticatedUserInterface;
import Utils.User.UserProvider;
import Repository.UserRepository;

public class AuthentificationController implements Initializable
{
    private UserRepository userRepository;
    private UserProvider userProvider;
    private UserService userService;

    private AlertMessage alert ;

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


    public AuthentificationController
        (
            UserRepository userRepository,
            UserService userService,
            UserProvider userProvider
        )
    {
        this.userRepository = userRepository;
        this.userProvider = userProvider;
        this.userService = userService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {
        //to do 
    }
    @FXML
    private void handleLogin()
    {
        String username = LoginForm_username.getText();
        String password = LoginForm_password.getText();

        PasswordAuthenticatedUserInterface newUser = userProvider.authenticate(username, password);

        if(newUser != null)
        {
            userService.setUser(newUser);
            alert.successMessage("Login Successfully");
            clearLogin();
            //redirect to main page!
        }
        else
        {
            alert.errorMessage("Login Failed. Check your username or password");
            clearLogin();
        }
    }

    @FXML
    private void handleRegister() throws SQLException
    {
        String username = register_username.getText();
        String password = register_password.getText();
        String ComfirmedPassword = register_comfirmPassword.getText();

        if(userRepository.getUser(username) != null) 
        {
            alert.errorMessage("Username already exist change it");
            clearRegister();
            return;
        }
        if(password != ComfirmedPassword)
        {
            alert.errorMessage("Password does not match");
            clearRegister();
            return;
        }
        
        PasswordAuthenticatedUserInterface newUser = new User(username, password);
        userRepository.addUser(newUser);
        RegisterForm.setVisible(false);
        LoginForm.setVisible(true);
        alert.comfirmationMessage("Registration successful !");
        
    }


    public void switchForm(ActionEvent event) {
        if (event.getSource() == OpenRegister) {
            LoginForm.setVisible(false);
            RegisterForm.setVisible(true);
        } else if (event.getSource() == OpenLogin) {
            RegisterForm.setVisible(false);
            LoginForm.setVisible(true);
        }
    }

    public void clearRegister()
    {
        register_username.clear();
        register_password.clear();
        register_comfirmPassword.clear();
    }
    public void clearLogin()
    {
        LoginForm_password.clear();
        LoginForm_username.clear();
    }

}
