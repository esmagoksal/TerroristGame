
import java.util.*;

/**
 * 
 */
public abstract class TerroristDecorator extends Terrorist {

    private Terrorist decoratedTerrorist;
    /**
     * Default constructor
     */
    public TerroristDecorator() {
    }

    /**
     * @param t
     */
    public TerroristDecorator(Terrorist t) {
        super(t.getName(), t.getSpeed(), t.getPosition(),t.getDirection(), t.getStrategy());
        decoratedTerrorist = t;
    }

    /**
     * @return the decoratedTerrorist
     */
    public Terrorist getDecoratedTerrorist() {
        return decoratedTerrorist;
    }

    /**
     * @param decoratedTerrorist the decoratedTerrorist to set
     */
    public void setDecoratedTerrorist(Terrorist decoratedTerrorist) {
        this.decoratedTerrorist = decoratedTerrorist;
    }

}