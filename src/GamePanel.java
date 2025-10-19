import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * GamePanel is the main panel for the Snake game, handling key and action
 * events.
 */
public class GamePanel extends JPanel implements KeyListener {

    private static final int BOARD_WIDTH = 20;
    private static final int BOARD_HEIGHT = 20;
    private static final int CELL_SIZE = 25;

    private final Snake snake;
    private final Food food;

    private final Timer gameTimer;
    private boolean isGameOver = false;

    /**
     * Constructs a new GamePanel,
     * Initializes the snake and food objects, and sets up panel properties.
     */

    GamePanel() {
        // Constructor logic here

        //setBounds(0, 0, BOARD_WIDTH * CELL_SIZE, BOARD_HEIGHT * CELL_SIZE);
        setSize(new Dimension(BOARD_WIDTH * CELL_SIZE, BOARD_HEIGHT * CELL_SIZE));
        setBackground(Color.white);

        setFocusable(true);
        addKeyListener(this);

        setLayout(null);
        setVisible(true);

        // Initialize game objects
        snake = new Snake(BOARD_WIDTH / 2, BOARD_HEIGHT / 2, BOARD_WIDTH, BOARD_HEIGHT,
                Color.GREEN);
        food = new Food(BOARD_WIDTH, BOARD_HEIGHT);

        // Set up game timer
        gameTimer = new Timer(100, e -> {
            if (!isGameOver) {
                gameLoop();
            }
        });

        gameTimer.start();
    }

    private void gameLoop() {
        // Game loop logic here
        if (food.isEaten(snake)) {
            snake.grow();
            food.respawn(BOARD_WIDTH, BOARD_HEIGHT, snake);
        } else {
            snake.move();
        }

        if (snake.checkCollision(BOARD_HEIGHT, BOARD_WIDTH)) {
            isGameOver = true;
            gameTimer.stop();
            JOptionPane.showMessageDialog(this, "Game Over!");
        }
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        drawFood(g2d);

        drawSnake(g2d);
    }

    private void drawSnake(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        for (Point segment : snake.getBody()) {
            g2d.fillRect(segment.x * CELL_SIZE, segment.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    private void drawFood(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillOval(food.getPosition().x * CELL_SIZE,
                food.getPosition().y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w', 'W' -> {
                snake.changeDirection(Direction.UP);
                System.out.println("Up key pressed");
            }
            case 's', 'S' -> {
                snake.changeDirection(Direction.DOWN);
                System.out.println("Down key pressed");
            }
            case 'a', 'A' -> {
                snake.changeDirection(Direction.LEFT);
                System.out.println("Left key pressed");
            }
            case 'd', 'D' -> {
                snake.changeDirection(Direction.RIGHT);
                System.out.println("Right key pressed");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}