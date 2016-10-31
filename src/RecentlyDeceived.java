
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
public class RecentlyDeceived extends Terrorist {

    
    private double range;
    private BufferedImage img;
    /**
     * Default constructor
     */
    public RecentlyDeceived() {
    }

    /**
     * @param name
     * @param s
     * @param pos
     * @param dir
     * @param str
     * @param imgName
     * @param e
     */
    public RecentlyDeceived(String name, double s, Position pos,Position dir, StepStrategy str, String imgName) {
        super(name, s, pos, dir, str);
        
        try                    { this.img = ImageIO.read( new File(imgName ) ) ; }
        catch ( Exception e )  { System.out.println( "Failed to read background image!" )   ;
                                   System.out.println( "bground:" +imgName);}
        
        this.range = 100.0;
    }

    /**
     * @param g2d 
     * @return
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage( /* image */ getImg(),
                 /* x              */ (int)this.getPosition().x ,
                 /* y              */ (int)this.getPosition().y ,
                 /* width          */ 50,
                 /* height         */ 50,
                 /* image observer */ null) ;
        float[] dashPattern = {10, 10};
        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
        g2d.setColor(Color.RED);
        //g2d.setStroke(new BasicStroke(2));
        g2d.drawOval((int) (this.getPosition().x-70), 
                (int) (this.getPosition().y-60) , (int) (2*getRange()), (int) (2*getRange()));
        
        g2d.setColor(Color.RED);
        g2d.drawString( this.getStrategy().getName() , (int) (this.getPosition().x+20), (int) (this.getPosition().y+60) ) ;
        
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