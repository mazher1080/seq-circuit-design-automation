package state_table_solver.VHDLGeneration.condition;

/**
 * <p> And is a binary VHDL condition which outputs the string representation of the 
 * logical and of two VHDLCondition objects.
 * 
 * @author Jacob Head
 */

public class And extends BinaryCondition {
    public And(VHDLCondition left, VHDLCondition right) {
        super(left, right, "and");
    }
}