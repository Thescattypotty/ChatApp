package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controllers.Main.MessagerieController;
import Utils.AbstractController.AbstractController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainController extends AbstractController
{
    @FXML
    private BorderPane MainApp;

    @FXML
    private BorderPane CenternewBorderPane;

    private double xOffset;
    private double yOffset;

    private static MainController instance;

    public MainController() {
        instance = this;
    }

    @Override
    public void initialize(URL location , ResourceBundle resourceBundle)
    {
        MainApp.setOnMousePressed(event -> {
            xOffset = LauncherController.getPrimaryStageObj().getX() - event.getScreenX();
            yOffset = LauncherController.getPrimaryStageObj().getY() - event.getScreenY();
            MainApp.setCursor(Cursor.CLOSED_HAND);
        });

        MainApp.setOnMouseDragged(event -> {
            LauncherController.getPrimaryStageObj().setX(event.getScreenX() + xOffset);
            LauncherController.getPrimaryStageObj().setY(event.getScreenY() + yOffset);

        });

        MainApp.setOnMouseReleased(event -> {
            MainApp.setCursor(Cursor.DEFAULT);
        });
    }
    public MessagerieController getMessagerieController()
    {
        Node centerNode = CenternewBorderPane.getCenter();
        if(centerNode instanceof AnchorPane)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainApplicationInterface/CenterAndMainMessagerie.fxml"));
            try {
                loader.load();
                return loader.getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    
    public static MainController getInstance() {
        return instance;
    }

    public BorderPane GetCenter() {
        return CenternewBorderPane;
    }
    public BorderPane GetCenterCenter()
    {
        return (BorderPane) CenternewBorderPane.getCenter();
    }

}

