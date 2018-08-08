package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.model.ColorFoldLevelSolution;
import game.colorfold.designer.model.ColorFoldMove;
import game.colorfold.designer.view.FindLevelSolutionDialog;

public class AddSolutionToLevelAction extends AbstractAction {

	private FindLevelSolutionDialog findLevelSolutionDialog;
	private LevelSolverRunnable levelSolverRunnable;
	private LevelDesignerController levelDesignerController;

	public AddSolutionToLevelAction(FindLevelSolutionDialog findLevelSolutionDialog,
			LevelDesignerController levelDesignerController, LevelSolverRunnable levelSolverRunnable) {
		super("Add solution to level");
		this.findLevelSolutionDialog = findLevelSolutionDialog;
		this.levelSolverRunnable = levelSolverRunnable;
		this.levelDesignerController = levelDesignerController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int size = levelSolverRunnable.getMovesStack().size();
		if (size > 0) {
			ColorFoldLevelSolution colorFoldLevelSolution = new ColorFoldLevelSolution();
			for (int i = 0; i < size; i++) {
				ColorFoldMove colorFoldMove = levelSolverRunnable.getMovesStack().get(i);
				colorFoldLevelSolution.addMove(colorFoldMove);
			}
			ColorFoldLevel colorFoldLevel = levelSolverRunnable.getColorFoldLevel();
			colorFoldLevel.setColorFoldLevelSolution(colorFoldLevelSolution);
			colorFoldLevel.setSaveNeeded(true);
			findLevelSolutionDialog.dispose();
			levelDesignerController.updateLevelDesignerFrameTitle();

		}
	}

}
