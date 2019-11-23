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


public class DrawingPanel extends JPanel implements MouseListener,MouseMotionListener, Serializable,IDelegate {

    private IModel model;
    private LinkedList<CustomShape> shapes,storedShapes;
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

    public DrawingPanel(IModel model){
        addMouseListener(this);
        addMouseMotionListener(this);
        this.setVisible(true);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY,5));
        this.setBackground(Color.WHITE);
        this.model = model;
        this.model.addObservers(this);
        shapes = new LinkedList<>();
        storedShapes = new LinkedList<>();
    }

    public void paintComponent(Graphics g) {
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

    @Override
    public void mouseClicked(MouseEvent e) {

        //must loop over all the storedshapes, fu=inf the one select if there is one selectd and call move ewhen dragging.
        if(model.getSelectMode()){
            for (CustomShape shape: storedShapes) {
                switch (shape.getShapeID()){
                    case RECTANGLE:
                    case SQUARE:
                        currentSelectedShape =((CustomQuadrilateral)shape).getShapeInArea(e.getPoint());
                            break;
                    case LINE:
                        currentSelectedShape = ((CustomLine)shape).getShapeInArea(e.getPoint());
                        break;
                    case TRIANGLE:
                        currentSelectedShape = ((CustomTriangle)shape).getShapeInArea(e.getPoint());
                        break;
                    case ELLIPSE:
                        currentSelectedShape = (((CustomEllipse)shape).getShapeInArea(e.getPoint()));
                        break;
                }
                if(currentSelectedShape != null)
                    break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch(model.getCurrentShapeSelected()){
            case LINE:
                line = new CustomLine(LINE,model.getColor(),e.getPoint(),new Point(),hasFocus());
                break;
            case RECTANGLE:
                rectangle = new CustomRectangle(RECTANGLE,model.getColor(),e.getPoint(),new Point(),model.getHasFilling());
                break;
            case SQUARE:
                square = new CustomSquare(SQUARE,model.getColor(),e.getPoint(),new Point(),model.getHasFilling());
                break;
            case ELLIPSE:
                ellipse = new CustomEllipse(ELLIPSE,model.getColor(),e.getPoint(),new Point(),model.getHasFilling());
                break;
            case TRIANGLE:
                triangle = new CustomTriangle(TRIANGLE,model.getColor(),e.getPoint(),3,model.getHasFilling());
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!model.getSelectMode()){
            switch(model.getCurrentShapeSelected()){
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

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(!model.getSelectMode()){
            switch(model.getCurrentShapeSelected()){
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
            repaint();
        }else {
            if(currentSelectedShape != null){
                switch (currentSelectedShape.getShapeID()){
                    case RECTANGLE:
                    case SQUARE:
                        ((CustomQuadrilateral) currentSelectedShape).moveShape(e.getPoint());
                        break;
                    case LINE:
                        ((CustomLine)currentSelectedShape).moveShape(e.getPoint());
                        break;
                    case ELLIPSE:
                        ((CustomEllipse)currentSelectedShape).moveShape(e.getPoint());

                }
                repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public LinkedList<CustomShape> getShapes(){
        return shapes;
    }

    public void setShapes(LinkedList<CustomShape> shapes){
        this.shapes = (LinkedList<CustomShape>)shapes.clone();
        this.storedShapes =  (LinkedList<CustomShape>)shapes.clone();
    }

    public void setModel(IModel model){
        this.model = model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if(event.getSource().equals(model)){
            if(event.getPropertyName().equalsIgnoreCase("ClearPage")){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        shapes = new LinkedList<>();
                        storedShapes = new LinkedList<>();
                        repaint();
                    }
                });
            }
        }
    }
}
