package state_table_solver.VHDLGeneration.condition;

/**
 * <p> Equal is a binary VHDL condition which outputs the string representation of the 
 * logical equal of two VHDLCondition objects.
 * 
 * @author Jacob Head
 */

public class Equal extends BinaryCondition {

    /**
     * Class constructor. Performs the equal operation between two condtions.
     * @see BinaryCondition
     * 
     * @param left the left vhdl condition.
     * @param right the right vhdl condtion.
     */
    public Equal(VHDLCondition left, VHDLCondition right) {
        super(left, right, "=");
    }
    
}