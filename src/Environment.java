
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * 
 */
public class Environment {

    

    /**
     * Default constructor
     */
    public Environment() {
    }


    private ArrayList<Entity> entities;
    private int windowWidth;
    private int windowHeight;
    private int topPanelHeight;
    private int numOfEntites;
    private BufferedImage logo;
    private BufferedImage background;
    private int numberOfSteps;
    private int numOfDeadCit;
    private int numOfDeadSol;
    private int numOfDeadAgt;
    private int numOfDeadTerr;
    private int caughtByAgents;
    private int caughtBySoldiers;
    public Random random;
    private AudioStream boom;
    private AudioStream shoot;
    private Entity geziParki;

    /**
     * @param windowWidth
     * @param windowHeight
     * @param topPanelHeight 
     * @param numOfEntities
     * @param logoFname
     * @param backgroundFname
     */
    public Environment(int windowWidth, int windowHeight, int topPanelHeight, int numOfEntities, String logoFname, String backgroundFname) {
        // TODO implement here
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.topPanelHeight = topPanelHeight;
        this.numOfEntites = numOfEntities;
        this.entities = new ArrayList<>();
        this.numberOfSteps = 0;
        this.random = new Random();
        this.numOfDeadTerr =0;
        
        try                    { this.background = ImageIO.read( new File( backgroundFname ) ) ; }
        catch ( Exception e )  { System.out.println( "Failed to read background image!" )   ;
                                   System.out.println( "bground:" +backgroundFname );}
        
        try                    { this.logo = ImageIO.read( new File( logoFname ) ) ; }
        catch ( Exception e )  { System.out.println( "Failed to read logo image!" )   ; 
                                   System.out.println( "logo:" +logoFname );}
        
        try{ InputStream stream = new FileInputStream("./src/images/explosion.wav");
            boom = new AudioStream(stream); }
        catch(Exception exc){
            exc.printStackTrace();}
        
        try{ InputStream stream = new FileInputStream("./src/images/shoot.wav");
            shoot = new AudioStream(stream); }
        catch(Exception exc){
            exc.printStackTrace();}
        
        int numberOfAgent = random.nextInt(4)+4;
        int numberOfTerrorist = random.nextInt(5)+10;
        int numberOfSoldier = random.nextInt(4)+4;
        int numOfCitizen = random.nextInt(30)+60;
        int numOfCamp = random.nextInt(4)+5;
        int numOfSupplyStorage = random.nextInt(4)+5;
        int numOfGeziParki = random.nextInt(2)+1;
        
        for(int i =0; i<numberOfAgent; i++)
        {
            Entity e = this.createEntity(new AgentFactory());
            this.addEntity(e);
        }
        
        for(int i =0; i<numberOfTerrorist; i++)
        {
            Entity e = this.createEntity(new TerroristFactory());
            this.addEntity(e);
        }
        
        for(int i =0; i<numberOfSoldier; i++)
        {
            Entity e = this.createEntity(new SoldierFactory());
            this.addEntity(e);
        }
        
        for(int i =0; i<numOfCitizen; i++)
        {
            Entity e = this.createEntity(new CitizenFactory());
            this.addEntity(e);
        }
        
        for(int i=0; i<numOfCamp; i++)
        {
            Position dir = generateRandomPosition(10);
            dir.normalize();
            
            int numberOfTurns = 1000000000;
            StepStrategy s = new StandStill( this , numberOfTurns );
            
            Entity e = new Camp(Color.ORANGE, "Camp", 0, generateRandomPosition(10), dir, s );
            this.addEntity(e);
        }
        
        for(int i=0; i<numOfSupplyStorage; i++)
        {
            Position dir = generateRandomPosition(10);
            dir.normalize();
            
            int numberOfTurns = 1000000000;
            StepStrategy s = new StandStill( this , numberOfTurns );
            
            Entity e = new SupplyStorage(Color.BLUE, "SupplyStorage",0, generateRandomPosition(10),dir,s);
            this.addEntity(e);
        }
         
        for(int i=0; i<2; i++)
        {
            Position dir = generateRandomPosition(10);
            dir.normalize();
            String imgName1 = "./src/images/erdogan.png";
            String imgName2 = "./src/images/ahmet.png";
            
            if(i == 0)
            {
                Entity e = new Untouchable("Erdogan", generateRandomSpeed(2.0, 4.0),
                        generateRandomPosition(10), dir, generateUntouchableStepStrategy(),imgName1 );
                this.addEntity(e);
            }
            if(i==1)
            {
                Entity e = new Untouchable("Erdogan", generateRandomSpeed(2.0, 4.0),
                        generateRandomPosition(10), dir, generateUntouchableStepStrategy(),imgName2 );
                this.addEntity(e);
            }
   
        }
        
        for(int i=0; i<numOfGeziParki; i++)
        {
            Position dir = generateRandomPosition(10);
            dir.normalize();
            
            int numberOfTurns = 1000000000;
            StepStrategy s = new StandStill( this , numberOfTurns );
            
            Entity e = new GeziParki(Color.GREEN, "GeziParki",0, generateRandomPosition(10),dir,s);
            this.addEntity(e);
        }
    
 
    }

