
import java.awt.Graphics2D;
import java.util.*;

/**
 * 
 */
public class Exploded extends TerroristDecorator {

    private double range;
    /**
     * Default constructor
     */
    public Exploded() {
    }

    /**
     * @param t
     */
    public Exploded(Terrorist t) {
        super(t);
        this.range = t.getRange();
    }

    /**
     * @param g2d 
     * @return
     */
    @Override
    public void draw(Graphics2D g2d) {
        StepStrategy str = new StandStill();
        str.setNumberOfTurns(100000);
        getDecoratedTerrorist().setName(getName());
        getDecoratedTerrorist().setSpeed(getSpeed());
        getDecoratedTerrorist().setPosition(getPosition());
        getDecoratedTerrorist().setDirection(getDirection());
        getDecoratedTerrorist().setStrategy(str);
        getDecoratedTerrorist().setImg(getImg());
        getDecoratedTerrorist().setRange(getRange());        
        getDecoratedTerrorist().draw(g2d); 
        
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