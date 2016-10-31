
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;

/**
 * 
 */
public class Soldier extends Entity {

    /**
     * Default constructor
     */
    public Soldier() {
    }

    /**
     * 
     */
    private int caughtTerrorist;

    /**
     * 
     */
    private boolean isAlive;

    /**
     * 
     */
    private double range;

    /**
     * 
     */
    private BufferedImage img;

    /**
     * @param name 
     * @param s 
     * @param pos 
     * @param dir 
     * @param str
     * @param imageName
     */
    public Soldier(String name, double s, Position pos,Position dir, StepStrategy str, String imageName) {
        super(name, s, pos, dir, str);
        
        try                    { this.img = ImageIO.read( new File(imageName ) ) ; }
        catch ( Exception e )  { System.out.println( "Failed to read background image!" )   ;
                                   System.out.println( "bground:" +imageName);}
        
        range = 30.0;
        isAlive = true;
        this.setIsAlive(true);
        caughtTerrorist =0;
    }

    /**
     * @param g2d 
     * @return
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage( /* image */ img,
                 /* x              */ (int)this.getPosition().x ,
                 /* y              */ (int)this.getPosition().y ,
                 /* width          */ 40,
                 /* height         */ 40,
                 /* image observer */ null) ;
        
        float[] dashPattern = {10, 10};
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
        g2d.setColor(Color.green);
        
        g2d.drawOval((int) (this.getPosition().x-10), 
                (int) (this.getPosition().y-10) , (int) (2*getRange()), (int) (2*getRange()));
        
        g2d.setColor(Color.RED);
        g2d.drawString( this.getStrategy().getName() , (int) (this.getPosition().x+10), (int) (this.getPosition().y+50) ) ;
        
    }

    public void catchTerrorist(Entity e) {
        
        caughtTerrorist++;
        e.setIsAlive(false);
        
    }
    
    /**
     * @return the caughtTerrorist
     */
    public int getCaughtTerrorist() {
        return caughtTerrorist;
    }

    /**
     * @param caughtTerrorist the caughtTerrorist to set
     */
    public void setCaughtTerrorist(int caughtTerrorist) {
        this.caughtTerrorist = caughtTerrorist;
    }

    /**
     * @return the range
     */
    public double getRange() {
        return range;
    }

    /**
     * @param range the range to set
     */
    public void setRange(double range) {
        this.range = range;
    }

}