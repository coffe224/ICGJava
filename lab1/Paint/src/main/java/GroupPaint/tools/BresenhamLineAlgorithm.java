package GroupPaint.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BresenhamLineAlgorithm {

    // Helper method to check if a point is within canvas bounds
    private boolean isInBounds(BufferedImage canvas, int x, int y) {
        return x >= 0 && x < canvas.getWidth() && y >= 0 && y < canvas.getHeight();
    }

    protected void drawLine(BufferedImage canvas, Point p1, Point p2, int color) {
        int x0 = p1.x;
        int y0 = p1.y;
        int x1 = p2.x;
        int y1 = p2.y;

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int x = x0;
        int y = y0;

        // Only draw starting point if it's in bounds
        if (isInBounds(canvas, x, y)) {
            canvas.setRGB(x, y, color);
        }

        // Octant 1: shallow, up-right (dx >= dy, x1 >= x0, y1 >= y0)
        if (dx >= dy && x1 >= x0 && y1 >= y0) {
            int err = -dx;
            for (int i = 0; i < dx; ++i) {
                x++;
                err += 2 * dy;
                if (err > 0) {
                    err -= 2 * dx;
                    y++;
                }
                if (isInBounds(canvas, x, y)) {
                    canvas.setRGB(x, y, color);
                }
            }
        }
        // Octant 2: steep, up-right (dx < dy, x1 >= x0, y1 >= y0)
        else if (dx < dy && x1 >= x0 && y1 >= y0) {
            int err = -dy;
            for (int i = 0; i < dy; ++i) {
                y++;
                err += 2 * dx;
                if (err > 0) {
                    err -= 2 * dy;
                    x++;
                }
                if (isInBounds(canvas, x, y)) {
                    canvas.setRGB(x, y, color);
                }
            }
        }
        // Octant 3: steep, up-left (dx < dy, x1 < x0, y1 >= y0)
        else if (dx < dy && x1 < x0 && y1 >= y0) {
            int err = -dy;
            for (int i = 0; i < dy; ++i) {
                y++;
                err += 2 * dx;
                if (err > 0) {
                    err -= 2 * dy;
                    x--;
                }
                if (isInBounds(canvas, x, y)) {
                    canvas.setRGB(x, y, color);
                }
            }
        }
        // Octant 4: shallow, up-left (dx >= dy, x1 < x0, y1 >= y0)
        else if (dx >= dy && x1 < x0 && y1 >= y0) {
            int err = -dx;
            for (int i = 0; i < dx; ++i) {
                x--;
                err += 2 * dy;
                if (err > 0) {
                    err -= 2 * dx;
                    y++;
                }
                if (isInBounds(canvas, x, y)) {
                    canvas.setRGB(x, y, color);
                }
            }
        }
        // Octant 5: shallow, down-left (dx >= dy, x1 < x0, y1 < y0)
        else if (dx >= dy && x1 < x0 && y1 < y0) {
            int err = -dx;
            for (int i = 0; i < dx; ++i) {
                x--;
                err += 2 * dy;
                if (err > 0) {
                    err -= 2 * dx;
                    y--;
                }
                if (isInBounds(canvas, x, y)) {
                    canvas.setRGB(x, y, color);
                }
            }
        }
        // Octant 6: steep, down-left (dx < dy, x1 < x0, y1 < y0)
        else if (dx < dy && x1 < x0 && y1 < y0) {
            int err = -dy;
            for (int i = 0; i < dy; ++i) {
                y--;
                err += 2 * dx;
                if (err > 0) {
                    err -= 2 * dy;
                    x--;
                }
                if (isInBounds(canvas, x, y)) {
                    canvas.setRGB(x, y, color);
                }
            }
        }
        // Octant 7: steep, down-right (dx < dy, x1 >= x0, y1 < y0)
        else if (dx < dy && x1 >= x0 && y1 < y0) {
            int err = -dy;
            for (int i = 0; i < dy; ++i) {
                y--;
                err += 2 * dx;
                if (err > 0) {
                    err -= 2 * dy;
                    x++;
                }
                if (isInBounds(canvas, x, y)) {
                    canvas.setRGB(x, y, color);
                }
            }
        }
        // Octant 8: shallow, down-right (dx >= dy, x1 >= x0, y1 < y0)
        else if (dx >= dy && x1 >= x0 && y1 < y0) {
            int err = -dx;
            for (int i = 0; i < dx; ++i) {
                x++;
                err += 2 * dy;
                if (err > 0) {
                    err -= 2 * dx;
                    y--;
                }
                if (isInBounds(canvas, x, y)) {
                    canvas.setRGB(x, y, color);
                }
            }
        }
    }
}