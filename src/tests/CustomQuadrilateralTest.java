package tests;

import Shapes.CustomLine;
import Shapes.CustomQuadrilateral;
import Shapes.CustomRectangle;
import Shapes.CustomShape;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import static org.junit.Assert.*;

public class CustomQuadrilateralTest {


    private CustomQuadrilateral rectangle,square;

    @Before
    public void setup() {
        rectangle = new CustomRectangle("Rectangle", Color.black,new Point(1,1),new Point(3,3),false);
        square = new CustomRectangle("Rectangle", Color.black,new Point(4,4),new Point(7,7),false);
    }

    @Test
    public void testFindStartEndPoints(){
        rectangle.findStartEndPoints();
        square.findStartEndPoints();

        assertEquals(rectangle.getStartPoint(),new Point(1,1));
        assertEquals(rectangle.getEndPoint(),new Point(3,3));
        assertEquals(square.getStartPoint(),new Point(4,4));
        assertEquals(square.getEndPoint(),new Point(7,7));
    }
    @Test
    public void testCalculateWidthAndHeigh(){
        rectangle.calculateShapeVariables();
        square.calculateShapeVariables();

        assertEquals(rectangle.getWidth(),rectangle.getEndPoint().x - rectangle.getStartPoint().x);
        assertEquals(rectangle.getWidth(),rectangle.getEndPoint().x - rectangle.getStartPoint().x);

        assertEquals(square.getWidth(),rectangle.getEndPoint().x - rectangle.getStartPoint().x);
        assertEquals(square.getHeight(),rectangle.getEndPoint().y - rectangle.getStartPoint().y);
    }


    @Test
    public void testGetShapeInArea(){
        rectangle.findStartEndPoints();
        rectangle.calculateShapeVariables();
        square.findStartEndPoints();
        square.calculateShapeVariables();

        CustomShape expectedRectangle = rectangle.getShapeInArea(new Point(2,2));
        CustomShape expectedSquare = square.getShapeInArea(new Point(5,5));

        assertEquals(expectedRectangle,rectangle);
        assertEquals(expectedSquare,square);
    }

    @Test
    public void testMoveShape(){
        rectangle.findStartEndPoints();
        rectangle.calculateShapeVariables();
        square.findStartEndPoints();
        square.calculateShapeVariables();

        rectangle.moveShape(new Point(30,30));
        square.moveShape(new Point(20,20));
        assertEquals(rectangle.getStartPoint(),new Point(30,30));
        assertEquals(rectangle.getEndPoint(),new Point(31,31));
        assertEquals(square.getStartPoint(),new Point(20,20));
        assertEquals(square.getEndPoint(),new Point(24,24));

    }



}
