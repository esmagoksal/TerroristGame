
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;

/**
 * 
 */
public class MoveRandom extends StepStrategy {

    /**
     * Default constructor
     */
    public MoveRandom() {
    }

    /**
     * @param env
     * @param numOfTurns
     */
    public MoveRandom(Environment env, int numOfTurns) {
        super(env, numOfTurns);
    }

    /**
     * @return
     */
    @Override
    public String getName() { 
        return "MR";
    }

    /**
     * @param e 
     * @param deltaTime
     */
    @Override
    public void step(Entity e, double deltaTime) {
        
       setNumberOfTurns(getNumberOfTurns()-1);
       
        
        Random r = new Random();
        float _x = r.nextFloat()*2-1;
        float _y = r.nextFloat()*2-1;
        Position dir = new Position(_x, _y);
        dir.normalize();
        e.setDirection(dir);
        
         //e.getPosition().x += e.getDirection().x * e.getSpeed() * deltaTime ;
        // e.getPosition().y += e.getDirection().y * e.getSpeed() * deltaTime ;
        
        double newX = e.getPosition().x + e.getDirection().x * e.getSpeed() * deltaTime ;
        double newY = e.getPosition().y + e.getDirection().y * e.getSpeed() * deltaTime ;
          
        
        Position newpos = new Position(newX, newY);
        if(newX>=0 && newX<= this.getEnviroment().getWindowWidth()
                && newY >=this.getEnviroment().getTopPanelHeight() && newY<=this.getEnviroment().getWindowHeight())
            
        {  e.setPosition(newpos);}
        
        
      
    }

}