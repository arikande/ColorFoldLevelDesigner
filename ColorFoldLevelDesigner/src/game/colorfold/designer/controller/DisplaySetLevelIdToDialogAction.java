package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.view.SetLevelIdToDialog;

public class DisplaySetLevelIdToDialogAction extends AbstractAction {

	private LevelDesignerController levelDesignerController;

	public DisplaySetLevelIdToDialogAction(LevelDesignerController levelDesignerController) {
		super("Set level id to...");
		this.levelDesignerController = levelDesignerController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SetLevelIdToDialog setLevelIdToDialog = new SetLevelIdToDialog(levelDesignerController.getLevelDesignerFrame());
		ColorFoldLevel selectedLevel = levelDesignerController.getLevelDesignerModel().getSelectedLevel();
		if (selectedLevel != null) {
			int selectedLevelId = levelDesignerController.getLevelIdFromFileName(selectedLevel.getFile().getName());
			int maximumLevelId = levelDesignerController.getMaximumLevelId();
			setLevelIdToDialog.setLevelIdSpinnerValues(0, selectedLevelId, maximumLevelId);
			setLevelIdToDialog.setConfirmButtonAction(
					new SetLevelIdConfirmButtonAction(levelDesignerController, setLevelIdToDialog));
			setLevelIdToDialog.setCancelButtonAction(new DisposeDialogAction(setLevelIdToDialog));
			setLevelIdToDialog.setVisible(true);
		}
	}

}
