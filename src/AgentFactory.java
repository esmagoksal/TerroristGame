
import java.util.*;

/**
 * 
 */
public class AgentFactory extends EntityFactory {

    /**
     * Default constructor
     */
    public AgentFactory() {
    }


    /**
     * @param env Environment 
     * @return
     */
    @Override
    public Entity createEntity(Environment env) {
        Position tempDir = env.generateRandomPosition(10);
        tempDir.normalize();
        String name = "Agent";
        double speed = env.generateRandomSpeed(3.0 , 8.0 ) ;
        Position position = env.generateRandomPosition(10) ;
        Position direction = tempDir ;
        StepStrategy strategy  = env.generateAgentStepStrategy() ;
        String imgName = "./src/images/smith.jpg";
        return new Agent(name, speed, position,direction, strategy, imgName);
    }

}