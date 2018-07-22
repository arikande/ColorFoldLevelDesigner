package game.colorfold.designer.controller;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.model.Coordinate;
import game.colorfold.designer.model.LevelDesignerModel;
import game.colorfold.designer.view.LevelDesignerFrame;

public class SolutionPanelMouseAdapter extends MouseInputAdapter {

    private LevelDesignerController levelDesignerController;
    private LevelDesignerFrame levelDesignerFrame;

    public SolutionPanelMouseAdapter(LevelDesignerController levelDesignerController,
	    LevelDesignerFrame levelDesignerFrame) {
	this.levelDesignerController = levelDesignerController;
	this.levelDesignerFrame = levelDesignerFrame;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
	if (levelDesignerController.getLevelDesignerModel().getMode() == LevelDesignerModel.DESIGN_MODE) {
	    Point point = mouseEvent.getPoint();
	    Coordinate coordinateAtPoint = levelDesignerFrame.getSolutionPanelCoordinateAtPoint(point);
	    ColorFoldLevel colorFoldLevel = levelDesignerController.getLevelDesignerModel().getSelectedLevel();
	    int selectedColorId = levelDesignerController.getLevelDesignerModel().getSelectedColorId();
	    if (coordinateAtPoint != null && colorFoldLevel != null) {
		if ((mouseEvent.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
		    if (colorFoldLevel.isValidCoordinate(coordinateAtPoint) && selectedColorId != -1) {
			colorFoldLevel.getSolution().put(coordinateAtPoint, selectedColorId);
			colorFoldLevel.setSaveNeeded(true);
		    }
		} else if ((mouseEvent.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
		    if (colorFoldLevel.isValidCoordinate(coordinateAtPoint)) {
			colorFoldLevel.removeValidCoordinate(coordinateAtPoint);
		    } else {
			colorFoldLevel.addValidCoordinate(coordinateAtPoint);
		    }
		    colorFoldLevel.setSaveNeeded(true);
		}
		levelDesignerController.updateLevelDesignerFrameTitle();
		levelDesignerController.updateLevelDesignerMainPanel();
	    }
	}
    }

}