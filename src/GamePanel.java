import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * GamePanel is the main panel for the Snake game, handling key and action
 * events.
 */
public class GamePanel extends JPanel {

    private static final int BOARD_WIDTH = 15;
    private static final double CELL_SIZE = 32*1.5;

    private final Snake snake;
    private final Food food;

    private final Timer gameTimer;
    private boolean isGameOver = false;
    private boolean canMove = true;
    private int frame = 0;

    ImageIcon deathIcon = new ImageIcon("assets/images/DeathIcon.png");
    Image image = deathIcon.getImage();
    Image scaled = image.getScaledInstance(10, 10, Image.SCALE_SMOOTH); // width, height, scale type
    ImageIcon scaledDeathIcon = new ImageIcon(scaled);

    /**
     * Constructs a new GamePanel,
     * Initializes the snake and food objects, and sets up panel properties.
     */

    GamePanel(Color snakeColor) {
        // Constructor logic here
        setSize(new Dimension((int)Math.round(BOARD_WIDTH * CELL_SIZE), (int)Math.round(BOARD_WIDTH * CELL_SIZE)));
        setOpaque(false);

        setFocusable(true);

        setLayout(null);
        setVisible(true);
        requestFocusInWindow();
        setupKeyBindings();

        // Initialize game objects
        snake = new Snake(BOARD_WIDTH / 2, BOARD_WIDTH / 2, BOARD_WIDTH, BOARD_WIDTH,
                snakeColor);
        food = new Food(BOARD_WIDTH, BOARD_WIDTH);

        // Set up game timer
        gameTimer = new Timer(150, e -> {
            if (!isGameOver) {
                gameLoop();
                frame++;
            }
        });

        gameTimer.start();
    }

    private void gameLoop() {
        // Game loop logic here
        canMove = true;
        if (food.isEaten(snake)) {
            snake.grow();
            food.respawn(BOARD_WIDTH, BOARD_WIDTH, snake);
        } else {
            snake.move();
        }

        if (snake.checkCollision(BOARD_WIDTH, BOARD_WIDTH)) {
            isGameOver = true;
            gameTimer.stop();


            int choice = JOptionPane.showConfirmDialog(this,
                "Game Over! Score: " + (snake.getBody().size() - 3) + "\nPlay again?", "Game Over",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, scaledDeathIcon
            );
            if (choice == JOptionPane.YES_OPTION) {
                Window window = SwingUtilities.getWindowAncestor(this);
                window.dispose();
                new GameFrame();
            } else {
                System.exit(0);
            }
        }
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        drawFood(g2d);

        drawSnake(g2d);

        g2d.dispose();
    }

    private void drawSnake(Graphics2D g2d) {
        g2d.setColor(snake.getColor());
        for (Point segment : snake.getBody()) {
            g2d.fillRect((int)Math.round(segment.x * CELL_SIZE), (int)Math.round(segment.y * CELL_SIZE), (int)Math.round(CELL_SIZE), (int)Math.round(CELL_SIZE));
        }
    }

    private void drawFood(Graphics2D g2d) {
        g2d.drawImage(SpriteSheet.getPicture("assets/images/Apple.png"),
                (int) Math.round(food.getPosition().x * CELL_SIZE + Math.sin(frame * Math.PI/10) / 4),
                (int) Math.round(food.getPosition().y * CELL_SIZE + Math.sin(frame * Math.PI/10) * 4),
                (int)Math.round(CELL_SIZE) * 3 / 4, (int)Math.round(CELL_SIZE) * 3 / 4, null);
    }

    private void setupKeyBindings() {
        InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        // WASD & Arrow keys
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "up");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "left");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "down");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "right");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "down");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right");

        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (canMove) {
                    snake.changeDirection(Direction.UP);
                    canMove = false;
                }
            }
        });
        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (canMove) {
                    snake.changeDirection(Direction.LEFT);
                    canMove = false;
                }
            }
        });
        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (canMove) {
                    snake.changeDirection(Direction.DOWN);
                    canMove = false;
                }
            }
        });
        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (canMove) {
                    snake.changeDirection(Direction.RIGHT);
                    canMove = false;
                }
            }
        });
    }
}