package GroupPaint.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BresenhamLineAlgorithm {
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

        if (isInBounds(canvas, x, y)) {
            canvas.setRGB(x, y, color);
        }

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