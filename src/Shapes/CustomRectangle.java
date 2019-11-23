package Shapes;

import java.awt.*;
import java.io.Serializable;

/**
 * The type Custom rectangle.
 */
public class CustomRectangle extends CustomQuadrilateral implements Serializable {

    /**
     * Instantiates a new Custom rectangle.
     *
     * @param shapeId    the shape id
     * @param shapeColor the shape color
     * @param startPoint the start point
     * @param endPoint   the end point
     * @param isFilled   the is filled
     */
    public CustomRectangle(String shapeId, Color shapeColor,Point startPoint, Point endPoint,boolean isFilled) {
        super(shapeId, shapeColor,startPoint,endPoint,isFilled);
    }

}
