package Controllers;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LauncherController extends Application
{

    private static Stage primaryStageObj;

    @Override
    public void start(Stage stage) throws Exception {

        primaryStageObj = stage;
        // Parent root =
        // FXMLLoader.load(getClass().getResource("../Views/Form/register.fxml"));
        Parent root = FXMLLoader
                .load(new File("/home/senshi/Desktop/ChatApp/src/Views/Form/register.fxml").toURI().toURL());
        //stage.getIcons().add(new Image("/home/senshi/Desktop/ChatApp/src/Views/Images/1.png").toString());
        stage.setTitle("ChatApp - Authentification");
        Scene mainScene = new Scene(root, 1024, 600);
        mainScene.setRoot(root);
        stage.setResizable(false);
        stage.setScene(mainScene);
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }
    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStageObj() {
        return primaryStageObj;
    }
    


}
