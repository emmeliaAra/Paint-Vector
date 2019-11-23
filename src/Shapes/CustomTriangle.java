package Shapes;

import java.awt.*;
import java.awt.geom.Area;
import java.io.Serializable;

/**
 * The type Custom triangle.
 */
public class CustomTriangle extends CustomShape implements Serializable {

    private Point[] points;
    private Point initialPoint;

    /**
     * Instantiates a new Custom triangle.
     *
     * @param shapeId      the shape id
     * @param shapeColor   the shape color
     * @param initialPoint the initial point
     * @param pointNumber  the point number
     * @param isFilled     the is filled
     */
    public CustomTriangle(String shapeId, Color shapeColor, Point initialPoint, int pointNumber ,boolean isFilled) {
        super(shapeId, shapeColor, isFilled);
        points = new Point[pointNumber];
        this.initialPoint = initialPoint;
    }

    /**
     * Accessor for the initial point point.
     * @return the point
     */
    public Point getInitialPoint(){
        return initialPoint;
    }

    /**
     * Accessor for the points.
     * @param points the points
     */
    public void setPoints(Point[] points){
        this.points = points;
    }

    /**
     * Accessor for the point array
     * @return the point [ ]
     */
    public Point[] getPoints(){
        return points;
    }

    /**
     * Method used to move the triangle around the canvas
     * @param newPoint the new point
     */
    @Override
    public void moveShape(Point newPoint) {

    }

    @Override
    public CustomShape getShapeInArea(Point point) {
        int[] xPoints = {points[0].x, points[1].x,points[2].x};
        int[] yPoints = {points[0].y, points[1].y,points[2].y};
        Area area = new Area(new Polygon(xPoints,yPoints,3));
        if(area.contains(point)){
            return this;
        }
        return null;
    }
}
