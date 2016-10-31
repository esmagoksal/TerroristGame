
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
public class Equipped extends TerroristDecorator {

    //private BufferedImage mainImage;
    private BufferedImage icon;
    private double range;
    /**
     * Default constructor
     */
    public Equipped() {
    }

    /**
     * @param t
     */
    public Equipped(Terrorist t) {
        super(t);
        //this.mainImage = t.getImg();
        this.range = t.getRange();
         try { this.icon = ImageIO.read( new File( "./src/images/bomb.jpg") ) ; }
        catch ( Exception e )  { System.out.println( "Failed to read background image!" )   ;
                                   System.out.println( "icon:" );}
    }

    /**
     * @param g2d 
     * @return
     */
    @Override
    public void draw(Graphics2D g2d) {
        /*g2d.drawImage( /* image */ /*getMainImage(),
                 /* x              */ /*(int)this.getPosition().x ,
                 /* y              *//* (int)this.getPosition().y ,
                 /* width          */ /*50,
                 /* height         */ /*50,
                 /* image observer */ /*null) ;*/
        
        g2d.drawImage( /* image */ getIcon(),
                 /* x              */ (int)this.getPosition().x-20 ,
                 /* y              */ (int)this.getPosition().y+10 ,
                 /* width          */ 20,
                 /* height         */ 20,
                 /* image observer */ null) ;
        
        //float[] dashPattern = {10, 10};
        //g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
        //g2d.setColor(Color.RED);
        //g2d.setStroke(new BasicStroke(2));
        //g2d.drawOval((int) (this.getPosition().x-25), 
                //(int) (this.getPosition().y-15) , (int) (2*getRange()), (int) (2*getRange()));
        getDecoratedTerrorist().setName(getName());
        getDecoratedTerrorist().setSpeed(getSpeed());
        getDecoratedTerrorist().setPosition(getPosition());
        getDecoratedTerrorist().setDirection(getDirection());
        getDecoratedTerrorist().setStrategy(getStrategy());
        getDecoratedTerrorist().setImg(getImg());
        getDecoratedTerrorist().setRange(getRange());        
        getDecoratedTerrorist().draw(g2d);        
        
    }


    /**
     * @return the icon
     */
    public BufferedImage getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(BufferedImage icon) {
        this.icon = icon;
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