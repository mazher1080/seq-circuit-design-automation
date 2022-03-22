package state_table_solver.stateTable;

import org.junit.Test;

import state_table_solver.booleanLogic.BitConst;
import state_table_solver.booleanLogic.BitValue;
import state_table_solver.booleanLogic.SumOfProducts;

import static org.junit.Assert.*;

public class MooreTableTest {
    @Test
    public void toStringTest() {
        StateTable mooreTable = new MooreTable();
        mooreTable.addState("Q1");
        mooreTable.addState("Q2");
        mooreTable.addState("Q3");
        mooreTable.addState("Q4");
        mooreTable.addState("Q5");
        assertEquals(
            mooreTable.toString(), 
            "[ Current State, Next Low State, Next High State, Output ]\n" +
            "[ Q1, Q1, Q1, - ]\n"+
            "[ Q2, Q2, Q2, - ]\n"+
            "[ Q3, Q3, Q3, - ]\n"+
            "[ Q4, Q4, Q4, - ]\n"+
            "[ Q5, Q5, Q5, - ]\n"
        );
    }

    @Test
    public void removeRowTest() {
        StateTable mooreTable = new MooreTable();
        mooreTable.addState("Q1");
        mooreTable.addState("Q2");
        mooreTable.addState("Q3");
        mooreTable.addState("Q4");
        mooreTable.addState("Q5");

        mooreTable.removeRow(3);
        assertEquals(mooreTable.toString(),
        "[ Current State, Next Low State, Next High State, Output ]\n" +
        "[ Q1, Q1, Q1, - ]\n"+
        "[ Q2, Q2, Q2, - ]\n"+
        "[ Q3, Q3, Q3, - ]\n"+
        "[ Q5, Q5, Q5, - ]\n");
        mooreTable.removeRow(3);
        assertEquals(mooreTable.toString(),
        "[ Current State, Next Low State, Next High State, Output ]\n" +
        "[ Q1, Q1, Q1, - ]\n"+
        "[ Q2, Q2, Q2, - ]\n"+
        "[ Q3, Q3, Q3, - ]\n");
    }

    @Test
    public void accessValuesTest() {
        MooreTable mooreTable = new MooreTable();
        mooreTable.addState("Q1");
        mooreTable.addState("Q2");
        mooreTable.addState("Q3");
        mooreTable.addState("Q4");
        mooreTable.addState("Q5");

        mooreTable.getOutputCol().set(0, new BitConst(BitValue.HIGH));
        mooreTable.getOutputCol().set(2, new BitConst(BitValue.LOW));

        State Q1 = mooreTable.getCurrentStateCol().get(0);
        mooreTable.getNextHighStateCol().set(2, Q1);
        mooreTable.getNextLowStateCol().set(4, Q1);

        State Q2 = mooreTable.getCurrentStateCol().get(1);
        mooreTable.getNextLowStateCol().set(0, Q2);
        mooreTable.getNextHighStateCol().set(3, Q2);

        assertEquals(
            mooreTable.toString(), 
            "[ Current State, Next Low State, Next High State, Output ]\n" +
            "[ Q1, Q2, Q1, 1 ]\n"+
            "[ Q2, Q2, Q2, - ]\n"+
            "[ Q3, Q3, Q1, 0 ]\n"+
            "[ Q4, Q4, Q2, - ]\n"+
            "[ Q5, Q1, Q5, - ]\n"
        );

        mooreTable.getCurrentStateCol().get(0).setId("TEST");

        assertEquals(
            mooreTable.toString(), 
            "[ Current State, Next Low State, Next High State, Output ]\n" +
            "[ TEST, Q2, TEST, 1 ]\n"+
            "[ Q2, Q2, Q2, - ]\n"+
            "[ Q3, Q3, TEST, 0 ]\n"+
            "[ Q4, Q4, Q2, - ]\n"+
            "[ Q5, TEST, Q5, - ]\n"
        );
    }

    @Test
    public void getSoPTest() {
        MooreTable mooreTable = new MooreTable();
        mooreTable.addState("Q1");
        mooreTable.addState("Q2");
        mooreTable.addState("Q3");
        mooreTable.addState("Q4");
        mooreTable.addState("Q5");

        mooreTable.getOutputCol().set(0, new BitConst(BitValue.HIGH));
        mooreTable.getOutputCol().set(2, new BitConst(BitValue.HIGH));

        State Q1 = mooreTable.getCurrentStateCol().get(0);
        mooreTable.getNextHighStateCol().set(2, Q1);
        mooreTable.getNextLowStateCol().set(4, Q1);

        State Q2 = mooreTable.getCurrentStateCol().get(1);
        mooreTable.getNextLowStateCol().set(0, Q2);
        mooreTable.getNextHighStateCol().set(3, Q2);

        assertEquals(
            mooreTable.toString(), 
            "[ Current State, Next Low State, Next High State, Output ]\n" +
            "[ Q1, Q2, Q1, 1 ]\n"+
            "[ Q2, Q2, Q2, - ]\n"+
            "[ Q3, Q3, Q1, 1 ]\n"+
            "[ Q4, Q4, Q2, - ]\n"+
            "[ Q5, Q1, Q5, - ]\n"
        );

        SumOfProducts outputSoP = mooreTable.getOutputSoP();
        assertEquals(outputSoP.toString(), "d2'.d1'.d0' + d2'.d1.d0'");

        SumOfProducts stateSoP = mooreTable.getStateSoP("Q1");
        assertEquals(stateSoP.toString(), "x.d2'.d1'.d0' + x.d2'.d1.d0' + x'.d2.d1'.d0'");
    }

}
