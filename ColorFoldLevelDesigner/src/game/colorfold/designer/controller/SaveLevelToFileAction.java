package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.utility.LevelFileXMLConverter;

public class SaveLevelToFileAction extends AbstractAction {

    private LevelDesignerController levelDesignerController;

    public SaveLevelToFileAction(LevelDesignerController levelDesignerController) {
	super("Save");
	this.levelDesignerController = levelDesignerController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	ColorFoldLevel selectedLevel = levelDesignerController.getLevelDesignerModel().getSelectedLevel();
	if (selectedLevel != null && selectedLevel.isSaveNeeded()) {
	    new LevelFileXMLConverter().writeToFile(selectedLevel, selectedLevel.getFile());
	    selectedLevel.setSaveNeeded(false);
	    levelDesignerController.updateLevelDesignerFrameTitle();
	}
    }

}
