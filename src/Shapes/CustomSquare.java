package Shapes;

import java.awt.*;
import java.io.Serializable;

/**
 * The type Custom square.
 */
public class CustomSquare extends CustomQuadrilateral implements Serializable {

    /**
     * Instantiates a new Custom square.
     *
     * @param shapeId    the shape id
     * @param shapeColor the shape color
     * @param startPoint the start point
     * @param endPoint   the end point
     * @param isFilled   the is filled
     */
    public CustomSquare(String shapeId, Color shapeColor, Point startPoint, Point endPoint, boolean isFilled) {
        super(shapeId, shapeColor, startPoint, endPoint, isFilled);

    }

    /**
     * Sets the height of the shape to be the same as the width.
     * @param height the height
     */
    @Override
    public void setHeight(int height) {
        super.setHeight(this.getWidth());
    }
}
