package game.colorfold.designer.utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import game.colorfold.designer.model.ColorFoldLevel;
import game.colorfold.designer.model.ColorFoldLevelSolution;
import game.colorfold.designer.model.ColorFoldMove;
import game.colorfold.designer.model.Coordinate;

public class LevelFileXMLConverter {

    private boolean addingSolutionMoves = true;

    public String toXML(ColorFoldLevel colorFoldLevel) {
	StringBuilder xml = new StringBuilder();
	xml.append("<colorfold_level>");
	xml.append(System.getProperty("line.separator"));
	xml.append(LevelDesignerConstants.TAB);
	xml.append("<swap>");
	xml.append(colorFoldLevel.isSwapEnabled() ? "1" : "0");
	xml.append("</swap>");
	xml.append(System.getProperty("line.separator"));
	xml.append(LevelDesignerConstants.TAB);
	xml.append("<divide>");
	xml.append(colorFoldLevel.isDivideEnabled() ? "1" : "0");
	xml.append("</divide>");
	xml.append(System.getProperty("line.separator"));
	xml.append(LevelDesignerConstants.TAB);
	xml.append("<duplicate>");
	xml.append(colorFoldLevel.isDuplicateEnabled() ? "1" : "0");
	xml.append("</duplicate>");
	xml.append(System.getProperty("line.separator"));
	xml.append(LevelDesignerConstants.TAB);
	xml.append("<maximum_moves>");
	xml.append(colorFoldLevel.getMaximumMoves());
	xml.append("</maximum_moves>");
	xml.append(System.getProperty("line.separator"));

	xml.append(LevelDesignerConstants.TAB);
	xml.append("<valid_coordinates>");
	xml.append(System.getProperty("line.separator"));
	Iterator<Coordinate> validCoordinatesIterator = colorFoldLevel.getValidCoordinatesIterator();
	while (validCoordinatesIterator.hasNext()) {
	    Coordinate coordinate = validCoordinatesIterator.next();
	    xml.append(LevelDesignerConstants.TAB + LevelDesignerConstants.TAB);
	    xml.append(convertCoordinateToString(coordinate));
	    xml.append(System.getProperty("line.separator"));
	}
	xml.append(LevelDesignerConstants.TAB);
	xml.append("</valid_coordinates>");
	xml.append(System.getProperty("line.separator"));

	xml.append(LevelDesignerConstants.TAB);
	xml.append("<puzzle>");
	xml.append(System.getProperty("line.separator"));
	Iterator<Coordinate> coordinatesIterator = colorFoldLevel.getPuzzle().keySet().iterator();
	while (coordinatesIterator.hasNext()) {
	    Coordinate coordinate = coordinatesIterator.next();
	    xml.append(LevelDesignerConstants.TAB + LevelDesignerConstants.TAB);
	    xml.append("<tile>");
	    xml.append(System.getProperty("line.separator"));

	    xml.append(LevelDesignerConstants.TAB + LevelDesignerConstants.TAB + LevelDesignerConstants.TAB);
	    xml.append(convertCoordinateToString(coordinate));
	    xml.append(System.getProperty("line.separator"));

	    xml.append(LevelDesignerConstants.TAB + LevelDesignerConstants.TAB + LevelDesignerConstants.TAB);
	    xml.append("<color_id>");
	    xml.append(colorFoldLevel.getPuzzle().get(coordinate));
	    xml.append("</color_id>");
	    xml.append(System.getProperty("line.separator"));

	    xml.append(LevelDesignerConstants.TAB + LevelDesignerConstants.TAB);
	    xml.append("</tile>");
	    xml.append(System.getProperty("line.separator"));
	}
	xml.append(LevelDesignerConstants.TAB);
	xml.append("</puzzle>");
	xml.append(System.getProperty("line.separator"));

	xml.append(LevelDesignerConstants.TAB);
	xml.append("<solution>");
	xml.append(System.getProperty("line.separator"));
	coordinatesIterator = colorFoldLevel.getSolution().keySet().iterator();
	while (coordinatesIterator.hasNext()) {
	    Coordinate coordinate = coordinatesIterator.next();
	    xml.append(LevelDesignerConstants.TAB + LevelDesignerConstants.TAB);
	    xml.append("<tile>");
	    xml.append(System.getProperty("line.separator"));

	    xml.append(LevelDesignerConstants.TAB + LevelDesignerConstants.TAB + LevelDesignerConstants.TAB);
	    xml.append(convertCoordinateToString(coordinate));
	    xml.append(System.getProperty("line.separator"));

	    xml.append(LevelDesignerConstants.TAB + LevelDesignerConstants.TAB + LevelDesignerConstants.TAB);
	    xml.append("<color_id>");
	    xml.append(colorFoldLevel.getSolution().get(coordinate));
	    xml.append("</color_id>");
	    xml.append(System.getProperty("line.separator"));

	    xml.append(LevelDesignerConstants.TAB + LevelDesignerConstants.TAB);
	    xml.append("</tile>");
	    xml.append(System.getProperty("line.separator"));
	}

	xml.append(LevelDesignerConstants.TAB);
	xml.append("</solution>");
	xml.append(System.getProperty("line.separator"));

	if (addingSolutionMoves) {
	    ColorFoldLevelSolution colorFoldLevelSolution = colorFoldLevel.getColorFoldLevelSolution();
	    if (colorFoldLevelSolution != null) {
		xml.append(LevelDesignerConstants.TAB);
		xml.append("<solution_moves>");
		xml.append(System.getProperty("line.separator"));
		Iterator<ColorFoldMove> movesIterator = colorFoldLevelSolution.getMovesIterator();
		while (movesIterator.hasNext()) {
		    ColorFoldMove colorFoldMove = movesIterator.next();
		    ColorFoldMoveXMLConverter colorFoldMoveXMLConverter = new ColorFoldMoveXMLConverter();
		    xml.append(colorFoldMoveXMLConverter.toXML(colorFoldMove));
		}
		xml.append(LevelDesignerConstants.TAB);
		xml.append("</solution_moves>");
		xml.append(System.getProperty("line.separator"));
	    }
	}

	xml.append("</colorfold_level>");
	return xml.toString();
    }

