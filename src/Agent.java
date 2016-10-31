
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
public class Agent extends Entity {

    /**
     * Default constructor
     */
    public Agent() {
    }


    private int caughtTerrorist;
    private double range;
    private BufferedImage img;

    /**
     * @param name 
     * @param s 
     * @param pos 
     * @param dir 
     * @param str
     * @param imgName
     */
    public Agent(String name, double s, Position pos, Position dir, StepStrategy str, String imgName) {
        super(name, s, pos,dir, str);
        
        try                    { this.img = ImageIO.read( new File(imgName ) ) ; }
        catch ( Exception e )  { System.out.println( "Failed to read background image!" )   ;
                                   System.out.println( "bground:" +imgName);}
        
        
        range = 30.0;
        this.setIsAlive(true);
        //bufferedImage
        caughtTerrorist =0;
    }

    /**
     * @param e
     */
    public void catchTerrorist(Entity e) {
        
        caughtTerrorist++;
        e.setIsAlive(false);
        
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
        g2d.setColor(Color.BLUE);
        //g2d.setStroke(new BasicStroke(2));
        g2d.drawOval( (int) (this.getPosition().x-10), 
                (int) (this.getPosition().y-10) , (int) (2*range), (int) (2*range));
        
        g2d.setColor(Color.RED);
        g2d.drawString( this.getStrategy().getName() , (int) (this.getPosition().x+10), (int) (this.getPosition().y+50) ) ;
        
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

    /**
     * @return the img
     */
    public BufferedImage getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(BufferedImage img) {
        this.img = img;
    }

}