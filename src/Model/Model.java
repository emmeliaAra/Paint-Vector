package Model;

import Shapes.*;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Stack;

/**
 * The type Model.
 */
public class Model implements IModel, Serializable {

    private boolean hasFilling, isSelectEnable;
    private PropertyChangeSupport notifier;
    private String currentShapeSelected = "Line";
    private LinkedList<CustomShape> shapes;
    private Stack<CustomShape> undoShapeStack;
    private Color color;

    /**
     * Instantiates a new Model.
     */
    public Model(){
        notifier = new PropertyChangeSupport(this);
        shapes = new LinkedList<>();
        color = Color.BLACK;
        hasFilling = isSelectEnable = false;
        undoShapeStack = new Stack<>();
    }

    /**
     * This method is used to add observers to the observable object
     * @param listener the listener
     */
    @Override
    public void addObservers(PropertyChangeListener listener) {
        notifier.addPropertyChangeListener(listener);
    }

    /**
     * This method is sed to set the current shape and then notify the observers about the change.
     * @param shape the shape
     */
    @Override
    public void setCurrentShapeSelected(String shape) {
        String previousShapeSelected = currentShapeSelected;
        currentShapeSelected = shape;
        notifier.firePropertyChange("ShapeSelectedChange",previousShapeSelected,currentShapeSelected);
    }

    /**
     * This method is called whenever a new line must be created.
     * @param startPoint the start point
     * @param endPoint   the end point
     */
    @Override
    public void createLine(Point startPoint,Point endPoint) {
        LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
        CustomShape customShape = new CustomLine(currentShapeSelected,color,startPoint,endPoint,hasFilling);
        shapes.add(customShape);
        notifier.firePropertyChange("CreateShape",tempShapeList,shapes);
    }

    /**
     * This method is called when an new Rectangle must be created
     * @param startPoint the start point
     * @param endPoint   the end point
     */
    @Override
    public void createRect(Point startPoint, Point endPoint) {
        LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
        CustomShape customShape = new CustomRectangle(currentShapeSelected,color,startPoint,endPoint,hasFilling);
        ((CustomRectangle)customShape).findStartEndPoints();
        ((CustomRectangle)customShape).calculateShapeVariables();
        shapes.add(customShape);
        notifier.firePropertyChange("CreateShape",tempShapeList,shapes);
    }

    /**
     * This method is called when a new square must be created.
     * @param startPoint the start point
     * @param endPoint   the end point
     */
    @Override
    public void createSquare(Point startPoint, Point endPoint) {
        LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
        CustomShape customShape = new CustomSquare(currentShapeSelected,color,startPoint,endPoint,hasFilling);
        ((CustomSquare)customShape).findStartEndPoints();
        ((CustomSquare)customShape).calculateShapeVariables();
        ((CustomSquare)customShape).setHeight(((CustomSquare) customShape).getWidth());
        shapes.add(customShape);
        notifier.firePropertyChange("CreateShape",tempShapeList,shapes);
    }

    /**
     * This method is called when a new ellipse must be created.
     * @param startPoint the start point
     * @param endPoint   the end point
     */
    @Override
    public void createEllipse(Point startPoint, Point endPoint) {
        LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
        CustomShape customShape = new CustomEllipse(currentShapeSelected,color,startPoint,endPoint,hasFilling);
        ((CustomEllipse)customShape).findBoundaries();
        shapes.add(customShape);
        notifier.firePropertyChange("CreateShape",tempShapeList,shapes);
    }

    /**
     * This method is called when a new triangle must be created.
     * @param initialPoint the initial point
     * @param points       the points
     */
    @Override
    public void createTriangle(Point initialPoint,Point[] points){
        LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
        CustomShape customShape = new CustomTriangle(currentShapeSelected,color,initialPoint,3,hasFilling);
        ((CustomTriangle)customShape).setPoints(points);
        shapes.add(customShape);
        notifier.firePropertyChange("CreateShape",tempShapeList,shapes);
    }

    /**
     * This method is called when the user tries to change the color.
     * Changes the color and notifies observes
     * @param color the color
     */
    @Override
    public void setColor(Color color) {
        Color previousColor = this.color;
        this.color = color;
        notifier.firePropertyChange("ColorChange",previousColor,color);
    }

    /**
     * This method is called when the user was to enable or disable filling.
     * Changes the filling value and notifies observers
     */
    @Override
    public void enableFilling() {
        hasFilling = !hasFilling;
        notifier.firePropertyChange("HasFillingValueChange",!hasFilling,hasFilling);
    }

    /**
     * This method is called when the user wants to undo an action.
     * Saves the current shape into a stack and removes it from the list.
     * Then notifies the observers to update the view.
     */
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

    /**
     * This method is called when the user wants to redo an undo action
     * Retrieves the shape from the stack and adds it to the list
     * then notifies the observes to update the view.
     */
    @Override
    public void redo() {
        if(undoShapeStack.size()> 0){
            CustomShape customShape = undoShapeStack.pop();
            LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
            shapes.addLast(customShape);
            notifier.firePropertyChange("RedoMode",tempShapeList,shapes);
        }
    }

    /**
     * This method is called to change the selectMode value
     * @param isSelectEnable boolean to indicate if the select mode is on or off
     */
    @Override
    public void setSelectMode(boolean isSelectEnable) {
        boolean previousSelectMode = this.isSelectEnable;
        this.isSelectEnable = isSelectEnable;
        notifier.firePropertyChange("SelectModeChange",previousSelectMode,isSelectEnable);
    }

    /**
     * This method is used to clear the page.
     * Removes all the shapes from the list and the undo stack (if any).
     */
    @Override
    public void clearPage() {
        //A user cannot undo a clear page operation
        undoShapeStack = new Stack<>();
        LinkedList<CustomShape> tempShapeList = (LinkedList<CustomShape>)shapes.clone();
        shapes = new LinkedList<>();
        notifier.firePropertyChange("ClearPage",tempShapeList,shapes);
    }

    /**
     * This method is called when the user needs to save the current canvas
     */
    @Override
    public void saveCanvas() {
        notifier.firePropertyChange("SaveCanvas",null,this);
    }

    /**
     * This method is called when a user wants to load an already existing canvas.
     */
    @Override
    public void loadCanvas() {
        notifier.firePropertyChange("LoadCanvas",null,this);
    }

    /**
     * This method will set all the variables to their initial value and notify the observes once a canvas is loaded.
     */
    @Override
    public void updateVariablesAfterLoading() {
        color = Color.BLACK;
        hasFilling = false;
        isSelectEnable = false;
        currentShapeSelected = "Line";
        undoShapeStack = new Stack<>();

        notifier.firePropertyChange("ColorChange",null,color);
        notifier.firePropertyChange("HasFillingValueChange",null,hasFilling);
        notifier.firePropertyChange("SelectModeChange",null,isSelectEnable);
        notifier.firePropertyChange("ShapeSelectedChange",null,currentShapeSelected);
    }

    /**
     * Accessor for the storedShapes.
     * @return a linkedList that contains all the stored shapes.
     */
    @Override
    public LinkedList<CustomShape> getStoredShapes() {
        return shapes;
    }
}
