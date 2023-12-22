package Controllers.Main;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import Utils.AbstractController.AbstractController;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SubSideBarController extends AbstractController
{
    @FXML
    private ImageView Gifts;

    @FXML
    private ImageView ListAppel;

    @FXML
    private ImageView MessageImportants;

    @FXML
    private ImageView MessageView;

    @FXML
    private ImageView Paramtres;

    @FXML
    private ImageView ProfileImage;

    @FXML
    private ImageView Stories;

    @Override
    public void initialize(URL location , ResourceBundle resourceBundle)
    {
        if(this.getProfile() != null && this.getProfile().getProfileImage() != null)
        {
            Image image = new Image(new ByteArrayInputStream(this.getProfile().getProfileImage()));
            ProfileImage.setImage(image);
        }
    }

}
