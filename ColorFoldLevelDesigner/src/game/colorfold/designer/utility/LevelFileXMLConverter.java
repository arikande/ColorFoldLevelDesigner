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
import game.colorfold.designer.model.Coordinate;

public class LevelFileXMLConverter {

    private static final String TAB = "    ";

    public void writeToFile(ColorFoldLevel colorFoldLevel) {
	StringBuilder fileContent = new StringBuilder();
	fileContent.append("<colorfold_level>");
	fileContent.append(System.getProperty("line.separator"));
	fileContent.append(TAB);
	fileContent.append("<swap>");
	fileContent.append(colorFoldLevel.isSwapEnabled() ? "1" : "0");
	fileContent.append("</swap>");
	fileContent.append(System.getProperty("line.separator"));
	fileContent.append(TAB);
	fileContent.append("<divide>");
	fileContent.append(colorFoldLevel.isDivideEnabled() ? "1" : "0");
	fileContent.append("</divide>");
	fileContent.append(System.getProperty("line.separator"));
	fileContent.append(TAB);
	fileContent.append("<duplicate>");
	fileContent.append(colorFoldLevel.isDuplicateEnabled() ? "1" : "0");
	fileContent.append("</duplicate>");
	fileContent.append(System.getProperty("line.separator"));
	fileContent.append(TAB);
	fileContent.append("<maximum_moves>");
	fileContent.append(colorFoldLevel.getMaximumMoves());
	fileContent.append("</maximum_moves>");
	fileContent.append(System.getProperty("line.separator"));

	fileContent.append(TAB);
	fileContent.append("<valid_coordinates>");
	fileContent.append(System.getProperty("line.separator"));
	Iterator<Coordinate> validCoordinatesIterator = colorFoldLevel.getValidCoordinatesIterator();
	while (validCoordinatesIterator.hasNext()) {
	    Coordinate coordinate = validCoordinatesIterator.next();
	    fileContent.append(TAB + TAB);
	    fileContent.append(convertCoordinateToString(coordinate));
	    fileContent.append(System.getProperty("line.separator"));
	}
	fileContent.append(TAB);
	fileContent.append("</valid_coordinates>");
	fileContent.append(System.getProperty("line.separator"));

	fileContent.append(TAB);
	fileContent.append("<puzzle>");
	fileContent.append(System.getProperty("line.separator"));
	Iterator<Coordinate> coordinatesIterator = colorFoldLevel.getPuzzle().keySet().iterator();
	while (coordinatesIterator.hasNext()) {
	    Coordinate coordinate = coordinatesIterator.next();
	    fileContent.append(TAB + TAB);
	    fileContent.append("<tile>");
	    fileContent.append(System.getProperty("line.separator"));

	    fileContent.append(TAB + TAB + TAB);
	    fileContent.append(convertCoordinateToString(coordinate));
	    fileContent.append(System.getProperty("line.separator"));

	    fileContent.append(TAB + TAB + TAB);
	    fileContent.append("<color_id>");
	    fileContent.append(colorFoldLevel.getPuzzle().get(coordinate));
	    fileContent.append("</color_id>");
	    fileContent.append(System.getProperty("line.separator"));

	    fileContent.append(TAB + TAB);
	    fileContent.append("</tile>");
	    fileContent.append(System.getProperty("line.separator"));
	}
	fileContent.append(TAB);
	fileContent.append("</puzzle>");
	fileContent.append(System.getProperty("line.separator"));

	fileContent.append(TAB);
	fileContent.append("<solution>");
	fileContent.append(System.getProperty("line.separator"));
	coordinatesIterator = colorFoldLevel.getSolution().keySet().iterator();
	while (coordinatesIterator.hasNext()) {
	    Coordinate coordinate = coordinatesIterator.next();
	    fileContent.append(TAB + TAB);
	    fileContent.append("<tile>");
	    fileContent.append(System.getProperty("line.separator"));

	    fileContent.append(TAB + TAB + TAB);
	    fileContent.append(convertCoordinateToString(coordinate));
	    fileContent.append(System.getProperty("line.separator"));

	    fileContent.append(TAB + TAB + TAB);
	    fileContent.append("<color_id>");
	    fileContent.append(colorFoldLevel.getSolution().get(coordinate));
	    fileContent.append("</color_id>");
	    fileContent.append(System.getProperty("line.separator"));

	    fileContent.append(TAB + TAB);
	    fileContent.append("</tile>");
	    fileContent.append(System.getProperty("line.separator"));
	}

	fileContent.append(TAB);
	fileContent.append("</solution>");
	fileContent.append(System.getProperty("line.separator"));

	fileContent.append("</colorfold_level>");
	try {
	    FileWriter fileWriter = new FileWriter(colorFoldLevel.getFile());
	    fileWriter.write(fileContent.toString());
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
	    colorFoldLevel.setFile(file);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return colorFoldLevel;
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
