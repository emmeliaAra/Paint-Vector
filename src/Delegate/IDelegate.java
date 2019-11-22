package Delegate;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface IDelegate extends PropertyChangeListener {
    void propertyChange(PropertyChangeEvent evt);
}