    public void writeToFile(ColorFoldLevel colorFoldLevel, File file) {
	try {
	    FileWriter fileWriter = new FileWriter(file);
	    fileWriter.write(toXML(colorFoldLevel));
	    fileWriter.flush();
	    fileWriter.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public ColorFoldLevel parse(File file) {
	ColorFoldLevel colorFoldLevel = new ColorFoldLevel();
	try {
	    DOMParser builder = new DOMParser();
	    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
	    InputSource inputSource = new InputSource(bufferedInputStream);
	    try {
		builder.parse(inputSource);
	    } catch (SAXException ex) {
	    } catch (IOException ex) {
	    }
	    Node colorFoldLevelNode = XMLUtility.findNode(builder.getDocument(), "colorfold_level");
	    Node swapNode = XMLUtility.findNode(colorFoldLevelNode, "swap");
	    int swap = Integer.parseInt(swapNode.getFirstChild().getNodeValue());
	    colorFoldLevel.setSwapEnabled(swap == 1);
	    Node divideNode = XMLUtility.findNode(colorFoldLevelNode, "divide");
	    int divide = Integer.parseInt(divideNode.getFirstChild().getNodeValue());
	    colorFoldLevel.setDivideEnabled(divide == 1);
	    Node duplicateNode = XMLUtility.findNode(colorFoldLevelNode, "duplicate");
	    int duplicate = Integer.parseInt(duplicateNode.getFirstChild().getNodeValue());
	    colorFoldLevel.setDuplicateEnabled(duplicate == 1);
	    Node maximumMovesNode = XMLUtility.findNode(colorFoldLevelNode, "maximum_moves");
	    int maximumMoves = Integer.parseInt(maximumMovesNode.getFirstChild().getNodeValue());
	    colorFoldLevel.setMaximumMoves(maximumMoves);
	    colorFoldLevel.setRemainingMoves(maximumMoves);

	    Node validCoordinatesNode = XMLUtility.findNode(colorFoldLevelNode, "valid_coordinates");
	    for (int i = 0; i < validCoordinatesNode.getChildNodes().getLength(); i++) {
		Node subNode = validCoordinatesNode.getChildNodes().item(i);
		if (subNode.getNodeType() == Node.ELEMENT_NODE) {
		    colorFoldLevel.addValidCoordinate(convertNodeToCoordinate(subNode));
		}
	    }
	    Node puzzleNode = XMLUtility.findNode(colorFoldLevelNode, "puzzle");
	    for (int i = 0; i < puzzleNode.getChildNodes().getLength(); i++) {
		Node subNode = puzzleNode.getChildNodes().item(i);
		if (subNode.getNodeType() == Node.ELEMENT_NODE) {
		    Node coordinateNode = XMLUtility.findNode(subNode, "coordinate");
		    Coordinate coordinate = convertNodeToCoordinate(coordinateNode);
		    int colorId = Integer
			    .parseInt(XMLUtility.findNode(subNode, "color_id").getFirstChild().getNodeValue());
		    colorFoldLevel.getPuzzle().put(coordinate, colorId);
		}
	    }

	    Node solutionNode = XMLUtility.findNode(colorFoldLevelNode, "solution");
	    for (int i = 0; i < solutionNode.getChildNodes().getLength(); i++) {
		Node subNode = solutionNode.getChildNodes().item(i);
		if (subNode.getNodeType() == Node.ELEMENT_NODE) {
		    Node coordinateNode = XMLUtility.findNode(subNode, "coordinate");
		    Coordinate coordinate = convertNodeToCoordinate(coordinateNode);
		    int colorId = Integer
			    .parseInt(XMLUtility.findNode(subNode, "color_id").getFirstChild().getNodeValue());
		    colorFoldLevel.putSolutionTile(coordinate, colorId);
		}
	    }

	    Node solutionMovesNode = XMLUtility.findNode(colorFoldLevelNode, "solution_moves");
	    if (solutionMovesNode != null) {
		ColorFoldLevelSolution colorFoldLevelSolution = new SolutionMovesXMLConverter()
			.parse(solutionMovesNode);
		colorFoldLevel.setColorFoldLevelSolution(colorFoldLevelSolution);
	    }
	    colorFoldLevel.setFile(file);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return colorFoldLevel;
    }

    public void setAddingSolutionMoves(boolean addingSolutionMoves) {
	this.addingSolutionMoves = addingSolutionMoves;
    }

    private String convertCoordinateToString(Coordinate coordinate) {
	return new CoordinateXMLConverter().toXML(coordinate);
    }

    private Coordinate convertNodeToCoordinate(Node node) {
	Node xNode = XMLUtility.findNode(node, "x");
	Node yNode = XMLUtility.findNode(node, "y");
	int x = Integer.parseInt(xNode.getFirstChild().getNodeValue());
	int y = Integer.parseInt(yNode.getFirstChild().getNodeValue());
	return new Coordinate(x, y);
    }
}
