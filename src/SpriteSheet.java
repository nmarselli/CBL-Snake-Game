import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Creates the Spritesheet class.
 * Creates the slicer for the spritesheet.
 * Indexes the sliced subimages.
 */
public class SpriteSheet {
    private final BufferedImage sheet;
    private final int tileWidth;
    private final int cols;
    private final int rows;

    /**
     * Constructs the Spritesheet class.
     */
    public SpriteSheet(BufferedImage sheet, int tileWidth) {
        this.sheet = sheet;
        this.tileWidth = tileWidth;
        this.cols = sheet.getWidth() / tileWidth;
        this.rows = sheet.getHeight() / tileWidth;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    /**
     * Get subimage.
     */
    public BufferedImage get(int col, int row) {
        int x = col * tileWidth;
        int y = row * tileWidth;
        return sheet.getSubimage(x, y, tileWidth, tileWidth);
    }

    /**
     * Get indexed (linear by row).
     */
    public BufferedImage getIndex(int index) {
        int col = index % cols;
        int row = index / cols;
        return get(col, row);
    }

    /**
     * Slice the image into tiles.
     */
    public List<BufferedImage> sliceAll() {
        List<BufferedImage> list = new ArrayList<>(cols * rows);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                list.add(get(c, r));
            }
        }
        return list;
    }
    /**
     * Get image sheet from path.
     */
    public static BufferedImage getPicture(String path) {
        try {
            Path p = Paths.get(path);
            BufferedImage sheet1 = ImageIO.read(p.toFile());
            return sheet1;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}