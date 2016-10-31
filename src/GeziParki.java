
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Esma
 */
public class GeziParki extends Entity {
    
    /**
     * Default constructor
     */
    public GeziParki() {
    }

    /**
     * 
     */
    private double radius;

    /**
     * @param col 
     * @param name 
     * @param s 
     * @param pos
     * @param dir
     * @param str
     */
    public GeziParki(Color col, String name, double s, Position pos,Position dir, StepStrategy str) {
        super(col, name, s, pos, dir,str);
        this.radius = 45.0;
    }
    
    /**
     * @param g2d 
     * @return
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(new Color((float)(getColor().getRed())/255.0f
                ,(float)(getColor().getGreen())/255.0f
                ,(float)(getColor().getBlue())/255.0f,0.2f));
        g2d.drawOval((int) (this.getPosition().x - getRadius()) , (int) (this.getPosition().y - getRadius()) , (int) (2 * getRadius()) , (int) (2 * getRadius()) ) ;
        g2d.fillOval((int) (this.getPosition().x - getRadius()) , (int) (this.getPosition().y - getRadius()) , (int) (2 * getRadius()) , (int) (2 * getRadius()) ) ;
        g2d.setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        g2d.setColor(Color.decode("#111111"));
        g2d.drawString(getName(), (int) (getPosition().x-3*getRadius()/4), (int)(getPosition().y));
        
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
