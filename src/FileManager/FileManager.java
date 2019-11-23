package FileManager;

import Model.IModel;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;


public class FileManager {

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

    public static IModel loadCanvas(IModel newValue,JFrame frame) throws IOException {
        String fileName = null;
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(frame);

        if(returnValue == JFileChooser.APPROVE_OPTION)
            fileName  = fileChooser.getSelectedFile().getPath();

        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            IModel model = (IModel)objectInputStream.readObject();
            return model;
        } catch (FileNotFoundException e) {
            System.out.println("File not Found or File not selected. Try Again");
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
