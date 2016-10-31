
import java.util.*;

/**
 * 
 */
public class StandStill extends StepStrategy {

    
    /**
     * Default constructor
     */
    public StandStill() {
    }

    /**
     * @param env
     */
    public StandStill(Environment env, int numOfTurns) {
        super(env,numOfTurns);
    }

    /**
     * @return
     */
    @Override
    public String getName() {
        // TODO implement here
        return "SS";
    }

    /**
     * @param e 
     * @param deltaTime 
     * @return
     */
    @Override
    public void step(Entity e, double deltaTime) {
        setNumberOfTurns(getNumberOfTurns()-1);
        //System.out.println(getNumberOfTurns());
        
        
    }

}