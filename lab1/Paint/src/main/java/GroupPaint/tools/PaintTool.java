package GroupPaint.tools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public interface PaintTool {
    boolean isReady(int pointCount);
    void draw(BufferedImage canvas, Color color, List<Point> points);
    public String getToolID();
}
