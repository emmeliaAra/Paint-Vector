package Shapes;

import java.awt.*;
import java.awt.geom.Area;

public abstract class CustomShape{

    private Color shapeColor;
    private String shapeID;
    private boolean isFilled;

    public CustomShape(String shapeId, Color shapeColor,boolean isFilled) {
        this.shapeID = shapeId;
        this.shapeColor = shapeColor;
        this.isFilled = isFilled;
    }
    public Color getColor() {
        return shapeColor;
    }

    public String getShapeID() {
        return shapeID;
    }

    public boolean getIsFilled(){
        return isFilled;
    }

    public abstract void moveShape(Point newPoint);

    public abstract CustomShape getShapeInArea(Point point);

}
