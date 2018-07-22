package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.colorfold.designer.model.LevelDesignerModel;

public class PlayModeRadioButtonActionListener implements ActionListener {

	private LevelDesignerController levelDesignerController;

	public PlayModeRadioButtonActionListener(LevelDesignerController levelDesignerController) {
		this.levelDesignerController = levelDesignerController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		levelDesignerController.getLevelDesignerModel().setMode(LevelDesignerModel.PLAY_MODE);
		levelDesignerController.getLevelDesignerFrame().setSwapInfoLabelEnabled(false);
		levelDesignerController.getLevelDesignerFrame().setSwapCountSpinnerEnabled(false);
		levelDesignerController.getLevelDesignerFrame().setDivideInfoLabelEnabled(false);
		levelDesignerController.getLevelDesignerFrame().setDivideCountSpinnerEnabled(false);
		levelDesignerController.getLevelDesignerFrame().setDuplicateInfoLabelEnabled(false);
		levelDesignerController.getLevelDesignerFrame().setDuplicateCountSpinnerEnabled(false);
		levelDesignerController.getLevelDesignerFrame().setMaximumMovesInfoLabelEnabled(false);
		levelDesignerController.getLevelDesignerFrame().setMaximumMovesSpinnerEnabled(false);
		levelDesignerController.getLevelDesignerFrame().setSwapPlayModeToggleButtonEnabled(true);
		levelDesignerController.getLevelDesignerFrame().setDividePlayModeToggleButtonEnabled(true);
		levelDesignerController.getLevelDesignerFrame().setDuplicatePlayModeToggleButtonEnabled(true);
	}

}
