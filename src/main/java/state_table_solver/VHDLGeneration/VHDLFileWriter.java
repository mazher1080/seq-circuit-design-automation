package state_table_solver.VHDLGeneration;

import java.io.*;

import state_table_solver.AppData;

public class VHDLFileWriter {
    public String entityName = "testEntity";
    public String inputName = "x";
    public String outputName = "y";
    public String stateTypes = "Q1, Q2, Q3, Q4";
    public String initialState = "Q1";
    public String clockEdge = "1";

    private AppData appData;
    private File file;
    private PrintWriter printWriter;
    private String currentIndentation = "";

    public VHDLFileWriter(String filePath, AppData appData) {
        this.appData = appData;
        File file = new File(filePath);
        this.file = file;
    }

    public void startWriting() {
        try {
            PrintWriter pw = new PrintWriter(file);
            this.printWriter = pw;
        } catch(FileNotFoundException fn) {
            System.out.println("ERR");
        }
    }

    public void endWriting() {
        this.printWriter.close();
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

    public void writeLine(String s) {
        this.printWriter.println(this.currentIndentation + s);
    }

    private void n() {
        writeLine("");
    }

    private void writeImports() {
        writeLine("library ieee");
        writeLine("use ieee.std_logic_1164.all;");
    }

    private void writeEntity() {
        writeLine("entity " + this.entityName + " is");
        indent();
        writeLine("port(clk, " + this.inputName + " : in std_logic;");
        indent();
        writeLine(this.outputName + " : out std_logic);");
        unIndent(); unIndent();
        writeLine("end " + this.entityName + ";");
    }

    private void writeArchitecture() {
        writeLine("architecture datapath of " + this.entityName + " is");
        indent();
        writeLine("");
        writeLine("type state_type is (" + this.stateTypes + ")");
        writeLine("signal current_state, next_state : state_type := " + this.initialState);
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
        writeLine("process(current_state, " + this.inputName + ")");
        writeLine("begin");
        writeLine("end process;");
    }

    private void writeFile() {
        writeImports();
        n();
        writeEntity();
        n();
        writeArchitecture();
    }

    public static void main(String[] args) {
        VHDLFileWriter myWriter = new VHDLFileWriter("C:/Users/jake4/OneDrive/Desktop/testProj/testProj3.txt");
        myWriter.startWriting();
        myWriter.writeFile();
        myWriter.endWriting();
    }

}