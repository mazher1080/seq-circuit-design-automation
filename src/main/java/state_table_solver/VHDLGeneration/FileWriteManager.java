package state_table_solver.VHDLGeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * <p> FileWriteManager is a class used to handle file writing from multiple
 * objects at once.
 * 
 * @author Jacob Head
 */

public class FileWriteManager {
    private String currentIndentation = "";
    private File file;
    private PrintWriter printWriter;

    public FileWriteManager(String filePath) {
        File file = new File(filePath);
        this.file = file;
    }

    public void startWriting() {
        try {
            PrintWriter pw = new PrintWriter(file);
            this.printWriter = pw;
        } catch(FileNotFoundException fn) {
            System.out.println("ERROR: File not found. See class: FileWriteManager");
        }
    }

    public void endWriting() {
        this.printWriter.close();
    }

    public void writeLine(String s) {
        this.printWriter.println(getCurrentIndentation() + s);
    }

    public void n() {
        writeLine("");
    }

    public void indent() {
        this.currentIndentation += "\t";
    }

    public void unIndent() {
        int length = this.currentIndentation.length();
        if(length >= 1) {
            this.currentIndentation = this.currentIndentation.substring(0, length - 1);
        }
    }

    public String getCurrentIndentation() {
        return currentIndentation;
    }
}
