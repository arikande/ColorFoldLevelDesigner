package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import game.colorfold.designer.view.NewLevelDialog;

public class DisplayNewLevelDialogAction extends AbstractAction {

	private LevelDesignerController levelDesignerController;

	public DisplayNewLevelDialogAction(LevelDesignerController levelDesignerController) {
		super("New level...");
		this.levelDesignerController = levelDesignerController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (levelDesignerController.getLevelDesignerModel().getLevelFilesFolder() == null) {
			JOptionPane.showMessageDialog(levelDesignerController.getLevelDesignerFrame(), "Open a level folder");
			return;
		}
		NewLevelDialog newLevelDialog = new NewLevelDialog(levelDesignerController.getLevelDesignerFrame());
		int maximumLevelId = levelDesignerController.getMaximumLevelId();
		newLevelDialog.setLevelIdSpinnerValues(maximumLevelId + 1, maximumLevelId + 1);
		newLevelDialog
				.setConfirmButtonAction(new NewLevelDialogConfirmButtonAction(levelDesignerController, newLevelDialog));
		newLevelDialog.setCancelButtonAction(new DisposeDialogAction(newLevelDialog));
		newLevelDialog.setVisible(true);
	}

}
