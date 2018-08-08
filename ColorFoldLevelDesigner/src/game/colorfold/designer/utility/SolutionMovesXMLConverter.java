package game.colorfold.designer.utility;

import org.w3c.dom.Node;

import game.colorfold.designer.model.ColorFoldLevelSolution;
import game.colorfold.designer.model.ColorFoldMove;
import game.colorfold.designer.model.Coordinate;

public class SolutionMovesXMLConverter {

	public ColorFoldLevelSolution parse(Node node) {
		ColorFoldLevelSolution colorFoldLevelSolution = new ColorFoldLevelSolution();
		try {
			for (int i = 0; i < node.getChildNodes().getLength(); i++) {
				Node subNode = node.getChildNodes().item(i);
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
