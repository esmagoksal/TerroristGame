
import java.util.*;

/**
 * 
 */
public class TerroristFactory extends EntityFactory {

    /**
     * Default constructor
     */
    public TerroristFactory() {
    }

    /**
     * 
     */
    public void TerroristFactory() {
        // TODO implement here
    }

    /**
     * @param env 
     * @return
     */
    @Override
    public Entity createEntity(Environment env) {
        Position tempDir = env.generateRandomPosition(10);
        tempDir.normalize();
        String name = "Terrorist";
        double speed = env.generateRandomSpeed(5.0 , 10.0 ) ;
        Position position = env.generateRandomPosition(10) ;
        Position direction = tempDir ;
        StepStrategy strategy  = env.generateAgentStepStrategy() ;
        String imgName = "./src/images/counter-Terrorist.png";
        return new RecentlyDeceived(name, speed, position,direction, strategy, imgName);
    }

}