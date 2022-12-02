import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;



public class Client extends Thread{

	
	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	
	GameButton[][] board;
	int player;
	boolean isTurn;
	
	String ip_addr;
	int port;
	
	private Consumer<Serializable> callback;
	
	Client(Consumer<Serializable> call){
		
		callback = call;
	}
	
	public void run() {
		
		try {
			// Update to using ip_addr and port
			socketClient= new Socket("127.0.0.1",5555);
		    out = new ObjectOutputStream(socketClient.getOutputStream());
		    in = new ObjectInputStream(socketClient.getInputStream());
		    socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {}
		
		while(true) {
			 
			try {
				String message = in.readObject().toString();
				callback.accept(message);
			}
			catch(Exception e) {}
		}
	
    }
	
	boolean isBoardFull() {
		for (int i = 0; i < 7; i++) {
			if (board[0][i].player == 0) {
				return false;
			}
		}
		return true;
	}
	
	boolean isWinningMove(int row, int col) {
		boolean upLeft, upRight, downLeft, downRight, left, right, up, down;
		upLeft = true; upRight = true; downLeft = true; up = true;
		downRight = true; left = true; right = true; down = true;
		int horizontalStreak = 1; 
		int verticalStreak = 1;
		int positiveDiag = 1; // slope up
		int negativeDiag = 1; // slope down
		int count = 1;
		while (upLeft || upRight || downLeft || downRight
				|| left || right || up || down) {
			// Checking for out of bounds
			if (row - count == -1) {
				upLeft = false;
				upRight = false;
				up = false;
			}
			if (row + count == 6) {
				downLeft = false;
				downRight = false;
				down = false;
			}
			if (col - count == -1) {
				left = false;
				upLeft = false;
				downLeft = false;
			}
			if (col + count == 7) {
				right = false;
				upRight = false;
				downRight = false;
			}
			// Checking for streaks of buttons
			if (left == true) {
				if (board[row][col-count].player == this.player) {
					horizontalStreak++;
					if (horizontalStreak == 4) return true;
				} else {
					left = false;
				}
			}
			if (right == true) {
				if (board[row][col+count].player == this.player) {
					horizontalStreak++;
					if (horizontalStreak == 4) return true;
				} else {
					right = false;
				}
			}
			if (up == true) {
				if (board[row-count][col].player == this.player) {
					verticalStreak++;
					if (verticalStreak == 4) return true;
				} else {
					up = false;
				}
			}
			if (down == true) {
				if (board[row+count][col].player == this.player) {
					verticalStreak++;
					if (verticalStreak == 4) return true;
				} else {
					down = false;
				}
			}
			if (upLeft == true) {
				if (board[row-count][col-count].player == this.player) {
					negativeDiag++;
					if (negativeDiag == 4) return true;
				} else {
					upLeft = false;
				}
			}
			if (downRight == true) {
				if (board[row+count][col+count].player == this.player) {
					negativeDiag++;
					if (negativeDiag == 4) return true;
				} else {
					downRight = false;
				}
			}
			if (upRight == true) {
				if (board[row-count][col+count].player == this.player) {
					positiveDiag++;
					if (positiveDiag == 4) return true;
				} else {
					upRight = false;
				}
			}
			if (downLeft == true) {
				if (board[row+count][col-count].player == this.player) {
					positiveDiag++;
					if (positiveDiag == 4) return true;
				} else {
					downLeft = false;
				}
			}
		} // end while loop
		return false;
		
	}
	
	public void gameStart() {
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				board[row][col] = new GameButton(row, col);
				board[row][col].setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						// Checking for winning move
						GameButton b = (GameButton) event.getSource();
						if (b.row == 5 || board[b.row+1][b.col].player != 0) {
							boolean winMove = isWinningMove(b.row, b.col);
							boolean boardFull = isBoardFull();
							CFourInfo move = new CFourInfo(player, b.row, b.col, winMove,
									boardFull, true, false);
							
							sendInfo(move);
						} else {
							// print to GUI that it was not a valid move
						}
					}
				});
			}
		}
	}
	
	public void send(String data) {
		
		try {
			out.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void receiveInfo(CFourInfo moveInfo) {
		board[moveInfo.buttonRow][moveInfo.buttonCol].player = moveInfo.player;
		if (moveInfo.winningMove) {
			// set scene to win screen for other player. ask for replay or quit
		}
		if (moveInfo.fullBoard) {
			// set scene to tie game. ask for replay or quit
		}
		if (moveInfo.have2Players == false) {
			
		}
		
	}
	
	// Called when a GameButton is pressed
	public void sendInfo(CFourInfo moveInfo){
		try {
			out.writeObject(moveInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}