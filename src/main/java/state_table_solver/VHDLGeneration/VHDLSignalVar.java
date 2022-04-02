package state_table_solver.VHDLGeneration;

/**
 * <p> VHDLSignalVar is a class used to create a new VHDLSignal with
 * an id with no other functionality.
 * 
 * @author Jacob Head
 */

public class VHDLSignalVar implements VHDLSignal {

    private String id;

    /**
     * Class constructor. Creates a vhdl signal with a given id.
     * 
     * @param id The id of the vhdl signal.
     */
    public VHDLSignalVar(String id) {
        this.id = id;
    }

    /**
     * Getter for signal id.
     * 
     * @return The current signal id.
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Setter for signal id.
     *
     * @param  The new signal id to set.
     */
    @Override
    public void setId(String s) {
        this.id = s;
    }

}
