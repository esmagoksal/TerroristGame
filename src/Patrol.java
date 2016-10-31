
import java.util.*;

/**
 * 
 */
public class Patrol extends StepStrategy {

    private Environment env;
    private int numOfTurns ;
    private Position target;
    /**
     * Default constructor
     */
    public Patrol() {
    }

    /**
     * @param env
     * @param numOfTurns
     */
    public Patrol(Environment env, int numOfTurns, Position t) {
        super(env,numOfTurns);
        this.target = t;
    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return "P";
    }

    /**
     * @param e 
     * @param deltaTime
     * @return 
     */
    public Position generatePosition(Entity e)
    {
        Random random = new Random();
        int xMin = (int)e.getPosition().x - 15 ;
        int xMax = (int)e.getPosition().x + 15 ;
        int yMin = (int)e.getPosition().y - 15 ;
        int yMax = (int)e.getPosition().y + 15 ;

        int x = random.nextInt( xMax - xMin + 1 ) +xMin ;
        int y = random.nextInt( yMax - yMin + 1 ) +yMin ;
        
        return new Position(x,y);
    }
    
    @Override
    public void step(Entity e, double deltaTime) {
        this.setNumberOfTurns(this.getNumberOfTurns()-1);
        
        /*target = generatePosition(e);
        Position direction = new Position();
        direction.setAsNormalizedDirection(e.getPosition(),target );*/
        
        Position dir = new Position();
        dir.setDirection(e.getPosition(), target);
        e.setDirection(dir);
        
        //e.getPosition().x += direction.x * e.getSpeed() * deltaTime ;
        //e.getPosition().y += direction.y * e.getSpeed() * deltaTime ;
        double newX = e.getPosition().x + e.getDirection().x * e.getSpeed() * deltaTime ;
        double newY = e.getPosition().y + e.getDirection().y * e.getSpeed() * deltaTime ;
          
        
        Position newpos = new Position(newX, newY);
        if(newX>=0 && newX<= this.getEnviroment().getWindowWidth()
                && newY >=this.getEnviroment().getTopPanelHeight() && newY<=this.getEnviroment().getWindowHeight())
            
        {  e.setPosition(newpos);}
        
    }

}