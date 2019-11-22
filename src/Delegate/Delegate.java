package Delegate;

import Model.IModel;
import Model.Model;
import Shapes.CustomShape;
import FileManager.FileManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.LinkedList;

public class Delegate extends JFrame implements IDelegate{

    private int FRAME_WIDTH = 600;
    private int FRAME_HEIGHT = 600;
    private String currentSelectedShape = null;
    private static final String LINE = "Line";
    private static final String RECTANGLE = "Rectangle";
    private static final String SQUARE = "Square";
    private static final String ELLIPSE = "Ellipse";
    private static final String TRIANGLE = "Triangle";
    private IModel model;
    private JPanel mainPanel;
    private LinkedList<JButton> toolButtons;
    private LinkedList<CustomShape> shapes ;
    private Color color;
    DrawingPanel drawingPanel;


    public Delegate(IModel model) {
        this.model = model;
        model.addObservers(this);
        color = Color.BLACK;
        currentSelectedShape = LINE;
        shapes = new LinkedList<>();
        setFrame();

    }
    private void setFrame() {

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        drawingPanel = new DrawingPanel(model);
        createButtons();
        addActionsToButtons();

        JToolBar toolBar = new JToolBar();
        toolBar = addButtons(toolBar);

        mainPanel.add(toolBar, BorderLayout.NORTH);
        mainPanel.add(drawingPanel, BorderLayout.CENTER);

        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainPanel);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void createButtons() {
        Icon lineIcon = new ImageIcon("Icons\\LineIcon.png");
        Icon rectangleIcon = new ImageIcon("Icons\\rectangleIcon.png");
        Icon squareIcon = new ImageIcon("Icons\\squareIcon.png");
        Icon triangleIcon = new ImageIcon("Icons\\triangleIcon.png");
        Icon ellipseIcon = new ImageIcon("Icons\\ellipseIcon.png");
        Icon paintPalette = new ImageIcon("Icons\\paletteIcon.png");
        Icon paintBucket = new ImageIcon("Icons\\paintBucket.png");
        Icon selectAndMoveIcon = new ImageIcon("Icons\\selectAndMoveIcon.png");
        Icon undoIcon = new ImageIcon("Icons\\undo.png");
        Icon redoIcon = new ImageIcon("Icons\\redo.png");
        Icon saveIcon = new ImageIcon("Icons\\saveIcon.png");
        Icon loadIcon = new ImageIcon("Icons\\loadIcon.png");

        toolButtons = new LinkedList<>();

        JButton lineButton = new JButton(lineIcon);
        JButton rectangleButton = new JButton(rectangleIcon);
        JButton squareButton = new JButton(squareIcon);
        JButton triangleButton = new JButton(triangleIcon);
        JButton ellipseButton = new JButton(ellipseIcon);
        JButton paletteButton = new JButton(paintPalette);
        JButton bucketButton = new JButton(paintBucket);
        JButton selectAndMoveButton = new JButton(selectAndMoveIcon);
        JButton undoButton = new JButton(undoIcon);
        JButton redoButton = new JButton(redoIcon);
        JButton saveButton = new JButton(saveIcon);
        JButton loadButton = new JButton(loadIcon);

        lineButton.setToolTipText("Line");
        rectangleButton.setToolTipText("Rectangle");
        squareButton.setToolTipText("Square");
        triangleButton.setToolTipText("Triangle");
        ellipseButton.setToolTipText("Ellipse");
        bucketButton.setToolTipText("Fill");
        selectAndMoveButton.setToolTipText("Select and move Shapes");
        undoButton.setToolTipText("Undo");
        redoButton.setToolTipText("Redo");
        saveButton.setToolTipText("Save Canvas");
        saveButton.setToolTipText("Load Canvas");

        toolButtons.add(lineButton);
        toolButtons.add(rectangleButton);
        toolButtons.add(squareButton);
        toolButtons.add(triangleButton);
        toolButtons.add(ellipseButton);
        toolButtons.add(paletteButton);
        toolButtons.add(bucketButton);
        toolButtons.add(selectAndMoveButton);
        toolButtons.add(undoButton);
        toolButtons.add(redoButton);
        toolButtons.add(saveButton);
        toolButtons.add(loadButton);
    }

    public JToolBar addButtons(JToolBar toolBar){
        for (JButton button: toolButtons) {
            toolBar.add(button);
        }
        return toolBar;
    }

    public void addActionsToButtons(){
        /*Button(0) -> lineButton                   Button(5) -> Palette
         *Button(1) -> RectangleButton              Button(6) -> bucket
         *Button(2) -> SquareButton                 Button(7) -> SelectAndMove
         *Button(3) -> TriangleButton               Button(8) -> Undo
         *Button(4) -> EllipseButton                Button(9) -> Redo*/

        toolButtons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.setCurrentShapeSelected(LINE);
            }
        });

        toolButtons.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.setCurrentShapeSelected(RECTANGLE);
            }
        });
        toolButtons.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.setCurrentShapeSelected(SQUARE);
            }
        });

        toolButtons.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.setCurrentShapeSelected(TRIANGLE);
            }
        });

        toolButtons.get(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.setCurrentShapeSelected(ELLIPSE);
            }
        });

        toolButtons.get(5).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                Color tempColor = JColorChooser.showDialog(Delegate.this,"Color Palette",color);
                model.setColor(tempColor);
            }
        });

        toolButtons.get(6).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.enableFilling();
            }
        });

        toolButtons.get(7).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(true);
            }
        });

        toolButtons.get(8).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.undo();
            }
        });
        toolButtons.get(9).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.redo();
            }
        });

        toolButtons.get(10).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.saveCanvas();
            }
        });

        toolButtons.get(11).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setSelectMode(false);
                model.loadCanvas();
            }
        });
    }

    public void redrawAfterUndoRedo(LinkedList<CustomShape> shapes){
        drawingPanel.setShapes(shapes);
        drawingPanel.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if(event.getSource().equals(model)){
            if(event.getPropertyName().equalsIgnoreCase("ShapeSelectedChange")){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        currentSelectedShape = (String)event.getNewValue();
                    }
                });

            } else if(event.getPropertyName().equalsIgnoreCase("UndoMode")){
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
                            FileManager.saveCanvas((IModel)event.getNewValue());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }else if(event.getPropertyName().equalsIgnoreCase("LoadCanvas")){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        try {
                            model = FileManager.loadCanvas((IModel)event.getNewValue());
                            drawingPanel.setModel(model);
                            drawingPanel.setShapes(model.getStoredShapes());
                            drawingPanel.repaint();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
}
