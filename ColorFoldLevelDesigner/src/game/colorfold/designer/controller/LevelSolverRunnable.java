package game.colorfold.designer.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.model.ColorFoldMove;
import game.colorfold.designer.model.Coordinate;
import game.colorfold.designer.model.LevelDesignerModel;
import game.colorfold.designer.view.FindLevelSolutionDialog;

public class LevelSolverRunnable implements Runnable {

    private int totalMoves;
    private ColorFoldLevel colorFoldLevel;
    private FindLevelSolutionDialog findLevelSolutionDialog;
    private Stack<ColorFoldMove> movesStack;
    private Stack<Map<Coordinate, Integer>> puzzleStatusStack;
    private boolean solutionFound;
    private boolean canceled;
    private boolean failed;

    public LevelSolverRunnable(ColorFoldLevel colorFoldLevel, FindLevelSolutionDialog findLevelSolutionDialog) {
	this.colorFoldLevel = colorFoldLevel;
	this.findLevelSolutionDialog = findLevelSolutionDialog;
	movesStack = new Stack<ColorFoldMove>();
	puzzleStatusStack = new Stack<Map<Coordinate, Integer>>();
	solutionFound = false;
    }

    @Override
    public void run() {
	long startTime = System.currentTimeMillis();
	solve(colorFoldLevel, 0);
	long duration = System.currentTimeMillis() - startTime;
	if (solutionFound) {
	    findLevelSolutionDialog.setSolutionMovesValueLabelText(String.valueOf(movesStack.size()));
	    findLevelSolutionDialog.setConfirmButtonVisible(true);
	    findLevelSolutionDialog.setWriteSolutionToFileButtonVisible(true);
	    findLevelSolutionDialog.setCancelButtonVisible(false);
	    for (ColorFoldMove colorFoldMove : movesStack) {
		findLevelSolutionDialog.appendSolutionOutputText(colorFoldMove.toString());
		findLevelSolutionDialog.appendSolutionOutputText(System.getProperty("line.separator"));
	    }
	} else if (canceled) {
	    findLevelSolutionDialog.appendSolutionOutputText("Canceled at " + totalMoves + " tries.");
	    findLevelSolutionDialog.setCancelButtonText("Close");
	} else {
	    failed = true;
	    findLevelSolutionDialog.appendSolutionOutputText("Could not solve after making " + totalMoves + " tries.");
	    findLevelSolutionDialog.setCancelButtonText("Close");
	}
	findLevelSolutionDialog.setDurationValueLabelText(String.valueOf(duration));
    }

    public boolean isCanceled() {
	return canceled;
    }

    public void setCanceled(boolean canceled) {
	this.canceled = canceled;
    }

    public boolean isFailed() {
	return failed;
    }

    public Stack<ColorFoldMove> getMovesStack() {
	return movesStack;
    }

    public ColorFoldLevel getColorFoldLevel() {
	return colorFoldLevel;
    }

    private void solve(ColorFoldLevel colorFoldLevel, int depth) {
	if (canceled) {
	    return;
	}
	if (colorFoldLevel.isSolved()) {
	    solutionFound = true;
	    return;
	}
	if (colorFoldLevel.getRemainingMoves() == 0) {
	    return;
	}
	if (checkPreviousPuzzleStates()) {
	    return;
	}
	findLevelSolutionDialog.setCurrentDepthValueLabelText(String.valueOf(depth));
	Iterator<Coordinate> validCoordinatesIterator = colorFoldLevel.getValidCoordinatesIterator();

	while (validCoordinatesIterator.hasNext()) {
	    Coordinate validCoordinate = validCoordinatesIterator.next();
	    List<Coordinate> neighborCoordinates = colorFoldLevel.getNeighborCoordinates(validCoordinate);
	    for (Coordinate neighborCoordinate : neighborCoordinates) {
		List<ColorFoldMove> moves = decideCoordinateMoves(colorFoldLevel, validCoordinate, neighborCoordinate);
		for (ColorFoldMove move : moves) {
		    if (!solutionFound) {
			makeMove(colorFoldLevel, move, depth);
			totalMoves++;
			findLevelSolutionDialog.setNumberOfTriesValueLabelText(String.valueOf(totalMoves));
		    }
		}

	    }
	}
    }

