import java.awt.Dimension;

import javax.microedition.io.StreamConnection;
import javax.swing.ImageIcon;


public class WindowChessGameController extends ChessBoard {

	public WindowChessGameSingle windowChessGameSingle;
	public WindowChessGameBT windowChessGameBT;
	
	//WindowChessGameSingle
    public void makeWindowChessGameSingle() {
    	windowChessGameSingle = new WindowChessGameSingle();
        windowChessGameSingle.setSize(new Dimension(450, 450));
    }
    public void SingleGameSetNames(String name1, String name2) {
        windowChessGameSingle.setNames(name1, name2);
    }
    public void setupImages(ImageIcon[] imgWhite, ImageIcon[] imgBlack) {
    	windowChessGameSingle.setupImages(imgWhite, imgBlack);
    }
    public void SingleGameNewGame() {
    	windowChessGameSingle.newGame();
    }
    public String[] getMovementRecord() {
        return windowChessGameSingle.getMovementRecord();
    }
    
    //WindowChessGameBT
    public void makeWindowChessGameBT(boolean type) {
    	windowChessGameBT = new WindowChessGameBT(type);
        windowChessGameBT.setSize(new Dimension(410, 440));
    }
    public void BTGameSetNames(String name1, String name2) {
    	windowChessGameBT.setNames(name1, name2);
    }
    public void BTGameNewGame(boolean isServer1, StreamConnection conn1) {
    	windowChessGameBT.newGame(isServer1, conn1);
    }
    public void processCommands(String[] command) {
    	windowChessGameBT.processCommands(command);
    }

}