package game.colorfold.designer.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import game.colorfold.designer.model.Coordinate;
import game.colorfold.designer.model.LevelDesignerModel;

public class ColorFoldPanel extends JPanel {

    private List<Coordinate> validCoordinates;
    private Map<Coordinate, Integer> coordinateColorIdMap;

    private int triangleSideLength = 160;

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(Color.black);
	if (validCoordinates != null) {
	    Iterator<Coordinate> validCoordinatesIterator = validCoordinates.iterator();
	    while (validCoordinatesIterator.hasNext()) {
		Coordinate coordinate = validCoordinatesIterator.next();
		drawTriangle(g2d, triangleSideLength, coordinate);
	    }
	}
    }

    public Coordinate getCoordinateAtPoint(Point point) {
	int triangleHeight = getTriangleHeight();
	int M = 2 * point.x / triangleSideLength;
	int N = point.y / getTriangleHeight();
	int relativeX = point.x % (triangleSideLength / 2);
	int relativeY = point.y % triangleHeight;
	int abscissa = M;
	if ((M + N) % 2 == 0) {
	    if (relativeY + 2 * triangleHeight * relativeX / triangleSideLength < triangleHeight) {
		abscissa--;
	    }
	} else if (relativeY > 2 * triangleHeight * relativeX / triangleSideLength) {
	    abscissa--;
	}
	int ordinate = N;
	return new Coordinate(abscissa, ordinate);
    }

    public void setTriangleSideLength(int triangleSideLength) {
	this.triangleSideLength = triangleSideLength;
    }

    private void drawTriangle(Graphics2D g2d, int triangleSideLength, Coordinate coordinate) {
	int xPoints[] = { 0, 0, 0 };
	int yPoints[] = { 0, 0, 0 };
	List<Point> trianglePoints = getTrianglePoints(triangleSideLength, coordinate.getAbscissa(),
		coordinate.getOrdinate());
	for (int i = 0; i < trianglePoints.size(); i++) {
	    Point point = trianglePoints.get(i);
	    xPoints[i] = point.x;
	    yPoints[i] = point.y;
	}
	if (coordinateColorIdMap != null && coordinateColorIdMap.containsKey(coordinate)) {
	    int colorId = coordinateColorIdMap.get(coordinate);
	    Color color = LevelDesignerModel.colorMap.get(colorId);
	    g2d.setColor(color);
	    g2d.fillPolygon(xPoints, yPoints, 3);
	} else {
	    g2d.setColor(Color.WHITE);
	    g2d.fillPolygon(xPoints, yPoints, 3);
	}

	g2d.setColor(Color.black);
	g2d.setStroke(new BasicStroke(2));
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g2d.drawPolygon(xPoints, yPoints, 3);
    }

    public void setValidCoordinates(List<Coordinate> validCoordinates) {
	this.validCoordinates = validCoordinates;
    }

    public void setCoordinateColorIdMap(Map<Coordinate, Integer> coordinateColorIdMap) {
	this.coordinateColorIdMap = coordinateColorIdMap;
    }

    private List<Point> getTrianglePoints(int triangleSideLength, int x, int y) {
	int triangleHeight = getTriangleHeight();
	List<Point> trianglePoints = new ArrayList<Point>();
	int x1 = triangleSideLength * x / 2;
	int x2 = triangleSideLength / 2 + x1;
	int x3 = triangleSideLength / 2 + x2;
	int y1;
	int y2;
	int y3;
	if ((x % 2 == 0 && y % 2 == 1) || x % 2 == 1 && y % 2 == 0) {
	    y1 = triangleHeight * y;
	    y2 = triangleHeight * (y + 1);
	    y3 = triangleHeight * y;
	} else {
	    y1 = triangleHeight * (y + 1);
	    y2 = triangleHeight * y;
	    y3 = triangleHeight * (y + 1);
	}
	trianglePoints.add(new Point(x1, y1));
	trianglePoints.add(new Point(x2, y2));
	trianglePoints.add(new Point(x3, y3));
	return trianglePoints;
    }

    private int getTriangleHeight() {
	return (int) Math.sqrt((Math.pow(triangleSideLength, 2) - Math.pow(triangleSideLength / 2, 2)));
    }
}
