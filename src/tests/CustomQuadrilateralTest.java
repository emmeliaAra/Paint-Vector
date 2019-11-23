package tests;

import Shapes.CustomQuadrilateral;
import Shapes.CustomRectangle;
import Shapes.CustomShape;
import Shapes.CustomSquare;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import static org.junit.Assert.*;

/**
 * This class is used to test the CustomQuadrilateral class
 */
public class CustomQuadrilateralTest {


    private CustomQuadrilateral rectangle,square,rectangle2, square2;

    /**
     * Sets up the environment fo testing
     */
    @Before
    public void setup() {
        rectangle = new CustomRectangle("Rectangle", Color.black,new Point(1,1),new Point(3,3),false);
        rectangle2 = new CustomRectangle("Rectangle", Color.black,new Point(3,3),new Point(1,1),false);

        square = new CustomSquare("Square", Color.black,new Point(4,4),new Point(7,7),false);
        square2 = new CustomSquare("Square", Color.black,new Point(7,7),new Point(4,4),false);
    }

    /**
     * Test find start end points.
     */
    @Test
    public void testFindStartEndPoints(){
        rectangle.findStartEndPoints();
        rectangle2.findStartEndPoints();

        square.findStartEndPoints();
        square2.findStartEndPoints();

        assertEquals(rectangle.getStartPoint(),new Point(1,1));
        assertEquals(rectangle.getEndPoint(),new Point(3,3));
        assertEquals(rectangle2.getStartPoint(),new Point(1,1));
        assertEquals(rectangle2.getEndPoint(),new Point(3,3));

        assertEquals(square.getStartPoint(),new Point(4,4));
        assertEquals(square.getEndPoint(),new Point(7,7));
        assertEquals(square.getStartPoint(),new Point(4,4));
        assertEquals(square.getEndPoint(),new Point(7,7));
    }

    /**
     * Test calculate width and heigh.
     */
    @Test
    public void testCalculateWidthAndHeigh(){
        rectangle.calculateShapeVariables();
        square.calculateShapeVariables();

        assertEquals(rectangle.getWidth(),rectangle.getEndPoint().x - rectangle.getStartPoint().x);
        assertEquals(rectangle.getWidth(),rectangle.getEndPoint().x - rectangle.getStartPoint().x);

        assertEquals(square.getWidth(),rectangle.getEndPoint().x - rectangle.getStartPoint().x);
        assertEquals(square.getHeight(),rectangle.getEndPoint().y - rectangle.getStartPoint().y);
    }

    /**
     * Test get shape in area.
     */
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

    /**
     * Test move shape.
     */
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
