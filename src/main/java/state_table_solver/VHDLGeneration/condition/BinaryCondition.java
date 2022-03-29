package state_table_solver.VHDLGeneration.condition;

/**
 * <p> BinaryCondition is a VHDLCondition which is computed based on two
 * other VHDLCondititions.
 * 
 * @author Jacob Head
 * 
 */

public abstract class BinaryCondition implements VHDLCondition {
    public VHDLCondition leftCondition;
    public VHDLCondition rightCondition;
    private String operationSymbol;

    /** Class constructor.
     * 
     * @param leftCondition
     * @param rightCondition
     * @param operationSymbol string used to represent the operation
     * 
     */
    public BinaryCondition(VHDLCondition leftCondition, VHDLCondition rightCondition, String operationSymbol) {
        this.leftCondition = leftCondition;
        this.rightCondition = rightCondition;
        this.operationSymbol = operationSymbol;
    }

    /** Overrides toString method declaration defined in VHDLCondtion
     * 
     * @see VHDLCondition
     * @return string representing this object
     * 
     */
    @Override 
    public String toString() {
        return (
            "(" + this.leftCondition.toString() +
            " " + this.operationSymbol + " " +
            this.rightCondition.toString() + ")"
        );
    }
}