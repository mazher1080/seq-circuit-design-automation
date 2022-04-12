package state_table_solver.booleanLogic;

/** 
 * <p> BitConst is a class which stores a bit value.
 * @author Jacob Head
 */

public class BitConst extends Bit {

    /**
     * Class constructor. Initializes id and value.
     * @param id Bit id.
     * @param value Bit value.
     */
    public BitConst(BitValue value) {
        super(value.toString(), value);
    }

    /**
     * Setter for id. Overrides Bit.
     * @see Bit
     * 
     * @param id String to set bit id to be.
     */
    @Override
    public void setId(String id) {
        return; // Do not allow overriding of a constants id.
    }

    /** 
     * Sets the value of this bit.
     * Since this is a constant value and id,
     * should always be equal.
    */
    @Override
    public void setValue(BitValue v) {
        super.setValue(v);
        this.id = v.toString();
    }

    /**
     * Overrides java Object toString method 
     * @see Object
     * 
     * @return String representation of the bitValue.
     */
    @Override
    public String toString() {
        return this.getValue().toString();
    }
    
}
