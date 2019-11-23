package Delegate;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The interface Delegate.
 * Used to extend PropertyChangeListener.
 */
    public interface IDelegate extends PropertyChangeListener {
    void propertyChange(PropertyChangeEvent evt);
}
