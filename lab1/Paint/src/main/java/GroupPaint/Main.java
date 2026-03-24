package GroupPaint;

import GroupPaint.ui.components.CanvasPanel;
import GroupPaint.ui.components.MenuBar;
import GroupPaint.ui.components.PaintFrame;
import GroupPaint.ui.components.ToolBar;

import javax.swing.*;

// сделать интерфейсы ToolParameters и ToolFabric (20 минут)
// сделать сами инструменты (пока без Брезенхема)
// сделать модальные окна для выбора инструментов и для выбора цвета
// подключить всё к MenuBar и проверить что работает
// сделать поддержку ресайза и скроллов
// действия при отмене



public class Main {
    private static final int DEFAULT_CANVAS_WIDTH = 720;
    private static final int DEFAULT_CANVAS_HEIGHT = 480;

    public static void main(String[] args) {


        // создание модели
        ToolState toolState = new ToolState();
        CanvasState canvasState = new CanvasState(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
        PaintModel paintModel = new PaintModel(toolState, canvasState);

        // создание контроллера
        PaintController controller = new PaintController(paintModel);

        // создание view

        CanvasPanel panel = new CanvasPanel(controller, canvasState, DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
        MenuBar menuBar = new MenuBar(controller, toolState);
        ToolBar toolBar = new ToolBar(controller, toolState);

        PaintFrame mainFrame = new PaintFrame(panel, menuBar, toolBar);

        SwingUtilities.invokeLater(() ->
                mainFrame.setVisible(true)
        );
        controller.setMainFrame(mainFrame);
    }
}

