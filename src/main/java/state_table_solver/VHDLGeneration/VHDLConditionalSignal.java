package state_table_solver.VHDLGeneration;

import state_table_solver.VHDLGeneration.condition.VHDLCondition;

/**
 * <p> VHDLConditionalSignal is a class used to write conditional assignments and state transtions
 * based on a VHDLsignal and a VHDLcondition.
 * 
 * @author Jacob Head
 */

public class VHDLConditionalSignal extends VHDLWritableData {
    private VHDLSignal signal;
    private VHDLCondition condition;

    VHDLConditionalSignal(VHDLSignal s, VHDLCondition conditional, FileWriteManager indentationManager) {
        super(indentationManager);
        this.condition = conditional;
        this.signal = s;
    }

    public void setCondition(VHDLCondition condition) {
        this.condition = condition;
    }

    public VHDLCondition getCondition() {
        return condition;
    }

    public void setSignal(VHDLSignal signal) {
        this.signal = signal;
    }

    public VHDLSignal getSignal() {
        return this.signal;
    }

    public void writeConditionalAssignmentString() {
        String output = "";
        if(this.condition == null) {
            output += getSignal().getId() + " <= '0';";
        } else {
            output += getSignal().getId() + " <= '1' when ";
            output += getCondition().toString() + " else '0';";
        }
        writeLine(output);
    }

    public void writeStateTransitionString(String currentStateId) {
        String assignmentString = currentStateId + " <= " + getSignal().getId() + ";";
        String[] ifBlock = { assignmentString };

        VHDLIfStatement ifStatement = new VHDLIfStatement(getCondition(), ifBlock, getFileWriteManager());
        ifStatement.writeIfStatementString();
    }
}
