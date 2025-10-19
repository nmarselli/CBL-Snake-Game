import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * GamePanel is the main panel for the Snake game, handling key and action events.
 */
public class GamePanel extends JPanel implements KeyListener {

    private static final int BOARD_WIDTH = 20;
    private static final int BOARD_HEIGHT = 20;
    private static final int CELL_SIZE = 25;

    private final Snake snake;
    private final Food food;

    /**
     * Constructs a new GamePanel, 
     * Initializes the snake and food objects, and sets up panel properties.
     */
    
    GamePanel() {
        // Constructor logic here

        setBounds(0, 0, BOARD_WIDTH * CELL_SIZE, BOARD_HEIGHT * CELL_SIZE);
        setBackground(Color.BLACK);

        setFocusable(true);
        addKeyListener(this);
        
        setLayout(null);
        setVisible(true);

        //Initialize game objects
        snake = new Snake(BOARD_WIDTH / 2, BOARD_HEIGHT / 2, BOARD_WIDTH, BOARD_HEIGHT,
            Color.GREEN);
        food = new Food(BOARD_WIDTH, BOARD_HEIGHT);
    }

    
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        switch (e.getKeyChar()) {
            case 'w', 'W' -> snake.setNextDirection(Direction.UP);
            case 's', 'S' -> snake.setNextDirection(Direction.DOWN);
            case 'a', 'A' -> snake.setNextDirection(Direction.LEFT);
            case 'd', 'D' -> snake.setNextDirection(Direction.RIGHT);
            default -> snake.setNextDirection(snake.getDirection());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        switch (e.getKeyChar()) {
            case 'w', 'W' -> snake.setNextDirection(Direction.UP);
            case 's', 'S' -> snake.setNextDirection(Direction.DOWN);
            case 'a', 'A' -> snake.setNextDirection(Direction.LEFT);
            case 'd', 'D' -> snake.setNextDirection(Direction.RIGHT);
            default -> snake.setNextDirection(snake.getDirection());
        }
    }  
} 