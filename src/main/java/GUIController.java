import javax.swing.JFrame;

public class GUIController{
	private Mode mode;
	public GUISingle singleChessWindow;
	public GUIBT BTChessWindow;
	public CreateGUICommand command;
	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	public void makeChesswindow(JFrame main) {
		switch(mode) {
		case S: 
			singleChessWindow = new GUISingle();
			singleChessWindow.createGUI(main);
			break;
		case B:
			BTChessWindow = new GUIBT(false);
			BTChessWindow.createGUI(main);
			break;
		}
	}
//    public void makeSingleChessWindow(JFrame main) {
//    	singleChessWindow = new GUISingle();
//    	singleChessWindow.createGUI(main);
//    	// single Mode
//    }
//
//    public void makeBTChessWindow(JFrame main) {
//    	BTChessWindow = new GUIBT(false);
//        BTChessWindow.createGUI(main);
//        // BT Mode
//    }
}