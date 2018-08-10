package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Iterator;

import javax.swing.AbstractAction;

import game.colorfold.designer.controller.command.TestLevelSolutionCommand;
import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.model.ColorFoldLevelSolution;
import game.colorfold.designer.model.ColorFoldMove;
import game.colorfold.designer.view.TestSolutionsResultDialog;

public class DisplayTestSolutionsDialogAction extends AbstractAction {

    private LevelDesignerController levelDesignerController;

    public DisplayTestSolutionsDialogAction(LevelDesignerController levelDesignerController) {
	super("Test solutions");
	this.levelDesignerController = levelDesignerController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	TestSolutionsResultDialog testSolutionsResultDialog = new TestSolutionsResultDialog(
		levelDesignerController.getLevelDesignerFrame());
	long start = System.currentTimeMillis();
	Iterator<File> levelFilesIterator = levelDesignerController.getLevelDesignerModel().getLevelFilesIterator();
	int passedCount = 0;
	while (levelFilesIterator.hasNext()) {
	    File file = levelFilesIterator.next();
	    ColorFoldLevel colorFoldLevel = levelDesignerController.getLevelDesignerModel().getLevel(file);
	    ColorFoldLevelSolution colorFoldLevelSolution = colorFoldLevel.getColorFoldLevelSolution();
	    if (colorFoldLevelSolution != null) {
		if (colorFoldLevel.getMaximumMoves() != colorFoldLevelSolution.getMoveCount()) {
		    testSolutionsResultDialog.appendTestOutputText(
			    file.getName() + ".....Level (" + colorFoldLevel.getMaximumMoves() + ") and solution ("
				    + colorFoldLevelSolution.getMoveCount() + ") move counts are different");
		    testSolutionsResultDialog.appendTestOutputText(System.getProperty("line.separator"));
		} else if (!colorFoldLevel.isSwapEnabled()
			&& solutionContainsMoveType(colorFoldLevelSolution, ColorFoldMove.SWAP_MOVE)) {
		    testSolutionsResultDialog
			    .appendTestOutputText(file.getName() + ".....Swap move is not allowed for this level");
		    testSolutionsResultDialog.appendTestOutputText(System.getProperty("line.separator"));
		} else if (!colorFoldLevel.isDivideEnabled()
			&& solutionContainsMoveType(colorFoldLevelSolution, ColorFoldMove.DIVIDE_MOVE)) {
		    testSolutionsResultDialog
			    .appendTestOutputText(file.getName() + ".....Divide move is not allowed for this level");
		    testSolutionsResultDialog.appendTestOutputText(System.getProperty("line.separator"));
		} else if (!colorFoldLevel.isDuplicateEnabled()
			&& solutionContainsMoveType(colorFoldLevelSolution, ColorFoldMove.DUPLICATE_MOVE)) {
		    testSolutionsResultDialog
			    .appendTestOutputText(file.getName() + ".....Duplicate move is not allowed for this level");
		    testSolutionsResultDialog.appendTestOutputText(System.getProperty("line.separator"));
		} else {
		    TestLevelSolutionCommand testLevelSolutionCommand = new TestLevelSolutionCommand(colorFoldLevel,
			    colorFoldLevelSolution);
		    boolean result = testLevelSolutionCommand.execute();
		    if (result) {
			testSolutionsResultDialog.appendTestOutputText(file.getName() + ".....Success in "
				+ colorFoldLevelSolution.getMoveCount() + " moves.");
			passedCount = passedCount + 1;
		    } else {
			testSolutionsResultDialog.appendTestOutputText(file.getName() + ".....Failed");
		    }
		    testSolutionsResultDialog.appendTestOutputText(System.getProperty("line.separator"));
		}
	    } else {
		testSolutionsResultDialog.appendTestOutputText(file.getName() + ".....No solution");
		testSolutionsResultDialog.appendTestOutputText(System.getProperty("line.separator"));
	    }
	}
	long duration = System.currentTimeMillis() - start;
	testSolutionsResultDialog.appendTestOutputText(System.getProperty("line.separator"));
	int levelCount = levelDesignerController.getLevelDesignerModel().getLevelCount();
	StringBuilder testOutput = new StringBuilder();
	testOutput.append(levelCount);
	testOutput.append(" tests completed in ");
	testOutput.append(duration);
	testOutput.append(" milliseconds.");
	testOutput.append(System.getProperty("line.separator"));
	testOutput.append(passedCount);
	testOutput.append(" tests passed. ");
	testOutput.append(levelCount - passedCount);
	testOutput.append(" tests failed.");
	testSolutionsResultDialog.appendTestOutputText(testOutput.toString());
	testSolutionsResultDialog.setVisible(true);
    }

    private boolean solutionContainsMoveType(ColorFoldLevelSolution colorFoldLevelSolution, int moveType) {
	Iterator<ColorFoldMove> movesIterator = colorFoldLevelSolution.getMovesIterator();
	while (movesIterator.hasNext()) {
	    ColorFoldMove colorFoldMove = movesIterator.next();
	    if (colorFoldMove.getMoveType() == moveType) {
		return true;
	    }
	}
	return false;
    }

}
