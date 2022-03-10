package state_table_solver.userInterface;
import java.io.Serializable;

import state_table_solver.AppData;

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
