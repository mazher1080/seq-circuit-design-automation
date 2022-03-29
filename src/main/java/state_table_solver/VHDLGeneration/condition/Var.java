package state_table_solver.VHDLGeneration.condition;

import state_table_solver.VHDLGeneration.VHDLSignal;

/**
 * <p> Var is a class used to create a VHDLCondtion from a VHDLSignal.
 * 
 * @author Jacob Head
 */

public class Var implements VHDLCondition {
    private VHDLSignal signal;
    
    public Var(VHDLSignal signal) {
        this.signal = signal;
    }

    @Override
    public String toString() {
        return signal.getId();
    }
}
