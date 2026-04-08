package ICGFilter.core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageFileManager {
    public BufferedImage openImage() {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image Files (PNG, JPEG, BMP, GIF)",
                "png", "jpg", "jpeg", "bmp", "gif"
        );

        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Open Image");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            try {
                return formatImage(ImageIO.read(fileToOpen));
            } catch (IOException e) {
                System.err.println("Error reading image file: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    private BufferedImage formatImage(BufferedImage source) {
        BufferedImage target = new BufferedImage(
                source.getWidth(),
                source.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = target.createGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return target;
    }

    public boolean saveImageAsPng(BufferedImage image) {
        if (image == null) {
            System.err.println("Cannot save null image.");
            return false;
        }

        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PNG Images", "png"
        );

        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Save Image As PNG");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".png")) {
                fileToSave = new File(filePath + ".png");
            }

            try {
                boolean success = ImageIO.write(image, "png", fileToSave);
                return success;
            } catch (IOException e) {
                System.err.println("Error saving image file: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}