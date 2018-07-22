package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.utility.LevelFileXMLConverter;

public class ReloadLevelFromFileAction extends AbstractAction {

	private LevelDesignerController levelDesignerController;

	public ReloadLevelFromFileAction(LevelDesignerController levelDesignerController) {
		super("Reload from file");
		this.levelDesignerController = levelDesignerController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ColorFoldLevel selectedLevel = levelDesignerController.getLevelDesignerModel().getSelectedLevel();
		if (selectedLevel != null) {
			ColorFoldLevel levelFromFile = new LevelFileXMLConverter().parse(selectedLevel.getFile());
			selectedLevel.reset();
			levelFromFile.copyTo(selectedLevel);
			levelDesignerController.updateLevelDesignerFrameTitle();
			levelDesignerController.updateLevelDesignerMainPanel();
		}
	}

}
