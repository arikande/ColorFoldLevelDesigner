package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.JToggleButton;

import game.colorfold.designer.model.ColorFoldLevelSolution;
import game.colorfold.designer.model.ColorFoldMove;
import game.colorfold.designer.utility.ColorFoldMoveXMLConverter;

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
	    ColorFoldLevelSolution recordedSolution = levelDesignerController.getLevelDesignerModel()
		    .getRecordedSolution();
	    StringBuilder fileContent = new StringBuilder();
	    fileContent.append("<colorfold_level_solution>");
	    fileContent.append(System.getProperty("line.separator"));
	    Iterator<ColorFoldMove> movesIterator = recordedSolution.getMovesIterator();
	    while (movesIterator.hasNext()) {
		ColorFoldMove colorFoldMove = movesIterator.next();
		fileContent.append(new ColorFoldMoveXMLConverter().toXML(colorFoldMove));
	    }
	    fileContent.append("</colorfold_level_solution>");
	    System.out.println(fileContent);
	}
    }

}
