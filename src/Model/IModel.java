package Model;


import Shapes.CustomLine;

import java.awt.*;
import java.beans.PropertyChangeListener;

public interface IModel {

    void addObservers(PropertyChangeListener listener);
    void setCurrentShapeSelected(String shape);
    void createLine(Point startPoint, Point endPoint);
    void createRect(Point startPoint, Point endPoint);
    void createSquare(Point startPoint, Point endPoint);
    void createEllipse(Point startPoint, Point endPoint);
    void createTriangle(Point initialPoint,Point[] points);
    void setColor(Color color);
    void enableFilling();
    void undo();
    void redo();
    String getCurrentShapeSelected();
    Color getColor();
}
