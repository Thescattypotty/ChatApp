package Controllers.Form;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javax.security.auth.login.LoginException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Controllers.LoginController;
import Controllers.RegisterController;

public class FormController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {

    }

    public void ButtonsManage(ActionEvent event) {
        if (event.getSource() == LoginButton) {
            try {
                LoginController loginCont = new LoginController();
                loginCont.Login(LoginForm_username.getText(), LoginForm_password.getText());
            } catch (LoginException e) {
                alert.errorMessage("Login failed: " + e.getMessage());
            }

        } else if (event.getSource() == RegisterButton) {
            try {
                RegisterController = registerCont = new RegisterController();
                registerCont.register(register_username, register_password, register_comfirmPassword);
            } catch (RegisterException e) {
                alert.errorMessage("Register failed: " + e.getMessage());
            }

        }

    }
    public void switchForm(ActionEvent event) {
        if (event.getSource() == OpenRegister) {
            LoginForm.setInvisible(false);
            RegisterForm.setInvisible(true);
        } else if (event.getSource() == OpenLogin) {
            RegisterForm.setInvisible(false);
            LoginForm.setInvisible(true);
        }
    }

}