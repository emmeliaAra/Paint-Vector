package tests;

import Shapes.CustomLine;
import Shapes.CustomShape;
import org.junit.Before;
import org.junit.Test;
import java.awt.*;

import static org.junit.Assert.*;

/**
 * This class is used to test the CustomLine Class
 */
public class CustomLineTest {

    private CustomLine line;

    /**
     *  Sets up the environment fo testing
     */
    @Before
    public void setup() {
        line = new CustomLine("Line", Color.black,new Point(1,1),new Point(3,3),false);
    }

    /**
     * Test get shape area.
     */
    @Test
    public void testGetShapeArea(){
        CustomShape expectedLine = line.getShapeInArea(new Point(2,2));
        assertEquals(expectedLine,line);
    }

    /**
     * Test move shape.
     */
    @Test
    public void testMoveShape(){
        line.moveShape(new Point(7,8));
        assertEquals(line.getStartPoint(),new Point(7,8));
        assertEquals(line.getEndPoint(), new Point(9,10));
    }
}
