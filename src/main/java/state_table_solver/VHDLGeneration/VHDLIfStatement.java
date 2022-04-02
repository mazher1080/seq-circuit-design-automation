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

    /**
     * Class constructor. Creates a vhdl if statement string based on a vhdl condition and a block.
     * Does this with a file write manager.
     * 
     * @param condition The if statement vhdl condition.
     * @param blockLines The lines inside the vhdl if statement block.
     * @param writeManager The file write manager.
     */
    public VHDLIfStatement(VHDLCondition condition, String[] blockLines, FileWriteManager writeManager) {
        super(writeManager);
        this.condition = condition;
        this.blockLines = blockLines;
    }

    /**
     * Writes the if statement string using the file write manager.
     */
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
