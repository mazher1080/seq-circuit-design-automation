package state_table_solver.VHDLGeneration;

/**
 * <p> VHDLSignalVar is a class used to create a new VHDLSignal with
 * an id.
 * 
 * @author Jacob Head
 */

public class VHDLSignalVar implements VHDLSignal {
    String id;

    public VHDLSignalVar(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String s) {
        this.id = s;
    }


}
