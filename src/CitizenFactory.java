
import java.awt.Color;
import java.util.*;

/**
 * 
 */
public class CitizenFactory extends EntityFactory {

    /**
     * Default constructor
     */
    public CitizenFactory() {
    }


    /**
     * @param env Environment 
     * @return
     */
    @Override
    public Entity createEntity(Environment env) {
        Position tempDir = env.generateRandomPosition(10);
        tempDir.normalize();
        String name = "Citizen";
        Random r = new Random();
        Color color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
        double speed = env.generateRandomSpeed(1.0 , 2.0 ) ;
        Position position = env.generateRandomPosition(10) ;
        Position direction = tempDir;
        StepStrategy strategy  = env.generateAgentStepStrategy() ;
        return new Citizen(color, name, speed, position,direction, strategy);
    }

}