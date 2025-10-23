
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.*;

/**
 * Loads the background, using tiles form the Spritesheet.
 */
public class Background extends JPanel {
    private java.util.List<BufferedImage> tileList = new java.util.ArrayList<>();
    private int[] finalSettings = new int[5];
    int[][] tiles = new int[finalSettings[0]][finalSettings[0]];

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0;
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        // Draw each tile
        for (int i = 0; i < tiles.length; i++) {
            BufferedImage f;
            for (int j = 0; j < tiles.length; j++) {
                if (i % 2 == 0 && j % 2 == 0 || i % 2 == 1 && j % 2 == 1) {
                    f = tileList.get(tiles[i][j]);
                } else {
                    f = color(0.85, 0.85, 0.85, tileList.get(tiles[i][j]));
                }
                g.drawImage(f, (int) Math.round(i * f.getWidth() * finalSettings[3] / 10),
                        (int) Math.round(j * f.getWidth() * finalSettings[3] / 10),
                        (int) Math.round(f.getWidth() * finalSettings[3] / 10),
                        (int) Math.round(f.getWidth() * finalSettings[3] / 10), null);
            }
        }
    }

    /**
     * Sets up the background panel.
     * Calls to get the spritesheet and slices it into tiles.
     */
    public Background(int[] finalSettings) {
        this.finalSettings = finalSettings;
        setSize((int) Math.round(32 * finalSettings[0] * finalSettings[3] / 10 + 16),
                (int) Math.round(32 * finalSettings[0] * finalSettings[3] / 10 + 38));
        // Load spritesheet and create an object, slices it into tiles
        SpriteSheet ss = new SpriteSheet(SpriteSheet.getPicture("assets/images/TileSet3.png"), 32);
        // Adds all tiles to a list
        tileList.clear();
        for (int r = 0; r < ss.getRows(); r++) {
            for (int c = 0; c < ss.getCols(); c++) {
                tileList.add(ss.get(c, r));
            }
        }
        tiles = new int[finalSettings[0]][finalSettings[0]];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                
                if (i % 2 == 0 && j % 2 == 0 || i % 2 == 1 && j % 2 == 1) {
                    tiles[i][j] = new Random().nextInt(16);
                } else {
                    tiles[i][j] = new Random().nextInt(24);
                }
                if (i == 0) {
                    tiles[i][j] = new Random().nextInt(40, 44);
                } else if (i == tiles.length - 1) {
                    tiles[i][j] = new Random().nextInt(32, 36);
                } else if (j == 0) {
                    tiles[i][j] = new Random().nextInt(44, 48);
                } else if (j == tiles.length - 1) {
                    tiles[i][j] = new Random().nextInt(36, 40);
                }
                if (i == 0 && j == 0) {
                    tiles[i][j] = 56;
                }
                if (i == 0 && j == tiles.length - 1) {
                    tiles[i][j] = 59;
                }
                if (i == tiles.length - 1 && j == tiles.length - 1) {
                    tiles[i][j] = 58;
                }
                if (i == tiles.length - 1 && j == 0) {
                    tiles[i][j] = 57;
                }
            }
        }
        this.repaint();
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
        BufferedImage newImage = new BufferedImage(
                src.getWidth(), src.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(src, 0, 0, null);
        graphics.dispose();

        // Color image
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int ax = newImage.getColorModel().getAlpha(
                        newImage.getRaster().getDataElements(i, j, null));
                int rx = newImage.getColorModel().getRed(
                        newImage.getRaster().getDataElements(i, j, null));
                int gx = newImage.getColorModel().getGreen(
                        newImage.getRaster().getDataElements(i, j, null));
                int bx = newImage.getColorModel().getBlue(
                        newImage.getRaster().getDataElements(i, j, null));
                rx *= r;
                gx *= g;
                bx *= b;
                newImage.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx));
            }
        }
        return newImage;
    }
}
