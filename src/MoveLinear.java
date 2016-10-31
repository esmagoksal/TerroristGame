
import java.util.*;

/**
 * 
 */
public class MoveLinear extends StepStrategy {

    private Position direction;
    /**
     * Default constructor
     */
    public MoveLinear() {
    }

    /**
     * @param env
     * @param numOfTurns
     */
    public MoveLinear(Environment env, int numOfTurns) {
        super(env, numOfTurns);
        direction = env.generateRandomDirection() ;
    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return "ML";
    }

    /**
     * @param e 
     * @param deltaTime
     */
    @Override
    public void step(Entity e, double deltaTime) {
        setNumberOfTurns(getNumberOfTurns()-1);
        
        double newX = e.getPosition().x + direction.x * e.getSpeed() * deltaTime ;
        double newY = e.getPosition().y + direction.y * e.getSpeed() * deltaTime ;
          
        
        Position newpos = new Position(newX, newY);
        if(newX>=0 && newX<= getEnviroment().getWindowWidth()
                && newY >=getEnviroment().getTopPanelHeight() && newY<=getEnviroment().getWindowHeight())
            
        {  e.setPosition(newpos);}
        e.setDirection(direction);
        
        Position dir = e.getDirection();
        if ( ( e.getPosition().x < 0.0 ) && ( e.getDirection().x < 0 ) )  { dir.x *= -1  ; }
        if ( ( e.getPosition().x > getEnviroment().getWindowWidth() ) && ( e.getDirection().x > 0 ) )  { dir.x *= -1 ; }
        if ( ( e.getPosition().y < getEnviroment().getTopPanelHeight() ) && ( e.getDirection().y < 0 ) )  { dir.y *= -1 ; }
        if ( ( e.getPosition().y > getEnviroment().getWindowHeight() ) && ( e.getDirection().y  > 0 ) )  { dir.y *= -1 ; }
        
        e.setDirection(dir);
        
       
    }

}