    private List<ColorFoldMove> decideCoordinateMoves(ColorFoldLevel colorFoldLevel, Coordinate fromCoordinate,
	    Coordinate toCoordinate) {
	List<ColorFoldMove> moves = new ArrayList<ColorFoldMove>();
	int fromColorId = colorFoldLevel.getPuzzleColorId(fromCoordinate);
	int toColorId = colorFoldLevel.getPuzzleColorId(toCoordinate);
	if (colorFoldLevel.isSwapEnabled() && fromColorId > toColorId) {
	    ColorFoldMove move = new ColorFoldMove(fromCoordinate, toCoordinate, ColorFoldMove.SWAP_MOVE);
	    if (!movesStack.isEmpty()) {
		ColorFoldMove previousMove = movesStack.peek();
		if (move.equals(previousMove)) {
		    move = null;
		}
	    }
	    if (move != null) {
		moves.add(move);
	    }
	}
	if (colorFoldLevel.isDivideEnabled()) {
	    if (fromColorId != 0) {
		int remainingColor = fromColorId / 2;
		int targetColor = toColorId + fromColorId - remainingColor;
		if (LevelDesignerModel.colorMap.containsKey(targetColor)) {
		    moves.add(new ColorFoldMove(fromCoordinate, toCoordinate, ColorFoldMove.DIVIDE_MOVE));
		}
	    }
	}
	if (colorFoldLevel.isDuplicateEnabled() && fromColorId != toColorId) {
	    moves.add(new ColorFoldMove(fromCoordinate, toCoordinate, ColorFoldMove.DUPLICATE_MOVE));
	}
	return moves;
    }

    private void makeMove(ColorFoldLevel colorFoldLevel, ColorFoldMove currentMove, int depth) {
	Coordinate fromCoordinate = currentMove.getFromCoordinate();
	Coordinate toCoordinate = currentMove.getToCoordinate();
	int fromColorId = colorFoldLevel.getPuzzleColorId(fromCoordinate);
	int toColorId = colorFoldLevel.getPuzzleColorId(toCoordinate);
	ColorFoldLevel trySolutionLevel = colorFoldLevel.clone();
	switch (currentMove.getMoveType()) {
	case ColorFoldMove.SWAP_MOVE:
	    trySolutionLevel.getPuzzle().put(fromCoordinate, toColorId);
	    trySolutionLevel.getPuzzle().put(toCoordinate, fromColorId);
	    break;
	case ColorFoldMove.DUPLICATE_MOVE:
	    trySolutionLevel.getPuzzle().put(toCoordinate, fromColorId);
	    break;
	case ColorFoldMove.DIVIDE_MOVE:
	    int remainingColor = fromColorId / 2;
	    int targetColor = toColorId + fromColorId - remainingColor;
	    trySolutionLevel.getPuzzle().put(fromCoordinate, remainingColor);
	    trySolutionLevel.getPuzzle().put(toCoordinate, targetColor);
	    break;
	}
	trySolutionLevel.setRemainingMoves(trySolutionLevel.getRemainingMoves() - 1);
	movesStack.push(currentMove);
	puzzleStatusStack.push(colorFoldLevel.getPuzzle());
	solve(trySolutionLevel, depth + 1);
	if (!solutionFound) {
	    movesStack.pop();
	    puzzleStatusStack.pop();
	}
    }

    private boolean checkPreviousPuzzleStates() {
	if (puzzleStatusStack.size() > 1) {
	    Map<Coordinate, Integer> lastState = puzzleStatusStack.peek();
	    for (int i = 0; i < puzzleStatusStack.size() - 1; i++) {
		Map<Coordinate, Integer> check = puzzleStatusStack.get(i);
		if (lastState.equals(check)) {
		    return true;
		}
	    }
	}
	return false;
    }

}
