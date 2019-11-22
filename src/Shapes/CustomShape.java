package Shapes;

import java.awt.*;

public abstract class CustomShape{

    private Color shapeColor;
    private String shapeID;

    public CustomShape(String shapeId, Color shapeColor) {
        this.shapeID = shapeId;
        this.shapeColor = shapeColor;
    }
    public Color getColor() {
        return shapeColor;
    }

    public String getShapeID() {
        return shapeID;
    }
}
