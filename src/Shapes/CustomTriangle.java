package Shapes;

import java.awt.*;
import java.awt.geom.Area;
import java.io.Serializable;

public class CustomTriangle extends CustomShape implements Serializable {

    private Point[] points;
    private Point initialPoint;

    public CustomTriangle(String shapeId, Color shapeColor, Point initialPoint, int pointNumber ,boolean isFilled) {
        super(shapeId, shapeColor, isFilled);
        points = new Point[pointNumber];
        this.initialPoint = initialPoint;
    }

    public Point getInitialPoint(){
        return initialPoint;
    }

    public void setPoints(Point[] points){
        this.points = points;
    }

    public Point[] getPoints(){
        return points;
    }

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
