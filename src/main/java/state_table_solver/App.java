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
    private EventListener eventListener;

    public App() {
        this.controller = new Controller();
        this.controller.initApplication();
        this.eventListener = new EventListener(this.controller);
    }
    
    // TODO method
    public void kill() {

    }
}
