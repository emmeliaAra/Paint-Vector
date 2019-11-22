package FileManager;

import Model.IModel;
import Shapes.CustomShape;

import java.io.*;


public class FileManager {

    public static void saveCanvas(IModel model) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("test",true);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(model);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(objectOutputStream != null)
                objectOutputStream.close();
            if (fileOutputStream != null)
                fileOutputStream.close();
        }
    }

    public static IModel loadCanvas(IModel newValue) throws IOException {
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("test");
            objectInputStream = new ObjectInputStream(fileInputStream);
            IModel model = (IModel)objectInputStream.readObject();
            return model;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(objectInputStream != null)
                objectInputStream.close();
            if(fileInputStream != null)
                fileInputStream.close();
        }
        return null;
    }

}
