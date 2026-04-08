package ICGFilter.core;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageScaler {
    public BufferedImage resizeImage(int panelWidth, int panelHeight, BufferedImage image) {
        if (image == null) {
            return null;
        }

        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();

        double widthRatio = (double) panelWidth / originalWidth;
        double heightRatio = (double) panelHeight / originalHeight;

        double scaleRatio = Math.min(widthRatio, heightRatio);

        int newWidth = (int) Math.round(originalWidth * scaleRatio);
        int newHeight = (int) Math.round(originalHeight * scaleRatio);

        newWidth = Math.max(1, newWidth);
        newHeight = Math.max(1, newHeight);

        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2d.drawImage(image, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        return resizedImage;
    }
}
