package state_table_solver;
import java.io.Serializable;

// TODO entire class
public class Controller implements Serializable {
    AppData appData;
    public Controller(AppData appData) {
        this.appData = appData;
    }

    public AppData getAppData() {
        return appData;
    }

    public void setAppData(AppData appData) {
        this.appData = appData;
    }
}
