package Shapes;


import java.awt.*;
import java.awt.geom.Area;
import java.io.Serializable;

/**
 * The type Custom quadrilateral.
 * This is an abstract class used to represent all Quadrilateral shapes
 */
public abstract class CustomQuadrilateral extends CustomShape implements Serializable {

    private int width, height;
    private Point startPoint, endPoint,point1,point2;

    /**
     * Instantiates a new Custom quadrilateral.
     *
     * @param shapeId    the shape id
     * @param shapeColor the shape color
     * @param point1     the point 1
     * @param point2     the point 2
     * @param isFilled   the is filled
     */
    public CustomQuadrilateral( String shapeId, Color shapeColor,Point point1, Point point2, boolean isFilled) {
        super(shapeId,shapeColor,isFilled);
        this.point1 = point1;
        this.point2 = point2;
        startPoint = new Point();
        endPoint = new Point();

    }

    public void findStartEndPoints(){
        /* Case 1: If the second point (x) is smaller than the first one means the the mouse is being dragged to the left and the points must switch
         * else: set them as normal
         * Case 2: If the second point (y) is smaller than the first one means the the mouse is being dragged upwards and the points must switch.
         * else: set them as normal*/
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

    /**
     * This method creates an area around the shape and then checks whether the point lies withing that area
     * @param point the point
     * @return the shape itself if its in the area or null otherwise
     */
    public CustomShape getShapeInArea(Point point){

        Area area = new Area(new Rectangle(getStartPoint().x,getStartPoint().y,getWidth(),getHeight()));
        if(area.contains(point)){
            return this;
        }
        return null;
    }

    /**
     * This method is used to move the shape around the canvas
     * @param newPoint the new point
     */
    @Override
    public void moveShape(Point newPoint) {
        /* 1. Set start point to be the new point
         * 2. Then makes the end point to be the addition of the initial startPoint and the new point
         */

        int startPointX,  startPointY,  endPointX,  endPointY;
        startPointX = newPoint.x;
        startPointY = newPoint.y;
        endPointX = this.getStartPoint().x + newPoint.x;
        endPointY = this.getStartPoint().y +newPoint.y;

        this.setStartPoint(new Point(startPointX,startPointY));
        this.setEndPoint(new Point(endPointX,endPointY));
        this.findStartEndPoints();
    }

    /**
     * Mutator for the end point.
     * Once a point is given the boundaries are calculates again
     * @param point the point
     */
    public void setEndPoint(Point point){
        point2 = point;
        findStartEndPoints();
    }

    /**
     * Mutator for the start point.
     * Once a point is given the boundaries are calculates again
     * @param point the point
     */
    public void setStartPoint(Point point){
        point1 = point;
        findStartEndPoints();
    }

    /**
     * Calculates the width of the shape
     */
    private void calculateWidth(){
        width = endPoint.x - startPoint.x;
    }

    /**
     * Calculates the Height of the shape
     */
    private void calculateHeight(){
        height = endPoint.y - startPoint.y;
    }

    /**
     * Calculate shape variables.
     */
    public void calculateShapeVariables(){
        calculateHeight();
        calculateWidth();
    }

    /**
     * Accessor for width of the shape.
     * @return the int
     */
    public int getWidth(){
        return width;
    }

    /**
     * Accessor for the height of the shape.
     * @return the int
     */
    public int getHeight(){
        return height;
    }

    /**
     * Accessor of the start point point.
     * @return the point
     */
    public Point getStartPoint(){
        return startPoint;
    }

    /**
     * Accessor of the end point point.
     * @return the point
     */
    public Point getEndPoint(){
        return endPoint;
    }

    /**
     * Mutator for the height.
     * @param height the height
     */
    public void setHeight(int height){
        this.height = height;
    }
}