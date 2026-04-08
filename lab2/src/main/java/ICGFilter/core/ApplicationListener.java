package ICGFilter.core;

public interface ApplicationListener {
    void applyFilter(String filterName);
    void saveImage();
    void openImage();
    void setAdaptedView(boolean isAdaptedView);
    void toggleAdaptedView();
    void setProcessedView(boolean isProcessedView);
    void toggleProcessedView();
    void handleResize(int newWidth, int newHeight);
}