package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.model.ColorFoldLevelSolution;

public class RecordSolutionAction extends AbstractAction {

	private LevelDesignerController levelDesignerController;

	public RecordSolutionAction(LevelDesignerController levelDesignerController) {
		super("Record");
		this.levelDesignerController = levelDesignerController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JToggleButton recordToggleButton = ((JToggleButton) e.getSource());
		if (recordToggleButton.isSelected()) {
			recordToggleButton.setText("Recording...");
			levelDesignerController.getLevelDesignerModel().getRecordedSolution().clearMoves();
		} else {
			recordToggleButton.setText("Record");

			ColorFoldLevel colorFoldLevel = levelDesignerController.getLevelDesignerModel().getSelectedLevel();
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(levelDesignerController.getLevelDesignerFrame(),
					"Add solution to " + colorFoldLevel.getFile().getName() + "?", "Add solution", dialogButton);
			if (dialogResult == 0) {
				ColorFoldLevelSolution recordedSolution = levelDesignerController.getLevelDesignerModel()
						.getRecordedSolution();
				colorFoldLevel.setColorFoldLevelSolution(recordedSolution);
				colorFoldLevel.setSaveNeeded(true);
			}

		}
	}

}
