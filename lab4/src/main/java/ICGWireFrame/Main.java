package ICGWireFrame;

import ICGWireFrame.generatrix.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        ViewTransform viewTransform = new ViewTransform();

        DrawingModel drawingModel = new DrawingModel(viewTransform);
        drawingModel.addDrawable(new Grid());
        drawingModel.addDrawable(new Axis());
        drawingModel.addDrawable(new WorldBorder());
        drawingModel.addInteractable(new SplineControlPoint(new Point(10, 10)));
        drawingModel.addInteractable(new SplineControlPoint(new Point(50, 10)));
        drawingModel.addInteractable(new SplineControlPoint(new Point(10, 50)));

        EditorCanvas editorCanvas = new EditorCanvas(drawingModel);
        drawingModel.setListener(editorCanvas);

        CanvasActionHandler canvasActionHandler = new CanvasActionHandler(viewTransform, editorCanvas, drawingModel);
        editorCanvas.setListener(canvasActionHandler);

        JFrame mainFrame = new JFrame();
        mainFrame.setMinimumSize(new Dimension(800, 640));
        mainFrame.add(editorCanvas);

        SwingUtilities.invokeLater(() ->
                mainFrame.setVisible(true)
        );
    }
}