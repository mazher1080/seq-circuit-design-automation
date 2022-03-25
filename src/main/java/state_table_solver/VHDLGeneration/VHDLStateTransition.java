package state_table_solver.VHDLGeneration;

public class VHDLStateTransition {
    private State currentState;
    private State nextState;
    private VHDLConditional conditional;

    public VHDLStateTransition(State curState, State nextState, VHDLConditional conditional) {
        this.currentState = curState;
        this.nextState = nextState;
        this.conditional = conditional;
    }

    
    
}
