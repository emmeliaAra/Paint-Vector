package Shapes;

import java.awt.*;
import java.io.Serializable;

/**
 * The type Custom line.
 */
public class CustomLine extends CustomShape implements Serializable {

    private Point startPoint, endPoint;

    /**
     * Instantiates a new Custom line.
     *
     * @param shapeId    the shape id
     * @param shapeColor the shape color
     * @param startPoint the start point
     * @param endPoint   the end point
     * @param isFilled   the is filled
     */
    public CustomLine(String shapeId,Color shapeColor,Point startPoint, Point endPoint,boolean isFilled){
        super(shapeId,shapeColor,isFilled);
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    /**
     * Accessor for the start point point.
     * @return the point
     */
    public Point getStartPoint(){
        return startPoint;
    }

    /**
     * Accessor for the end point point.
     * @return the point
     */
    public Point getEndPoint(){
        return endPoint;
    }

    /**
     * Mutator for the end point.
     * @param point the point
     */
    public void setEndPoint(Point point){
        endPoint = point;
    }

    /**
     * This method is called whenever we want to move a line, to change the line's end and startPoints
     * @param newPoint the new point
     */
    @Override
    public void moveShape(Point newPoint) {
        /* 1. Finds the X Area the the line expands to.
         * 2. Finds the Y Area the the lind expands to.
         * 3. Sets the new point to be the start point and calculates the end point based on variables found in steps 1 & 2.
         */
        int startEndXDifference = Math.abs(this.getStartPoint().x - this.getEndPoint().x);
        int startEndYDifference = Math.abs(this.getStartPoint().y - this.getEndPoint().y);
        startPoint = newPoint;
        endPoint.x = newPoint.x + startEndXDifference;
        endPoint.y = newPoint.y + startEndYDifference;
    }

    /**
     * This method is used to indicate whether the point (the current position of the mouse) is included in the area of the shape.
     * @param point the point
     * @return the shape itself if the point is in the area of the shape or null otherwise.
     */
    @Override
    public CustomShape getShapeInArea(Point point){
        //Check if the point is withing the boundaries of the line.
        if(point.x >= startPoint.x && point.x <= endPoint.x && point.y >= startPoint.y && point.y <= endPoint.y)
            return this;
        return null;
    }
}
