package game.colorfold.designer.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ColorFoldLevel {
	private List<Coordinate> validCoordinates;
	private Map<Coordinate, Integer> puzzle;
	private Map<Coordinate, Integer> solution;
	private boolean swapEnabled;
	private boolean divideEnabled;
	private boolean duplicateEnabled;
	private int maximumMoves;
	private int remainingMoves;
	private File file;
	private boolean saveNeeded;
	private ColorFoldLevelSolution colorFoldLevelSolution;

	public ColorFoldLevel() {
		validCoordinates = new ArrayList<Coordinate>();
		puzzle = new HashMap<Coordinate, Integer>();
		solution = new HashMap<Coordinate, Integer>();
		maximumMoves = 1;
	}

	public void reset() {
		validCoordinates.clear();
		puzzle.clear();
		solution.clear();
		swapEnabled = false;
		divideEnabled = false;
		duplicateEnabled = false;
		maximumMoves = 0;
		remainingMoves = 0;
		file = null;
		saveNeeded = false;
	}

	public ColorFoldLevel clone() {
		ColorFoldLevel clone = new ColorFoldLevel();
		copyTo(clone);
		return clone;
	}

	public ColorFoldLevel copyTo(ColorFoldLevel colorFoldLevel) {
		Iterator<Coordinate> validCoordinatesIterator = getValidCoordinatesIterator();
		while (validCoordinatesIterator.hasNext()) {
			Coordinate validCoordinate = validCoordinatesIterator.next();
			colorFoldLevel.addValidCoordinate(validCoordinate);
			if (getPuzzle().containsKey(validCoordinate) && getPuzzle().get(validCoordinate) != null) {
				colorFoldLevel.getPuzzle().put(validCoordinate, getPuzzle().get(validCoordinate));
			}
			if (getSolution().containsKey(validCoordinate) && getSolution().get(validCoordinate) != null) {
				colorFoldLevel.getSolution().put(validCoordinate, getSolution().get(validCoordinate));
			}
		}
		colorFoldLevel.setSwapEnabled(isSwapEnabled());
		colorFoldLevel.setDivideEnabled(isDivideEnabled());
		colorFoldLevel.setDuplicateEnabled(isDuplicateEnabled());
		colorFoldLevel.setMaximumMoves(getMaximumMoves());
		colorFoldLevel.setRemainingMoves(getRemainingMoves());
		colorFoldLevel.setFile(getFile());
		colorFoldLevel.setSaveNeeded(isSaveNeeded());
		return colorFoldLevel;
	}

	public int getPuzzleColorId(Coordinate coordinate) {
		int colorId = 0;
		if (getPuzzle().containsKey(coordinate) && getPuzzle().get(coordinate) != null) {
			colorId = getPuzzle().get(coordinate);
		}
		return colorId;
	}

	public boolean isSolved() {
		Iterator<Coordinate> validCoordinatesIterator = getValidCoordinatesIterator();
		while (validCoordinatesIterator.hasNext()) {
			Coordinate validCoordinate = validCoordinatesIterator.next();
			int puzzleColorId = 0;
			if (getPuzzle().containsKey(validCoordinate) && getPuzzle().get(validCoordinate) != null) {
				puzzleColorId = getPuzzle().get(validCoordinate);
			}
			int solutionColorId = 0;
			if (getSolution().containsKey(validCoordinate) && getSolution().get(validCoordinate) != null) {
				solutionColorId = getSolution().get(validCoordinate);
			}
			if (puzzleColorId != solutionColorId) {
				return false;
			}
		}
		return true;
	}

	public List<Coordinate> getNeighborCoordinates(Coordinate coordinate) {
		List<Coordinate> neighborCoordinates = new ArrayList<Coordinate>();
		Coordinate leftCoordinate = new Coordinate(coordinate.getAbscissa() - 1, coordinate.getOrdinate());
		if (isValidCoordinate(leftCoordinate)) {
			neighborCoordinates.add(leftCoordinate);
		}
		Coordinate rightCoordinate = new Coordinate(coordinate.getAbscissa() + 1, coordinate.getOrdinate());
		if (isValidCoordinate(rightCoordinate)) {
			neighborCoordinates.add(rightCoordinate);
		}
		if ((coordinate.getAbscissa() + coordinate.getOrdinate()) % 2 == 0) {
			Coordinate bottomCoordinate = new Coordinate(coordinate.getAbscissa(), coordinate.getOrdinate() + 1);
			if (isValidCoordinate(bottomCoordinate)) {
				neighborCoordinates.add(bottomCoordinate);
			}
		} else {
			Coordinate topCoordinate = new Coordinate(coordinate.getAbscissa(), coordinate.getOrdinate() - 1);
			if (isValidCoordinate(topCoordinate)) {
				neighborCoordinates.add(topCoordinate);
			}
		}
		return neighborCoordinates;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public boolean areValidCoordinatesEqualTo(ColorFoldLevel otherColorFoldLevel) {
		Iterator<Coordinate> validCoordinatesIterator = getValidCoordinatesIterator();
		while (validCoordinatesIterator.hasNext()) {
			Coordinate validCoordinate = validCoordinatesIterator.next();
			if (!otherColorFoldLevel.isValidCoordinate(validCoordinate)) {
				return false;
			}
		}
		Iterator<Coordinate> otherValidCoordinatesIterator = otherColorFoldLevel.getValidCoordinatesIterator();
		while (otherValidCoordinatesIterator.hasNext()) {
			Coordinate validCoordinate = otherValidCoordinatesIterator.next();
			if (!isValidCoordinate(validCoordinate)) {
				return false;
			}
		}
		return true;
	}

	public int getValidCoordinatesSize() {
		return validCoordinates.size();
	}

	public void addValidCoordinate(Coordinate coordinate) {
		validCoordinates.add(coordinate);
		puzzle.put(coordinate, 0);
		solution.put(coordinate, 0);
	}

	public void removeValidCoordinate(Coordinate coordinate) {
		validCoordinates.remove(coordinate);
		puzzle.remove(coordinate);
		solution.remove(coordinate);
	}

	public Iterator<Coordinate> getValidCoordinatesIterator() {
		return validCoordinates.iterator();
	}

	public boolean isValidCoordinate(Coordinate coordinate) {
		return validCoordinates.contains(coordinate);
	}

	public void setAllValidCoordinatesToColorId(Integer colorId) {
		Iterator<Coordinate> validCoordinatesIterator = getValidCoordinatesIterator();
		while (validCoordinatesIterator.hasNext()) {
			Coordinate coordinate = validCoordinatesIterator.next();
			puzzle.put(coordinate, colorId);
			solution.put(coordinate, colorId);
		}
	}

	public int getPuzzleTotalColorValue() {
		int totalColorValue = 0;
		if (puzzle != null) {
			for (Integer colorValue : puzzle.values()) {
				totalColorValue = totalColorValue + colorValue;
			}
		}
		return totalColorValue;
	}

	public int getSolutionTotalColorValue() {
		int totalColorValue = 0;
		if (solution != null) {
			for (Integer colorValue : solution.values()) {
				totalColorValue = totalColorValue + colorValue;
			}
		}
		return totalColorValue;
	}

	public int getMaximumAbscissa() {
		int maximum = 0;
		Iterator<Coordinate> validCoordinatesIterator = getValidCoordinatesIterator();
		while (validCoordinatesIterator.hasNext()) {
			Coordinate coordinate = validCoordinatesIterator.next();
			if (coordinate.getAbscissa() > maximum) {
				maximum = coordinate.getAbscissa();
			}
		}
		return maximum;
	}

	public int getMaximumOrdinate() {
		int maximum = 0;
		Iterator<Coordinate> validCoordinatesIterator = getValidCoordinatesIterator();
		while (validCoordinatesIterator.hasNext()) {
			Coordinate coordinate = validCoordinatesIterator.next();
			if (coordinate.getOrdinate() > maximum) {
				maximum = coordinate.getOrdinate();
			}
		}
		return maximum;
	}

	public void putSolutionTile(Coordinate coordinate, int colorId) {
		solution.put(coordinate, colorId);
	}

	public Map<Coordinate, Integer> getPuzzle() {
		return puzzle;
	}

	public Map<Coordinate, Integer> getSolution() {
		return solution;
	}

	public boolean areCoordinatesNeighbor(Coordinate coordinate1, Coordinate coordinate2) {
		if (coordinate1 == null || coordinate2 == null) {
			return false;
		}
		if (!isValidCoordinate(coordinate1) || !isValidCoordinate(coordinate2)) {
			return false;
		}
		if (coordinate1.getOrdinate() == coordinate2.getOrdinate()) {
			return Math.abs(coordinate1.getAbscissa() - coordinate2.getAbscissa()) == 1;
		}
		if (coordinate1.getAbscissa() == coordinate2.getAbscissa()
				&& Math.abs(coordinate1.getOrdinate() - coordinate2.getOrdinate()) == 1) {
			int minimumOrdinate = Math.min(coordinate1.getOrdinate(), coordinate2.getOrdinate());
			return (minimumOrdinate % 2 == 0 && coordinate1.getAbscissa() % 2 == 0)
					|| (minimumOrdinate % 2 == 1 && coordinate1.getAbscissa() % 2 == 1);
		}
		return false;
	}

	public boolean isSwapEnabled() {
		return swapEnabled;
	}

	public void setSwapEnabled(boolean swapEnabled) {
		this.swapEnabled = swapEnabled;
	}

	public boolean isDivideEnabled() {
		return divideEnabled;
	}

	public void setDivideEnabled(boolean divideEnabled) {
		this.divideEnabled = divideEnabled;
	}

	public boolean isDuplicateEnabled() {
		return duplicateEnabled;
	}

	public void setDuplicateEnabled(boolean duplicateEnabled) {
		this.duplicateEnabled = duplicateEnabled;
	}

	public int getMaximumMoves() {
		return maximumMoves;
	}

	public void setMaximumMoves(int maximumMoves) {
		this.maximumMoves = maximumMoves;
	}

	public int getRemainingMoves() {
		return remainingMoves;
	}

	public void setRemainingMoves(int remainingMoves) {
		this.remainingMoves = remainingMoves;
	}

	public boolean isSaveNeeded() {
		return saveNeeded;
	}

	public void setSaveNeeded(boolean saveNeeded) {
		this.saveNeeded = saveNeeded;
	}

	public int getPuzzleSolutionDifferenceSize() {
		int size = 0;
		Iterator<Coordinate> validCoordinatesIterator = getValidCoordinatesIterator();
		while (validCoordinatesIterator.hasNext()) {
			Coordinate coordinate = validCoordinatesIterator.next();
			if (getPuzzle().get(coordinate) != getSolution().get(coordinate)) {
				size = size + 1;
			}
		}
		return size;
	}

	public ColorFoldLevelSolution getColorFoldLevelSolution() {
		return colorFoldLevelSolution;
	}

	public void setColorFoldLevelSolution(ColorFoldLevelSolution colorFoldLevelSolution) {
		this.colorFoldLevelSolution = colorFoldLevelSolution;
	}

}