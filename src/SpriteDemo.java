import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SpriteDemo extends JPanel {
    private final List<BufferedImage> frames;
    private int frameIndex = 0;

    public SpriteDemo(List<BufferedImage> frames) {
        this.frames = frames;
        setPreferredSize(new Dimension(400, 300));

        // Simple timer for animation (~60 FPS)
        new Timer(1000 / 60, e -> {
            frameIndex = (frameIndex + 1) % frames.size();
            repaint();
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0;
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        // Draw current frame scaled 4x at center
        BufferedImage f = frames.get(frameIndex);
        int scale = 4;
        int x = (getWidth() - f.getWidth() * scale) / 2;
        int y = (getHeight() - f.getHeight() * scale) / 2;
        g.drawImage(f, x, y, f.getWidth() * scale, f.getHeight() * scale, null);
    }

    public static void animator() {
                try {
            // 1) Load your sheet
            Path p = Paths.get("assets/images/SpriteSheet.png"); // forward slashes
            if (!Files.exists(p)) {
                System.out.println("CWD: " + Paths.get("").toAbsolutePath());
                throw new FileNotFoundException("Not found: " + p.toAbsolutePath());
            }
            BufferedImage sheet = ImageIO.read(p.toFile()); // returns null if format unsupported
            if (sheet == null)
                throw new IOException("ImageIO couldn't decode the file (corrupt/unsupported).");

            // 2) Create slicer (example: 32x32 tiles, no margin/spacing)
            SpriteSheet ss = new SpriteSheet(sheet, 32, 32/* , margin=0, spacing=0 */);
            // 3) Pick frames (e.g., first row)
            java.util.List<BufferedImage> row0 = new java.util.ArrayList<>();
            for (int c = 0; c < ss.getCols(); c++)
                row0.add(ss.get(c, 0));

            // 4) Show window
            SwingUtilities.invokeLater(() -> {
                JFrame f = new JFrame("Sprite Demo");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setContentPane(new SpriteDemo(row0));
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            });
        } catch (

        java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
