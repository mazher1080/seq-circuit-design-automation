package state_table_solver.VHDLGeneration;

import state_table_solver.VHDLGeneration.condition.VHDLCondition;

/**
 * <p> VHDLSignal is a decorator interface used to allow objects to be
 * represented as a VHDL signal.
 * 
 * @author Jacob Head
 */

public interface VHDLSignal extends VHDLCondition {

    /**
     * Gets the id for the given vhdl signal.
     * 
     * @return The signal id.
     */
    public String getId();

    /**
     * Sets the id for a given vhdl signal.
     * 
     * @param s The new signal id.
     */
    public void setId(String s);

}
