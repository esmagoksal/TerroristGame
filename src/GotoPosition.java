
import java.util.*;

/**
 * 
 */
public class GotoPosition extends StepStrategy {

    /**
     * Default constructor
     */
    public GotoPosition() {
    }

    /**
     * 
     */
    public Position target;
    private ArrayList<Entity> entities;

    /**
     * @param env 
     * @param t
     * @param numOfTurns
     */
    public GotoPosition(Environment env, int numOfTurns, Position t) {
        super(env, numOfTurns);
        this.target = t;
        entities = env.getEntities();
    }
    
    @Override
    public String getName(){
        return "GP";
    }
    
    /**
     * @param e
     * @param deltaTime
     */
    @Override
    public void step(Entity e, double deltaTime){
        this.setNumberOfTurns(this.getNumberOfTurns()-1);
        if(e instanceof RecentlyDeceived)
        {
            double min = 100000;
           for(Entity other: entities)
           {
               if(other instanceof Camp)
               {
                   double distance = e.distanceTo( other ) ;
                   if(distance <min ){
                       min = distance;
                       target = other.getPosition();}
               }
           }
        }
        
        else if(e instanceof BrainWashed)
        {
            double min = 100000;
           for(Entity other: entities)
           {
               if(other instanceof SupplyStorage)
               {
                   double distance = e.distanceTo( other ) ;
                   if(distance <min ){
                       min = distance;
                       target = other.getPosition();}
               }
           }
        }
        /*else if(e instanceof Citizen)
        {
           double min = 100000;
           for(Entity other: entities)
           {
               if(other instanceof GeziParki)
               {
                   double distance = e.distanceTo( other ) ;
                   if(distance <min ){
                       min = distance;
                       target = other.getPosition();
                    }
               }
           }
        }*/
        Position dir = new Position();
        dir.setDirection(e.getPosition(), target);
        e.setDirection(dir);
        //e.getPosition().x += dir.x * e.getSpeed() * deltaTime ;
        //e.getPosition().y += dir.y * e.getSpeed() * deltaTime ;
        double newX = e.getPosition().x + e.getDirection().x * e.getSpeed() * deltaTime ;
        double newY = e.getPosition().y + e.getDirection().y * e.getSpeed() * deltaTime ;
          
        
        Position newpos = new Position(newX, newY);
        if(newX>=0 && newX<= this.getEnviroment().getWindowWidth()
                && newY >=this.getEnviroment().getTopPanelHeight() && newY<=this.getEnviroment().getWindowHeight())
            
        {  e.setPosition(newpos);}
        
        
    }

}