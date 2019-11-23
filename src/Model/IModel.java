package Model;


import Shapes.CustomLine;
import Shapes.CustomShape;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;

/**
 * The interface Model.
 */
public interface IModel {

    /**
     * Add observers.
     *
     * @param listener the listener
     */
    void addObservers(PropertyChangeListener listener);

    /**
     * Create line.
     *
     * @param startPoint the start point
     * @param endPoint   the end point
     */
    void createLine(Point startPoint, Point endPoint);

    /**
     * Create rect.
     *
     * @param startPoint the start point
     * @param endPoint   the end point
     */
    void createRect(Point startPoint, Point endPoint);

    /**
     * Create square.
     *
     * @param startPoint the start point
     * @param endPoint   the end point
     */
    void createSquare(Point startPoint, Point endPoint);

    /**
     * Create ellipse.
     *
     * @param startPoint the start point
     * @param endPoint   the end point
     */
    void createEllipse(Point startPoint, Point endPoint);

    /**
     * Create triangle.
     *
     * @param initialPoint the initial point
     * @param points       the points
     */
    void createTriangle(Point initialPoint,Point[] points);

    /**
     * Sets current shape selected.
     *
     * @param shape the shape
     */
    void setCurrentShapeSelected(String shape);

    /**
     * Sets color.
     *
     * @param color the color
     */
    void setColor(Color color);

    /**
     * Enable filling.
     */
    void enableFilling();

    /**
     * Undo.
     */
    void undo();

    /**
     * Redo.
     */
    void redo();

    /**
     * Sets select mode.
     *
     * @param isSelected the is selected
     */
    void setSelectMode(boolean isSelected);

    /**
     * Clear page.
     */
    void clearPage();

    /**
     * Save canvas.
     */
    void saveCanvas();

    /**
     * Load canvas.
     */
    void loadCanvas();

    /**
     * Update variables after loading.
     */
    void updateVariablesAfterLoading();

    /**
     * Gets stored shapes.
     *
     * @return the stored shapes
     */
    LinkedList<CustomShape> getStoredShapes();
}
