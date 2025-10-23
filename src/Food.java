import java.awt.*;
import java.util.*;

/**
 * Represents the food on the game board, storing its position and providing
 * methods to generate and respawn the food without colliding with the snake.
 */
public class Food {

    private Point position;
    private final Random random;

    /**  
     * Constructor.
     */
    public Food(int boardWidth, int boardLength) {
        this.random = new Random();
        this.position = generateRandomPosition(boardWidth, boardLength);
    }

    /*
     * Generates a new random position for the food on the board.
     */
    private Point generateRandomPosition(int boardWidth, int boardLength) {
        int x = random.nextInt(boardWidth);
        int y = random.nextInt(boardLength);

        return new Point(x, y);
    }

    /**
     * Respawns food at new random position, does not allow food to spawn on snake body.
     */
    public void respawn(int boardWidth, int boardLength, Snake snake) {
        Point newPosition;
        boolean validPosition;

        do {
            newPosition = generateRandomPosition(boardWidth, boardLength);
            validPosition = true;

            for (Point segment : snake.getBody()) {
                if (newPosition.equals(segment)) {
                    validPosition = false;
                    break;
                }

            } 
        } while (!validPosition);

        this.position = newPosition;
    }

    public boolean isEaten(Snake snake) {
        return this.position.equals(snake.getHead());
    }

    //Getters and Setters
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}