package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

public class DisposeDialogAction extends AbstractAction {

	private JDialog dialog;

	public DisposeDialogAction(JDialog dialog) {
		super("Cancel");
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.dispose();
	}

}
