package FileManager;

import Model.IModel;
import javax.swing.*;
import java.io.*;


/**
 * The type File manager.
 * This class is used to load and save the canvas.
 */
public class FileManager {

    /**
     * This method is used to save a canvas as a Serializable object so that it can be loaded again.
     * @param model    the model
     * @param fileName the file name
     * @throws IOException the io exception
     */
    public static void saveCanvas(IModel model,String fileName) throws IOException {

        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName,true);
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

    /**
     * This method loads the Serializable IModel object back into a IModel object and pass it to the Delegate so that the
     * a saved canvas will be retrieved.
     * @param frame    the frame
     * @return the model
     * @throws IOException the io exception
     */
    public static IModel loadCanvas(JFrame frame) throws IOException {
        //Create a file chooser to allow the user to choose the file he/she wants to load.
        JFileChooser fileChooser = new JFileChooser();
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = null;
        String fileName = null;

        int returnValue = fileChooser.showOpenDialog(frame);
        if(returnValue == JFileChooser.APPROVE_OPTION)
            fileName  = fileChooser.getSelectedFile().getPath();
        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            IModel model = (IModel)objectInputStream.readObject();
            return model;
        } catch (FileNotFoundException e) {
            System.out.println("File not Found or File not selected. Try Again");
        } catch (IOException | ClassNotFoundException e) {
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
