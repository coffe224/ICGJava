package GroupPaint.tools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class FigureTool extends BresenhamLineAlgorithm implements PaintTool {
    private final int n;
    private final int radius;
    private final int angle;

    public FigureTool(int n, int radius, int angle, boolean isStar) {
        this.n = n;
        this.radius = radius;
        this.angle = angle;
    }

    public boolean isReady(int pointCount) {
        return pointCount == 1;
    }

    public void draw(BufferedImage canvas, Color color, List<Point> points) {
        if (points.isEmpty()) return;

        Point center = points.getFirst();

        double angleRad = Math.toRadians(angle) - Math.PI/2;

        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n;

            double angle1 = angleRad + (2 * Math.PI * i / n);
            double angle2 = angleRad + (2 * Math.PI * next / n);

            int x1 = (int)(center.x + radius * Math.cos(angle1));
            int y1 = (int)(center.y + radius * Math.sin(angle1));
            int x2 = (int)(center.x + radius * Math.cos(angle2));
            int y2 = (int)(center.y + radius * Math.sin(angle2));

            drawLine(canvas, new Point(x1, y1), new Point(x2, y2), 0);
        }
    }

    @Override
    public String getToolID() {
        return "FIGURE";
    }
}

