package Utils.AbstractController;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Utils.User.PasswordAuthenticatedUserInterface;
import Utils.User.UserProvider;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class AbstractController extends UserProvider implements Initializable
{

    public AbstractController()
    {
        super();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

    protected PasswordAuthenticatedUserInterface getUser(String username)
    {

        PasswordAuthenticatedUserInterface user = null;
        try{
            user = this.userRepository.getUser(username);
            return user;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
    /*

    ask gpt about this
    public void redirectToView(AnchorPane anch , String name)
    {
        anch.getChildren().setAll(FXMLLoader.load(new File("/home/senshi/Desktop/ChatApp/src/Views/Form/register.fxml").toURI().toURL()));
    }*/
/*
 * 
 * salut j'ai le code suivant :
@Override
    public void start(Stage stage) throws Exception {
        
        //Parent root = FXMLLoader.load(getClass().getResource("../Views/Form/register.fxml"));
        Parent root = FXMLLoader.load(new File("/home/senshi/Desktop/ChatApp/src/Views/Form/register.fxml").toURI().toURL());

        stage.setTitle("ChatApp - Authentification");
        stage.setScene(new Scene(root, 1024, 600));
        stage.show();
    }
j'ai un controlleur qui gere le register.fxml , et au moment de l'authentification je voudrai closer ce fxml et afficher un autre appeler mainApplication.fxml
 * 
 */
    
}
