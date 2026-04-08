package ICGFilter.core;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ImageManager {
    private final ImageProcessor imageProcessor;
    private final ImageScaler imageScaler;
    private final ImageView imageView;

    private final JPanel viewPanel;

    private boolean isProcessedView = true;
    private boolean isAdaptedView = false;

    public ImageManager(ImageProcessor imageProcessor, ImageScaler imageScaler, ImageView imageView, JPanel viewPanel) {
        this.imageProcessor = imageProcessor;
        this.imageScaler = imageScaler;
        this.imageView = imageView;
        this.viewPanel = viewPanel;
    }

    public void applyFilter(Filter filter) {
        if (!imageProcessor.isImageSet()) {
            return;
        }

        imageProcessor.applyFilter(filter);
        updateView();
    }

    public void setAdaptedView(boolean setAdapted) {
        if (isAdaptedView == setAdapted) {
            return;
        }
        System.out.println(viewPanel.getWidth() + " " + viewPanel.getHeight());
        isAdaptedView = setAdapted;
        updateView();
    }

    public void setProcessedView(boolean setProcessed) {
        if (isProcessedView == setProcessed) {
            return;
        }
        isProcessedView = setProcessed;
        updateView();
    }

    public void updateView() {
        BufferedImage newImage;
        if (isProcessedView) {
            newImage = imageProcessor.getProcessedImage();
        } else {
            newImage = imageProcessor.getOriginalImage();
        }

        if (newImage == null) {
            return;
        }

        if (isAdaptedView) {
            newImage = imageScaler.resizeImage(viewPanel.getWidth(), viewPanel.getHeight(), newImage);
        }
        imageView.setImage(newImage);
    }

    public void setOriginalImage(BufferedImage newImage) {
        imageProcessor.setOriginalImage(newImage);
        updateView();
    }

    public BufferedImage getProcessedImage() {
        return imageProcessor.getProcessedImage();
    }

    public boolean isAdaptedView() {
        return isAdaptedView;
    }

    public boolean isProcessedView() {
        return isProcessedView;
    }
}
