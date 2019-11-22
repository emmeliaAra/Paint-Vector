package Delegate;

import Model.IModel;
import Shapes.CustomRectangle;
import Shapes.CustomShape;
import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.util.LinkedList;

public class Delegate extends JFrame implements IDelegate{

    private int FRAME_WIDTH = 600;
    private int FRAME_HEIGHT = 600;
    private String currentSelectedShape = null;
    private static final String LINE = "Line";
    private static final String RECTANGLE = "Rectangle";
    private static final String ELLIPSE = "Ellipse";
    private IModel model;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JMenu file, edit;
    private JMenuItem load, save, saveAS, undo, redo;
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
        createMenuBar();
        addActionsToButtons();

        JToolBar toolBar = new JToolBar();
        toolBar.add(menuBar);
        toolBar = addButtons(toolBar);

        mainPanel.add(toolBar, BorderLayout.NORTH);
        mainPanel.add(drawingPanel, BorderLayout.CENTER);

        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");

        menuBar.add(file);
        menuBar.add(edit);

        save = new JMenuItem("Save");
        saveAS = new JMenuItem("SaveAs");
        load = new JMenuItem("Load");
        file.add(save);
        file.add(saveAS);
        file.add(load);

        undo = new JMenuItem("Undo");
        redo = new JMenuItem("redo");
        edit.add(undo);
        edit.add(redo);
    }

    public void createButtons() {
        Icon lineIcon = new ImageIcon("Icons\\LineIcon.png");
        Icon rectangleIcon = new ImageIcon("Icons\\rectangleIcon.png");
        Icon ellipseIcon = new ImageIcon("Icons\\ellipseIcon.png");
        Icon paintBrush = new ImageIcon("Icons\\paintBrush.png");
        Icon paintBucket = new ImageIcon("Icons\\paintBucket.png");
        Icon undoIcon = new ImageIcon("Icons\\undo.png");
        Icon redoIcon = new ImageIcon("Icons\\redo.png");

        toolButtons = new LinkedList<>();

        JButton lineButton = new JButton(lineIcon);
        JButton rectangleButton = new JButton(rectangleIcon);
        JButton ellipseButton = new JButton(ellipseIcon);
        JButton brushButton = new JButton(paintBrush);
        JButton bucketButton = new JButton(paintBucket);
        JButton undoButton = new JButton(undoIcon);
        JButton redoButton = new JButton(redoIcon);

        toolButtons.add(lineButton);
        toolButtons.add(rectangleButton);
        toolButtons.add(ellipseButton);
        toolButtons.add(brushButton);
        toolButtons.add(bucketButton);
        toolButtons.add(undoButton);
        toolButtons.add(redoButton);
    }

    public JToolBar addButtons(JToolBar toolBar){
        for (JButton button: toolButtons) {
            toolBar.add(button);
        }
        return toolBar;
    }

    public void addActionsToButtons(){
        //Button(0) -> lineButton
        //Button(1) -> RectangleButton
        //Button(2) -> EllipseButton
        //Button(3) -> Pencil
        //Button(4) -> bucket
        //Button(5) -> Undo
        //Button(6) -> Redo

        toolButtons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setCurrentShapeSelected(LINE);
            }
        });

        toolButtons.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setCurrentShapeSelected(RECTANGLE);
            }
        });

        toolButtons.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setCurrentShapeSelected(ELLIPSE);
            }
        });

        toolButtons.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color tempColor = JColorChooser.showDialog(Delegate.this,"Color Palette",color);
            }
        });

        toolButtons.get(5).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.undo();
            }
        });
    }

    public void redrawAfterUndo(LinkedList<CustomShape> shapes){
        drawingPanel.setShapes(shapes);
        drawingPanel.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if(event.getSource().equals(model)){
            if(event.getPropertyName().equalsIgnoreCase("ShapeSelectedChange")){
                currentSelectedShape = (String)event.getNewValue();
            }else if(event.getPropertyName().equalsIgnoreCase("UndoMode")){
                redrawAfterUndo((LinkedList<CustomShape>) event.getNewValue());
            }
        }
    }
}
