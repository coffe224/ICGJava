package ICGWireFrame.generatrix;

import java.awt.*;
import java.awt.geom.Point2D;

public class Grid implements Drawable {
    private static final int LINE_THICKNESS = 1;
    private static final int HORIZONTAL_LINES = 12;
    private static final int VERTICAL_LINES = 20;

    @Override
    public void draw(Graphics2D g2d, ViewTransform transform) {
        Dimension worldSize = transform.getWorldSize();

        // Calculate grid spacing
        double horizontalSpacing = (double) worldSize.height / HORIZONTAL_LINES;
        double verticalSpacing = (double) worldSize.width / VERTICAL_LINES;

        g2d.setStroke(new BasicStroke(LINE_THICKNESS));
        g2d.setColor(Color.DARK_GRAY);

        // Draw horizontal grid lines (from left to right border)
        for (int i = 0; i <= HORIZONTAL_LINES; i++) {
            double y = (double) worldSize.height / 2 - i * horizontalSpacing;

            // Only draw if within world borders
            if (y >= -worldSize.height / 2 && y <= worldSize.height / 2) {
                Point2D leftPoint = transform.worldToScreen(-worldSize.width / 2, y);
                Point2D rightPoint = transform.worldToScreen(worldSize.width / 2, y);

                g2d.drawLine((int) leftPoint.getX(), (int) leftPoint.getY(),
                        (int) rightPoint.getX(), (int) rightPoint.getY());
            }
        }

        // Draw vertical grid lines (from top to bottom border)
        for (int i = 0; i <= VERTICAL_LINES; i++) {
            double x = -worldSize.width / 2 + i * verticalSpacing;

            // Only draw if within world borders
            if (x >= -worldSize.width / 2 && x <= worldSize.width / 2) {
                Point2D topPoint = transform.worldToScreen(x, worldSize.height / 2);
                Point2D bottomPoint = transform.worldToScreen(x, -worldSize.height / 2);

                g2d.drawLine((int) topPoint.getX(), (int) topPoint.getY(),
                        (int) bottomPoint.getX(), (int) bottomPoint.getY());
            }
        }
    }
}