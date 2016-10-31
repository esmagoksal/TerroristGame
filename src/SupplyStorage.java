
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.*;

/**
 * 
 */
public class SupplyStorage extends Entity {

    /**
     * Default constructor
     */
    public SupplyStorage() {
    }

    /**
     * 
     */
    private double radius;

    /**
     * @param col 
     * @param name 
     * @param pos
     */
    public SupplyStorage(Color col, String name, double s, Position pos,Position dir, StepStrategy str) {
        
        super(col, name, s, pos, dir,str);
        
        Random rand = new Random();
        radius = rand.nextInt(25) + 20;
    }

    /**
     * @param g2d 
     * @return
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(new Color((float)(getColor().getRed())/255.0f
                ,(float)(getColor().getGreen())/255.0f
                ,(float)(getColor().getBlue())/255.0f,0.2f));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawOval( (int) (this.getPosition().x - radius) , (int) (this.getPosition().y - radius) , (int) (2 * radius) , (int) (2 * radius) ) ;        
        g2d.fillOval( (int) (this.getPosition().x - radius) , (int) (this.getPosition().y - radius) , (int) (2 * radius) , (int) (2 * radius) ) ;
        g2d.setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        g2d.setColor(Color.decode("#111111"));
        g2d.drawString(getName(), (int) (getPosition().x-3*radius/4), (int)(getPosition().y));
        
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

}