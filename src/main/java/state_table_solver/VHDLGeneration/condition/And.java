package state_table_solver.VHDLGeneration.condition;

public class And extends BinaryCondition {
    public And(VHDLCondition left, VHDLCondition right) {
        super(left, right, "and");
    }
}