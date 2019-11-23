package Shapes;

import java.awt.*;
import java.io.Serializable;


/**
 * The type Custom ellipse.
 */
public class CustomEllipse extends CustomShape implements Serializable {

    private Point startPoint, endPoint,point1,point2;
    private int height,width;

    /**
     * Instantiates a new Custom ellipse.
     *
     * @param shapeId    the shape id
     * @param shapeColor the shape color
     * @param point1     the point 1
     * @param point2     the point 2
     * @param isFilled   the is filled
     */
    public CustomEllipse(String shapeId, Color shapeColor,Point point1,Point point2,boolean isFilled) {
        super(shapeId, shapeColor,isFilled);

        this.point1 = point1;
        this.point2 = point2;
        startPoint = new Point();
        endPoint = new Point();
    }

    /**
     * Find boundaries of the rectangle that contains the ellipse.
     */
    public void findBoundaries(){
        findStartEndPoints();
        calculateHeight();
        calculateWidth();
    }

    /**
     * Calculates the start and end point in case that the mouse is going towards the negative direction of x and y axis
     */
    private void findStartEndPoints(){
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
     * Calculates the width of the rectangle that contains the ellipse.
     */
    private void calculateHeight(){
        height = endPoint.y - startPoint.y;
    }

    /**
     * Calculates the width of the rectangle that contains the ellipse.
     */
    private void calculateWidth(){
        width = endPoint.x - startPoint.x;
    }

    /**
     * Mutator fot the end point of the ellipse.
     * @param point the point
     */
    public void setEndPoint(Point point){
        point2 = point;
        findBoundaries();
    }

    /**
     * Accessor for the width of the ellipse
     * @return the int
     */
    public int getWidth(){
        return width;
    }

    /**
     * Accessor for the height of the ellipse
     * @return the int
     */
    public int getHeight(){
        return height;
    }

    /**
     * Accessor for the StartPoint of the ellipse
     * @return the point
     */
    public Point getStartPoint(){
        return startPoint;
    }

    /**
     * Accessor for the EndPoint of the ellipse
     * @return the point
     */
    public Point getEndPoint(){
        return endPoint;
    }

    /**
     * This method is used to move the ellipse around the canvas
     * @param newPoint the new point
     */
    @Override
    public void moveShape(Point newPoint) {
        /*Sets the startPoint to be the new point every time this method called.
         *does not change the width and height because the ellipse must stay the same*/
        startPoint = newPoint;
    }

    /**
     * This method is used to indicate whether the point (the current position of the mouse) is included in the area of the shape.
     * @param point the point
     * @return the shape itself if the point is in the area of the shape or null otherwise.
     */
    @Override
    public CustomShape getShapeInArea(Point point) {
        if(startPoint.x <= point.x && point.x <= (startPoint.x + width) && startPoint.y <= point.y && point.y <= (startPoint.y +height)){
            return this;
        }
        return null;
    }
}
