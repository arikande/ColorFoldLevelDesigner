package game.colorfold.designer.utility;

import game.colorfold.designer.model.Coordinate;

public class CoordinateXMLConverter {
    public String toXML(Coordinate coordinate) {
	StringBuilder coordinateStringBuilder = new StringBuilder();
	coordinateStringBuilder.append("<coordinate>");
	coordinateStringBuilder.append("<x>");
	coordinateStringBuilder.append(coordinate.getAbscissa());
	coordinateStringBuilder.append("</x>");
	coordinateStringBuilder.append("<y>");
	coordinateStringBuilder.append(coordinate.getOrdinate());
	coordinateStringBuilder.append("</y>");
	coordinateStringBuilder.append("</coordinate>");
	return coordinateStringBuilder.toString();
    }
}
