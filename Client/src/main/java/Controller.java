import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField ipEnter;
    @FXML
    private TextField portEnter;
    @FXML
    private BorderPane root;

    @FXML
    private BorderPane rootWin;
    @FXML
    private BorderPane rootLoss;
    @FXML
    private BorderPane rootTie;
    @FXML
    private BorderPane rootBoard;
    @FXML
    private GridPane boardGrid;
    @FXML
    private Button playAgainButton;
    @FXML
    private Button exitButton;

    public Controller() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void resetGame() throws IOException{

    }

    public void exitGame() throws IOException{

    }
}
