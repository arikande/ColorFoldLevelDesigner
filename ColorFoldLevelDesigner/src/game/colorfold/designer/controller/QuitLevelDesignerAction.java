package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class QuitLevelDesignerAction extends AbstractAction {

	public QuitLevelDesignerAction() {
		super("Quit");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
