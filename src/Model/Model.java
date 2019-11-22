package Model;

import Shapes.CustomEllipse;
import Shapes.CustomLine;
import Shapes.CustomRectangle;
import Shapes.CustomShape;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.XMLDecoder;
import java.util.LinkedList;
import java.util.Stack;

public class Model implements IModel {

    private PropertyChangeSupport notifier;
    private static final String LINE = "Line";
    private static final String RECTANGLE = "Rectangle";
    private static final String ELLIPSE= "Ellipse";
    private String currentShapeSelected = LINE;
    private LinkedList<CustomShape> shapes;
    private Color color;
    private boolean undoMode,redoMode;
    private Stack<CustomShape> undoShapeStack;

    public Model(){
        notifier = new PropertyChangeSupport(this);
        shapes = new LinkedList<>();
        color = Color.BLACK;
        undoMode = false;
        redoMode = false;
        undoShapeStack = new Stack<>();
    }

    @Override
    public void addObservers(PropertyChangeListener listener) {
        notifier.addPropertyChangeListener(listener);
    }

    @Override
    public void setCurrentShapeSelected(String shape) {
        String previousShapeSelected = currentShapeSelected;
        currentShapeSelected = shape;
        notifier.firePropertyChange("ShapeSelectedChange",previousShapeSelected,currentShapeSelected);
    }

    @Override
    public void createLine(Point startPoint,Point endPoint) {
        LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
        CustomShape customShape = new CustomLine(currentShapeSelected,color,startPoint,endPoint);
        shapes.add(customShape);
        notifier.firePropertyChange("CreateShape",tempShapeList,shapes);
    }

    @Override
    public void createRect(Point startPoint, Point endPoint) {
        LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
        CustomShape customShape = new CustomRectangle(currentShapeSelected,color,startPoint,endPoint);
        ((CustomRectangle)customShape).findRectangleBoundaries();
        shapes.add(customShape);
        notifier.firePropertyChange("CreateShape",tempShapeList,shapes);
    }

    @Override
    public void createEllipse(Point startPoint, Point endPoint) {
        LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
        CustomShape customShape = new CustomEllipse(currentShapeSelected,color,startPoint,endPoint);
        ((CustomEllipse)customShape).findRectangleBoundaries();
        shapes.add(customShape);
        notifier.firePropertyChange("CreateShape",tempShapeList,shapes);
    }

    @Override
    public void setColor(Color color) {

    }

    @Override
    public void enableFilling() {

    }

    @Override
    public void undo() {
        if(shapes.size()> 0){
            CustomShape customShape = shapes.getLast();
            undoShapeStack.push(customShape);
            LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
            shapes.removeLast();
            notifier.firePropertyChange("UndoMode",tempShapeList,shapes);
        }
    }

    @Override
    public void redo() {

    }
    @Override
    public String getCurrentShapeSelected(){
        return currentShapeSelected;
    }

    @Override
    public Color getColor(){
        return color;
    }
}
