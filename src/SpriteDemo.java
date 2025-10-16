import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
//import java.io.FileNotFoundException;
//import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class SpriteDemo extends JPanel {
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

    private final List<BufferedImage> frames;
    private BufferedImage frame;
    private int frameIndex = 0;

    public SpriteDemo(List<BufferedImage> frames) {
        this.frames = frames;
        // Simple timer for animation (~60 FPS)
        new Timer(100, e -> {
            frameIndex = (frameIndex + 1) % frames.size();
            repaint();
        }).start();
    }

    public void SpriteDem(BufferedImage frame) {
        this.frame = frame;
    }

    public static void animator() {
        try {
            // 1. Load the spritesheet image
            Path p = Paths.get("assets/images/SpriteSheet.png");
            BufferedImage sheet = ImageIO.read(p.toFile());

            // 2. Create the Spritesheet object
            SpriteSheet ss = new SpriteSheet(sheet, 32);

            // 3. Select frames to display 
            java.util.List<BufferedImage> glance = new java.util.ArrayList<>();
            for (int c = 0; c < ss.getCols(); c++) {
                for (int r = 0; r < ss.getRows(); r++) {
                    glance.add(ss.get(c, r));
                }
            }

            // 4. Show window
            SwingUtilities.invokeLater(() -> {
                JFrame f = new JFrame("Sprite Demo");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setPreferredSize(new Dimension(400, 300));
                // f.setContentPane(new SpriteDemo(glance));
                BufferedImage image = ss.get(0, 0);
                JLabel picLabel = new JLabel(new ImageIcon(image));
                f.add(picLabel);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            });
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
