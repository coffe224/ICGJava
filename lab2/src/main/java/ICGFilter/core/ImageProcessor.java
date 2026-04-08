package ICGFilter.core;

import java.awt.image.BufferedImage;

public class ImageProcessor {
    private BufferedImage originalImage = null;
    private BufferedImage processedImage = null;

    private boolean isImageProcessed = false;
    private boolean isImageSet = false;

    public void setOriginalImage(BufferedImage newImage) {
        originalImage = newImage;
        processedImage = newImage;
        isImageSet = true;
        isImageProcessed = false;
    }

    public boolean isImageSet() {
        return isImageSet;
    }

    public void applyFilter(Filter filter) {
        processedImage = filter.apply(originalImage);
        isImageProcessed = true;
    }

    public BufferedImage getProcessedImage() {

        return isImageProcessed ? processedImage : originalImage;
    }

    public BufferedImage getOriginalImage() {
        return originalImage;
    }
}
