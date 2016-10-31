
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * 
 */
public abstract class Terrorist extends Entity {

    /**
     * Default constructor
     */
    public Terrorist() {
    }

    /**
     * 
     */
    //private boolean isAlive;
    private double range;
    private BufferedImage img;

    /**
     * @param name 
     * @param s 
     * @param pos 
     * @param dir 
     * @param str
     */
    public Terrorist(String name, double s, Position pos,Position dir, StepStrategy str) {
        super(name, s, pos, dir, str);
        
        range = 4.5;
        //isAlive = true;
        this.setIsAlive(true);
        //bufferedimage
        //killedpeople??
    }

    public void killThemAll(Environment env, Terrorist t) {
        
       //System.out.println("BBOOOMM!!");
       ArrayList<Entity> en = env.getEntities();
       double tRadius = t.getRange();
       //System.out.println(tRadius); 
       //Entity eG = env.getGeziParki();
       //GeziParki geziParki = (GeziParki) eG;
       double gRadius;
       double distanceG;
       for(Entity o: en)
       {
           if(o instanceof GeziParki)
           {
               GeziParki geziParki = (GeziParki) o;
               gRadius = geziParki.getRadius();
               distanceG = t.distanceTo(geziParki);
               for(Entity other: en)
               {
                   if((distanceG-gRadius) < (tRadius/3.0))
                   {
                       if(other instanceof Citizen)
                        {
                            double distance = t.distanceTo( other ) ;
                            double distanceGP = geziParki.distanceTo(other);
                            Citizen c = (Citizen) other;
                            double cRadius = c.getRadius();
                            if((distanceGP - cRadius) > (gRadius/2.0))
                            {
                                if((distance-cRadius)< (tRadius/3.0) )
                                {
                                c.setIsAlive(false);
                                env.setNumOfDeadCit(env.getNumOfDeadCit()+1);
                                }
                            }
                        }
                        if(other instanceof Soldier)
                        {
                            double distance = t.distanceTo( other ) ;
                            double distanceGP = geziParki.distanceTo(other);
                            Soldier s = (Soldier) other;
                            double sRadius = s.getRange(); 
                            //if((distanceGP - sRadius) > (gRadius/2.0))
                            //{
                                if((distance-sRadius)< (tRadius/3.0) )
                                {
                                    s.setIsAlive(false);
                                    env.setNumOfDeadCit(env.getNumOfDeadCit()+1);
                    
                                }
                            //}
                        }
                        if(other instanceof Agent)
                        {
                            double distance = t.distanceTo( other ) ;
                            double distanceGP = geziParki.distanceTo(other);
                            Agent a = (Agent) other;
                            double aRadius = a.getRange(); 
                            //if((distanceGP - aRadius) > (gRadius/2.0))
                            //{
                                if((distance-aRadius)< (tRadius/3.0) )
                                {
                                    a.setIsAlive(false);
                                    env.setNumOfDeadCit(env.getNumOfDeadCit()+1);
                    
                                }
                            //}
                        }
                        if(other instanceof Terrorist)
                        {
                            if(!other.equals(t))
                            {
                                double distance = t.distanceTo( other ) ;
                                Terrorist tt = (Terrorist) other;
                                double ttRadius = t.getRange(); 
                                if((distance-ttRadius)< (tRadius/3.0) )
                                {
                                    tt.setIsAlive(false);
                                    env.setNumOfDeadTerr(env.getNumOfDeadTerr()+1);
                                    System.out.println("Terrorist");
                                }
                            }
                        }
                    }
                    else
                   {
                        if(other instanceof Citizen)
                        {
                            double distance = t.distanceTo( other ) ;
                            Citizen c = (Citizen) other;
                            double cRadius = c.getRadius(); 
                            if((distance-cRadius)< (tRadius/3.0) )
                            {
                                c.setIsAlive(false);
                                env.setNumOfDeadCit(env.getNumOfDeadCit()+1);
                            }
                        }
                        if(other instanceof Soldier)
                        {
                            double distance = t.distanceTo( other ) ;
                            Soldier s = (Soldier) other;
                            double sRadius = s.getRange(); 
                    //System.out.println("RRAAA");
                            if((distance-sRadius)< (tRadius/3.0) )
                            {
                                s.setIsAlive(false);
                                env.setNumOfDeadSol(env.getNumOfDeadSol()+1);
                                System.out.println("Soldier");
                            }
                        }
                        if(other instanceof Agent)
                        {
                            double distance = t.distanceTo( other ) ;
                            Agent a = (Agent) other;
                            double aRadius = a.getRange(); 
                            if((distance-aRadius)< (tRadius/3.0) )
                            {
                            a.setIsAlive(false);
                            env.setNumOfDeadAgt(env.getNumOfDeadAgt()+1);
                            System.out.println("Agent");     
                            }
                        }
           
                        if(other instanceof Terrorist)
                        {
                            if(!other.equals(t))
                            {
                                double distance = t.distanceTo( other ) ;
                                Terrorist tt = (Terrorist) other;
                                double ttRadius = t.getRange(); 
                                if((distance-ttRadius)< (tRadius/3.0) )
                                {
                                    tt.setIsAlive(false);
                                    env.setNumOfDeadTerr(env.getNumOfDeadTerr()+1);
                                    System.out.println("Terrorist");
                                }
                            }
                        }
                        
                   }
                }
            }
       }
       t.setIsAlive(false);    
               
      /* if((distanceG-gRadius) < (tRadius/3.0))
       {
           for(Entity other: en)
           {
                if(other instanceof Citizen)
                {
                    double distance = t.distanceTo( other ) ;
                    double distanceGP = geziParki.distanceTo(other);
                    Citizen c = (Citizen) other;
                    double cRadius = c.getRadius(); 
                    if((distanceGP - cRadius) > (gRadius/2.0))
                    {
                        if((distance-cRadius)< (tRadius/3.0) )
                        {
                            c.setIsAlive(false);
                            env.setNumOfDeadCit(env.getNumOfDeadCit()+1);
                    
                        }
                    }
                }
                if(other instanceof Soldier)
                {
                    double distance = t.distanceTo( other ) ;
                    double distanceGP = geziParki.distanceTo(other);
                    Soldier s = (Soldier) other;
                    double sRadius = s.getRange(); 
                    if((distanceGP - sRadius) > (gRadius/2.0))
                    {
                        if((distance-sRadius)< (tRadius/3.0) )
                        {
                            s.setIsAlive(false);
                            env.setNumOfDeadCit(env.getNumOfDeadCit()+1);
                    
                        }
                    }
                }
                if(other instanceof Agent)
                {
                    double distance = t.distanceTo( other ) ;
                    double distanceGP = geziParki.distanceTo(other);
                    Agent a = (Agent) other;
                    double aRadius = a.getRange(); 
                    if((distanceGP - aRadius) > (gRadius/2.0))
                    {
                        if((distance-aRadius)< (tRadius/3.0) )
                        {
                            a.setIsAlive(false);
                            env.setNumOfDeadCit(env.getNumOfDeadCit()+1);
                    
                        }
                    }
                }
           
                if(other instanceof Terrorist)
                {
                    if(!other.equals(t))
                    {
                        double distance = t.distanceTo( other ) ;
                        double distanceGP = geziParki.distanceTo(other);
                        Terrorist tt = (Terrorist) other;
                        double ttRadius = tt.getRange(); 
                        if((distanceGP - ttRadius) > (gRadius/2.0))
                        {
                            if((distance-ttRadius)< (tRadius/3.0) )
                            {
                                tt.setIsAlive(false);
                                env.setNumOfDeadCit(env.getNumOfDeadCit()+1);
                    
                            }
                        }
                    }
                }
                t.setIsAlive(false);
            }
       }
       else
       {
           for(Entity other: en)
           {
                if(other instanceof Citizen)
                {
                    double distance = t.distanceTo( other ) ;
                    Citizen c = (Citizen) other;
                    double cRadius = c.getRadius(); 
                    if((distance-cRadius)< (tRadius/3.0) )
                    {
                        c.setIsAlive(false);
                        env.setNumOfDeadCit(env.getNumOfDeadCit()+1);
                    
                    }
                }
                if(other instanceof Soldier)
                {
                    double distance = t.distanceTo( other ) ;
                    Soldier s = (Soldier) other;
                    double sRadius = s.getRange(); 
                    //System.out.println("RRAAA");
                    if((distance-sRadius)< (tRadius/3.0) )
                    {
                        s.setIsAlive(false);
                        env.setNumOfDeadSol(env.getNumOfDeadSol()+1);
                        System.out.println("Soldier");
                    }
                }
                if(other instanceof Agent)
                {
                    double distance = t.distanceTo( other ) ;
                    Agent a = (Agent) other;
                    double aRadius = a.getRange(); 
                    if((distance-aRadius)< (tRadius/3.0) )
                    {
                        a.setIsAlive(false);
                        env.setNumOfDeadAgt(env.getNumOfDeadAgt()+1);
                        System.out.println("Agent");
                    
                    }
                }
           
                if(other instanceof Terrorist)
                {
                    if(!other.equals(t))
                    {
                        double distance = t.distanceTo( other ) ;
                        Terrorist tt = (Terrorist) other;
                        double ttRadius = t.getRange(); 
                        if((distance-ttRadius)< (tRadius/3.0) )
                        {
                            t.setIsAlive(false);
                            env.setNumOfDeadTerr(env.getNumOfDeadTerr()+1);
                            System.out.println("Terrorist");
                    
                        }
                    }
                }
                t.setIsAlive(false);
            }
       }
       */
        
    }
    /**
     * @param g2d 
     * @return
     */
    @Override
    public abstract void draw(Graphics2D g2d); 


    /**
     * @return the range
     */
    public double getRange() {
        return range;
    }

    /**
     * @param range the range to set
     */
    public void setRange(double range) {
        this.range = range;
    }

    /**
     * @return the img
     */
    public BufferedImage getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(BufferedImage img) {
        this.img = img;
    }

    /**
     * @return the isAlive
     */
   /* public boolean isIsAlive() {
        return isAlive;
    }*/

    /**
     * @param isAlive the isAlive to set
     */
    /*public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }*/

}