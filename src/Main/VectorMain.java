package Main;

import Delegate.Delegate;
import Model.IModel;
import Model.Model;

/**
 * The type Vector main.
 */
public class VectorMain {

    /**
     * This is the main method that is used to call the Delegate and start the paint application.
     * @param arg the arg
     */
    public static void main(String[]arg){
        IModel model = new Model();
        Delegate delegate = new Delegate(model);

    }
}
