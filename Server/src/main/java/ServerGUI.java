import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import javafx.fxml.FXMLLoader;


/* GUI:
- TODO: A way to chose the port number to listen to
- TODO: Have a button to turn on the server.
** BELOW ALL HELD INSIDE ListView<String> moves
- TODO: Display the state of the game(you can display more info, this is the minimum): 
- TODO: how many clients are connected to the server.
- TODO: what each player played.
- TODO: if someone won the game.
- TODO: are they playing again.  *Held inside ListView<String> moves
- Any other GUI elements you feel are necessary for your implementation.
*/

public class ServerGUI extends Application {
	
	Server serverConnection;
	ListView<String> listItems;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Server");
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/serverFXML1.fxml"));
		listItems = new ListView<String>();
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: coral");
		pane.setCenter(listItems);
		
		serverConnection = new Server(data -> {
			Platform.runLater(()->{
				listItems.getItems().add(data.toString());
			});
		});
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		primaryStage.setScene(new Scene(root, 500, 400));
		primaryStage.show();
		
		
	}

}
