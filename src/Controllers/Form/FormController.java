package Controllers.Form;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
/*
public class FormController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {

        @FXML
        private VBox vbox;
        private Parent fxml;
        try {
            this.fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Views/Form/SignUp.fxml")));
            this.vbox.getChildren().removeAll();
            this.vbox.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.open_SignIn();
    }

    @FXML
    private void open_SignIn() {
        // animation a charaf or transition etc ..

        try {
            this.fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Views/Form/SignIn.fxml")));
            this.vbox.getChildren().removeAll();
            this.vbox.getChildren().setAll(this.fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void open_SignUp() {
        // animation a charaf or transition etc ..

        try {
            this.fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Views/Form/SignUp.fxml")));
            this.vbox.getChildren().removeAll();
            this.vbox.getChildren().setAll(this.fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.close();
        System.exit(1);
    }

}*/