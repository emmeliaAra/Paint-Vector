package Shapes;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class CustomLine extends CustomShape implements Serializable {

    private Point startPoint, endPoint;

    public CustomLine(String shapeId,Color shapeColor,Point startPoint, Point endPoint,boolean isFilled){
        super(shapeId,shapeColor,isFilled);
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Point getStartPoint(){
        return startPoint;
    }

    public Point getEndPoint(){
        return endPoint;
    }

    public void setEndPoint(Point point){
        endPoint = point;
    }

    @Override
    public void moveShape(Point newPoint) {
        System.out.println("I am innnnn Whyyy aren't you movinggg ???");
        int startEndXDifference = Math.abs(this.getStartPoint().x - this.getEndPoint().x);
        int startEndYDifference = Math.abs(this.getStartPoint().y - this.getEndPoint().y);
        startPoint = newPoint;
        endPoint.x = newPoint.x + startEndXDifference;
        endPoint.y = newPoint.y + startEndYDifference;
    }
    @Override
    public CustomShape getShapeInArea(Point point){
        //Check if the point is withing the boundaries of the line.
        if(point.x >= startPoint.x && point.x <= endPoint.x && point.y >= startPoint.y && point.y <= endPoint.y)
            return this;
        return null;
    }
}
