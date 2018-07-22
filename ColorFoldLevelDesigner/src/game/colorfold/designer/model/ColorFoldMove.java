package game.colorfold.designer.model;

public class ColorFoldMove {
    public static final int SWAP_MOVE = 0;
    public static final int DIVIDE_MOVE = 1;
    public static final int DUPLICATE_MOVE = 2;

    private Coordinate fromCoordinate;
    private Coordinate toCoordinate;
    private int moveType;

    public ColorFoldMove(Coordinate fromCoordinate, Coordinate toCoordinate, int moveType) {
	this.fromCoordinate = fromCoordinate;
	this.toCoordinate = toCoordinate;
	this.moveType = moveType;
    }

    public String toString() {
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append(fromCoordinate);
	stringBuilder.append(" -> ");
	stringBuilder.append(toCoordinate);
	stringBuilder.append(" : ");
	switch (moveType) {
	case SWAP_MOVE:
	    stringBuilder.append("SWAP");
	    break;
	case DIVIDE_MOVE:
	    stringBuilder.append("DIVIDE");
	    break;
	case DUPLICATE_MOVE:
	    stringBuilder.append("DUPLICATE");
	    break;
	}
	return stringBuilder.toString();
    }

    public boolean equals(ColorFoldMove colorFoldMove) {
	if (getMoveType() == colorFoldMove.getMoveType()) {
	    if (getMoveType() == SWAP_MOVE) {
		if ((getFromCoordinate().equals(colorFoldMove.getFromCoordinate())
			&& getToCoordinate().equals(colorFoldMove.getToCoordinate()))
			|| (getFromCoordinate().equals(colorFoldMove.getToCoordinate())
				&& getToCoordinate().equals(colorFoldMove.getFromCoordinate()))) {
		    return true;
		}
	    }
	}
	return false;
    }

    public Coordinate getFromCoordinate() {
	return fromCoordinate;
    }

    public Coordinate getToCoordinate() {
	return toCoordinate;
    }

    public int getMoveType() {
	return moveType;
    }

}
