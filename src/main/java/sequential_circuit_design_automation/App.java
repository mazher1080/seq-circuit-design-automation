package sequential_circuit_design_automation;
import sequential_circuit_design_automation.userInterface.AppUI;

/**
 * <p> App is the top level component which distributes all data associated
 * with our app to all objects. It is responsible for running and killing program.
 * 
 * @author Jacob Head
 */
public class App 
{
    private AppData appData;
    private AppUI appUI = new AppUI(appData);

    /**
     * Class contructor. Initializes appData.
     */
    public App() {
        this.appData = new AppData();
        this.appUI = new AppUI(this.appData);
    }
    
    // TODO method
    public void kill() {

    }

    /** 
     * Retrieves the data associated with the current application
     * 
     * @return app data
     */
    public AppData getData() {
        return this.appData;
    }
}
