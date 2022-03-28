package state_table_solver.VHDLGeneration;

import state_table_solver.VHDLGeneration.condition.VHDLCondition;

public class VHDLIfStatement extends VHDLWritableData {
    VHDLCondition condition;
    String[] blockLines;
    FileWriteManager writeManager;

    public VHDLIfStatement(VHDLCondition condition, String[] blockLines, FileWriteManager writeManager) {
        super(writeManager);
        this.condition = condition;
        this.blockLines = blockLines;
    }

    public void writeIfStatementString() {
        writeLine("if " + this.condition.toString() + " then");
        indent();
        for(String line : this.blockLines) {
            writeLine(line);
        }
        unIndent();
        writeLine("end if;");
    }
}
