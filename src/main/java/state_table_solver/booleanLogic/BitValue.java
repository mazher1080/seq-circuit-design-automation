package state_table_solver.booleanLogic;

/** 
 * <p> BitValue is an enumerator that represents all values a bit can take on.
 * @author Jacob Head
 */

public enum BitValue {

    HIGH("1"),
    LOW("0"),
    UNKNOWN("-");

    private final String label;

    /**
     * Class Constructor.
     * 
     * @param label Label to represent the bit value.
     */
    private BitValue(String label) {
        this.label = label;
    }

    /**
     * Overrides java Object toString method 
     * @see Object
     * 
     * @return String representation of the bitValue.
     */
    @Override 
    public String toString() {
        return this.label;
    }

    /**
     * Returns the negated value of this bit product
     * 
     * @return The negated value of this bit product
     */
    public BitValue negatedValue() {
        if(this.equals(Bit.HIGH)) {
            return BitValue.LOW;
        } else if(this.equals(BitValue.LOW)) {
            return Bit.HIGH;
        } else {
            return BitValue.UNKNOWN;
        }
    }
    
}
