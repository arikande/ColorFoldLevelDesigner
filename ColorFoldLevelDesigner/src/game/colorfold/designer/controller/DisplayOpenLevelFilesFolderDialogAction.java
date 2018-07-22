package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import game.colorfold.designer.controller.command.SetSelectedLevelCommand;
import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.utility.LevelFileXMLConverter;

public class DisplayOpenLevelFilesFolderDialogAction extends AbstractAction {

    private LevelDesignerController levelDesignerController;
    private JFileChooser openLevelFilesFolderChooser;

    public DisplayOpenLevelFilesFolderDialogAction(LevelDesignerController levelDesignerController) {
	super("Open folder...");
	this.levelDesignerController = levelDesignerController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	int option = getOpenLevelFilesFolderChooser().showOpenDialog(levelDesignerController.getLevelDesignerFrame());
	if (option == JFileChooser.APPROVE_OPTION) {
	    File levelFilesFolder = getOpenLevelFilesFolderChooser().getSelectedFile();
	    levelDesignerController.getLevelDesignerModel().setLevelFilesFolder(levelFilesFolder);
	    File[] files = levelFilesFolder.listFiles();
	    List<File> levelFiles = new ArrayList<File>();
	    for (File file : files) {
		String fileName = file.getName();
		if (file.isFile() && fileName.startsWith("level") && fileName.endsWith(".xml")) {
		    levelFiles.add(file);
		}
	    }
	    levelDesignerController.getLevelDesignerModel().clearLevels();
	    new SetSelectedLevelCommand(levelDesignerController, null).execute();
	    for (File levelFile : levelFiles) {
		ColorFoldLevel colorFoldLevel = new LevelFileXMLConverter().parse(levelFile);
		colorFoldLevel.setFile(levelFile);
		levelDesignerController.getLevelDesignerModel().addLevel(colorFoldLevel);
	    }
	    levelDesignerController.selectFirstLevel();
	    levelDesignerController.updateLevelNavigationList();
	    levelDesignerController.updateLevelDesignerFrameTitle();
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
