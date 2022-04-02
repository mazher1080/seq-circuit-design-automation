package state_table_solver;

/**
 * <p> App is the top level component which distributes all data associated
 * with our app to all objects. It is responsible for running and killing program.
 * 
 * @author Jacob Head
 */
public class App 
{

    private Controller controller;

    /**
     * Class constructor. Creates a new application.
     * Initializes controller with an event listener.
     */
    public App() {
        this.controller = new Controller();
        this.controller.initApplication();
        new EventListener(this.controller);
    }
    
}
