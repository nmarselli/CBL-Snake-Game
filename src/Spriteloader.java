import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public final class Spriteloader {
    private Spriteloader() {}

    // From disk
    public static BufferedImage load(String path) throws IOException {
        return ImageIO.read(new File(path));
    }

    // From classpath (e.g., src/main/resources/sprites.png)
    public static BufferedImage loadResource(String resourcePath) throws IOException {
        try (InputStream in = Spriteloader.class.getResourceAsStream(resourcePath)) {
            if (in == null) throw new IOException("Resource not found: " + resourcePath);
            return ImageIO.read(in);
        }
    }
}