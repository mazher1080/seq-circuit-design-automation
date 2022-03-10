package state_table_solver.booleanLogic;

/** 
 * <p> BitVar is a class which stores a bit with an associted identifier.
 * @author Jacob Head
 */

public class BitVar extends Bit {

    /**
     * Class constructor. Initializes id and value.
     * @param id Bit id.
     * @param value Bit value.
     */
    public BitVar(String id, BitValue value) {
        super(id, value);
        assert ReservedBitIds.validateId(id) : ("Reserved Bit Id Error.");
    }

    /**
     * Overrides java Object toString method 
     * @see Object
     * 
     * @return String representation of the bitValue.
     */
    @Override
    public String toString() {
        return this.id() + (this.getValue() == BitValue.LOW ? "\'" : "");
    }
    
}
