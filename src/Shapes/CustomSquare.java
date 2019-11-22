package Shapes;

import java.awt.*;

public class CustomSquare extends CustomQuadrilateral {

    public CustomSquare(String shapeId, Color shapeColor, Point startPoint, Point endPoint, boolean isFilled) {
        super(shapeId, shapeColor, startPoint, endPoint, isFilled);

    }

    //Set the heigh to be the same as the width
    @Override
    public void setHeight(int height) {
        super.setHeight(this.getWidth());
    }
}
