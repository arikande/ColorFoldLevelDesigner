package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;

import javax.swing.AbstractAction;

import game.colorfold.designer.model.ColorFoldMove;
import game.colorfold.designer.utility.ColorFoldMoveXMLConverter;
import game.colorfold.designer.view.FindLevelSolutionDialog;

public class WriteSolutionToFileAction extends AbstractAction {

    private LevelSolverRunnable levelSolverRunnable;
    private LevelDesignerController levelDesignerController;

    public WriteSolutionToFileAction(LevelDesignerController levelDesignerController,
	    LevelSolverRunnable levelSolverRunnable) {
	super("Write to file");
	this.levelSolverRunnable = levelSolverRunnable;
	this.levelDesignerController = levelDesignerController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	StringBuilder fileContent = new StringBuilder();
	fileContent.append("<colorfold_level_solution>");
	fileContent.append(System.getProperty("line.separator"));
	int size = levelSolverRunnable.getMovesStack().size();
	for (int i = 0; i < size; i++) {
	    ColorFoldMove colorFoldMove = levelSolverRunnable.getMovesStack().get(i);
	    fileContent.append(new ColorFoldMoveXMLConverter().toXML(colorFoldMove));
	}
	fileContent.append("</colorfold_level_solution>");

	String levelFileName = levelSolverRunnable.getColorFoldLevel().getFile().getName();

	File file = new File(levelDesignerController.getLevelDesignerModel().getSolutionFilesFolder(), levelFileName);
	try {
	    FileWriter fileWriter = new FileWriter(file);
	    fileWriter.write(fileContent.toString());
	    fileWriter.close();
	} catch (Exception exception) {
	    exception.printStackTrace();
	}

    }

}
