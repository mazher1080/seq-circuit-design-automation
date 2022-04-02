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

    /**
     * Class constructor. Creates a file write manager for a given filepath.
     * 
     * @param filePath Filepath of the file to manage.
     */
    public FileWriteManager(String filePath) {
        File file = new File(filePath);
        this.file = file;
    }

    /**
     * Starts writing to the file if file is valid.
     */
    public void startWriting() {
        try {
            PrintWriter pw = new PrintWriter(file);
            this.printWriter = pw;
        } catch(FileNotFoundException fn) {
            System.out.println("ERROR: File not found. See class: FileWriteManager");
        }
    }

    /**
     * Stop writing to the open file.
     */
    public void endWriting() {
        this.printWriter.close();
    }
    
    /** 
     * Writes a new line with string s to the current file.
     * Automatically adjusts the indentation.
     * 
     * @param s The string to add to the new line.
     */
    public void writeLine(String s) {
        this.printWriter.println(getCurrentIndentation() + s);
    }

    /**
     * Adds a blank line to the currently opened file.
     */
    public void n() {
        writeLine("");
    }

    /**
     * Adds an indent to the current indentation.
     */
    public void indent() {
        this.currentIndentation += "\t";
    }

    /**
     * Removes and indent from the current indentation.
     */
    public void unIndent() {
        int length = this.currentIndentation.length();
        if(length >= 1) {
            this.currentIndentation = this.currentIndentation.substring(0, length - 1);
        }
    }
    
    /** 
     * Getter for current indentation.
     * 
     * @return The characters representing the current indentation.
     */
    public String getCurrentIndentation() {
        return currentIndentation;
    }

}
