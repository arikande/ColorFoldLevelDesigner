package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Iterator;

import javax.swing.AbstractAction;

import game.colorfold.designer.controller.command.TestLevelSolutionCommand;
import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.model.ColorFoldLevelSolution;
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
	while (levelFilesIterator.hasNext()) {
	    File file = levelFilesIterator.next();
	    ColorFoldLevel colorFoldLevel = levelDesignerController.getLevelDesignerModel().getLevel(file);
	    ColorFoldLevelSolution colorFoldLevelSolution = levelDesignerController.getLevelDesignerModel()
		    .getLevelSolution(file.getName());
	    if (colorFoldLevelSolution != null) {

		if (colorFoldLevel.getMaximumMoves() != colorFoldLevelSolution.getMoveCount()) {
		    testSolutionsResultDialog.appendTestOutputText(file.getName() + ".....Level and solution move counts are different");
		    testSolutionsResultDialog.appendTestOutputText(System.getProperty("line.separator"));
		} else {
		    TestLevelSolutionCommand testLevelSolutionCommand = new TestLevelSolutionCommand(colorFoldLevel,
			    colorFoldLevelSolution);
		    boolean result = testLevelSolutionCommand.execute();
		    if (result) {
			testSolutionsResultDialog.appendTestOutputText(file.getName() + ".....Success");
		    } else {
			testSolutionsResultDialog.appendTestOutputText(file.getName() + ".....Failed");
		    }
		    testSolutionsResultDialog.appendTestOutputText(System.getProperty("line.separator"));
		}
	    } else {
		testSolutionsResultDialog.appendTestOutputText(file.getName() + ".....No solution file");
		testSolutionsResultDialog.appendTestOutputText(System.getProperty("line.separator"));
	    }
	}
	long duration = System.currentTimeMillis() - start;
	testSolutionsResultDialog.appendTestOutputText(System.getProperty("line.separator"));
	testSolutionsResultDialog.appendTestOutputText("Tests completed in " + duration + " milliseconds.");
	testSolutionsResultDialog.setVisible(true);
    }

}
