import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.*;
import java.util.List;
import java.util.Random;

public class SpriteDemo extends JPanel {

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0;
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        // Draw current frame scaled 4x at center
        for (int i = 0; i < 15; i++) {
            BufferedImage f;
            for (int j = 0; j < 15; j++) {
                if (i % 2 == 0 && j % 2 == 0 || i % 2 == 1 && j % 2 == 1) {
                    f = frames.get(new Random().nextInt(16));
                } else {
                    f = color(0.85, 0.85, 0.85, frames.get(new Random().nextInt(24)));
                }
                System.out.println("Frame " + i);
                int scale = 2;
                int x = i * f.getWidth() * scale;
                int y = j * f.getHeight() * scale;
                g.drawImage(f, x, y, f.getWidth() * scale, f.getHeight() * scale, null);
            }
        }
    }

    private final List<BufferedImage> frames;

    // quick glace through the tiles / tick generator
    public SpriteDemo(List<BufferedImage> frames) {
        this.frames = frames;
        // new Timer(100, e -> {repaint();}).start();
        repaint();
    }

    public static void animator() {
        try {
            // 1. Load the spritesheet image
            Path p = Paths.get("assets/images/TileSet2.png");
            BufferedImage sheet = ImageIO.read(p.toFile());

            // 2. Create the Spritesheet object
            SpriteSheet ss = new SpriteSheet(sheet, 32);

            // Temporary - glance through all the tiles
            java.util.List<BufferedImage> glance = new java.util.ArrayList<>();
            for (int r = 0; r < ss.getRows(); r++) {
                for (int c = 0; c < ss.getCols(); c++) {
                    glance.add(ss.get(c, r));
                }
            }

            // 3. Display
            SwingUtilities.invokeLater(() -> {
                JFrame f = new JFrame("Sprite Demo");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setPreferredSize(new Dimension(976, 998));
                f.setContentPane(new SpriteDemo(glance));
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            });
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Colors an image with specified color.
     * 
     * @param r   Red value. Between 0 and 1
     * @param g   Green value. Between 0 and 1
     * @param b   Blue value. Between 0 and 1
     * @param src The image to color
     * @return The colored image
     */
    protected BufferedImage color(Double r, Double g, Double b, BufferedImage src) {

        // Copy image
        BufferedImage newImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(src, 0, 0, null);
        graphics.dispose();

        // Color image
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int ax = newImage.getColorModel().getAlpha(newImage.getRaster().getDataElements(i, j, null));
                int rx = newImage.getColorModel().getRed(newImage.getRaster().getDataElements(i, j, null));
                int gx = newImage.getColorModel().getGreen(newImage.getRaster().getDataElements(i, j, null));
                int bx = newImage.getColorModel().getBlue(newImage.getRaster().getDataElements(i, j, null));
                rx *= r;
                gx *= g;
                bx *= b;
                newImage.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx << 0));
            }
        }
        return newImage;
    }
}
