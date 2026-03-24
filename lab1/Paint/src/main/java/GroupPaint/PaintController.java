package GroupPaint;

import GroupPaint.tools.FigureTool;
import GroupPaint.tools.LineTool;
import GroupPaint.ui.dialogs.ColorSelector;
import GroupPaint.ui.dialogs.tooldialogs.LineParametersSelector;

import java.awt.*;

public class PaintController {
    private Frame mainFrame = null;
    private final PaintModel paintModel;

    public PaintController(PaintModel paintModel) {
        this.paintModel = paintModel;
    }

    public void setMainFrame(Frame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void chooseColor() {
        Color currentColor = paintModel.getToolState().getCurrentColor();
        ColorSelector colorSelector = new ColorSelector(mainFrame, currentColor);
        colorSelector.setVisible(true);

        if (colorSelector.isConfirmed()) {
            chooseColor(colorSelector.getSelectedColor());
        }
    }

    public void chooseColor(Color color) {
        paintModel.getToolState().setCurrentColor(color);
    }

    public void chooseFigure() {
        // модальное окно
        System.out.println("Выбрана фигура");
        FigureTool tool = new FigureTool(3, 10, 0, false);
        paintModel.setTool(tool);
    }

    public void chooseLine() {
        System.out.println("Выбрана линия");

        LineParametersSelector lineDialog = new LineParametersSelector(mainFrame);
        lineDialog.setVisible(true);

        if (!lineDialog.isConfirmed()) {
            return;
        }
        LineTool tool = new LineTool(lineDialog.getThickness());
        paintModel.setTool(tool);
    }

    public void handleResize(int newWidth, int newHeight) {
        int oldWidth = paintModel.getCanvasState().getCanvas().getWidth();
        int oldHeight = paintModel.getCanvasState().getCanvas().getHeight();

        if (oldWidth >= newWidth && oldHeight >= newHeight) {
            return;
        }

        if (oldWidth >= newWidth) {
            newWidth = oldWidth;
        }
        if (oldHeight >= newHeight) {
            newHeight = oldHeight;
        }

        paintModel.getCanvasState().resizeCanvas(newWidth, newHeight);
    }

    public void chooseFilling() {
        System.out.println("Выбрана заливка");

        // создание инструмента заливка
    }

    public void clearCanvas() {
        paintModel.getCanvasState().clearCanvas();
    }

    public void unselectTool() {
        paintModel.unselectTool();
    }

    public void addPoint(Point point) {
        paintModel.addPoint(point);
    }
}
