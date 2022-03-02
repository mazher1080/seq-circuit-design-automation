package sequential_circuit_design_automation;

import java.io.Serializable;

/**
 * <p> AppData is a serializable component used for storing all data associated
 * with our app. It is referenced in app through the data method.
 * 
 * @author Jacob Head
 */

public class AppData implements Serializable {
    private AppUI appUI;
    private AppLogic appLogic;

    public AppData() {
        this.appUI = new AppUI();
        this.appLogic = new AppLogic();
    }

    public AppUI UI() {
        return this.appUI;
    }

    public AppLogic logic() {
        return this.appLogic;
    }
}
