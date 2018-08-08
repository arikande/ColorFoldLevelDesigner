package game.colorfold.designer.utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import game.colorfold.designer.model.ColorFoldLevelSolution;
import game.colorfold.designer.model.ColorFoldMove;
import game.colorfold.designer.model.Coordinate;

public class SolutionFileXMLConverter {

	public ColorFoldLevelSolution parse(File file) {
		ColorFoldLevelSolution colorFoldLevelSolution = new ColorFoldLevelSolution();
		try {
			DOMParser builder = new DOMParser();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
			InputSource inputSource = new InputSource(bufferedInputStream);
			try {
				builder.parse(inputSource);
			} catch (SAXException ex) {
			} catch (IOException ex) {
			}
			Node colorFoldLevelSolutionNode = XMLUtility.findNode(builder.getDocument(), "colorfold_level_solution");
			colorFoldLevelSolution.setFile(file);
			for (int i = 0; i < colorFoldLevelSolutionNode.getChildNodes().getLength(); i++) {
				Node subNode = colorFoldLevelSolutionNode.getChildNodes().item(i);
				if (subNode.getNodeType() == Node.ELEMENT_NODE) {
					Node fromNode = XMLUtility.findNode(subNode, "from");
					Node fromCoordinateNode = XMLUtility.findNode(fromNode, "coordinate");
					Coordinate fromCoordinate = convertNodeToCoordinate(fromCoordinateNode);
					Node toNode = XMLUtility.findNode(subNode, "to");
					Node toCoordinateNode = XMLUtility.findNode(toNode, "coordinate");
					Coordinate toCoordinate = convertNodeToCoordinate(toCoordinateNode);
					Node typeNode = XMLUtility.findNode(subNode, "type");
					int type = Integer.parseInt(typeNode.getFirstChild().getNodeValue().trim());
					colorFoldLevelSolution.addMove(new ColorFoldMove(fromCoordinate, toCoordinate, type));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return colorFoldLevelSolution;
	}

	private Coordinate convertNodeToCoordinate(Node node) {
		Node xNode = XMLUtility.findNode(node, "x");
		Node yNode = XMLUtility.findNode(node, "y");
		int x = Integer.parseInt(xNode.getFirstChild().getNodeValue());
		int y = Integer.parseInt(yNode.getFirstChild().getNodeValue());
		return new Coordinate(x, y);
	}
}
