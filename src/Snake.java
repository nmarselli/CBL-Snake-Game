import java.awt.*;
import java.util.*;

/**
 * Represents a snake in the Snake game,.
 * Includes its position, direction, color, and body segments.
 */

public class Snake { 

    // Direction enum
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private Direction direction;
    private Direction nextDirection;
    private Color color;
    private ArrayList<Point> body; // List to hold the segments of the snake's body as coordinates

    // Constructor
    public Snake(int startX, int startY, Color color) {
        
        this.body = new ArrayList<>();
        // Initialize the body with the head position
        this.body.add(new Point(startX, startY));
        this.body.add(new Point(startX - 1, startY)); 
        this.body.add(new Point(startX - 2, startY));

        this.direction = Direction.RIGHT; // Default direction
        this.nextDirection = Direction.RIGHT; // Default next direction

        this.color = color;
    }
    
    //Methods
    /**
     * Moves the snake in its current direction by updating its body segments.
     */
    public void move() {
        // Logic to move the snake
        Point head = body.get(0);
        Point newHead = new Point(0, 0);

        switch (direction) {
            case UP -> newHead = new Point(head.x, head.y + 1);
            case DOWN -> newHead = new Point(head.x, head.y - 1);
            case LEFT -> newHead = new Point(head.x - 1, head.y);
            case RIGHT -> newHead = new Point(head.x + 1, head.y);
            default -> newHead = new Point(head.x, head.y);
        }
        
        body.add(newHead);
        body.remove(body.size() - 1);

    }
    
    public void grow() {
        // Logic to grow the snake

    }

    /**
     * Changes the direction of the snake,
     * if the new direction is not opposite to the current direction.
     */
    public void changeDirection(Direction newDirection) {
        if (!isOppositeDirection(newDirection)) {
            direction = newDirection;
        }
    }

    private boolean isOppositeDirection(Direction newDirection) {
        return (this.direction == Direction.UP && newDirection == Direction.DOWN) 
            || (this.direction == Direction.DOWN && newDirection == Direction.UP) 
            || (this.direction == Direction.LEFT && newDirection == Direction.RIGHT) 
            || (this.direction == Direction.RIGHT && newDirection == Direction.LEFT);
    }

    /**
     * Checks if the snake has collided with itself or the boundaries.
     */
    public boolean checkCollision() {
        // Logic to check for collisions
        return checkSelfCollision() || checkBoundaryCollision();
    }

    private boolean checkSelfCollision() {
        // Logic to check for self-collision

        return false;
    }

    private boolean checkBoundaryCollision() {
        // Logic to check for boundary collision

        return false;
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

}