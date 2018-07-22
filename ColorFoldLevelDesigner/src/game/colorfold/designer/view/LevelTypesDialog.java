package game.colorfold.designer.view;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JDialog;

/**
 *
 * @author Deniz ARIKAN
 */
public class LevelTypesDialog extends JDialog {

	public LevelTypesDialog(Frame owner) {
		super(owner);
	}

	public LevelTypesDialog(Dialog owner) {
		super(owner);
	}

	public void display(int width, int height) {
		pack();
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		if (width > screenSize.width) {
			width = screenSize.width;
		}
		if (height > screenSize.height) {
			height = screenSize.height;
		}
		setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, width, height);
		setVisible(true);
		toFront();
	}

}
