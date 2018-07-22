package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;

import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.model.Coordinate;
import game.colorfold.designer.view.NewLevelDialog;

public class NewLevelDialogConfirmButtonAction extends AbstractAction {

	private LevelDesignerController levelDesignerController;
	private NewLevelDialog newLevelDialog;

	public NewLevelDialogConfirmButtonAction(LevelDesignerController levelDesignerController,
			NewLevelDialog newLevelDialog) {
		super("Confirm");
		this.levelDesignerController = levelDesignerController;
		this.newLevelDialog = newLevelDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int newLevelId = newLevelDialog.getLevelIdSpinnerValue();
		ColorFoldLevel colorFoldLevel = new ColorFoldLevel();
		colorFoldLevel.addValidCoordinate(new Coordinate(0, 0));
		String levelFilesFolderPath = levelDesignerController.getLevelDesignerModel().getLevelFilesFolder().getAbsolutePath(); 
		String fileName = levelFilesFolderPath + "/level" + String.format("%04d", newLevelId) + ".xml";
		File newLevelFile = new File(fileName);
		colorFoldLevel.setFile(newLevelFile);
		colorFoldLevel.setSaveNeeded(true);
		levelDesignerController.getLevelDesignerModel().addLevel(colorFoldLevel);
		levelDesignerController.updateLevelNavigationList();
		newLevelDialog.dispose();
	}

}
