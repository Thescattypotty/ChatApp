package Controllers.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Models.Discussion;
import Models.Message;
import Repository.DiscussionRepository;
import Utils.AbstractController.AbstractController;
import Utils.Listeners.Listener;
import Utils.User.PasswordAuthenticatedUserInterface;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MessagerieController extends AbstractController {

    private PasswordAuthenticatedUserInterface receiver;
    private static MessagerieController instance;
    private Discussion discussion;
    private DiscussionRepository discussionRepository;

    @FXML
    private Label receiverusername;

    @FXML
    private TextField messageContent;

    @FXML
    private ScrollPane sp_main;

    @FXML
    private ListView<HBox> chatPane;

    private Listener listener;

    @FXML
    private AnchorPane messagerieAnchorPane;

    Logger logger = LoggerFactory.getLogger(MessagerieController.class);

    public MessagerieController() {
        instance = this;
        discussionRepository = new DiscussionRepository();
        if (this.receiver != null && this.getUser() != null) {
            discussion = discussionRepository.GetDiscussion(this.getUser().getUsername(), this.receiver.getUsername());
            if (discussion == null) {
                discussion = new Discussion(this.getUser().getId(), this.receiver.getId());
                discussionRepository.addDiscussion(discussion);
            }
        } else {
            System.out.println("Error while getting user & receiver !!");
        }

    }

    public static MessagerieController getInstance() {
        return instance;
    }

    public void sendButtonAction() throws IOException {
        String msg = messageContent.getText();
        if (!msg.isEmpty()) {

            Message message = new Message(this.getUser(), this.receiver, msg);
            this.listener.send(message);
            RightMessage(msg);
            logger.info("Message sended" + message.toString());
            messageContent.clear();
        }
    }

    public void RightMessage(String message)
    {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.setPadding(new Insets(5, 5, 5, 10));
        Text text = new Text(message);
        TextFlow textflow = new TextFlow(text);
        textflow.setStyle("-fx-color: rgb(239, 242, 255);"+
            "-fx-background-color: rgb(15, 125 , 242);"+
            "-fx-background-radius: 20px"
        );
        textflow.setPadding(new Insets(5, 5, 5, 10));
        text.setFill(Color.color(0.934, 0.945, 0.996));
        hbox.getChildren().add(textflow);
        
        Platform.runLater(new Runnable() {
            @Override
            public void run()
            {
            chatPane.getItems().add(hbox);
            }
        });
    }
    public void LeftMessage(String message)
    {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(5, 5, 5, 10));
        Text text = new Text(message);
        TextFlow textflow = new TextFlow(text);
        textflow.setStyle("-fx-background-color: rgb(233, 233 , 235);"+
            "-fx-background-radius: 20px"
        );
        textflow.setPadding(new Insets(5, 10, 5, 10));
        text.setFill(Color.color(0.934, 0.945, 0.996));
        hbox.getChildren().add(textflow);
        
        Platform.runLater(new Runnable() {
            @Override
            public void run()
            {
            chatPane.getItems().add(hbox);
            }
        });
        
    }

    public void SetReceiver(String username) {
        this.receiver = this.getUser(username);
        if (receiver != null)
            this.receiverusername.setText(username);
    }

    public void sendMethod(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            sendButtonAction();
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listener = new Listener(this.getUser(), this);

        assert messagerieAnchorPane != null
                : "fx:id=\"messagerieAnchorPane\" was not injected: check your FXML file 'CenterAndMainMessagerie.fxml'.";
        assert receiverusername != null
                : "fx:id=\"receiverusername\" was not injected: check your FXML file 'CenterAndMainMessagerie.fxml'.";

        chatPane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sp_main.setVvalue((Double) newValue);
            }
        });
        messageContent.addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
                    sendButtonAction();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ke.consume();
            }
        });
    }

    public void receiveMessage(Message message) {

        if(message.getReceiver().getUsername().equals(this.getUser().getUsername()))
            LeftMessage(message.getContent());
            
        logger.info("Message received" + message.toString());
    }

}
