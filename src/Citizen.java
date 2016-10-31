
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.*;

/**
 * 
 */
public class Citizen extends Entity {

    /**
     * Default constructor
     */
    public Citizen() {
    }

    /**
     * 
     */
    private double radius;

    /**
     * 
     */
    private boolean isAlive;

    /**
     * @param col 
     * @param name 
     * @param s 
     * @param pos 
     * @param dir 
     * @param str
     */
    public Citizen(Color col, String name, double s, Position pos,Position dir, StepStrategy str) {
        super(col, name, s, pos, dir, str);
        
        radius = 8.0;
        isAlive = true;
        this.setIsAlive(true);
    }

    /**
     * @param g2d 
     * @return
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(this.getColor());
        g2d.setStroke(new BasicStroke(3));
        g2d.fillOval( (int) (this.getPosition().x - radius) , (int) (this.getPosition().y - radius) , (int) (2 * radius) , (int) (2 * radius) ) ;

        g2d.setColor(Color.RED);
        g2d.drawString( this.getStrategy().getName() , (int) (this.getPosition().x), (int) (this.getPosition().y+16) ) ;
        
    }

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(double radius) {
        this.radius = radius;
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