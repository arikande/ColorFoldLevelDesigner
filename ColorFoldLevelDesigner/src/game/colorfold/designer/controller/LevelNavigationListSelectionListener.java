package game.colorfold.designer.controller;

import java.io.File;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import game.colorfold.designer.controller.command.SetSelectedLevelCommand;
import game.colorfold.designer.model.ColorFoldLevel;

public class LevelNavigationListSelectionListener implements ListSelectionListener {

    private LevelDesignerController levelDesignerController;

    public LevelNavigationListSelectionListener(LevelDesignerController levelDesignerController) {
	this.levelDesignerController = levelDesignerController;
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
	if (!listSelectionEvent.getValueIsAdjusting()) {
	    JList<File> navigationList = (JList<File>) listSelectionEvent.getSource();
	    File selectedFile = navigationList.getSelectedValue();
	    if (selectedFile != null) {
		ColorFoldLevel colorFoldLevel = levelDesignerController.getLevelDesignerModel().getLevel(selectedFile);
		new SetSelectedLevelCommand(levelDesignerController, colorFoldLevel).execute();
		levelDesignerController.updateLevelDesignerMainPanel();
		levelDesignerController.updateLevelDesignerFrameTitle();
	    }
	}
    }

}
