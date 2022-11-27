import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class ClientGUI extends Application {
	
	ListView<String> listItems;
	VBox clientBox;
	Client clientConnection;
	Button b1;
	TextField c1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Client");
		
		b1 = new Button("Send");
		b1.setOnAction(e->{clientConnection.send(c1.getText()); c1.clear();});
		
		c1 = new TextField();
		listItems = new ListView<String>();
		
		clientBox = new VBox(10, c1, b1, listItems);
		clientBox.setStyle("-fx-background-color: blue");
		
		primaryStage.setScene(new Scene(clientBox, 400, 300));
		
		clientConnection = new Client(data->{
			Platform.runLater(()->{listItems.getItems().add(data.toString());
							});
			});
		clientConnection.start();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		primaryStage.show();
	}

}
