package Controllers;

import Utils.AbstractController.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class MainController extends AbstractController
{
    @FXML
    private BorderPane MainApp;

    @FXML
    private BorderPane CenternewBorderPane;

    private static MainController instance;

    public MainController() {
        instance = this;
    }

    public static MainController getInstance() {
        return instance;
    }

    public BorderPane GetCenter() {
        return CenternewBorderPane;
    }
}

