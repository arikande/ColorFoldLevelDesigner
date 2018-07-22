package game.colorfold.designer.controller.command;

import java.util.Map;

import game.colorfold.designer.model.ColorFoldMove;
import game.colorfold.designer.model.Coordinate;
import game.colorfold.designer.model.LevelDesignerModel;

public class FoldColorCommand {

    private Map<Coordinate, Integer> puzzle;
    private int mode;
    private Coordinate fromCoordinate;
    private Coordinate toCoordinate;

    public FoldColorCommand(Map<Coordinate, Integer> puzzle, Coordinate fromCoordinate, Coordinate toCoordinate,
	    int mode) {
	this.puzzle = puzzle;
	this.fromCoordinate = fromCoordinate;
	this.toCoordinate = toCoordinate;
	this.mode = mode;
    }

    public boolean execute() {
	int fromCooordinateColorId = 0;
	int toCooordinateColorId = 0;
	if (puzzle.containsKey(fromCoordinate)) {
	    fromCooordinateColorId = puzzle.get(fromCoordinate);
	}
	if (puzzle.containsKey(toCoordinate)) {
	    toCooordinateColorId = puzzle.get(toCoordinate);
	}

	switch (mode) {
	case ColorFoldMove.SWAP_MOVE:
	    if (fromCooordinateColorId != toCooordinateColorId) {
		puzzle.put(fromCoordinate, toCooordinateColorId);
		puzzle.put(toCoordinate, fromCooordinateColorId);
		return true;
	    }
	    break;
	case ColorFoldMove.DIVIDE_MOVE:
	    if (fromCooordinateColorId != 0) {
		int remainingColor = fromCooordinateColorId / 2;
		int targetColor = toCooordinateColorId + fromCooordinateColorId - remainingColor;
		if (LevelDesignerModel.colorMap.containsKey(targetColor)) {
		    puzzle.put(fromCoordinate, remainingColor);
		    puzzle.put(toCoordinate, targetColor);
		    return true;
		}
	    }
	    break;
	case ColorFoldMove.DUPLICATE_MOVE:
	    if (fromCooordinateColorId != toCooordinateColorId) {
		puzzle.put(toCoordinate, fromCooordinateColorId);
		return true;
	    }
	    break;
	}
	return false;
    }

}
