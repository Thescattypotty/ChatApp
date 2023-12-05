package Utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;


public class AlertMessage {
     
    private Alert alert;


    public void errorMessage(String Message)
    {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message !");
        alert.setHeaderText(null);
        alert.setContentText(Message);
        alert.showAndWait();
    }
    public void successMessage(String Message)
    {
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success Message !");
        alert.setHeaderText(null);
        alert.setContentText(Message);
        alert.showAndWait();
    }
    public Boolean comfirmationMessage(String Message)
    {
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("comfirmation Message !");
        alert.setHeaderText(null);
        alert.setContentText(Message);
        Optional<ButtonType> option = alert.showAndWait();
        
        if(option.get().equals(ButtonType.OK))
            return true;
        else
            return false;
 
    }
}
