package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import game.colorfold.designer.view.FindLevelSolutionDialog;

public class CancelFindLevelSolutionAction extends AbstractAction {

    private LevelSolverRunnable levelSolverRunnable;
    private FindLevelSolutionDialog findLevelSolutionDialog;

    public CancelFindLevelSolutionAction(LevelSolverRunnable levelSolverRunnable,
	    FindLevelSolutionDialog findLevelSolutionDialog) {
	super("Cancel");
	this.levelSolverRunnable = levelSolverRunnable;
	this.findLevelSolutionDialog = findLevelSolutionDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (levelSolverRunnable.isCanceled() || levelSolverRunnable.isFailed()) {
	    findLevelSolutionDialog.dispose();
	} else {
	    levelSolverRunnable.setCanceled(true);
	}
    }

}
