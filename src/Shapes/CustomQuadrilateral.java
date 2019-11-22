package Shapes;


import java.awt.*;
import java.awt.geom.Area;
import java.io.Serializable;

public abstract class CustomQuadrilateral extends CustomShape implements Serializable {

    private int width, height;
    private Point startPoint, endPoint,point1,point2;

    public CustomQuadrilateral( String shapeId, Color shapeColor,Point point1, Point point2, boolean isFilled) {
        super(shapeId,shapeColor,isFilled);
        this.point1 = point1;
        this.point2 = point2;
        startPoint = new Point();
        endPoint = new Point();

    }

    public void findRectangleBoundaries(){
        findStartEndPoints();
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

    public CustomShape getShapeInArea(Point point){

        Area area = new Area(new Rectangle(this.getStartPoint().x,this.getStartPoint().y,this.getWidth(),this.getHeight()));
        if(area.contains(point)){
            return this;
        }
        return null;
    }

    @Override
    public void moveShape(Point newPoint) {
        int startPointX,  startPointY,  endPointX,  endPointY;
        startPointX = newPoint.x;
        startPointY = newPoint.y;

        if(newPoint.x < this.getStartPoint().x){
            endPointX = this.getStartPoint().x;
        } else {
            endPointX = this.getStartPoint().x + newPoint.x;
        }
        if(newPoint.y < this.getStartPoint().y){
            endPointY = this.getStartPoint().y;
        } else {
            endPointY = this.getStartPoint().y +newPoint.y;
        }

        this.setStartPoint(new Point(startPointX,startPointY));
        this.setEndPoint(new Point(endPointX,endPointY));
        this.findRectangleBoundaries();
    }



    public void setEndPoint(Point point){
        point2 = point;
        findRectangleBoundaries();
    }

    public void setStartPoint(Point point){
        point1 = point;
        findRectangleBoundaries();
    }

    private void calculateWidth(){
        width = endPoint.x - startPoint.x;
    }

    private void calculateHeight(){
        height = endPoint.y - startPoint.y;
    }

    public void calculateShapeVariables(){
        calculateHeight();
        calculateWidth();
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

    public void setHeight(int height){
        this.height = height;
    }
}