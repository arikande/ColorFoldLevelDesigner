package game.colorfold.designer.controller.command;

import java.util.Iterator;

import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.model.ColorFoldLevelSolution;
import game.colorfold.designer.model.ColorFoldMove;

public class TestLevelSolutionCommand {

    private ColorFoldLevel colorFoldLevel;
    private ColorFoldLevelSolution colorFoldLevelSolution;

    public TestLevelSolutionCommand(ColorFoldLevel colorFoldLevel, ColorFoldLevelSolution colorFoldLevelSolution) {
	this.colorFoldLevel = colorFoldLevel;
	this.colorFoldLevelSolution = colorFoldLevelSolution;
    }

    public boolean execute() {
	ColorFoldLevel testLevel = colorFoldLevel.clone();
	Iterator<ColorFoldMove> movesIterator = colorFoldLevelSolution.getMovesIterator();
	while (movesIterator.hasNext()) {
	    ColorFoldMove colorFoldMove = movesIterator.next();
	    FoldColorCommand foldColorCommand = new FoldColorCommand(testLevel.getPuzzle(),
		    colorFoldMove.getFromCoordinate(), colorFoldMove.getToCoordinate(), colorFoldMove.getMoveType());
	    foldColorCommand.execute();
	}
	if (testLevel.isSolved()) {
	    return true;
	} else {
	    return false;
	}
    }

}
