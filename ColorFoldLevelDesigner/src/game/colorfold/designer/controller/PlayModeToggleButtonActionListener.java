package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayModeToggleButtonActionListener implements ActionListener {

	private LevelDesignerController levelDesignerController;
	private int mode;

	public PlayModeToggleButtonActionListener(LevelDesignerController levelDesignerController, int mode) {
		this.levelDesignerController = levelDesignerController;
		this.mode = mode;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		levelDesignerController.getLevelDesignerModel().setFoldColorMode(mode);
	}

}
