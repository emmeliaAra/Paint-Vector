package Delegate;

import Model.IModel;
import Shapes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.util.LinkedList;


/**
 * The type Drawing panel.
 */
public class DrawingPanel extends JPanel implements MouseListener,MouseMotionListener, Serializable,IDelegate {

    private IModel model;
    private static LinkedList<CustomShape> shapes,storedShapes;
    private static String shapeTypeSelected;
    private static Color color;
    private static boolean hasFilling,selectMode;
    private static final String LINE = "Line";
    private static final String RECTANGLE = "Rectangle";
    private static final String SQUARE = "Square";
    private static final String ELLIPSE = "Ellipse";
    private static final String TRIANGLE = "Triangle";

    private CustomLine line;
    private CustomRectangle rectangle;
    private CustomSquare square;
    private CustomEllipse ellipse;
    private CustomTriangle triangle;
    private CustomShape currentSelectedShape;

    /**
     * Instantiates a new Drawing panel.
     *
     * @param model the model
     */
    public DrawingPanel(IModel model){
        addMouseListener(this);
        addMouseMotionListener(this);
        this.setVisible(true);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY,5));
        this.setBackground(Color.WHITE);
        this.model = model;
        this.model.addObservers(this);
        shapeTypeSelected = LINE;
        color = Color.BLACK;
        hasFilling = false;
        shapes = new LinkedList<>();
        storedShapes = new LinkedList<>();
    }

    /**
     * This method is called automatically when an interaction with the JPanel Occurs.
     * @param g
     */
    public void paintComponent(Graphics g) {
        /* Makes the canvas white and adds a boarder
         * Then iterates over the shape list and draws all the shapes to the panel.
         * It will check if the shape is filled or not and then based on that will call the required method
         */
        super.paintComponents(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 600, 600);

        for (int i = 0; i<shapes.size(); i++) {
            CustomShape shape = shapes.get(i);
            g.setColor(shape.getColor());
            if(shape.getIsFilled()){

                switch (shape.getShapeID()){
                    case LINE:
                        g.drawLine(((CustomLine)shape).getStartPoint().x,((CustomLine)shape).getStartPoint().y,((CustomLine)shape).getEndPoint().x,((CustomLine)shape).getEndPoint().y );
                        break;
                    case RECTANGLE:
                        g.fillRect(((CustomRectangle)shape).getStartPoint().x,((CustomRectangle)shape).getStartPoint().y,((CustomRectangle)shape).getWidth(),((CustomRectangle)shape).getHeight());
                        break;
                    case SQUARE:
                        g.fillRect(((CustomSquare)shape).getStartPoint().x,((CustomSquare)shape).getStartPoint().y,((CustomSquare)shape).getWidth(),((CustomSquare)shape).getHeight());
                        break;
                    case ELLIPSE:
                        g.fillOval(((CustomEllipse)shape).getStartPoint().x,((CustomEllipse)shape).getStartPoint().y,((CustomEllipse)shape).getWidth(),((CustomEllipse)shape).getHeight());
                        break;
                    case TRIANGLE:
                        int[] xPoints = {(((CustomTriangle) shape).getPoints())[0].x,(((CustomTriangle) shape).getPoints())[1].x,(((CustomTriangle) shape).getPoints())[2].x};
                        int[] yPoints = {(((CustomTriangle) shape).getPoints())[0].y,(((CustomTriangle) shape).getPoints())[1].y,(((CustomTriangle) shape).getPoints())[2].y};
                        g.fillPolygon(xPoints,yPoints,3);
                        break;
                }
            } else {
                switch (shape.getShapeID()){
                    case LINE:
                        g.drawLine(((CustomLine)shape).getStartPoint().x,((CustomLine)shape).getStartPoint().y,((CustomLine)shape).getEndPoint().x,((CustomLine)shape).getEndPoint().y );
                        break;
                    case RECTANGLE:
                    case SQUARE:
                        g.drawRect(((CustomQuadrilateral)shape).getStartPoint().x,((CustomQuadrilateral)shape).getStartPoint().y,((CustomQuadrilateral)shape).getWidth(),((CustomQuadrilateral)shape).getHeight());
                        break;
                    case ELLIPSE:
                        g.drawOval(((CustomEllipse)shape).getStartPoint().x,((CustomEllipse)shape).getStartPoint().y,((CustomEllipse)shape).getWidth(),((CustomEllipse)shape).getHeight());
                        break;
                    case TRIANGLE:
                        int[] xPoints = {(((CustomTriangle) shape).getPoints())[0].x,(((CustomTriangle) shape).getPoints())[1].x,(((CustomTriangle) shape).getPoints())[2].x};
                        int[] yPoints = {(((CustomTriangle) shape).getPoints())[0].y,(((CustomTriangle) shape).getPoints())[1].y,(((CustomTriangle) shape).getPoints())[2].y};
                        g.drawPolygon(xPoints,yPoints,3);
                        break;
                }
            }
        }
    }

    /**
     * This method is called when the mouse is clicked on the panel
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        /* If the mouse is clicked and the selection mode is true then the user wants to select a shape to move
         * Iterate over all the stored shapes and find the one has an area that contains the current point of the mouse.
        */
        if(selectMode){
            for (CustomShape shape: storedShapes) {
                switch (shape.getShapeID()){
                    case RECTANGLE:
                    case SQUARE:
                        currentSelectedShape =shape.getShapeInArea(e.getPoint());
                            break;
                    case LINE:
                        currentSelectedShape = shape.getShapeInArea(e.getPoint());
                        break;
                    case TRIANGLE:
                        currentSelectedShape = shape.getShapeInArea(e.getPoint());
                        break;
                    case ELLIPSE:
                        currentSelectedShape = shape.getShapeInArea(e.getPoint());
                        break;
                }
            }
        }
    }

    /**
     * This method is called when the mouse is pressed.
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //When the mouse is pressed it will initialize the shape based on teh current shape type selected
        switch(shapeTypeSelected){
            case LINE:
                line = new CustomLine(LINE,color,e.getPoint(),new Point(),hasFilling);
                break;
            case RECTANGLE:
                rectangle = new CustomRectangle(RECTANGLE,color,e.getPoint(),new Point(),hasFilling);
                break;
            case SQUARE:
                square = new CustomSquare(SQUARE,color,e.getPoint(),new Point(),hasFilling);
                break;
            case ELLIPSE:
                ellipse = new CustomEllipse(ELLIPSE,color,e.getPoint(),new Point(),hasFilling);
                break;
            case TRIANGLE:
                triangle = new CustomTriangle(TRIANGLE,color,e.getPoint(),3,hasFilling);
                break;
        }
    }

    /**
     * This method is called when the mouse is released.
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        /* Once the mouse is released and the selection mode is off the shape created will be added to the shape list
         * and the model method will be called to create a shape.
         * If the mouse is released and the selection mode is on then then current shape selected will be set to null so that
         * other shapes can be selected again.*/
        if(!selectMode){
            switch(shapeTypeSelected){
                case LINE:
                    storedShapes.add(line);
                    model.createLine(line.getStartPoint(),line.getEndPoint());
                    break;
                case RECTANGLE:
                    storedShapes.add(rectangle);
                    model.createRect(rectangle.getStartPoint(),rectangle.getEndPoint());
                    break;
                case SQUARE:
                    storedShapes.add(square);
                    model.createSquare(square.getStartPoint(),square.getEndPoint());
                    break;
                case ELLIPSE:
                    storedShapes.add(ellipse);
                    model.createEllipse(ellipse.getStartPoint(),ellipse.getEndPoint());
                    break;
                case TRIANGLE:
                    storedShapes.add(triangle);
                    model.createTriangle(triangle.getInitialPoint(),triangle.getPoints());
                    break;
            }
        }else {
            currentSelectedShape = null;
        }
    }

    /**
     * This method is called when the mouse is dragged
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        /* 1.If the method is dragged while the selection mode is off it means that a new shape is created.
         *   Based on the current shape type selected the values for the current shape will be provided and the
         *   shape will be added to the list so that it can be drawn on the panel.
         * 2. If the selection mode is on while dragging the mouse, then a shape is being moved.
         *    Based on the shape Id call the method of the shape to move it
         * 3. At the end of either of the options repaint() the panel.
         */
        if(!selectMode){
            switch(shapeTypeSelected){
                case LINE:
                    line.setEndPoint(e.getPoint());
                    shapes.add(line);
                    break;
                case RECTANGLE:
                    rectangle.setEndPoint(e.getPoint());
                    rectangle.calculateShapeVariables();
                    shapes.add(rectangle);
                    break;
                case SQUARE:
                    square.setEndPoint(e.getPoint());
                    square.calculateShapeVariables();
                    square.setHeight(square.getWidth());
                    shapes.add(square);
                    break;
                case ELLIPSE:
                    ellipse.setEndPoint(e.getPoint());
                    shapes.add(ellipse);
                    break;
                case TRIANGLE:
                    int initialX = triangle.getInitialPoint().x;
                    int initialY = triangle.getInitialPoint().y;
                    Point[] points = new Point[3];
                    points[0] = (new Point(initialX + (e.getPoint().x - initialX)/2, initialY));
                    points[1] = (new Point(initialX, initialY + (e.getPoint().y - initialY)));
                    points[2] = (new Point(e.getPoint().x,e.getPoint().y));
                    triangle.setPoints(points);
                    shapes.add(triangle);
                    break;
            }
        }else {
            if(currentSelectedShape != null){
                switch (currentSelectedShape.getShapeID()){
                    case RECTANGLE:
                    case SQUARE:
                         currentSelectedShape.moveShape(e.getPoint());
                        break;
                    case LINE:
                        currentSelectedShape.moveShape(e.getPoint());
                        break;
                    case ELLIPSE:
                        currentSelectedShape.moveShape(e.getPoint());
                }
            }
        }
        repaint();
    }


    /**
     * This method is called when there is a change fired by the Observable Object (Model)
     * @param event the event that caused the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        /* Main Case -> checks if the event was fired by the observable object of this class
         * If yes then check what is the property of the change so that we take the appropriate actions
         *      Case 1-> Change of the selected shape
         *      Case 2-> Clears the page
         *      Case 3-> Change of color
         *      Case 4-> Change of isFilled value
         *      Case 5-> Change of selection mode value.
         */
        if(event.getSource().equals(model)){
            if(event.getPropertyName().equalsIgnoreCase("ShapeSelectedChange")){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        shapeTypeSelected = (String)event.getNewValue();
                    }
                });

            } else if(event.getPropertyName().equalsIgnoreCase("ClearPage")){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        shapes = new LinkedList<>();
                        storedShapes = new LinkedList<>();
                        repaint();
                    }
                });
            } else if(event.getPropertyName().equalsIgnoreCase("ColorChange")){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        color = (Color)event.getNewValue();
                    }
                });
            } else if(event.getPropertyName().equalsIgnoreCase("HasFillingValueChange")){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        hasFilling = (Boolean) event.getNewValue();
                    }
                });
            } else if(event.getPropertyName().equalsIgnoreCase("SelectModeChange")){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        selectMode = (Boolean) event.getNewValue();
                    }
                });
            }
        }
    }

    /**
     * Get shapes linked list.
     * @return the linked list
     */
    public LinkedList<CustomShape> getShapes(){
        return shapes;
    }

    /**
     * Set shapes.
     * @param shapes the shapes
     */
    public void setShapes(LinkedList<CustomShape> shapes){
        this.shapes = (LinkedList<CustomShape>)shapes.clone();
        this.storedShapes =  (LinkedList<CustomShape>)shapes.clone();
    }

    /**
     * Set model.
     * @param model the model
     */
    public void setModel(IModel model){
        this.model = model;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
