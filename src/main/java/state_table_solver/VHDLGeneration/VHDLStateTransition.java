package state_table_solver.VHDLGeneration;

import java.util.List;
import java.util.ArrayList;

/**
 * <p> VHDLStateTransition is a class used to write the state transition for a current state
 * based on various conditional signals.
 * 
 * @author Jacob Head
 */

public class VHDLStateTransition extends VHDLWritableData {

    private VHDLSignal currentState;
    private List<VHDLConditionalSignal> nextStates;

    /**
     * Class constructor. Creates a vhdl state transition for a current state.
     * 
     * @param curState The current state to be used for the transition.
     * @param indentationManager The file write manager to handle file writing.
     */
    public VHDLStateTransition(VHDLSignal curState, FileWriteManager indentationManager) {
        super(indentationManager);
        this.currentState = curState;
        this.nextStates = new ArrayList<VHDLConditionalSignal>();
    }

    /**
     * Adds a condtional state to the state transition.
     * 
     * @param cState The new conditional state.
     */
    public void addNextConditionalState(VHDLConditionalSignal cState) {
        this.nextStates.add(cState);
    }    

    /**
     * Writes the state transiton to the current vhdl file.
     * 
     * @param currentStateId The id of the signal holding the current state.
     */
    public void writeCaseString(String currentStateId) {
        writeLine("when " + this.currentState.getId() + " =>");
        indent();
        for(VHDLConditionalSignal nextState : nextStates) {
            nextState.writeStateTransitionString(currentStateId);
        }
        unIndent();
    }

}
