package Client;


import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Client extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        //Parent root = FXMLLoader.load(getClass().getResource("../Views/Form/register.fxml"));
        Parent root = FXMLLoader.load(new File("/home/senshi/Desktop/ChatApp/src/Views/Form/register.fxml").toURI().toURL());

        stage.setTitle("ChatApp - Authentification");
        stage.setScene(new Scene(root, 1024, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
