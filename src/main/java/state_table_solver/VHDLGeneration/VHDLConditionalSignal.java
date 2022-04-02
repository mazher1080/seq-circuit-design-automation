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

    /**
     * Class constructor. Creates a vhdl condtional signal with a signal and a condition. Uses a file
     * write manager.
     * 
     * @param s The vhdl signal to contain the vhdl condtion.
     * @param conditional The condition for the condtional signal.
     * @param indentationManager The file write manager
     */
    VHDLConditionalSignal(VHDLSignal s, VHDLCondition conditional, FileWriteManager indentationManager) {
        super(indentationManager);
        this.condition = conditional;
        this.signal = s;
    }


    /**
     * Setter for condtion.
     * @param VHDLCondition The new condtion to set.
     */
    public void setCondition(VHDLCondition condition) {
        this.condition = condition;
    }

    /**
     * Getter for condition.
     * @return The current condition.
     */
    public VHDLCondition getCondition() {
        return condition;
    }

    /**
     * Setter for signal.
     * @param VHDLSignal The new signal to set.
     */
    public void setSignal(VHDLSignal signal) {
        this.signal = signal;
    }

    /**
     * Getter for signal.
     * @return The current signal.
     */
    public VHDLSignal getSignal() {
        return this.signal;
    }

    /**
     * Writes the current vhdl conditional signal to a file
     * using FileWriteManager.
     */
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

    /** 
     * Writes the a vhdl state transition for the specified state
     * using FileWriteManager.
     * 
     * @param currentStateId
     */
    public void writeStateTransitionString(String currentStateId) {
        String assignmentString = currentStateId + " <= " + getSignal().getId() + ";";
        String[] ifBlock = { assignmentString };

        VHDLIfStatement ifStatement = new VHDLIfStatement(getCondition(), ifBlock, getFileWriteManager());
        ifStatement.writeIfStatementString();
    }
    
}
