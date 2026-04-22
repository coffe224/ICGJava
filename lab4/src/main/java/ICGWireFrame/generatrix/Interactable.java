package ICGWireFrame.generatrix;

import java.awt.*;

public interface Interactable extends Drawable {
    Rectangle getHitbox(ViewTransform viewTransform);
    void move(double deltaX, double deltaY);
    void setHovered(boolean isHovered);
}
