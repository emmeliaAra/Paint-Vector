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


/**
 * This class is used to test the FileManager Class
 */
public class FileManagerTest {

    private IModel model;
    private String fileName;
    private JFrame jFrame;


    /**
     *  Sets up the environment fo testing
     */
    @Before
    public void setup() {
       model = new Model();
       fileName = "testingFileManager";
       jFrame = new JFrame();
    }

    /**
     * Check save with null name.
     *
     * @throws IOException the io exception
     */
    @Test(expected = NullPointerException.class)
    public void checkSaveWithNullName() throws IOException {
        FileManager.saveCanvas(model,null);
        fail("This must throw an exception");
    }

    /**
     * Check save with null model.
     *
     * @throws IOException the io exception
     */
    @Test(expected = NullPointerException.class)
    public void checkSaveWithNullModel() throws IOException {
        FileManager.saveCanvas(null ,fileName);
        fail("This must throw an exception");
    }

    /**
     * Check save with valid input.
     *
     * @throws IOException the io exception
     */
    @Test
    public void checkSaveWithValidInput() throws IOException {
        FileManager.saveCanvas(model,fileName);
        File file = new File(fileName);
        String path = file.getAbsolutePath();
        assertNotEquals(path,null);
    }

    /**
     * Check file load with null name.
     * To work  press cancel on the Dialog window that pops up
     * @throws IOException the io exception
     */
    @Test
    public void checkFileLoadWithNullName() throws IOException {
        model = FileManager.loadCanvas(jFrame);
        assertEquals(model,null);
    }


    /**
     * Check file load.
     * To work load a file that is saved by this application.
     * @throws IOException the io exception
     */
    @Test
    public void checkFileLoad() throws IOException {
        FileManager.saveCanvas(model,fileName);
        IModel retrievedMode = FileManager.loadCanvas(jFrame);
        assertEquals(model.getStoredShapes(),retrievedMode.getStoredShapes());
    }

    /**
     * Ckecks the fileLoad method when loading a file not created by this application
     * Try to load a file that is not the one a file saved by this application
     * @throws IOException the io exception
     */
    @Test
    public void checkFileLoadWithInvalidFileType() throws IOException {
        FileManager.saveCanvas(model,fileName);
        IModel retrievedMode = FileManager.loadCanvas(jFrame);
        assertEquals(retrievedMode,null);
    }

}


