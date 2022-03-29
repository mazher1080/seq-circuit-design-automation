package state_table_solver.VHDLGeneration;

import state_table_solver.VHDLGeneration.condition.VHDLCondition;

/**
 * <p> VHDLIfStatement is a class used to write VHDL if statements based on
 * a VHDLCondition and a block.
 * 
 * @author Jacob Head
 */

public class VHDLIfStatement extends VHDLWritableData {
    VHDLCondition condition;
    String[] blockLines;

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
