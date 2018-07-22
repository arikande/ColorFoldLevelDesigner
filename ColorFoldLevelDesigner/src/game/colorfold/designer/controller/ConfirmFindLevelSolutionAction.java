package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import game.colorfold.designer.view.FindLevelSolutionDialog;

public class ConfirmFindLevelSolutionAction extends AbstractAction {

    private LevelSolverRunnable levelSolverRunnable;
    private FindLevelSolutionDialog findLevelSolutionDialog;

    public ConfirmFindLevelSolutionAction(LevelSolverRunnable levelSolverRunnable,
	    FindLevelSolutionDialog findLevelSolutionDialog) {
	super("Confirm");
	this.levelSolverRunnable = levelSolverRunnable;
	this.findLevelSolutionDialog = findLevelSolutionDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	findLevelSolutionDialog.dispose();
    }

}
