package state_table_solver.VHDLGeneration;

/**
 * <p> VHDLWritableData is a decorator class used to provide writable functionality to
 * a class using a FileWriteManager.
 * 
 * @author Jacob Head
 */

public abstract class VHDLWritableData {
    private FileWriteManager fileWriteManager;

    protected VHDLWritableData(FileWriteManager fileWriteManager) {
        this.fileWriteManager = fileWriteManager;
    }

    protected void startWriting() {
        fileWriteManager.startWriting();
    }

    protected void endWriting() {
        fileWriteManager.endWriting();
    }

    protected void writeLine(String s) {
        fileWriteManager.writeLine(s);
    }

    protected void n() {
        fileWriteManager.n();
    }

    protected void indent() {
        fileWriteManager.indent();
    }

    protected void unIndent() {
        fileWriteManager.unIndent();
    }

    protected String indentation() {
        return fileWriteManager.getCurrentIndentation();
    }

    protected FileWriteManager getFileWriteManager() {
        return this.fileWriteManager;
    }
}
