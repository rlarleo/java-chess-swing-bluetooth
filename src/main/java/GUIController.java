import javax.swing.JFrame;


public class GUIController{
	
	public GUISingle singleChessWindow;
	public GUIBT BTChessWindow;
	
    public void makeSingleChessWindow(JFrame main) {
    	singleChessWindow = new GUISingle();
    	singleChessWindow.createGUI(main);
    }
    
    public void makeBTChessWindow(JFrame main) {
    	BTChessWindow = new GUIBT(false);
        BTChessWindow.createGUI(main);

    }
}
