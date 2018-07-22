package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;

import game.colorfold.designer.controller.command.SetSelectedLevelCommand;
import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.view.SetLevelIdToDialog;

public class SetLevelIdConfirmButtonAction extends AbstractAction {

    private LevelDesignerController levelDesignerController;
    private SetLevelIdToDialog setLevelIdToDialog;

    public SetLevelIdConfirmButtonAction(LevelDesignerController levelDesignerController,
	    SetLevelIdToDialog setLevelIdToDialog) {
	super("Confirm");
	this.levelDesignerController = levelDesignerController;
	this.setLevelIdToDialog = setLevelIdToDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	ColorFoldLevel selectedLevel = levelDesignerController.getLevelDesignerModel().getSelectedLevel();
	if (selectedLevel == null) {
	    return;
	}
	int setLevelIdTo = setLevelIdToDialog.getLevelIdSpinnerValue();
	if (setLevelIdTo < 0) {
	    return;
	}
	int oldLevelId = levelDesignerController.getLevelIdFromFileName(selectedLevel.getFile().getName());
	if (oldLevelId == 0) {
	    return;
	}

	int updateCount = oldLevelId - setLevelIdTo;
	for (int i = 0; i < updateCount; i++) {
	    String newFileName = levelDesignerController.getFileNameFromLevelId(oldLevelId - i - 1);
	    String oldFileName = levelDesignerController.getFileNameFromLevelId(oldLevelId - i);

	    ColorFoldLevel decrementLevel = levelDesignerController.getLevelDesignerModel()
		    .getLevel(new File(oldFileName));
	    levelDesignerController.getLevelDesignerModel().removeLevel(decrementLevel);
	    decrementLevel.setFile(new File(newFileName));
	    decrementLevel.setSaveNeeded(true);

	    ColorFoldLevel existingLevel = levelDesignerController.getLevelDesignerModel()
		    .getLevel(new File(newFileName));
	    levelDesignerController.getLevelDesignerModel().removeLevel(existingLevel);
	    existingLevel.setFile(new File(oldFileName));
	    existingLevel.setSaveNeeded(true);
	    levelDesignerController.getLevelDesignerModel().addLevel(existingLevel);

	    levelDesignerController.getLevelDesignerModel().addLevel(decrementLevel);
	    new SetSelectedLevelCommand(levelDesignerController, decrementLevel).execute();
	}

	levelDesignerController.updateLevelDesignerFrameTitle();
	levelDesignerController.updateLevelNavigationList();
	setLevelIdToDialog.dispose();
    }

}
