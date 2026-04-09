package ICGWireFrame.generatrix;

import java.awt.event.MouseEvent;

public interface CanvasActionListener {
    void handleAction(MouseEvent event);
    void handleResize(int newWidth, int newHeight);
}
