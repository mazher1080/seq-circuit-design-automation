package state_table_solver.VHDLGeneration;

import java.io.File;

import state_table_solver.AppData;
import state_table_solver.stateTable.StateTable;
import state_table_solver.VHDLGeneration.condition.*;
import state_table_solver.booleanLogic.BitValue;

public class VHDLFileWriter extends VHDLWritableData {
    private static final String FILE_EXTENSION = ".vhd";
    private String entityName;
    private String clockEdge = "1";
    private AppData appData;

    public VHDLFileWriter(String filePath, AppData appData) {
        super(new FileWriteManager(filePath + FILE_EXTENSION));
        this.appData = appData;
        File f = new File(filePath + FILE_EXTENSION);
        this.entityName = f.getName();
    }

    private VHDLSignal getInitialState() {
        return this.appData.getStateTable().getCurrentStateCol().get(0);
    }

    private String getStateListString() {
        String output = "";
        StateTable stTable = this.appData.getStateTable();
        for(int i = 0; i < stTable.getCurrentStateCol().size(); i++) {
            if(i != 0) {
                output += ", ";
            }
            output += stTable.getCurrentStateCol().get(i);
        }
        return output;
    }

    private void writeStateTransitionString() {
        StateTable stTable = this.appData.getStateTable();
        VHDLCondition highVal = new Var(new VHDLSignalVar("'1'"));
        VHDLCondition lowVal = new Var(new VHDLSignalVar("'0'"));

        for(int i = 0; i < stTable.getCurrentStateCol().size(); i++ ) {
            VHDLSignal stateSig = stTable.getCurrentStateCol().get(i);
            VHDLStateTransition stateTransition = new VHDLStateTransition(stateSig, getFileWriteManager());

            VHDLSignal highInputState = stTable.getNextHighStateCol().get(i);
            VHDLCondition highInputCondition = new Equal(stTable.HIGH_INPUT, highVal);

            VHDLSignal lowInputState = stTable.getNextLowStateCol().get(i);
            VHDLCondition lowInputCondition = new Equal(stTable.HIGH_INPUT, lowVal);

            VHDLConditionalSignal highConditionalSignal = 
                new VHDLConditionalSignal(highInputState, highInputCondition, getFileWriteManager());
            VHDLConditionalSignal lowConditionalSignal = 
                new VHDLConditionalSignal(lowInputState, lowInputCondition, getFileWriteManager());

            stateTransition.addNextConditionalState(highConditionalSignal);
            stateTransition.addNextConditionalState(lowConditionalSignal);
            stateTransition.writeCaseString("current_state");
        }
    }

    private void writeOutputGeneration() {
        StateTable stTable = this.appData.getStateTable();
        VHDLCondition highVal = new Var(new VHDLSignalVar("'1'"));
        VHDLCondition lowVal = new Var(new VHDLSignalVar("'0'"));
        VHDLCondition currentState = new Var(new VHDLSignalVar("current_state"));

        VHDLCondition outputLowInputCondition = null;
        VHDLCondition outputHighInputCondition = null;
        VHDLCondition outputBothInputCondition = null;

        for(int i = 0; i < stTable.getCurrentStateCol().size(); i++ ) {
            VHDLSignal stateSig = stTable.getCurrentStateCol().get(i);
            VHDLCondition stateCondition = new Equal(currentState, stateSig);
            BitValue nextLowOutput = stTable.getNextLowOutputCol().get(i).getValue();
            BitValue nextHighOutput = stTable.getNextHighOutputCol().get(i).getValue();

            if (nextHighOutput == BitValue.HIGH && nextLowOutput == BitValue.HIGH) {
                outputBothInputCondition = outputBothInputCondition != null 
                    ? new Or(outputBothInputCondition, stateCondition)
                    : stateCondition;
            } else if(nextHighOutput == BitValue.HIGH) {
                outputHighInputCondition = outputHighInputCondition != null 
                    ? new Or(outputHighInputCondition, stateCondition)
                    : stateCondition;
            } else if(nextLowOutput == BitValue.HIGH) {
                outputLowInputCondition = outputLowInputCondition != null
                    ? new Or(outputLowInputCondition, stateCondition)
                    : stateCondition;
            }
        }
        VHDLCondition highInput = new Equal(stTable.HIGH_INPUT, highVal);
        VHDLCondition lowInput = new Equal(stTable.HIGH_INPUT, lowVal);

        outputLowInputCondition = outputLowInputCondition != null
            ? new And(lowInput, outputLowInputCondition)
            : null;
        
        outputHighInputCondition = outputHighInputCondition != null 
            ? new And(highInput, outputHighInputCondition)
            : null;
        
        VHDLCondition outputCondition = null;
        outputCondition = outputBothInputCondition != null 
            ? outputBothInputCondition
            : null;

        if(outputCondition != null) {
            outputCondition = outputLowInputCondition != null
                ? new Or(outputCondition, outputLowInputCondition)
                : outputCondition;
        } else {
            outputCondition = outputLowInputCondition != null
                ? outputLowInputCondition
                : null;
        }

        if(outputCondition != null) {
            outputCondition = outputHighInputCondition != null
                ? new Or(outputCondition, outputHighInputCondition)
                : outputCondition;
        } else {
            outputCondition = outputHighInputCondition != null
                ? outputHighInputCondition
                : null;
        }

        VHDLConditionalSignal conditionalOutput = new VHDLConditionalSignal(
            stTable.HIGH_OUTPUT, 
            outputCondition, 
            getFileWriteManager()
        );
        conditionalOutput.writeConditionalAssignmentString();
    }

    private void writeImports() {
        writeLine("library ieee");
        writeLine("use ieee.std_logic_1164.all;");
    }

    private void writeEntity() {
        writeLine("entity " + this.entityName + " is");
        indent();
        writeLine("port(clk, " + this.appData.getStateTable().HIGH_INPUT.getId() + " : in std_logic;");
        indent();
        writeLine(this.appData.getStateTable().HIGH_OUTPUT.getId() + " : out std_logic);");
        unIndent(); unIndent();
        writeLine("end " + this.entityName + ";");
    }

    private void writeArchitecture() {
        writeLine("architecture datapath of " + this.entityName + " is");
        indent();
        writeLine("");
        writeLine("type state_type is (" + getStateListString() + ");");
        writeLine("signal current_state, next_state : state_type := " + getInitialState().getId() + ";");
        writeLine("");
        unIndent();
        writeLine("begin");
        indent();
        n();
        writeDataFlowLogic();
        n();
        unIndent();
        writeLine("end datapath;");
    }

    private void writeDataFlowLogic() {
        writeLine("process(clk)");
        writeLine("begin");
        indent();
        writeStateTransition();
        n();
        writeNextStateGeneration();
        n();
        writeOutputGeneration();
        unIndent();
    }

    private void writeStateTransition() {
        writeLine("if (clk'event and clk = '" + this.clockEdge + "') then");
        indent();
        writeLine("current_state <= next_state;");
        unIndent();
        writeLine("end if;");
        unIndent();
        writeLine("end process;");
    }

    private void writeNextStateGeneration() {
        writeLine("process(current_state, " + this.appData.getStateTable().HIGH_INPUT.getId()  + ")");
        writeLine("begin");
        indent();
        writeLine("case current_state is");
        indent();
        writeStateTransitionString();
        unIndent();
        writeLine("end case;");
        unIndent();
        writeLine("end process;");
    }

    public void writeFile() {
        startWriting();
        writeImports();
        n();
        writeEntity();
        n();
        writeArchitecture();
        endWriting();
    }
}