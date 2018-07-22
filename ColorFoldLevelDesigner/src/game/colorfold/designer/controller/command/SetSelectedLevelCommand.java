package game.colorfold.designer.controller.command;

import game.colorfold.designer.controller.LevelDesignerController;
import game.colorfold.designer.model.ColorFoldLevel;

public class SetSelectedLevelCommand {

    private LevelDesignerController levelDesignerController;
    private ColorFoldLevel colorFoldLevel;

    public SetSelectedLevelCommand(LevelDesignerController levelDesignerController, ColorFoldLevel colorFoldLevel) {
	this.levelDesignerController = levelDesignerController;
	this.colorFoldLevel = colorFoldLevel;
    }

    public boolean execute() {
	levelDesignerController.getLevelDesignerModel().setSelectedLevel(colorFoldLevel);
	return true;
    }

}
