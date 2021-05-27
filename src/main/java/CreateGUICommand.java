import java.awt.Container;

import javax.swing.JFrame;

public interface CreateGUICommand {
	public abstract Container createGUI(JFrame mainApp);
}
