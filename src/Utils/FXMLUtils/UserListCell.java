package Utils.FXMLUtils;

import Controllers.Main.MessagerieController;
import Utils.User.PasswordAuthenticatedUserInterface;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

public class UserListCell implements
        Callback<ListView<PasswordAuthenticatedUserInterface>, ListCell<PasswordAuthenticatedUserInterface>> {

    public UserListCell() {
    }

    @Override
    public ListCell<PasswordAuthenticatedUserInterface> call(ListView<PasswordAuthenticatedUserInterface> u) {
        ListCell<PasswordAuthenticatedUserInterface> cell = new ListCell<PasswordAuthenticatedUserInterface>() {
            @Override
            protected void updateItem(PasswordAuthenticatedUserInterface user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Button userButton = createUserButton(user);
                    setGraphic(userButton);
                }
            }
        };
        return cell;
    }

    private Button createUserButton(PasswordAuthenticatedUserInterface user) {
        Button userButton = new Button();
        userButton.setStyle("-fx-background-color: #333333;");

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(5,5,5,5));
        
        Text text = new Text(user.getUsername());
        TextFlow textflow = new TextFlow(text);

        textflow.setPadding(new Insets(5, 5, 5, 10));

        text.setFill(Color.WHITE);

        hbox.getChildren().add(textflow);
        
        Platform.runLater(
            new Runnable() {
                @Override
                public void run()
                {
                    userButton.setGraphic(hbox);
                }
            }
        );
        userButton.setOnMouseClicked(event -> handleButtonClick(user));


        return userButton;
    }

    private void handleButtonClick(PasswordAuthenticatedUserInterface user) {
        try {
            MessagerieController controller = MessagerieController.getInstance();
            controller.SetReceiver(user.getUsername());
            System.out.println("User Clicked : " + user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
