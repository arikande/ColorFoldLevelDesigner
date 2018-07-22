package game.colorfold.designer.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ColorFoldLevelSolution {
    private File file;
    private List<ColorFoldMove> moves;

    public ColorFoldLevelSolution() {
	moves = new ArrayList<ColorFoldMove>();
    }

    public void clearMoves() {
	moves.clear();
    }

    public void addMove(ColorFoldMove colorFoldMove) {
	moves.add(colorFoldMove);
    }

    public Iterator<ColorFoldMove> getMovesIterator() {
	return moves.iterator();
    }

    public File getFile() {
	return file;
    }

    public void setFile(File file) {
	this.file = file;
    }

    public int getMoveCount() {
	return moves.size();
    }
}
