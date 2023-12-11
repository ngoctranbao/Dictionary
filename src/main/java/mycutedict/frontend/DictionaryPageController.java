package mycutedict.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import mycutedict.backend.Word;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryPageController extends BaseController implements Initializable {
    @FXML
    private Button EnterRecentWordButton, SettingButton, YourWordButton,
            SoundButton, ReturnButton, SaveButton;

    @FXML
    private Label DateLabel, WordLabel;

    @FXML
    private AnchorPane ScenePane;

    @FXML
    private ListView<Integer> recentWordsListView;

    private String previousStage;
    private boolean saved;

    public void setPreviousStage(String previousStage) {
        this.previousStage = previousStage;
    }

    public String getPreviousStage() {
        return previousStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateSetUp(DateLabel);
        buttonSetUp();
        listViewSetUp(recentWordsListView, "RecentWordsPage.fxml", ScenePane);
        saved = false;
    }

    private void buttonSetUp() {
        ButtonSetUp(SettingButton, SettingButtonImagePath, 142.6, 24, 432, 210);
        ButtonSetUp(YourWordButton, YourWordButtonImagePath, 142.6, 62.6, 432, 240);
        ButtonSetUp(EnterRecentWordButton, EnterRecentWordButtonImage, 17, 17, 442, 57);
        ButtonSetUp(SoundButton, SoundButtonImage, 15, 15);
        ButtonSetUp(ReturnButton, ReturnButtonImage, 15, 15);
        ButtonSetUp(SaveButton, UnsavedButtonImage, 35.0/3.0, 35.0/3.0, 392, 52);
    }

    public void switchToRecentWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("RecentWordsPage.fxml", event);
    }

    public void switchToYourWordsPage(ActionEvent event) throws IOException {
        switchToOtherPage("YourWordsPage.fxml", event);
    }

    public void returnToPreviousPage(ActionEvent event) throws IOException {
        switchToOtherPage(getPreviousStage(), event);
    }

    /* Save word to Your Words. */
    public void saveWord(ActionEvent event) throws IOException {
        if(!saved) {
            ButtonSetUp(SaveButton, SaveButtonImage, 35.0/3.0, 35.0/3.0, 392, 52);
            saved = true;
        } else {
            ButtonSetUp(SaveButton, UnsavedButtonImage, 35.0/3.0, 35.0/3.0, 392, 52);
            saved = false;
        }
    }

    public void displayWord(Word word) {
        WordLabel.setText(word.toString());
    }

    public void logOut(ActionEvent event) throws IOException {
        logOut(event, ScenePane);
    }

}
