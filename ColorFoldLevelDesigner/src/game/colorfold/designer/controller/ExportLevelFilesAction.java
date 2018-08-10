package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

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
