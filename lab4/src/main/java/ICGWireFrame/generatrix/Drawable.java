package ICGWireFrame.generatrix;

import java.awt.*;

public interface Drawable {
    void draw(Graphics2D g2d, ViewTransform transform);
}

// нужны координаты в мире объектов x и y у каждого.