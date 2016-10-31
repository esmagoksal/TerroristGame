
import java.util.*;

/**
 * 
 */
public class SoldierFactory extends EntityFactory {

    /**
     * Default constructor
     */
    public SoldierFactory() {
    }

    /**
     * 
     */
    public void SoldierFactory() {
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
        String name = "Soldier";
        double speed = env.generateRandomSpeed(3.0 , 5.0 ) ;
        Position position = env.generateRandomPosition(10) ;
        Position direction = tempDir;
        StepStrategy strategy  = env.generateAgentStepStrategy() ;
        String imgName = "./src/images/soldier.png";
        return new Soldier(name, speed, position,direction, strategy, imgName);
    }

}