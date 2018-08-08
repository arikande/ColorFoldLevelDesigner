package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.view.FindLevelSolutionDialog;

public class FindLevelSolutionAction extends AbstractAction {
	private LevelDesignerController levelDesignerController;

	public FindLevelSolutionAction(LevelDesignerController levelDesignerController) {
		super("Find level solution");
		this.levelDesignerController = levelDesignerController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ColorFoldLevel selectedLevel = levelDesignerController.getLevelDesignerModel().getSelectedLevel();
		if (selectedLevel == null) {
			return;
		}
		FindLevelSolutionDialog findLevelSolutionDialog = new FindLevelSolutionDialog(
				levelDesignerController.getLevelDesignerFrame());
		LevelSolverRunnable levelSolverRunnable = new LevelSolverRunnable(selectedLevel, findLevelSolutionDialog);
		findLevelSolutionDialog.setConfirmButtonAction(
				new ConfirmFindLevelSolutionAction(levelSolverRunnable, findLevelSolutionDialog));
		findLevelSolutionDialog.setWriteSolutionToFileButtonAction(
				new AddSolutionToLevelAction(findLevelSolutionDialog, levelDesignerController, levelSolverRunnable));
		findLevelSolutionDialog
				.setCancelButtonAction(new CancelFindLevelSolutionAction(levelSolverRunnable, findLevelSolutionDialog));
		findLevelSolutionDialog.setConfirmButtonVisible(false);
		findLevelSolutionDialog.setAddSolutionToLevelButtonVisible(false);
		new Thread(levelSolverRunnable).start();
		findLevelSolutionDialog.setVisible(true);
	}

}
