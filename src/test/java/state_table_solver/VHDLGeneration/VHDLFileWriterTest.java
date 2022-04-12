package state_table_solver.VHDLGeneration;

import org.junit.Test;

import state_table_solver.*;
import state_table_solver.booleanLogic.BitConst;
import state_table_solver.booleanLogic.Bit;
import state_table_solver.stateTable.*;

public class VHDLFileWriterTest {
    @Test
    public void testFileWrite() {
        AppData appData = new AppData();
        StateTable newStTable = new MealyTable();
        appData.setStateTable(newStTable);
        StateTable stTable = appData.getStateTable();
        stTable.addState("Q1");
        stTable.addState("Q2");
        stTable.addState("Q3");
        stTable.getNextLowStateCol().set(1, stTable.getCurrentStateCol().get(0));
        stTable.getNextHighOutputCol().set(0, new BitConst(Bit.HIGH));
        stTable.getNextLowOutputCol().set(1, new BitConst(Bit.HIGH));
        stTable.getNextHighOutputCol().set(2, new BitConst(Bit.HIGH));
        stTable.getNextLowOutputCol().set(2, new BitConst(Bit.HIGH));

        VHDLFileWriter myWriter = new VHDLFileWriter("C:/Users/jake4/OneDrive/Desktop/testProj/testProj3", appData);
        myWriter.writeFile();
    }
}
