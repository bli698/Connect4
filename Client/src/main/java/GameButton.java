import javafx.scene.control.Button;

public class GameButton extends Button{
	// which player controls that button
	int player; // 0 = none, 1 = player 1, 2 = player 2, etc.
	int row;
	int col;
}
