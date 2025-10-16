import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {
    private final BufferedImage sheet;
    private final int tileW, tileH, margin, spacing;
    private final int cols, rows;

    public SpriteSheet(BufferedImage sheet, int tileW, int tileH) {
        this(sheet, tileW, tileH, 0, 0);
    }

    public SpriteSheet(BufferedImage sheet, int tileW, int tileH, int margin, int spacing) {
        this.sheet = sheet;
        this.tileW = tileW;
        this.tileH = tileH;
        this.margin = margin;
        this.spacing = spacing;
        this.cols = (sheet.getWidth()  - 2*margin + spacing) / (tileW + spacing);
        this.rows = (sheet.getHeight() - 2*margin + spacing) / (tileH + spacing);
    }

    public int getCols() { return cols; }
    public int getRows() { return rows; }

    /** Get subimage by grid coordinate (col, row), 0-based. */
    public BufferedImage get(int col, int row) {
        if (col < 0 || col >= cols || row < 0 || row >= rows)
            throw new IndexOutOfBoundsException("col/row out of range");
        int x = margin + col * (tileW + spacing);
        int y = margin + row * (tileH + spacing);
        return sheet.getSubimage(x, y, tileW, tileH); // view backed by the sheet
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