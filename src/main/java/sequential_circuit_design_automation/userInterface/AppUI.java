package sequential_circuit_design_automation.userInterface;
import sequential_circuit_design_automation.AppData;

import java.io.Serializable;

import sequential_circuit_design_automation.stateDiagram.StateDiagram;

// TODO entire class
public class AppUI implements Serializable {
    AppData appData;
    public AppUI(AppData appData) {
        this.appData = appData;
    }

    public AppData getAppData() {
        return appData;
    }

    public void setAppData(AppData appData) {
        this.appData = appData;
    }
}
