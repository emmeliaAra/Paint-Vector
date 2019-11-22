package Shapes;

import java.awt.*;
import java.io.Serializable;

public class CustomRectangle extends CustomQuadrilateral implements Serializable {

    public CustomRectangle(String shapeId, Color shapeColor,Point startPoint, Point endPoint,boolean isFilled) {
        super(shapeId, shapeColor,startPoint,endPoint,isFilled);
    }

}
