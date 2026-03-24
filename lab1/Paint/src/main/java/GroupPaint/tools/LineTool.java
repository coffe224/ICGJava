package GroupPaint.tools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class LineTool extends BresenhamLineAlgorithm implements PaintTool {
    private final int thickness;

    public LineTool() {
        this(1);
    }

    public LineTool(int thickness) {
        this.thickness = thickness;
    }

    public boolean isReady(int pointCount) {
        return pointCount == 2;
    }

    public void draw(BufferedImage canvas, Color color, List<Point> points) {
        // добавить обработку исключений

        Point p1 = points.get(0);
        Point p2 = points.get(1);

        if (thickness == 1) {
            drawLine(canvas, p1, p2, color.getRGB());
        } else {
            Graphics2D g2d = canvas.createGraphics();
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            g2d.dispose();
        }
    }

    @Override
    public String getToolID() {
        return "LINE";
    }
}
