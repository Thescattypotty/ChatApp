package Controllers.Form;

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
import Controllers.LoginController;
import Controllers.RegisterController;
import Utils.AlertMessage;
import Utils.Exceptions.RegisterException;
import Utils.Exceptions.LoginException;


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

    private AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {
        //to do 
    }

    public void ButtonsManage(ActionEvent event) throws LoginException {
        if (event.getSource() == LoginButton) {
            try {
                LoginController loginCont = new LoginController();
                loginCont.Login(LoginForm_username.getText(), LoginForm_password.getText());
            } catch (LoginException e) {
                alert.errorMessage("Login failed: " + e.getMessage());
            }
            catch(SQLException e)
            {
                alert.errorMessage("Login Failed Cuz Sql :" + e.getMessage());
            }
            

        } else if (event.getSource() == RegisterButton) {
            try {
                RegisterController  registerCont = new RegisterController();
                registerCont.register(register_username.getText(), register_password.getText(), register_comfirmPassword.getText());
            } catch (RegisterException e) {
                alert.errorMessage("Register failed: " + e.getMessage());
            }
            catch(SQLException l)
            {
                alert.errorMessage("Register failed: " + l.getMessage());
            }

        }

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

}