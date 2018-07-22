package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.colorfold.designer.model.LevelDesignerModel;

public class DesignModeRadioButtonActionListener implements ActionListener {

	private LevelDesignerController levelDesignerController;

	public DesignModeRadioButtonActionListener(LevelDesignerController levelDesignerController) {
		this.levelDesignerController = levelDesignerController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		levelDesignerController.getLevelDesignerModel().setMode(LevelDesignerModel.DESIGN_MODE);
		levelDesignerController.getLevelDesignerFrame().setSwapInfoLabelEnabled(true);
		levelDesignerController.getLevelDesignerFrame().setSwapCountSpinnerEnabled(true);
		levelDesignerController.getLevelDesignerFrame().setDivideInfoLabelEnabled(true);
		levelDesignerController.getLevelDesignerFrame().setDivideCountSpinnerEnabled(true);
		levelDesignerController.getLevelDesignerFrame().setDuplicateInfoLabelEnabled(true);
		levelDesignerController.getLevelDesignerFrame().setDuplicateCountSpinnerEnabled(true);
		levelDesignerController.getLevelDesignerFrame().setMaximumMovesInfoLabelEnabled(true);
		levelDesignerController.getLevelDesignerFrame().setMaximumMovesSpinnerEnabled(true);
		levelDesignerController.getLevelDesignerFrame().setSwapPlayModeToggleButtonEnabled(false);
		levelDesignerController.getLevelDesignerFrame().setDividePlayModeToggleButtonEnabled(false);
		levelDesignerController.getLevelDesignerFrame().setDuplicatePlayModeToggleButtonEnabled(false);
	}

}
