
import java.util.*;

/**
 * 
 */
public abstract class StepStrategy {

    /**
     * Default constructor
     */
    public StepStrategy() {
    }

    private int numberOfTurns;
    private Environment enviroment;

    public StepStrategy(Environment environment, int numberOfTurns) {
        this.enviroment = environment;
        this.numberOfTurns = numberOfTurns;
    }

    /**
     * @return
     */
    public boolean isFinished() {
        // TODO implement here
        return (getNumberOfTurns()==0);
    }

    /**
     * @param e 
     * @param deltaTime 
     * @return
     */
    public abstract void step(Entity e, double deltaTime);
    /**
     * @return
     */
    public abstract String getName();

    /**
     * @return the numberOfTurns
     */
    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    /**
     * @param numberOfTurns the numberOfTurns to set
     */
    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    /**
     * @return the enviroment
     */
    public Environment getEnviroment() {
        return enviroment;
    }

    /**
     * @param enviroment the enviroment to set
     */
    public void setEnviroment(Environment enviroment) {
        this.enviroment = enviroment;
    }

}