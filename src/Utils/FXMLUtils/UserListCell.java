package Utils.FXMLUtils;


import Controllers.Main.MessagerieController;
import Utils.User.PasswordAuthenticatedUserInterface;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class UserListCell extends ListCell<PasswordAuthenticatedUserInterface> {

    @Override
    protected void updateItem(PasswordAuthenticatedUserInterface user, boolean empty) {
        super.updateItem(user, empty);
        if (empty || user == null) {
            setText(null);
            setGraphic(null);
        } else {
            Button userButton = new Button();
            userButton.getStyleClass().add("Label");

            Circle circle = new Circle(18, Color.WHITE);
            Label username = new Label(user.getUsername());
            Label time = new Label("0 time ago");

            AnchorPane contentPane = new AnchorPane();
            contentPane.getChildren().addAll(circle, username, time);

            AnchorPane.setLeftAnchor(circle, 25.0);
            AnchorPane.setTopAnchor(circle, 20.0);
            AnchorPane.setLeftAnchor(username, 50.0);
            AnchorPane.setTopAnchor(username, 11.0);
            AnchorPane.setLeftAnchor(time, 162.0);
            AnchorPane.setTopAnchor(time, 13.0);

            userButton.setGraphic(contentPane);
            setGraphic(userButton);

            userButton.setOnMouseClicked(event -> {
                System.out.println("Button clicked with username: " + user.getUsername());
                try {
  
                    MessagerieController controller = MessagerieController.getInstance();
                    controller.SetReceiver(user.getUsername());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}