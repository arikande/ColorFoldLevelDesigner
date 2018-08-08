package game.colorfold.designer.utility;

import game.colorfold.designer.model.ColorFoldMove;

public class ColorFoldMoveXMLConverter {

	public String toXML(ColorFoldMove colorFoldMove) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append("<move>");
		stringBuilder.append(System.getProperty("line.separator"));

		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append("<from>");
		stringBuilder.append(System.getProperty("line.separator"));
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(new CoordinateXMLConverter().toXML(colorFoldMove.getFromCoordinate()));
		stringBuilder.append(System.getProperty("line.separator"));

		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append("</from>");

		stringBuilder.append(System.getProperty("line.separator"));
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append("<to>");
		stringBuilder.append(System.getProperty("line.separator"));
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(new CoordinateXMLConverter().toXML(colorFoldMove.getToCoordinate()));
		stringBuilder.append(System.getProperty("line.separator"));
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append("</to>");
		stringBuilder.append(System.getProperty("line.separator"));

		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append("<type>");
		stringBuilder.append(colorFoldMove.getMoveType());
		stringBuilder.append("</type>");
		stringBuilder.append(System.getProperty("line.separator"));

		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append(LevelDesignerConstants.TAB);
		stringBuilder.append("</move>");
		stringBuilder.append(System.getProperty("line.separator"));
		return stringBuilder.toString();
	}

}
