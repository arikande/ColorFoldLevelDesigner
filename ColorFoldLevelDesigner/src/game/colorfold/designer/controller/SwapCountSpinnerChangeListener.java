package game.colorfold.designer.controller;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import game.colorfold.designer.model.ColorFoldLevel;

public class SwapCountSpinnerChangeListener implements ChangeListener {

	private LevelDesignerController levelDesignerController;

	public SwapCountSpinnerChangeListener(LevelDesignerController levelDesignerController) {
		this.levelDesignerController = levelDesignerController;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		int spinnerValue = (int) ((JSpinner) e.getSource()).getValue();
		ColorFoldLevel selectedLevel = levelDesignerController.getLevelDesignerModel().getSelectedLevel();
		if (selectedLevel != null) {
			if (spinnerValue == 1 && !selectedLevel.isSwapEnabled()) {
				selectedLevel.setSwapEnabled(true);
				selectedLevel.setSaveNeeded(true);
				levelDesignerController.updateLevelDesignerFrameTitle();
			} else if (spinnerValue == 0 && selectedLevel.isSwapEnabled()) {
				selectedLevel.setSwapEnabled(false);
				selectedLevel.setSaveNeeded(true);
				levelDesignerController.updateLevelDesignerFrameTitle();
			}
		}
	}

}
