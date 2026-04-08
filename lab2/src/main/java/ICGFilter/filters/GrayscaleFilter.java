package ICGFilter.filters;

import ICGFilter.core.Filter;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class GrayscaleFilter implements Filter {
    @Override
    public BufferedImage apply(BufferedImage image) {
        if (image == null) {
            return null;
        }

        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        int[] inPixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        int[] outPixels = ((DataBufferInt) newImage.getRaster().getDataBuffer()).getData();

        for (int i = 0; i < inPixels.length; i++) {
            int pixel = inPixels[i];

            int red = (pixel & 0x00FF0000) >> 16;
            int green = (pixel & 0x0000FF00) >> 8;
            int blue = pixel & 0x000000FF;

            int grey = Math.round((float)(red * 0.299) + (float)(green * 0.587) + (float)(blue * 0.114));

            int new_pixel = 0xFF000000 | (grey << 16) | (grey << 8) | grey;
            outPixels[i] = new_pixel;
        }
        return newImage;
    }
}
