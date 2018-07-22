package game.colorfold.designer.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Iterator;

import javax.swing.AbstractAction;

import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.model.LevelDesignerModel;
import game.colorfold.designer.utility.LevelFileXMLConverter;

public class SaveAllLevelsAction extends AbstractAction {

	private LevelDesignerController levelDesignerController;

	public SaveAllLevelsAction(LevelDesignerController levelDesignerController) {
		super("Save all");
		this.levelDesignerController = levelDesignerController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LevelDesignerModel levelDesignerModel = levelDesignerController.getLevelDesignerModel();
		Iterator<File> levelFilesIterator = levelDesignerModel.getLevelFilesIterator();
		while (levelFilesIterator.hasNext()) {
			File file = levelFilesIterator.next();
			ColorFoldLevel colorFoldLevel = levelDesignerModel.getLevel(file);
			if (colorFoldLevel.isSaveNeeded()) {
				new LevelFileXMLConverter().writeToFile(colorFoldLevel);
				colorFoldLevel.setSaveNeeded(false);
			}
		}
		levelDesignerController.updateLevelDesignerFrameTitle();
	}

}
