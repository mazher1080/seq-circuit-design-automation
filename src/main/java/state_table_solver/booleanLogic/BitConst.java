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

    /** */
    @Override
    public void setValue(BitValue v) {
        super.setValue(v);
        this.setId(v.toString());
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
