package Main;

import Delegate.Delegate;
import Model.IModel;
import Model.Model;

public class VectorMain {

    public static void main(String[]arg){
        IModel model = new Model();
        Delegate delegate = new Delegate(model);

    }
}