    /**
     * @param f 
     * @return
     */
    public Entity createEntity(EntityFactory f) {
        // TODO implement here
        return f.createEntity(this);
    }

    /**
     * @param e 
     * @return
     */
    public StepStrategy generateStrategy(Entity e) {
        // TODO implement here
        return null;
    }

    /**
     * @param deltaTime
     */
    public synchronized void stepAll(double deltaTime) {
        Random random = new Random();
        int probablity =0;
        for(Entity e: entities)
        {
            e.step(deltaTime);
            
            /*if ( e.getPosition().x < 0 )  { e.setPosition(0, e.getPosition().y); }
            if ( e.getPosition().x > windowWidth    )  { e.location.x = windowWidth    ; }
            if ( e.getPosition().y < topPanelHeight)  { e.location.y = 0              ; }
            if ( e.getPosition().y > windowHeight   )  { e.location.y = windowHeight   ; }*/

            if ( e.getStrategy().isFinished() )
            {
                if      ( e instanceof Agent)  { e.setStrategy(generateAgentStepStrategy()); }
                else if ( e instanceof Soldier)  { e.setStrategy(generateSoldierStepStrategy()); }
                else if ( e instanceof Terrorist)  { e.setStrategy(generateTerroristStepStrategy());
                                                probablity = random.nextInt(10);}
                else if ( e instanceof Citizen)  { e.setStrategy(generateCitizenStepStrategy());}
                else if ( e instanceof Untouchable)  { e.setStrategy(generateUntouchableStepStrategy());}
            }
            ///buraya caseler yazilcak
        }
        
        for ( int i = entities.size() - 1 ; i >= 0 ; i-- )
        {
            Entity e = entities.get( i ) ;
            
            if ( e.isIsAlive() == false )
            {
                if      ( e instanceof Agent) { entities.set(i ,createEntity(new AgentFactory())) ; }
                else if ( e instanceof Soldier) { entities.set(i ,createEntity(new SoldierFactory())) ; }
                else if ( e instanceof Terrorist) { entities.set(i ,createEntity(new TerroristFactory())) ;}
                else if ( e instanceof Citizen) { entities.set(i ,createEntity(new CitizenFactory())) ; }
            }
            
            if(e instanceof Terrorist)
            {
                Terrorist t  = (Terrorist) e ;
                double tRadius = t.getRange();
                
                if(e instanceof Equipped)
                {
                    if(probablity == 1)
                    {entities.set( i ,new Exploded((Terrorist)e )); 
                    }
                }
                if(e instanceof Exploded)
                {
                    
                    if(probablity == 1)
                    {
                        ((Exploded) e).killThemAll(this,((Exploded) e));
                        ((Exploded) e).setIsAlive(false);
                        try
                        {
                            SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {

                                try
                                {
                                    InputStream stream = new FileInputStream("./src/images/explosion.wav");
                                    boom = new AudioStream(stream);
                                    AudioPlayer.player.start(boom);
                                }
                                catch(Exception ex){
                                    ex.printStackTrace();
                                }
                            }
                            }); 
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
                    }
                }
                
                for(Entity other: entities)
                {
                    if(other instanceof Camp)
                    {   
                        double distance = t.distanceTo( other ) ;
                        Camp c = (Camp) other;
                        double cRadius = c.getRadius();
                        if(distance <cRadius)//-cRadius)< (tRadius/2.0) )
                        {
                            if ( e instanceof RecentlyDeceived) { entities.set( i ,new BrainWashed((Terrorist)e ));}
                        }
                    }
                    if(other instanceof SupplyStorage)
                    {   
                        double distance = t.distanceTo( other ) ;
                        SupplyStorage s = (SupplyStorage) other;
                        double sRadius = s.getRadius();
                        if(distance < sRadius)//-sRadius)< (tRadius/2.0) )
                        {
                            if ( e instanceof BrainWashed) { entities.set( i ,new Equipped((Terrorist)e ));}
                        }
                    }
                    /*else
                    {
                        if(t instanceof Exploded)
                        {
                            System.out.println("BOOM");
                            System.out.println(tRadius); 
                            
                            if(other instanceof Citizen)
                            {
                                
                                double distance = t.distanceTo( other ) ;
                                Citizen c = (Citizen) other;
                                double cRadius = c.getRadius(); 
                                if((distance-cRadius)< (tRadius/2.0) )
                                {
                                    System.out.println("CITIZEN");
                                    c.setIsAlive(false);
                                    numOfDeadCit++;
                                }
                            }
                            else if(other instanceof Agent)
                            {
                                double distance = t.distanceTo( other ) ;
                                Agent a = (Agent) other;
                                double aRadius = a.getRange(); 
                                if((distance-aRadius)< (tRadius/2.0) )
                                {
                                    System.out.println("AGENT");
                                    a.setIsAlive(false);
                                    numOfDeadAgt++;
                                }
                            }
                            else if(other instanceof Soldier)
                            {
                                double distance = t.distanceTo( other ) ;
                                Soldier s = (Soldier) other;
                                double sRadius = s.getRange(); 
                                if((distance-sRadius)< (tRadius/2.0) )
                                {
                                    s.setIsAlive(false);
                                    numOfDeadSol++;
                                }
                            }
                            t.setIsAlive(false);
                        }
                    }*/
                    
                }
            }
            else if((e instanceof Agent))
            {
                Agent a  = (Agent) e ;
                double aRadius = a.getRange();
                for(Entity other: entities)
                {
                    if(other instanceof Terrorist)
                    {   
                        double distance = a.distanceTo( other ) ;
                        Terrorist tt = (Terrorist) other;
                        double ttRadius = tt.getRange();
                        if(distance < (aRadius) )
                        {
                            a.catchTerrorist(tt);
                            numOfDeadTerr++;
                            setCaughtByAgents(getCaughtByAgents() + 1);
                        }
                    }
                }
            }
            else if((e instanceof Soldier))
            {
                Soldier s = (Soldier) e ;
                double sRadius = s.getRange();
                for(Entity other: entities)
                {
                    if(other instanceof Terrorist)
                    {   
                        double distance = s.distanceTo( other ) ;
                        Terrorist tt = (Terrorist) other;
                        double ttRadius = tt.getRange();
                        if(distance < sRadius )
                        {
                            s.catchTerrorist(tt);
                            numOfDeadTerr++;
                            setCaughtBySoldiers(getCaughtBySoldiers() + 1);
                            try
                        {
                            SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {

                                try
                                {
                                    InputStream stream = new FileInputStream("./src/images/shoot.wav");
                                    shoot = new AudioStream(stream);
                                    AudioPlayer.player.start(shoot);
                                }
                                catch(Exception ex){
                                    ex.printStackTrace();
                                }
                            }
                            }); 
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
                            
                        }
                    }
                }
            }
        }
        numberOfSteps++;

    }

    /**
     * @param e 
     * @return
     */
    /*public void removeEntity(Entity e) {
        // TODO implement here
   
    }*/

    /**
     * @param e 
     * @return
     */
    public void addEntity(Entity e) {
        entities.add(e);

        
    }

    public Color generateRandomColor ()
  {
    return new Color( random.nextInt(256) , random.nextInt(256) , random.nextInt(256) ) ;
  }

  public double generateRandomSpeed ( double minimumSpeed , double maximumSpeed )
  {
    return minimumSpeed + (maximumSpeed - minimumSpeed) * random.nextDouble() ;
  }

  //=================================================================================================================================================

  public Position generateRandomPosition ( int margin )
  {
    int xMin = 0 + margin ;
    int xMax = windowWidth    - margin ;
    int yMin = topPanelHeight + margin ;
    int yMax = windowHeight   - margin ;

    int x = xMin + random.nextInt( xMax - xMin + 1 ) ;
    int y = yMin + random.nextInt( yMax - yMin + 1 ) ;

    return new Position( x , y ) ;
  }
  
  public Position generateRandomDirection ()
  {
    return new Position( random.nextInt( 360 ) ) ;
  }

  
    public int generateRandomNumberOfTurns ( int minimum , int maximum )
  {
    return minimum + random.nextInt( maximum - minimum + 1 ) ;
  }
    public StepStrategy generateAgentStepStrategy ()
  {
    int numberOfTurns = generateRandomNumberOfTurns( 10 , 50) ;

    switch ( random.nextInt( 2 ) )
    {
      case 0  : return new StandStill( this , numberOfTurns ) ;
      case 1  : return new MoveRandom( this , numberOfTurns ) ;
      default : return null                                   ;
    }
  }
    
    public StepStrategy generateSoldierStepStrategy ()
  {
    int numberOfTurns = generateRandomNumberOfTurns( 10 , 50 ) ;

    switch ( random.nextInt( 3 ) )
    {
      case 0  : return new StandStill( this , numberOfTurns ) ;
      case 1  : return new MoveLinear( this , numberOfTurns ) ;
      case 2  : return new Patrol( this , numberOfTurns, generateRandomPosition(10) ) ;
      default : return null;
    }
  }
    
    public StepStrategy generateCitizenStepStrategy ()
  {
    int numberOfTurns = generateRandomNumberOfTurns( 10 ,100 ) ;

    switch ( random.nextInt( 3 ) )
    {
      case 0  : return new StandStill( this , numberOfTurns ) ;
      case 1  : return new MoveLinear( this , numberOfTurns ) ;
      case 2  : return new MoveRandom( this , numberOfTurns ) ;
      //case 3  : return new GotoPosition( this , numberOfTurns,generateRandomPosition(10) ) ;
      default : return null;
    }
  }
    
    public StepStrategy generateTerroristStepStrategy ()
  {
    int numberOfTurns = generateRandomNumberOfTurns( 10 , 100 ) ;
    Position pos = generateRandomPosition(10);

    switch ( random.nextInt( 4 ) )
    {
      case 0  : return new StandStill( this , numberOfTurns ) ;
      case 1  : return new MoveLinear( this , numberOfTurns ) ;
      case 2  : return new MoveRandom( this , numberOfTurns ) ;
      case 3  : return new GotoPosition( this ,numberOfTurns, generateRandomPosition(10) ) ;
      default : return null;
    }
  }
    
     public StepStrategy generateUntouchableStepStrategy ()
  {
    int numberOfTurns = generateRandomNumberOfTurns( 10 , 50 ) ;

    switch ( random.nextInt( 2 ) )
    {
      case 0  : return new StandStill( this , numberOfTurns ) ;
      case 1  : return new MoveLinear( this , numberOfTurns ) ;
      default : return null;
    }
  }

    /**
     * @return the windowWidth
     */
    public int getWindowWidth() {
        return windowWidth;
    }

    /**
     * @param windowWidth the windowWidth to set
     */
    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    /**
     * @return the windowHeight
     */
    public int getWindowHeight() {
        return windowHeight;
    }

    /**
     * @param windowHeight the windowHeight to set
     */
    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    /**
     * @return the topPanelHeight
     */
    public int getTopPanelHeight() {
        return topPanelHeight;
    }

    /**
     * @param topPanelHeight the topPanelHeight to set
     */
    public void setTopPanelHeight(int topPanelHeight) {
        this.topPanelHeight = topPanelHeight;
    }

    /**
     * @return the numOfEntites
     */
    public int getNumOfEntites() {
        return numOfEntites;
    }

    /**
     * @param numOfEntites the numOfEntites to set
     */
    public void setNumOfEntites(int numOfEntites) {
        this.numOfEntites = numOfEntites;
    }

    /**
     * @return the logo
     */
    public BufferedImage getLogo() {
        return logo;
    }

    /**
     * @param logo the logo to set
     */
    public void setLogo(BufferedImage logo) {
        this.logo = logo;
    }

    /**
     * @return the numOfDeadCit
     */
    public int getNumOfDeadCit() {
        return numOfDeadCit;
    }

    /**
     * @param numOfDeadCit the numOfDeadCit to set
     */
    public void setNumOfDeadCit(int numOfDeadCit) {
        this.numOfDeadCit = numOfDeadCit;
    }

    /**
     * @return the numOfDeadSol
     */
    public int getNumOfDeadSol() {
        return numOfDeadSol;
    }

    /**
     * @param numOfDeadSol the numOfDeadSol to set
     */
    public void setNumOfDeadSol(int numOfDeadSol) {
        this.numOfDeadSol = numOfDeadSol;
    }

    /**
     * @return the numOfDeadAgt
     */
    public int getNumOfDeadAgt() {
        return numOfDeadAgt;
    }

    /**
     * @param numOfDeadAgt the numOfDeadAgt to set
     */
    public void setNumOfDeadAgt(int numOfDeadAgt) {
        this.numOfDeadAgt = numOfDeadAgt;
    }

    /**
     * @return the numOfDeadTerr
     */
    public int getNumOfDeadTerr() {
        return numOfDeadTerr;
    }

    /**
     * @param numOfDeadTerr the numOfDeadTerr to set
     */
    public void setNumOfDeadTerr(int numOfDeadTerr) {
        this.numOfDeadTerr = numOfDeadTerr;
    }


    /**
     * @return the background
     */
    public BufferedImage getBackground() {
        return background;
    }

    /**
     * @param background the background to set
     */
    public void setBackground(BufferedImage background) {
        this.background = background;
    }
    /**
     * @return the numberOfSteps
     */
    public int getNumberOfSteps() {
        return numberOfSteps;
    }
    /**
     * @param numberOfSteps the numberOfSteps to set
     */
    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    /**
     * @return the entities
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * @param entities the entities to set
     */
    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    /**
     * @return the caughtByAgents
     */
    public int getCaughtByAgents() {
        return caughtByAgents;
    }

    /**
     * @param caughtByAgents the caughtByAgents to set
     */
    public void setCaughtByAgents(int caughtByAgents) {
        this.caughtByAgents = caughtByAgents;
    }

    /**
     * @return the caughtBySoldiers
     */
    public int getCaughtBySoldiers() {
        return caughtBySoldiers;
    }

    /**
     * @param caughtBySoldiers the caughtBySoldiers to set
     */
    public void setCaughtBySoldiers(int caughtBySoldiers) {
        this.caughtBySoldiers = caughtBySoldiers;
    }

    /**
     * @return the shoot
     */
    public AudioStream getShoot() {
        return shoot;
    }

    /**
     * @param shoot the shoot to set
     */
    public void setShoot(AudioStream shoot) {
        this.shoot = shoot;
    }

    /**
     * @return the boom
     */
    public AudioStream getBoom() {
        return boom;
    }

    /**
     * @param boom the boom to set
     */
    public void setBoom(AudioStream boom) {
        this.boom = boom;
    }

    /**
     * @return the geziParki
     */
    public Entity getGeziParki() {
        return geziParki;
    }

    /**
     * @param geziParki the geziParki to set
     */
    public void setGeziParki(Entity geziParki) {
        this.geziParki = geziParki;
    }



}