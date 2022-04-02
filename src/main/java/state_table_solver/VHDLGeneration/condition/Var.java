package state_table_solver.VHDLGeneration.condition;

import state_table_solver.VHDLGeneration.VHDLSignal;

/**
 * <p> Var is a class used to create a VHDLCondtion from a VHDLSignal.
 * 
 * @author Jacob Head
 */

public class Var implements VHDLCondition {

    private VHDLSignal signal;
    
    /**
     * Class constructor. Creates a vhdl condition from a single signal.
     * It is logically equivalent to and equals 1 for vhdl std_logic when used as
     * the only condition. This should most often be used as the base case for
     * recursive condtions.
     * 
     * @param signal The signal to create the condition with.
     */
    public Var(VHDLSignal signal) {
        this.signal = signal;
    }

    /**
     * Method for returning the string representation of a vhdl condition.
     * 
     * @return The string representation of the vhdl condition.
     */
    @Override
    public String toString() {
        return signal.getId();
    }

}
