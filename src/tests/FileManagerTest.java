package tests;


import FileManager.FileManager;
import Model.IModel;
import Model.Model;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;


public class FileManagerTest {

    private IModel model;
    private String fileName;
    private JFrame jFrame;


    @Before
    public void setup() {
       model = new Model();
       fileName = "testingFileManager";
       jFrame = new JFrame();
    }

    @Test(expected = NullPointerException.class)
    public void checkSaveWithNullName() throws IOException {
        FileManager.saveCanvas(model,null);
        fail("This must throw an exception");
    }

    @Test(expected = NullPointerException.class)
    public void checkSaveWithNullModel() throws IOException {
        FileManager.saveCanvas(null ,fileName);
        fail("This must throw an exception");
    }

    @Test
    public void checkSaveWithValidInput() throws IOException {
        FileManager.saveCanvas(model,fileName);
        File file = new File(fileName);
        String path = file.getAbsolutePath();
        assertNotEquals(path,null);
    }

    @Test
    public void checkFileLoadWithNullName() throws IOException {
        model = FileManager.loadCanvas(jFrame);
        assertEquals(model,null);
    }

    @Test
    public void checkFileLoad() throws IOException {
        FileManager.saveCanvas(model,fileName);
        IModel retrievedMode = FileManager.loadCanvas(jFrame);
        assertEquals(model.getStoredShapes(),retrievedMode.getStoredShapes());
    }

    /**
     * Try to load a file that is not the one a file saved by this application
     * @throws IOException
     */
    @Test
    public void checkFileLoadWithInvalidFileType() throws IOException {
        FileManager.saveCanvas(model,fileName);
        IModel retrievedMode = FileManager.loadCanvas(jFrame);
        assertEquals(retrievedMode,null);
    }

}


