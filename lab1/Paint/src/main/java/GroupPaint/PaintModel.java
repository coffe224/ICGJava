package GroupPaint;


import GroupPaint.tools.PaintTool;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class PaintModel {
    private final CanvasState canvasState;
    private final ToolState toolState;
    private final List<Point> points;

    public PaintModel(ToolState toolState, CanvasState canvasState) {
        this.toolState = toolState;
        this.canvasState = canvasState;

        points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        if (!toolState.isToolSelected()) {
            return;
        }

        points.add(point);
        PaintTool tool = toolState.getCurrentTool();
        if (!tool.isReady(points.size())) {
            return;
        }

        BufferedImage canvas = canvasState.getCanvas();
        tool.draw(canvas, toolState.getCurrentColor(), points);
        points.clear();
        canvasState.fireCanvasChangeEvent();
    }

    public void setTool(PaintTool tool) {
        points.clear();
        toolState.setCurrentTool(tool);
    }

    public void unselectTool() {
        points.clear();
        toolState.unselectTool();
    }

    public CanvasState getCanvasState() {
        return canvasState;
    }

    public ToolState getToolState() {
        return toolState;
    }
}
