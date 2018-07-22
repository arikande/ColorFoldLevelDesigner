package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.model.ColorFoldLevelSolution;
import game.colorfold.designer.utility.SolutionFileXMLConverter;

public class DisplayOpenSolutionFilesFolderDialogAction extends AbstractAction {

    private LevelDesignerController levelDesignerController;
    private JFileChooser openLevelFilesFolderChooser;

    public DisplayOpenSolutionFilesFolderDialogAction(LevelDesignerController levelDesignerController) {
	super("Open folder...");
	this.levelDesignerController = levelDesignerController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	int option = getOpenLevelFilesFolderChooser().showOpenDialog(levelDesignerController.getLevelDesignerFrame());
	if (option == JFileChooser.APPROVE_OPTION) {
	    File folder = getOpenLevelFilesFolderChooser().getSelectedFile();
	    levelDesignerController.getLevelDesignerModel().setSolutionFilesFolder(folder);
	    File[] files = folder.listFiles();
	    List<File> solutionFiles = new ArrayList<File>();
	    for (File file : files) {
		String fileName = file.getName();
		if (file.isFile() && fileName.startsWith("level") && fileName.endsWith(".xml")) {
		    solutionFiles.add(file);
		}
	    }
	    for (File levelFile : solutionFiles) {
		ColorFoldLevelSolution colorFoldLevelSolution = new SolutionFileXMLConverter().parse(levelFile);
		levelDesignerController.getLevelDesignerModel().addLevelSolution(colorFoldLevelSolution);
	    }
	}
    }

    private JFileChooser getOpenLevelFilesFolderChooser() {
	if (openLevelFilesFolderChooser == null) {
	    openLevelFilesFolderChooser = new JFileChooser();
	    openLevelFilesFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}
	return openLevelFilesFolderChooser;
    }

}
