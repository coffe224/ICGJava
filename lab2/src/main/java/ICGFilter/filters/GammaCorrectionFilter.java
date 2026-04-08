package ICGFilter.filters;

import ICGFilter.core.FilterWithParameters;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class GammaCorrectionFilter implements FilterWithParameters {
    private static final float MIN_GAMMA = 0.1f;
    private static final float MAX_GAMMA = 10f;
    private static final float GAMMA_STEP = 0.1f;

    private float gamma = 0.2f;


    @Override
    public void changeParameters() {
        SpinnerNumberModel model = new SpinnerNumberModel(gamma, MIN_GAMMA, MAX_GAMMA, GAMMA_STEP);
        JSpinner spinner = new JSpinner(model);

        // Optional: Set editor to show fewer decimal places if desired,
        // but default is usually fine for floats.

        int result = JOptionPane.showConfirmDialog(
                null,
                spinner,
                "Gamma Correction Parameters",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            this.gamma = ((Number) spinner.getValue()).floatValue();
        }
    }

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

            int new_red = (int) Math.round(Math.pow((double) (red) / 255, gamma) * 255);
            int new_green = (int) Math.round(Math.pow((double) (green) / 255, gamma) * 255);
            int new_blue = (int) Math.round(Math.pow((double) (blue) / 255, gamma) * 255);

            new_red = Math.min(255, Math.max(0, new_red));
            new_green = Math.min(255, Math.max(0, new_green));
            new_blue = Math.min(255, Math.max(0, new_blue));

            int new_pixel = 0xFF000000 | (new_red << 16) | (new_green << 8) | new_blue;
            outPixels[i] = new_pixel;
        }
        return newImage;
    }
}
