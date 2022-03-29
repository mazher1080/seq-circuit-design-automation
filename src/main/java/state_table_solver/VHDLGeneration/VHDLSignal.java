package state_table_solver.VHDLGeneration;

import state_table_solver.VHDLGeneration.condition.VHDLCondition;

/**
 * <p> VHDLSignal is a decorator interface used to allow objects to be
 * represented as a VHDL signal.
 * 
 * @author Jacob Head
 */

public interface VHDLSignal extends VHDLCondition {
    public String getId();
    public void setId(String s);
}
