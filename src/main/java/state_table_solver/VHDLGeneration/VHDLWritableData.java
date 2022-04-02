package state_table_solver.VHDLGeneration;

/**
 * <p> VHDLWritableData is a decorator class used to provide writable functionality to
 * a class using a FileWriteManager.
 * 
 * @author Jacob Head
 */

public abstract class VHDLWritableData {

    private FileWriteManager fileWriteManager;

    /**
     * Class constructor. Initilizes data with a file write manager.
     * 
     * @param fileWriteManager The file write manager.
     */
    protected VHDLWritableData(FileWriteManager fileWriteManager) {
        this.fileWriteManager = fileWriteManager;
    }

    /**
     * Starts writing the current vhdl associated with this data.
     */
    protected void startWriting() {
        fileWriteManager.startWriting();
    }

    /**
     * Stops writing the current vhdl associated with this data.
     */
    protected void endWriting() {
        fileWriteManager.endWriting();
    }

    /**
     * Write a string to the vhdl file associated with this data.
     * 
     * @param s The string to write to the file.
     */
    protected void writeLine(String s) {
        fileWriteManager.writeLine(s);
    }

    /**
     * Writes an empty line to the vhdl file associated with this data.
     */
    protected void n() {
        fileWriteManager.n();
    }

    /**
     * Indents the vhdl file associated with this data.
     */
    protected void indent() {
        fileWriteManager.indent();
    }

    /**
     * Unindents the vhdl file associated with this data.
     */
    protected void unIndent() {
        fileWriteManager.unIndent();
    }

    /**
     * Gets the indentation of the vhdl file associated with this data.
     * 
     * @return The string containing indentation characters for this file.
     */
    protected String indentation() {
        return fileWriteManager.getCurrentIndentation();
    }

    /**
     * Gets the file write manager associated with this data.
     * 
     * @return The file write manager associated with this data.
     */
    protected FileWriteManager getFileWriteManager() {
        return this.fileWriteManager;
    }

}
