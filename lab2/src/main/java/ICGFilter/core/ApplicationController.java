package ICGFilter.core;

import ICGFilter.ui.MainFrame;

import java.awt.image.BufferedImage;
import java.util.Map;

public class ApplicationController implements ApplicationListener {
    private final Map<String, Filter> filters;
    private final ImageFileManager imageFileManager = new ImageFileManager();
    private final ImageManager imageManager;

    private final MainFrame mainFrame;

    public ApplicationController(Map<String, Filter> filters, ImageManager imageManager, MainFrame mainFrame) {
        this.filters = filters;
        this.mainFrame = mainFrame;
        this.imageManager = imageManager;
        mainFrame.setListener(this);
    }

    public void applyFilter(String filterName) {
        Filter filter = filters.get(filterName);
        if (filter == null) {
            return;
        }
        imageManager.applyFilter(filter);
    }

    public void saveImage() {
        BufferedImage processedImage = imageManager.getProcessedImage();
        imageFileManager.saveImageAsPng(processedImage);
    }

    public void openImage() {
        BufferedImage image = imageFileManager.openImage();
        if (image == null) {
            System.out.println("Image is null. Returning");
            return;
        }
        imageManager.setOriginalImage(image);
        System.out.println("Image is set!");
    }

    public void setAdaptedView(boolean isAdaptedView) {
        imageManager.setAdaptedView(isAdaptedView);
    }

    public void toggleAdaptedView() {
        setAdaptedView(!imageManager.isAdaptedView());
    }

    public void setProcessedView(boolean isProcessedView) {
        imageManager.setProcessedView(isProcessedView);
    }

    public void toggleProcessedView() {
        setProcessedView(!imageManager.isProcessedView());
    }

    public void handleResize(int width, int height) {
        imageManager.updateView();
    }
}
