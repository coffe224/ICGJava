package GroupPaint.tools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class FigureTool extends BresenhamLineAlgorithm implements PaintTool {
    private final int n;
    private final int radius;
    private final int angle;
    private final boolean isStar;

    public FigureTool(int n, int radius, int angle, boolean isStar) {
        this.n = n;
        this.radius = radius;
        this.angle = angle;
        this.isStar = isStar;
    }

    public boolean isReady(int pointCount) {
        return pointCount == 1;
    }

    public void draw(BufferedImage canvas, Color color, List<Point> points) {
        if (points.isEmpty()) return;

        Point center = points.get(0);
        int colorRGB = color.getRGB();

        // Offset angle by -90° so vertex is at top when angle=0
        double angleRad = Math.toRadians(angle) - Math.PI/2;

        if (isStar) {
            // Calculate inner radius for star outline
            // For a {n/2} star, inner vertices are at this radius
            double innerRadius = radius * Math.cos(2 * Math.PI / n);

            // Draw star outline by alternating between outer and inner vertices
            // This creates only the perimeter, no internal crossing lines
            for (int i = 0; i < n; i++) {
                int next = (i + 1) % n;

                double angle1 = angleRad + (2 * Math.PI * i / n);
                double angle2 = angleRad + (2 * Math.PI * next / n);

                // Outer vertex i
                int x1 = (int)(center.x + radius * Math.cos(angle1));
                int y1 = (int)(center.y + radius * Math.sin(angle1));

                // Inner vertex i
                int x2 = (int)(center.x + innerRadius * Math.cos(angle1));
                int y2 = (int)(center.y + innerRadius * Math.sin(angle1));

                // Inner vertex next
                int x3 = (int)(center.x + innerRadius * Math.cos(angle2));
                int y3 = (int)(center.y + innerRadius * Math.sin(angle2));

                // Outer vertex next
                int x4 = (int)(center.x + radius * Math.cos(angle2));
                int y4 = (int)(center.y + radius * Math.sin(angle2));

                // Draw the outline: outer[i] -> inner[i] -> inner[next] -> outer[next]
                drawLine(canvas, new Point(x1, y1), new Point(x2, y2), colorRGB);
                drawLine(canvas, new Point(x2, y2), new Point(x3, y3), colorRGB);
                drawLine(canvas, new Point(x3, y3), new Point(x4, y4), colorRGB);
            }
        } else {
            // Draw regular polygon by connecting consecutive vertices
            for (int i = 0; i < n; i++) {
                int next = (i + 1) % n;

                double angle1 = angleRad + (2 * Math.PI * i / n);
                double angle2 = angleRad + (2 * Math.PI * next / n);

                int x1 = (int)(center.x + radius * Math.cos(angle1));
                int y1 = (int)(center.y + radius * Math.sin(angle1));
                int x2 = (int)(center.x + radius * Math.cos(angle2));
                int y2 = (int)(center.y + radius * Math.sin(angle2));

                drawLine(canvas, new Point(x1, y1), new Point(x2, y2), colorRGB);
            }
        }
    }

    @Override
    public String getToolID() {
        return "FIGURE";
    }
}

