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
     * Setter for id. Overrides Bit.
     * @see Bit
     * 
     * @param id String to set bit id to be.
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Overrides java Object toString method 
     * @see Object
     * 
     * @return String representation of the bitValue.
     */
    @Override
    public String toString() {
        return this.getId() + (this.getValue() == BitValue.LOW ? "\'" : "");
    }
    
}
