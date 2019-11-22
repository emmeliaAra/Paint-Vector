package Shapes;

import java.awt.*;

public class CustomLine extends CustomShape {

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
}
