
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
public class Untouchable extends Entity {

    /**
     * Default constructor
     */
    public Untouchable() {
    }

    /**
     * 
     */
    public BufferedImage img;

    /**
     * @param name 
     * @param s 
     * @param pos 
     * @param dir 
     * @param str
     */
    public Untouchable(String name, double s, Position pos,Position dir, StepStrategy str, String imageName) {
        super(name, s, pos,dir, str);
        
        try                    { this.img = ImageIO.read( new File(imageName ) ) ; }
        catch ( Exception e )  { System.out.println( "Failed to read background image!" )   ;
                                   System.out.println( "bground:" +imageName);}
        
        
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
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect( (int)this.getPosition().x-5 , (int)this.getPosition().y-5 , 50  , 50 ) ;
        
        g2d.setColor(Color.RED);
        g2d.drawString( this.getStrategy().getName() , (int) (this.getPosition().x+10), (int) (this.getPosition().y+60) ) ;
        
    }

}