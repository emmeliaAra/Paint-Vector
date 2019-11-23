package Shapes;

import java.awt.*;
import java.io.Serializable;

/**
 * The type Custom shape.
 */
public abstract class CustomShape implements Serializable {

    private Color shapeColor;
    private String shapeID;
    private boolean isFilled;

    /**
     * Instantiates a new Custom shape.
     *
     * @param shapeId    the shape id
     * @param shapeColor the shape color
     * @param isFilled   the is filled
     */
    public CustomShape(String shapeId, Color shapeColor,boolean isFilled) {
        this.shapeID = shapeId;
        this.shapeColor = shapeColor;
        this.isFilled = isFilled;
    }

    /**
     * Accessor for color.
     * @return the color
     */
    public Color getColor() {
        return shapeColor;
    }

    /**
     * Accessor for shape id.
     * @return the shape id
     */
    public String getShapeID() {
        return shapeID;
    }

    /**
     * Accessor for isFilled.
     * @return the boolean
     */
    public boolean getIsFilled(){
        return isFilled;
    }

    /**
     * An abstract method that all the classes that implement Shape must have to be able to move the shapes.
     * @param newPoint the new point
     */
    public abstract void moveShape(Point newPoint);

    /**
     * An abstract method that all classes tha implement shape must have so that they can calculate the area around a shape
     * when it must be selected.
     * @param point the point
     * @return the shape in area
     */
    public abstract CustomShape getShapeInArea(Point point);

}
