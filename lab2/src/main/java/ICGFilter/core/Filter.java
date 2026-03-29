package ICGFilter.core;

import java.awt.image.BufferedImage;

public interface Filter {
    BufferedImage apply(BufferedImage image);

    // подумать над архитектурой внутри для самих картинок (контроллер + модель)
    // два разных BufferedImage - для самой картинки и для того что видно
}
