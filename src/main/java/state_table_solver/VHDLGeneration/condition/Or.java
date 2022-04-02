package state_table_solver.VHDLGeneration.condition;

/**
 * <p> Or is a binary VHDL condition which outputs the string representation of the 
 * logical OR of two VHDLCondition objects.
 * 
 * @author Jacob Head
 */

public class Or extends BinaryCondition {

    /**
     * Class constructor. Performs the or operation between two condtions.
     * @see BinaryCondition
     * 
     * @param left the left vhdl condition.
     * @param right the right vhdl condtion.
     */
    public Or(VHDLCondition left, VHDLCondition right) {
        super(left, right, "or");
    }
    
}