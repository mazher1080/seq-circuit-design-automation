package state_table_solver.VHDLGeneration;

public abstract class VHDLWritableData {
    private FileWriteManager fileWriteManager;

    public VHDLWritableData(FileWriteManager fileWriteManager) {
        this.fileWriteManager = fileWriteManager;
    }

    public void startWriting() {
        fileWriteManager.startWriting();
    }

    public void endWriting() {
        fileWriteManager.endWriting();
    }

    public void writeLine(String s) {
        fileWriteManager.writeLine(s);
    }

    public void n() {
        fileWriteManager.n();
    }

    public void indent() {
        fileWriteManager.indent();
    }

    public void unIndent() {
        fileWriteManager.unIndent();
    }

    public String indentation() {
        return fileWriteManager.getCurrentIndentation();
    }

    public FileWriteManager getFileWriteManager() {
        return this.fileWriteManager;
    }
}
