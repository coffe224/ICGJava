package GroupPaint.tools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Stack;

public class FillingTool implements PaintTool {
    @Override
    public boolean isReady(int pointCount) {
        return pointCount == 1;
    }

    @Override
    public void draw(BufferedImage canvas, Color color, List<Point> points) {
        Point startPoint = points.getFirst();
        int startX = startPoint.x;
        int startY = startPoint.y;
        int newColor = color.getRGB();

        if (startX < 0 || startX >= canvas.getWidth() ||
                startY < 0 || startY >= canvas.getHeight()) {
            return;
        }

        int startColor = canvas.getRGB(startX, startY);
        if (startColor == newColor) {
            return;
        }

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startX, startY});

        while (!stack.isEmpty()) {
            int[] point = stack.pop();
            int x = point[0];
            int y = point[1];

            if (canvas.getRGB(x, y) != startColor) {
                continue;
            }

            int left = x;
            int right = x;

            while (left >= 0 && canvas.getRGB(left, y) == startColor) {
                left--;
            }
            left++;
            while (right < canvas.getWidth() && canvas.getRGB(right, y) == startColor) {
                right++;
            }
            right--;

            for (int i = left; i <= right; i++) {
                canvas.setRGB(i, y, newColor);
            }

            if (y > 0) {
                boolean inSpan = false;
                for (int i = left; i <= right; i++) {
                    if (canvas.getRGB(i, y - 1) == startColor) {
                        if (!inSpan) {
                            stack.push(new int[]{i, y - 1});
                            inSpan = true;
                        }
                    } else {
                        inSpan = false;
                    }
                }
            }
            if (y < canvas.getHeight() - 1) {
                boolean inSpan = false;
                for (int i = left; i <= right; i++) {
                    if (canvas.getRGB(i, y + 1) == startColor) {
                        if (!inSpan) {
                            stack.push(new int[]{i, y + 1});
                            inSpan = true;
                        }
                    } else {
                        inSpan = false;
                    }
                }
            }
        }
    }

    @Override
    public String getToolID() {
        return "FILLING";
    }
}