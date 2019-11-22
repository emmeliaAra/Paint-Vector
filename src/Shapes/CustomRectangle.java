package Shapes;

import java.awt.*;

public class CustomRectangle extends CustomShape {

    private Point startPoint, endPoint,point1,point2;
    private int height,width;

    public CustomRectangle(String shapeId, Color shapeColor,Point point1, Point point2) {
        super(shapeId, shapeColor);
        this.point1 = point1;
        this.point2 = point2;
        startPoint = new Point();
        endPoint = new Point();
    }

    public void findRectangleBoundaries(){
        findStartEndPoints();
        calculateHeight();
        calculateWidth();
    }

    private void findStartEndPoints(){
        if(point1.x > point2.x) {
            startPoint.x = point2.x;
            endPoint.x = point1.x;
        } else {
            startPoint.x = point1.x;
            endPoint.x = point2.x;
        }
        if(point1.y > point2.y) {
            startPoint.y = point2.y;
            endPoint.y = point1.y;
        } else {
            startPoint.y = point1.y;
            endPoint.y = point2.y;
        }
    }

    public void setEndPoint(Point point){
        point2 = point;
        findRectangleBoundaries();
    }

    private void calculateWidth(){
        width = endPoint.x - startPoint.x;
    }

    private void calculateHeight(){
        height = endPoint.y - startPoint.y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Point getStartPoint(){
        return startPoint;
    }

    public Point getEndPoint(){
        return endPoint;
    }
}
