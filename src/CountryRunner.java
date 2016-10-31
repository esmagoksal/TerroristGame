
import java.util.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 */
public class CountryRunner {


    /**
     * Default constructor
     */
    public CountryRunner() {
    }

    /**
     *
     */
    private JFrame frame;
    private Display display;
    private Environment environment;

    /**
     * @param windowName
     * @param backgroundFname
     * @param logoFname
     * @param windowWidth
     * @param windowHeight
     * @param panelHeight
     * @param numOfEntities
     */
    /*public CountryRunner(String windowName, String backgroundFname, String logoFname, int windowWidth, int windowHeight, int panelHeight, int numOfEntities) {
        // TODO implement here
        frame = new JFrame(windowName);
        environment = new Environment(windowWidth, windowHeight, panelHeight, numOfEntities, logoFname, backgroundFname);
        display = new Display(environment);

        frame.add(display);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }*/

    /**
     * @param args
     * @return
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO implement here
        String title = "The New Country: Analysis of Terrorist Attacks";
        String backgroundFname = "./src/images/turkishmap.jpg";
        String logoFname = "./src/images/logo.png";
        int windowWidth = 1296;
        int windowHeight = 600;
        int panelHeight = 70;
        int numOfEntities = 150;

        if (args.length >= 6) {
            numOfEntities = Integer.parseInt(args[5]);
        }
        if (args.length >= 5) {
            panelHeight = Integer.parseInt(args[4]);
        }
        if (args.length >= 4) {
            windowHeight = Integer.parseInt(args[3]);
        }
        if (args.length >= 3) {
            windowWidth = Integer.parseInt(args[2]);
        }
        if (args.length >= 2) {
            logoFname = args[1];
        }
        if (args.length >= 1) {
            backgroundFname = args[0];
        }
    

        CountryRunner runner = new CountryRunner();
        //CountryRunner runner = new CountryRunner(title, backgroundFname, logoFname, windowWidth, windowHeight, panelHeight, numOfEntities);

        runner.environment = new Environment(windowWidth, windowHeight, panelHeight, numOfEntities, logoFname, backgroundFname);
        runner.frame = new JFrame(title);
        runner.display = new Display(runner.environment);
        runner.frame.setSize(runner.display.getPreferredSize());
        runner.frame.add(runner.display);
        runner.frame.setResizable(false);
        runner.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        runner.frame.pack();
        runner.frame.setVisible(true);
        
        
        while ( true )
        {
            try{
                Thread.sleep(50);
            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            runner.environment.stepAll(0.5) ;
            runner.display.repaint() ;
        

            //Thread.sleep( 50 ) ;
        
        }
    
        
    }
}
