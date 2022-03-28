package state_table_solver.VHDLGeneration;

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
