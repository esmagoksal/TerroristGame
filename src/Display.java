
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * 
 */
public class Display extends JPanel {

    /**
     * Default constructor
     */
    public Display() {
    }


    public Environment env;

    /**
     * @param env
     */
    public Display(Environment env) {
        // TODO implement here
        this.env = env ;

       setBackground( Color.WHITE ) ;
    }

    /**
     * @return
     */
    @Override
    public Dimension getPreferredSize() {
        // TODO implement here
        return new Dimension( env.getWindowWidth() , env.getWindowHeight()) ;
    }

    /**
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        // TODO implement here
        super.paintComponent( g ) ;

        Graphics2D g2d = (Graphics2D) g ;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
             RenderingHints.VALUE_ANTIALIAS_ON);
        Font originalFont = g.getFont();
        Font newFont = new Font( "Lucida Console" , Font.BOLD , 12 );
        
        g.setFont( newFont ) ;
        g2d.setColor( Color.MAGENTA   ) ;  g2d.drawString(  "Step : " + env.getNumberOfSteps() , env.getWindowWidth()/2  ,20 ) ;
        g2d.setColor( Color.BLACK ) ;  g2d.drawString( "Death Toll : " , 5, 20 ) ;
        g2d.setColor( Color.CYAN) ;  g2d.drawString( "Citizen : " + env.getNumOfDeadCit() , 5, 40 ) ;
        g2d.setColor( Color.GREEN) ;  g2d.drawString( "Soldier : "+ env.getNumOfDeadSol() , 5, 60 ) ;
        g2d.setColor( Color.BLUE) ;  g2d.drawString( "Agent : "+ env.getNumOfDeadAgt(), 225, 40 ) ;
        g2d.setColor( Color.RED) ;  g2d.drawString( "Terrorist : "+ env.getNumOfDeadTerr(), 225, 60 ) ;
        g2d.setColor( Color.RED) ;  g2d.drawString(  "CaughtByAgents : " + env.getCaughtByAgents() , env.getWindowWidth()/2  ,40 ) ;
        g2d.setColor( Color.RED) ;  g2d.drawString(  "CaughtBySoldiers : " + env.getCaughtBySoldiers() , env.getWindowWidth()/2  ,60 ) ;
        
        g2d.setColor(Color.BLACK);
        g2d.drawLine( 0 , env.getTopPanelHeight(), env.getWindowWidth() , env.getTopPanelHeight()) ;
        
        //draw turkishmap
        g2d.drawImage( /* image */ env.getBackground(),
                 /* x              */   0 ,
                 /* y              */ env.getTopPanelHeight() ,
                 /* width          */ env.getWindowWidth(),
                 /* height         */ env.getWindowHeight()- env.getTopPanelHeight(),
                 /* image observer */ null) ;
        
        g2d.drawImage( /* image */ env.getLogo(),
                 /* x              */ env.getWindowWidth()- 70,
                 /* y              */ 5,
                 /* width          */ 60,
                 /* height         */ 60,
                 /* image observer */ null) ;
        
        /*RenderingHints rh = new RenderingHints(
             RenderingHints.KEY_TEXT_ANTIALIASING,
             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);*/
        
        
        g2d.setColor(Color.BLACK);
        g2d.drawLine( 0 , env.getTopPanelHeight(), env.getWindowWidth() , env.getTopPanelHeight()) ;
        //g2d.setColor( Color.MAGENTA   ) ;
        //g2d.drawOval(200, 200, 400, 400);
        
        ArrayList<Entity> entityList = env.getEntities();
        
        synchronized(env)
        {
            for(Entity e: entityList)
            {
                /*if(e.getName().equals("Soldier"))
                {
                    System.out.println(e.getStrategy());
                }*/
                
                e.draw(g2d);
            }
        }
        
        
    }

}