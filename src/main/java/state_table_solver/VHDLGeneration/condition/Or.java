package state_table_solver.VHDLGeneration.condition;

public class Or extends BinaryCondition {
    public Or(VHDLCondition left, VHDLCondition right) {
        super(left, right, "or");
    }
}