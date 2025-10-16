import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {
    private final BufferedImage sheet;
    private final int tileWidth;
    private final int cols, rows;

    public SpriteSheet(BufferedImage sheet, int tileWidth) {
        this.sheet = sheet;
        this.tileWidth = tileWidth;
        this.cols = sheet.getWidth() / tileWidth;
        this.rows = sheet.getHeight() / tileWidth;
    }

    public int getCols() { return cols; }
    public int getRows() { return rows; }

    /** Get subimage by grid coordinate (col, row), 0-based. */
    public BufferedImage get(int col, int row) {
        //if (col < 0 || col >= cols || row < 0 || row >= rows) { throw new IndexOutOfBoundsException("col/row out of range");}
        int x = col * tileWidth;
        int y = row * tileWidth;
        return sheet.getSubimage(x, y, tileWidth, tileWidth); // view backed by the sheet
    }

    /** Get by linear index (row-major). */
    public BufferedImage getIndex(int index) {
        int col = index % cols;
        int row = index / cols;
        return get(col, row);
    }

    /** Slice all frames row-major. */
    public List<BufferedImage> sliceAll() {
        List<BufferedImage> list = new ArrayList<>(cols * rows);
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                list.add(get(c, r));
        return list;
    }

    /** Draw a tile directly to a Graphics2D (optional convenience). */
    public void drawTile(Graphics2D g, int col, int row, int x, int y, int scale) {
        BufferedImage frame = get(col, row);
        g.drawImage(frame, x, y, frame.getWidth() * scale, frame.getHeight() * scale, null);
    }

    /** If you need an independent copy (not a subimage view). */
    public static BufferedImage copy(BufferedImage src) {
        BufferedImage out = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        out.getGraphics().drawImage(src, 0, 0, null);
        return out;
    }
}