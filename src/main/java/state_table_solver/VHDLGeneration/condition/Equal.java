package state_table_solver.VHDLGeneration.condition;

public class Equal extends BinaryCondition {
    public Equal(VHDLCondition left, VHDLCondition right) {
        super(left, right, "=");
    }
}