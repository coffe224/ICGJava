package ICGWireFrame;

import ICGWireFrame.generatrix.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        ViewTransform viewTransform = new ViewTransform();

        DrawingModel drawingModel = new DrawingModel(viewTransform);
        drawingModel.addDrawable(new Axis());

        EditorCanvas editorCanvas = new EditorCanvas(drawingModel);
        drawingModel.setListener(editorCanvas);

        CanvasActionHandler canvasActionHandler = new CanvasActionHandler(viewTransform, editorCanvas);
        editorCanvas.setListener(canvasActionHandler);

        JFrame mainFrame = new JFrame();
        mainFrame.setMinimumSize(new Dimension(800, 640));
        mainFrame.add(editorCanvas);

        SwingUtilities.invokeLater(() ->
                mainFrame.setVisible(true)
        );
    }
}