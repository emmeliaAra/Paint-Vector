package tests;

import Shapes.CustomEllipse;
import Shapes.CustomShape;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class CustomEllipseTest {

    private CustomEllipse ellipse;


    @Before
    public void setup() {
        ellipse = new CustomEllipse("Ellipse", Color.black,new Point(1,1),new Point(3,3),false);
        ellipse.findBoundaries();
    }

    @Test
    public void testFindBoundaries(){
        assertEquals(ellipse.getStartPoint().x,1);
        assertEquals(ellipse.getStartPoint().y,1);
        assertEquals(ellipse.getEndPoint().x,3);
        assertEquals(ellipse.getEndPoint().y,3);
        assertEquals(ellipse.getWidth(),2);
        assertEquals(ellipse.getHeight(),2);
    }

    @Test
    public void testGetShapeArea(){
        CustomShape expectedEllipse = ellipse.getShapeInArea(new Point(2,2));
        assertEquals(expectedEllipse,ellipse);
    }

    @Test
    public void testMoveShape(){
        ellipse.moveShape(new Point(7,8));
        assertEquals(ellipse.getStartPoint(),new Point(7,8));
    }

}
