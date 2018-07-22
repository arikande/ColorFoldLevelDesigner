package game.colorfold.designer.controller;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import game.colorfold.designer.model.ColorFoldLevel;

public class MaximumMovesSpinnerChangeListener implements ChangeListener {

    private LevelDesignerController levelDesignerController;

    public MaximumMovesSpinnerChangeListener(LevelDesignerController levelDesignerController) {
	this.levelDesignerController = levelDesignerController;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
	int spinnerValue = (int) ((JSpinner) e.getSource()).getValue();
	ColorFoldLevel selectedLevel = levelDesignerController.getLevelDesignerModel().getSelectedLevel();
	if (selectedLevel != null) {
	    if (selectedLevel.getMaximumMoves() != spinnerValue) {
		selectedLevel.setMaximumMoves(spinnerValue);
		selectedLevel.setRemainingMoves(spinnerValue);
		selectedLevel.setSaveNeeded(true);
		levelDesignerController.updateLevelDesignerFrameTitle();
	    }
	}
    }

}
