package game.colorfold.designer.model;
/**
 *
 * @author Deniz ARIKAN
 */
public class Coordinate implements Comparable {

	private int abscissa;
	private int ordinate;

	public Coordinate(int abscissa, int ordinate) {
		this.abscissa = abscissa;
		this.ordinate = ordinate;
	}

	@Override
	public String toString() {
		return "(" + getAbscissa() + "," + getOrdinate() + ")";
	}

	@Override
	public int hashCode() {
		return getAbscissa() * getOrdinate();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Coordinate) {
			Coordinate o = (Coordinate) other;
			return (o.getAbscissa() == getAbscissa()) && (o.getOrdinate() == getOrdinate());
		}
		return false;
	}

	public int getAbscissa() {
		return abscissa;
	}

	public void setAbscissa(int abscissa) {
		this.abscissa = abscissa;
	}

	public int getOrdinate() {
		return ordinate;
	}

	public void setOrdinate(int ordinate) {
		this.ordinate = ordinate;
	}

	public int compareTo(Object o) {
		if (getOrdinate() < ((Coordinate) o).getOrdinate()) {
			return -1;
		} else if (getOrdinate() > ((Coordinate) o).getOrdinate()) {
			return 1;
		} else {
			if (getAbscissa() < ((Coordinate) o).getAbscissa()) {
				return -1;
			} else if (getAbscissa() > ((Coordinate) o).getAbscissa()) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}