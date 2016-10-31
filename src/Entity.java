
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

/**
 * 
 */
public abstract class Entity {

    /**
     * Default constructor
     */
    public Entity() {
    }

    /**
     * 
     */
    private Color color;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private double speed;

    /**
     * 
     */
    private Position position;
    /**
     * 
     */
    private Position direction;

    /**
     * 
     */
    private StepStrategy strategy;
    private boolean isAlive;


    /**
     * @param color 
     * @param name 
     * @param speed 
     * @param position 
     * @param direction 
     * @param strategy
     */
    public Entity(Color color, String name, double speed, Position position, Position direction, StepStrategy strategy) {
        // TODO implement here
        this.color = color;
        this.name = name;
        this.speed = speed;
        this.position = position;
        this.direction = direction;
        this.strategy = strategy; 
    }

    /**
     * @param name 
     * @param speed 
     * @param position
     * @param direction
     * @param strategy
     */
    public Entity(String name, double speed, Position position, Position direction, StepStrategy strategy){
        
        this.name = name;
        this.speed = speed;
        this.position = position;
        this.direction = direction;
        this.strategy = strategy;
    }

    /**
     * @param deltatime
     */
    public void step(double deltatime) {
        
        strategy.step( this , deltatime) ;
    }
    
    public double distanceTo ( Entity other )
  {
    return position.distanceTo( other.position ) ;
  }
    

    /**
     * @param g2d
     */
    public abstract void draw(Graphics2D g2d);

    /**
     * @return the color
     */
    
    
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * @return the strategy
     */
    public StepStrategy getStrategy() {
        return strategy;
    }

    /**
     * @param strategy the strategy to set
     */
    public void setStrategy(StepStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * @return the direction
     */
    public Position getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Position direction) {
        this.direction = direction;
    }

    /**
     * @return the isAlive
     */
    public boolean isIsAlive() {
        return isAlive;
    }

    /**
     * @param isAlive the isAlive to set
     */
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    
    

}