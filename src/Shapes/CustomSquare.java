package Shapes;

import java.awt.*;

public class CustomSquare extends CustomQuadrilateral {

    public CustomSquare(String shapeId, Color shapeColor, Point point1, Point point2, boolean isFilled) {
        super(shapeId, shapeColor, point1, point2, isFilled);

    }

    //Set the heigh to be the same as the width
    @Override
    public void setHeight(int height) {
        super.setHeight(this.getWidth());
    }
}
