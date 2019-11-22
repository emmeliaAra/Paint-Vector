package Model;

import Shapes.*;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
    private boolean hasFilling, isSelectEnable;
    private Stack<CustomShape> undoShapeStack;

    public Model(){
        notifier = new PropertyChangeSupport(this);
        shapes = new LinkedList<>();
        color = Color.BLACK;
        hasFilling = isSelectEnable = false;
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
        CustomShape customShape = new CustomLine(currentShapeSelected,color,startPoint,endPoint,hasFilling);
        shapes.add(customShape);
        notifier.firePropertyChange("CreateShape",tempShapeList,shapes);
    }

    @Override
    public void createRect(Point startPoint, Point endPoint) {
        LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
        CustomShape customShape = new CustomRectangle(currentShapeSelected,color,startPoint,endPoint,hasFilling);
        ((CustomRectangle)customShape).findRectangleBoundaries();
        shapes.add(customShape);
        notifier.firePropertyChange("CreateShape",tempShapeList,shapes);
    }

    @Override
    public void createSquare(Point startPoint, Point endPoint) {
        LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
        CustomShape customShape = new CustomSquare(currentShapeSelected,color,startPoint,endPoint,hasFilling);
        ((CustomSquare)customShape).findRectangleBoundaries();
        ((CustomSquare)customShape).setHeight(((CustomSquare) customShape).getWidth());
        shapes.add(customShape);
        notifier.firePropertyChange("CreateShape",tempShapeList,shapes);
    }

    @Override
    public void createEllipse(Point startPoint, Point endPoint) {
        LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
        CustomShape customShape = new CustomEllipse(currentShapeSelected,color,startPoint,endPoint,hasFilling);
        ((CustomEllipse)customShape).findRectangleBoundaries();
        shapes.add(customShape);
        notifier.firePropertyChange("CreateShape",tempShapeList,shapes);
    }
    @Override
    public void createTriangle(Point initialPoint,Point[] points){
        LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
        CustomShape customShape = new CustomTriangle(currentShapeSelected,color,initialPoint,3,hasFilling);
        ((CustomTriangle)customShape).setPoints(points);
        shapes.add(customShape);
        notifier.firePropertyChange("CreateShape",tempShapeList,shapes);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void enableFilling() {
        boolean previousFillingState = hasFilling;
        hasFilling = !hasFilling;
        System.out.println(previousFillingState + " "+ hasFilling);
        notifier.firePropertyChange("FillingValueChange",previousFillingState,hasFilling);
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
        if(undoShapeStack.size()> 0){
            CustomShape customShape = undoShapeStack.pop();
            LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
            shapes.addLast(customShape);
            notifier.firePropertyChange("RedoMode",tempShapeList,shapes);
        }

    }

    @Override
    public void setSelectMode(boolean isSelectEnable) {
        this.isSelectEnable = isSelectEnable;
    }

    @Override
    public boolean getSelectMode(){
        return isSelectEnable;
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
