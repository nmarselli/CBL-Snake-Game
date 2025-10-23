import java.awt.*;
import java.util.*;

/**
 * Represents a snake in the Snake game,.
 * Includes its position, direction, color, and body segments.
 */

public class Snake { 

    private Direction direction;
    private Color color;
    private final ArrayList<Point> body; 

    /**
     * Constructor.
     */ 
    public Snake(int startX, int startY, int boardWidth, int boardHeight, Color color) {
        
        this.body = new ArrayList<>();
        // Initialize the body with the head position
        this.body.add(new Point(startX, startY));
        this.body.add(new Point(startX - 1, startY)); 
        this.body.add(new Point(startX - 2, startY));

        this.direction = Direction.RIGHT; // Default direction
        this.color = color;
    }
    
    //Methods
    /**
     * Moves the snake in its current direction by updating its body segments.
     */
    public void move() {
        // Logic to move the snake
        Point head = body.get(0);
        Point newHead;

        switch (direction) {
            case UP -> newHead = new Point(head.x, head.y - 1);
            case DOWN -> newHead = new Point(head.x, head.y + 1);
            case LEFT -> newHead = new Point(head.x - 1, head.y);
            case RIGHT -> newHead = new Point(head.x + 1, head.y);
            default -> newHead = new Point(head.x, head.y);
        }
        
        body.add(0, newHead);
        body.remove(body.size() - 1);

    }
    
    /**
     * Grows the snake by adding a new segment at its head.
     */
    public void grow() {
        // Logic to grow the snake

        Point head = body.get(0);
        Point newHead;

        switch (direction) {
            case UP -> newHead = new Point(head.x, head.y - 1);
            case DOWN -> newHead = new Point(head.x, head.y + 1);
            case LEFT -> newHead = new Point(head.x - 1, head.y);
            case RIGHT -> newHead = new Point(head.x + 1, head.y);
            default -> newHead = new Point(head.x, head.y);
        }
        body.add(0, newHead);
    }

    /**
     * Changes the direction of the snake,
     * if the new direction is not opposite to the current direction.
     */
    public void changeDirection(Direction nextDirection) {
        if (!isOppositeDirection(nextDirection)) {
            direction = nextDirection;
        }
    }

    private boolean isOppositeDirection(Direction nextDirection) {
        return switch (this.direction) {
            case UP -> nextDirection == Direction.DOWN;
            case DOWN -> nextDirection == Direction.UP;
            case LEFT -> nextDirection == Direction.RIGHT;
            case RIGHT -> nextDirection == Direction.LEFT;
            default -> false;
        };
    }

    /**
     * Checks if the snake has collided with itself or the boundaries.
     */
    public boolean checkCollision(int boardWidth, int boardHeight) {
        // Logic to check for collisions
        return checkSelfCollision() || checkBoundaryCollision(boardWidth, boardHeight);
    }

    private boolean checkSelfCollision() {
        // Logic to check for self-collision
        Point head = body.get(0);

        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }

        return false;
    }

    private boolean checkBoundaryCollision(int boardHeight, int boardWidth) {
        // Logic to check for boundary collision
        Point head = body.get(0);

        return (head.x < 0 || head.x >= boardWidth || head.y < 0 || head.y >= boardHeight);
    }

    // Getters and Setters
    public Direction getDirection() {
        return direction;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getHead() {
        return (body.get(0));
    }

    public ArrayList<Point> getBody() {
        return body;
    }
}