import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private TextField portInput;
    @FXML
    private Button startButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void startServer() throws IOException {
    	
    }
}
