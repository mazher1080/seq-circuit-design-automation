package state_table_solver.VHDLGeneration;

import java.util.List;
import java.util.ArrayList;

public class VHDLStateTransition extends VHDLWritableData {
    private VHDLSignal currentState;
    private List<VHDLConditionalSignal> nextStates;

    public VHDLStateTransition(VHDLSignal curState, FileWriteManager indentationManager) {
        super(indentationManager);
        this.currentState = curState;
        this.nextStates = new ArrayList<VHDLConditionalSignal>();
    }

    public void addNextConditionalState(VHDLConditionalSignal cState) {
        this.nextStates.add(cState);
    }    

    public void writeCaseString(String currentStateId) {
        writeLine("when " + this.currentState.getId() + " =>");
        indent();
        for(VHDLConditionalSignal nextState : nextStates) {
            nextState.writeStateTransitionString(currentStateId);
        }
        unIndent();
    }
}
