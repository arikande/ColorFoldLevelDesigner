package game.colorfold.designer.controller;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.event.MouseInputAdapter;

import game.colorfold.designer.controller.command.FoldColorCommand;
import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.model.ColorFoldMove;
import game.colorfold.designer.model.Coordinate;
import game.colorfold.designer.model.LevelDesignerModel;
import game.colorfold.designer.view.LevelDesignerFrame;

public class PuzzlePanelMouseAdapter extends MouseInputAdapter {

    private LevelDesignerController levelDesignerController;
    private LevelDesignerFrame levelDesignerFrame;

    public PuzzlePanelMouseAdapter(LevelDesignerController levelDesignerController) {
	this.levelDesignerController = levelDesignerController;
	this.levelDesignerFrame = levelDesignerController.getLevelDesignerFrame();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
	if (levelDesignerController.getLevelDesignerModel().getMode() == LevelDesignerModel.DESIGN_MODE) {
	    Point point = mouseEvent.getPoint();
	    Coordinate coordinateAtPoint = levelDesignerFrame.getPuzzlePanelCoordinateAtPoint(point);
	    ColorFoldLevel colorFoldLevel = levelDesignerController.getLevelDesignerModel().getSelectedLevel();
	    int selectedColorId = levelDesignerController.getLevelDesignerModel().getSelectedColorId();
	    if (coordinateAtPoint != null && colorFoldLevel != null) {
		if ((mouseEvent.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
		    if (colorFoldLevel.isValidCoordinate(coordinateAtPoint) && selectedColorId != -1) {
			colorFoldLevel.getPuzzle().put(coordinateAtPoint, selectedColorId);
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

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
	if (levelDesignerController.getLevelDesignerModel().getMode() == LevelDesignerModel.PLAY_MODE) {
	    Point point = mouseEvent.getPoint();
	    Coordinate coordinateAtPoint = levelDesignerFrame.getPuzzlePanelCoordinateAtPoint(point);
	    levelDesignerController.getLevelDesignerModel().setLastMousePressedCoordinate(coordinateAtPoint);
	}
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
	LevelDesignerModel levelDesignerModel = levelDesignerController.getLevelDesignerModel();
	if (levelDesignerModel.getMode() == LevelDesignerModel.PLAY_MODE) {
	    Point point = mouseEvent.getPoint();
	    Coordinate fromCoordinate = levelDesignerModel.getLastMousePressedCoordinate();
	    Coordinate toCoordinate = levelDesignerFrame.getPuzzlePanelCoordinateAtPoint(point);
	    ColorFoldLevel selectedLevel = levelDesignerModel.getSelectedLevel();
	    if (selectedLevel.isValidCoordinate(fromCoordinate) && selectedLevel.isValidCoordinate(toCoordinate)
		    && selectedLevel.areCoordinatesNeighbor(fromCoordinate, toCoordinate)) {
		Map<Coordinate, Integer> puzzle = levelDesignerModel.getSelectedLevel().getPuzzle();
		boolean commandExecuted = new FoldColorCommand(puzzle, fromCoordinate, toCoordinate,
			levelDesignerModel.getFoldColorMode()).execute();
		if (commandExecuted) {
		    ColorFoldMove colorFoldMove = new ColorFoldMove(fromCoordinate, toCoordinate, levelDesignerModel.getFoldColorMode());
		    levelDesignerModel.getRecordedSolution().addMove(colorFoldMove);
		    
		    selectedLevel.setSaveNeeded(true);
		    levelDesignerController.updateLevelDesignerFrameTitle();
		    levelDesignerController.updateLevelDesignerMainPanel();
		}
	    }
	}
    }

}