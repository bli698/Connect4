import java.io.Serializable;

public class CFourInfo implements Serializable{
	int player;
	int buttonRow;
	int buttonCol;
	boolean winningMove;
	boolean fullBoard;
	boolean have2Players;
	boolean disconnect;
	
	CFourInfo(int player, int buttonRow, int buttonCol, boolean winningMove, 
			boolean fullBoard, boolean have2Players, boolean disconnect) {
		this.player = player;
		this.buttonRow = buttonRow;
		this.buttonCol = buttonCol;
		this.winningMove = winningMove;
		this.fullBoard = fullBoard;
		this.have2Players = have2Players;
		this.disconnect = disconnect;
	}
}
