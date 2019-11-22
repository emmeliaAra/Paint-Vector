package Shapes;

import java.awt.*;
import java.util.LinkedList;

public class CustomTriangle extends CustomShape {

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
}
