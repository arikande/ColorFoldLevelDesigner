package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;

import game.colorfold.designer.controller.command.SetSelectedLevelCommand;
import game.colorfold.designer.model.ColorFoldLevel;

public class IncrementLevelIdAction extends AbstractAction {

    private LevelDesignerController levelDesignerController;

    public IncrementLevelIdAction(LevelDesignerController levelDesignerController) {
	super("Increment level id");
	this.levelDesignerController = levelDesignerController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	ColorFoldLevel selectedLevel = levelDesignerController.getLevelDesignerModel().getSelectedLevel();
	if (selectedLevel == null) {
	    return;
	}

	int oldLevelId = levelDesignerController.getLevelIdFromFileName(selectedLevel.getFile().getName());
	int newLevelId = oldLevelId + 1;
	String newFileName = levelDesignerController.getFileNameFromLevelId(newLevelId);
	String oldFileName = levelDesignerController.getFileNameFromLevelId(oldLevelId);

	levelDesignerController.getLevelDesignerModel().removeLevel(selectedLevel);
	selectedLevel.setFile(new File(newFileName));
	selectedLevel.setSaveNeeded(true);

	ColorFoldLevel existingLevel = levelDesignerController.getLevelDesignerModel().getLevel(new File(newFileName));
	if (existingLevel != null) {
	    levelDesignerController.getLevelDesignerModel().removeLevel(existingLevel);
	    existingLevel.setFile(new File(oldFileName));
	    existingLevel.setSaveNeeded(true);
	    levelDesignerController.getLevelDesignerModel().addLevel(existingLevel);
	}

	levelDesignerController.getLevelDesignerModel().addLevel(selectedLevel);
	new SetSelectedLevelCommand(levelDesignerController, selectedLevel).execute();
	levelDesignerController.updateLevelDesignerMainPanel();
	levelDesignerController.updateLevelDesignerFrameTitle();
    }

}
