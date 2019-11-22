package Shapes;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

public class CustomRectangle extends CustomQuadrilateral {

    Area area;

    public CustomRectangle(String shapeId, Color shapeColor,Point point1, Point point2,boolean isFilled) {
        super(shapeId, shapeColor,point1,point2,isFilled);
    }

    public void checkArea(Point point){

        area = new Area(new Rectangle(this.getStartPoint().x,this.getStartPoint().y,this.getWidth(),this.getHeight()));
        if(area.contains(point)){
            System.out.println("hola");
        }
    }

}
