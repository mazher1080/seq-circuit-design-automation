package state_table_solver.VHDLGeneration;

import state_table_solver.VHDLGeneration.condition.VHDLCondition;

public interface VHDLSignal extends VHDLCondition {
    public String getId();
    public void setId(String s);
}
