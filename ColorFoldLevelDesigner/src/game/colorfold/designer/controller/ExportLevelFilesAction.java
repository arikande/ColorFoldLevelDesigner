package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.utility.LevelFileXMLConverter;

public class ExportLevelFilesAction extends AbstractAction {

    private static final String DEFAULT_LEVEL_DIRECTORY = "/home/arikande/ColorFoldLevels";
    private LevelDesignerController levelDesignerController;
    private JFileChooser exportLevelFilesFolderChooser;

    public ExportLevelFilesAction(LevelDesignerController levelDesignerController) {
	super("Export level files...");
	this.levelDesignerController = levelDesignerController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	int option = getExportLevelFilesFolderChooser().showOpenDialog(levelDesignerController.getLevelDesignerFrame());
	if (option == JFileChooser.APPROVE_OPTION) {
	    File exportLevelFilesFolder = getExportLevelFilesFolderChooser().getSelectedFile();
	    Iterator<ColorFoldLevel> levelsIterator = levelDesignerController.getLevelDesignerModel()
		    .getLevelsIterator();
	    while (levelsIterator.hasNext()) {
		ColorFoldLevel colorFoldLevel = levelsIterator.next();
		String fileName = colorFoldLevel.getFile().getName();
		String exportFileName = exportLevelFilesFolder.getAbsolutePath() + System.getProperty("file.separator")
			+ fileName;
		File exportFile = new File(exportFileName);
		LevelFileXMLConverter levelFileXMLConverter = new LevelFileXMLConverter();
		levelFileXMLConverter.setAddingSolutionMoves(false);
		levelFileXMLConverter.writeToFile(colorFoldLevel, exportFile);
	    }

	}
    }

    private JFileChooser getExportLevelFilesFolderChooser() {
	if (exportLevelFilesFolderChooser == null) {
	    exportLevelFilesFolderChooser = new JFileChooser(DEFAULT_LEVEL_DIRECTORY);
	    exportLevelFilesFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}
	return exportLevelFilesFolderChooser;
    }

}
