package Delegate;

import Model.IModel;
import Shapes.CustomShape;
import FileManager.FileManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.LinkedList;

/**
 * The type Delegate.
 * Represents the main frame of the paint application
 */
public class Delegate extends JFrame implements IDelegate{

    private static final String LINE = "Line";
    private static final String RECTANGLE = "Rectangle";
    private static final String SQUARE = "Square";
    private static final String ELLIPSE = "Ellipse";
    private static final String TRIANGLE = "Triangle";
    private IModel model;
    private LinkedList<JButton> toolButtons;

    /**
     * The Drawing panel.
     */
    private DrawingPanel drawingPanel;


    /**
     * Instantiates a new Delegate.
     * @param model the model
     */
    public Delegate(IModel model) {
        this.model = model;
        model.addObservers(this);           // Makes this class an observer to the model passed as a parameter
        setFrame();                                 // Sets the main frame and the col
    }

    /**
     * This method is used to set the layout of the Frame and add the components to it.
     */
    private void setFrame() {
        /* 1. Creates a mainPanel that holds the Toolbar and the drawingCanvas1
         * 2. The toolBar holds all the buttons.
         * 3. The drawingCanvas allows the user to be interactive and draw the shapes
         */

        JPanel mainPanel = new JPanel();
        JToolBar toolBar = new JToolBar();
        drawingPanel = new DrawingPanel(model);

        mainPanel.setLayout(new BorderLayout());
        createButtons();
        addActionsToButtons();

        toolBar = addButtons(toolBar);
        mainPanel.add(toolBar, BorderLayout.NORTH);
        mainPanel.add(drawingPanel, BorderLayout.CENTER);

        int FRAME_HEIGHT = 600;
        int FRAME_WIDTH = 600;
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainPanel);
        this.setVisible(true);
        this.setResizable(false);
    }

    /**
     * This methos is used to create style the buttons.
     */
    public void createButtons() {
        /* Creates the Icons to be placed on the buttons
         * Creates The buttons and add the Icons to them
         * Add a tooltip to the buttons (description when hovering over)
         * Add the buttons to a list.
         */
        Icon loadIcon = new ImageIcon("Icons\\loadIcon.png");
        Icon saveIcon = new ImageIcon("Icons\\saveIcon.png");
        Icon undoIcon = new ImageIcon("Icons\\undo.png");
        Icon redoIcon = new ImageIcon("Icons\\redo.png");
        Icon selectAndMoveIcon = new ImageIcon("Icons\\selectAndMoveIcon.png");
        Icon clearIcon = new ImageIcon("Icons\\clearIcon.png");
        Icon lineIcon = new ImageIcon("Icons\\LineIcon.png");
        Icon rectangleIcon = new ImageIcon("Icons\\rectangleIcon.png");
        Icon squareIcon = new ImageIcon("Icons\\squareIcon.png");
        Icon triangleIcon = new ImageIcon("Icons\\triangleIcon.png");
        Icon ellipseIcon = new ImageIcon("Icons\\ellipseIcon.png");
        Icon paintPalette = new ImageIcon("Icons\\paletteIcon.png");
        Icon paintBucket = new ImageIcon("Icons\\paintBucket.png");

        toolButtons = new LinkedList<>();

        JButton loadButton = new JButton(loadIcon);
        JButton saveButton = new JButton(saveIcon);
        JButton undoButton = new JButton(undoIcon);
        JButton redoButton = new JButton(redoIcon);
        JButton selectAndMoveButton = new JButton(selectAndMoveIcon);
        JButton clearButton = new JButton(clearIcon);
        JButton lineButton = new JButton(lineIcon);
        JButton rectangleButton = new JButton(rectangleIcon);
        JButton squareButton = new JButton(squareIcon);
        JButton triangleButton = new JButton(triangleIcon);
        JButton ellipseButton = new JButton(ellipseIcon);
        JButton paletteButton = new JButton(paintPalette);
        JButton bucketButton = new JButton(paintBucket);

        loadButton.setToolTipText("Load Canvas");
        saveButton.setToolTipText("Save Canvas");
        undoButton.setToolTipText("Undo");
        redoButton.setToolTipText("Redo");
        selectAndMoveButton.setToolTipText("Select and move Shapes");
        clearButton.setToolTipText("Clear Canvas");
        lineButton.setToolTipText("Line");
        rectangleButton.setToolTipText("Rectangle");
        squareButton.setToolTipText("Square");
        triangleButton.setToolTipText("Triangle");
        ellipseButton.setToolTipText("Ellipse");
        paletteButton.setToolTipText("Color Palette");
        bucketButton.setToolTipText("Fill");

        toolButtons.add(loadButton);
        toolButtons.add(saveButton);
        toolButtons.add(undoButton);
        toolButtons.add(redoButton);
        toolButtons.add(selectAndMoveButton);
        toolButtons.add(clearButton);
        toolButtons.add(lineButton);
        toolButtons.add(rectangleButton);
        toolButtons.add(squareButton);
        toolButtons.add(triangleButton);
        toolButtons.add(ellipseButton);
        toolButtons.add(paletteButton);
        toolButtons.add(bucketButton);
    }

    /**
     * Add all the JButtons to the toolbar
     * @param toolBar the tool bar
     * @return the toolbar with the buttons
     */
    public JToolBar addButtons(JToolBar toolBar){
        for (JButton button: toolButtons) {
            toolBar.add(button);
        }
        return toolBar;
    }

    /**
     * Add actions to buttons. Calls the appropriate method when each button is clicked so that
     * the appropriate action is taken
     */
    public void addActionsToButtons(){
        /*Button(0) -> Load                         Button(7) -> Rectangle
         *Button(1) -> Save                         Button(8) -> Square
         *Button(2) -> Undo                         Button(9) -> Triangle
         *Button(3) -> Redo                         Button(10) -> Ellipse
         *Button(4) -> Select and Move              Button(11) -> Palette
         *Button(5) -> Clear                        Button(12) -> Bucket
         *Button(6) -> Line*/

        //Load
        toolButtons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.loadCanvas();
            }
        });

        //Save
        toolButtons.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.saveCanvas();
            }
        });

        //Undo
        toolButtons.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.undo();
            }
        });

        //Redo
        toolButtons.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.redo();
            }
        });

        //SelectMode = True
        toolButtons.get(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(true);
            }
        });

        //Clear
        toolButtons.get(5).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.clearPage();
            }
        });

        //Line
        toolButtons.get(6).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.setCurrentShapeSelected(LINE);
            }
        });

        //Rectangle
        toolButtons.get(7).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.setCurrentShapeSelected(RECTANGLE);
            }
        });

        //Square
        toolButtons.get(8).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.setCurrentShapeSelected(SQUARE);
            }
        });

        //Triangle
        toolButtons.get(9).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.setCurrentShapeSelected(TRIANGLE);
            }
        });

        //Ellipse
        toolButtons.get(10).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.setCurrentShapeSelected(ELLIPSE);
            }
        });

        //ColorPalette
        toolButtons.get(11).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                Color tempColor = JColorChooser.showDialog(Delegate.this,"Color Palette",Color.BLACK);
                model.setColor(tempColor);
            }
        });

        //Bucket -> change filling
        toolButtons.get(12).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.enableFilling();
            }
        });
    }

    /**
     * Create save pop up window so that the user can enter the name the he/she wants to save with.
     * @return the string
     */
    public String createSavePopUpWindow(){
        String fileName = (String)JOptionPane.showInputDialog(
                this,"Give a name for the file","File name dialog",JOptionPane.PLAIN_MESSAGE,null,null,null);
        return fileName;
    }

    /**
     * This method is called so that the shapes will be redrawed after after undo redo
     * @param shapes the shapes
     */
    public void redrawAfterUndoRedo(LinkedList<CustomShape> shapes){
        //Sets the shape list and then call the repaint method to redraw the canvas.
        drawingPanel.setShapes(shapes);
        drawingPanel.repaint();
    }

    /**
     * This method is called when there is a change fired by the Observable Object (Model)
     * @param event the event that caused the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        /* Main Case -> checks if the event was fired by the observable object of this class
         * If yes then check what is the property of the change so that we take the appropriate actions
         *      Case 1-> Undo
         *      Case 2-> Redo
         *      Case 3-> Save Canvas
         *      Case 4-> Load Canvas
         */
        if(event.getSource().equals(model)){
            if(event.getPropertyName().equalsIgnoreCase("UndoMode")){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        redrawAfterUndoRedo((LinkedList<CustomShape>) event.getNewValue());
                    }
                });
            } else if(event.getPropertyName().equalsIgnoreCase("RedoMode")){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        redrawAfterUndoRedo((LinkedList<CustomShape>) event.getNewValue());
                    }
                });
            }else if(event.getPropertyName().equalsIgnoreCase("SaveCanvas")){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        try {
                            String fileName = createSavePopUpWindow();
                            if(fileName != null && !fileName.isEmpty())
                                FileManager.saveCanvas((IModel)event.getNewValue(),createSavePopUpWindow());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }else if(event.getPropertyName().equalsIgnoreCase("LoadCanvas")){
                JFrame jFrame = this;
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        try{
                            //Set the model of this object and the drawingPanel to be the Serializable object loaded from the file if there is one loaded.
                            IModel tempModel = FileManager.loadCanvas(jFrame);
                            if(tempModel != null) {
                                model = tempModel;
                                drawingPanel.setModel(model);
                                model.updateVariablesAfterLoading();
                                drawingPanel.setShapes(model.getStoredShapes());
                                drawingPanel.repaint();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
}
