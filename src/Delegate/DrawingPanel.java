package Delegate;

import Model.IModel;
import Shapes.CustomEllipse;
import Shapes.CustomLine;
import Shapes.CustomRectangle;
import Shapes.CustomShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.XMLDecoder;
import java.util.LinkedList;
import java.util.zip.ZipEntry;


public class DrawingPanel extends JPanel implements MouseListener,MouseMotionListener,IDelegate {

    private IModel model;
    private Point startPoint,endPoint;
    private LinkedList<CustomShape> shapes,storedShapes;
    private static final String LINE = "Line";
    private static final String RECTANGLE = "Rectangle";
    private static final String ELLIPSE= "Ellipse";

    private CustomLine line;
    private CustomRectangle rectangle;
    private CustomEllipse ellipse;



    public DrawingPanel(IModel model){
        addMouseListener(this);
        addMouseMotionListener(this);
        this.setVisible(true);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY,5));
        this.setBackground(Color.WHITE);
        this.model = model;
        startPoint = new Point();
        endPoint = new Point();
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
            switch (shape.getShapeID()){
                case LINE:
                    g.drawLine(((CustomLine)shape).getStartPoint().x,((CustomLine)shape).getStartPoint().y,((CustomLine)shape).getEndPoint().x,((CustomLine)shape).getEndPoint().y );
                    break;
                case RECTANGLE:
                    g.drawRect(((CustomRectangle)shape).getStartPoint().x,((CustomRectangle)shape).getStartPoint().y,((CustomRectangle)shape).getWidth(),((CustomRectangle)shape).getHeight());
                    break;
                case ELLIPSE:
                    g.drawOval(((CustomEllipse)shape).getStartPoint().x,((CustomEllipse)shape).getStartPoint().y,((CustomEllipse)shape).getWidth(),((CustomEllipse)shape).getHeight());
                    break;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch(model.getCurrentShapeSelected()){
            case LINE:
                line = new CustomLine(LINE,model.getColor(),e.getPoint(),null);
                break;
            case RECTANGLE:
                rectangle = new CustomRectangle(RECTANGLE,model.getColor(),e.getPoint(),new Point());
                break;
            case ELLIPSE:
                ellipse = new CustomEllipse(ELLIPSE,model.getColor(),e.getPoint(),new Point());
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch(model.getCurrentShapeSelected()){
            case LINE:
                storedShapes.add(line);
                model.createLine(line.getStartPoint(),line.getEndPoint());
                break;
            case RECTANGLE:
               storedShapes.add(rectangle);
               model.createRect(rectangle.getStartPoint(),rectangle.getEndPoint());
               break;
            case ELLIPSE:
               storedShapes.add(ellipse);
               model.createEllipse(ellipse.getStartPoint(),ellipse.getEndPoint());
               break;
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
        switch(model.getCurrentShapeSelected()){
            case LINE:
                line.setEndPoint(e.getPoint());
                shapes.add(line);
                break;
            case RECTANGLE:
                rectangle.setEndPoint(e.getPoint());
                shapes.add(rectangle);
                break;
            case ELLIPSE:
                ellipse.setEndPoint(e.getPoint());
                shapes.add(ellipse);
                break;
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    public void setShapes(LinkedList<CustomShape> shapes){
        this.shapes = (LinkedList<CustomShape>)shapes.clone();
        this.storedShapes =  (LinkedList<CustomShape>)shapes.clone();
    }
    public LinkedList<CustomShape> getShapes(){
        return shapes;
    }


    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if(event.getSource().equals(model)){
            if(event.getPropertyName().equalsIgnoreCase("CreateShape")){
                setShapes((LinkedList<CustomShape>)event.getNewValue());
            }
        }
    }

}
