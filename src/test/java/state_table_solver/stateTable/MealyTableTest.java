package state_table_solver.stateTable;

import org.junit.Test;

import state_table_solver.booleanLogic.BitConst;
import state_table_solver.booleanLogic.BitValue;
import state_table_solver.booleanLogic.SumOfProducts;

import static org.junit.Assert.*;

public class MealyTableTest {
    @Test
    public void toStringTest() {
        StateTable mealyTable = new MealyTable();
        mealyTable.addState("Q1");
        mealyTable.addState("Q2");
        mealyTable.addState("Q3");
        mealyTable.addState("Q4");
        mealyTable.addState("Q5");
        assertEquals(
            mealyTable.toString(), 
            "[ Current State, Next Low State, Next High State, Low Output, High Output ]\n" +
            "[ Q1, Q1, Q1, -, - ]\n"+
            "[ Q2, Q2, Q2, -, - ]\n"+
            "[ Q3, Q3, Q3, -, - ]\n"+
            "[ Q4, Q4, Q4, -, - ]\n"+
            "[ Q5, Q5, Q5, -, - ]\n"
        );
    }

    @Test
    public void removeRowTest() {
        StateTable mealyTable = new MealyTable();
        mealyTable.addState("Q1");
        mealyTable.addState("Q2");
        mealyTable.addState("Q3");
        mealyTable.addState("Q4");
        mealyTable.addState("Q5");

        mealyTable.removeRow(3);
        assertEquals(mealyTable.toString(),
            mealyTable.toString(), 
            "[ Current State, Next Low State, Next High State, Low Output, High Output ]\n" +
            "[ Q1, Q1, Q1, -, - ]\n"+
            "[ Q2, Q2, Q2, -, - ]\n"+
            "[ Q3, Q3, Q3, -, - ]\n"+
            "[ Q5, Q5, Q5, -, - ]\n"
        );
        mealyTable.removeRow(3);
        assertEquals(
            mealyTable.toString(), 
            "[ Current State, Next Low State, Next High State, Low Output, High Output ]\n" +
            "[ Q1, Q1, Q1, -, - ]\n"+
            "[ Q2, Q2, Q2, -, - ]\n"+
            "[ Q3, Q3, Q3, -, - ]\n"
        );
    }

    @Test
    public void accessValuesTest() {
        MealyTable mealyTable = new MealyTable();
        mealyTable.addState("Q1");
        mealyTable.addState("Q2");
        mealyTable.addState("Q3");
        mealyTable.addState("Q4");
        mealyTable.addState("Q5");

        mealyTable.getNextHighOutputCol().set(0, new BitConst(BitValue.HIGH));
        mealyTable.getNextLowOutputCol().set(2, new BitConst(BitValue.LOW));

        State Q1 = mealyTable.getCurrentStateCol().get(0);
        mealyTable.getNextHighStateCol().set(2, Q1);
        mealyTable.getNextLowStateCol().set(4, Q1);

        State Q2 = mealyTable.getCurrentStateCol().get(1);
        mealyTable.getNextLowStateCol().set(0, Q2);
        mealyTable.getNextHighStateCol().set(3, Q2);

        assertEquals(
            mealyTable.toString(), 
            "[ Current State, Next Low State, Next High State, Low Output, High Output ]\n" +
            "[ Q1, Q2, Q1, -, 1 ]\n"+
            "[ Q2, Q2, Q2, -, - ]\n"+
            "[ Q3, Q3, Q1, 0, - ]\n"+
            "[ Q4, Q4, Q2, -, - ]\n"+
            "[ Q5, Q1, Q5, -, - ]\n"
        );

        mealyTable.getCurrentStateCol().get(0).setId("TEST");

        assertEquals(
            mealyTable.toString(), 
            "[ Current State, Next Low State, Next High State, Low Output, High Output ]\n" +
            "[ TEST, Q2, TEST, -, 1 ]\n"+
            "[ Q2, Q2, Q2, -, - ]\n"+
            "[ Q3, Q3, TEST, 0, - ]\n"+
            "[ Q4, Q4, Q2, -, - ]\n"+
            "[ Q5, TEST, Q5, -, - ]\n"
        );
    }

    @Test
    public void getSoPTest() {
        MealyTable mealyTable = new MealyTable();
        mealyTable.addState("Q1");
        mealyTable.addState("Q2");
        mealyTable.addState("Q3");
        mealyTable.addState("Q4");
        mealyTable.addState("Q5");

        mealyTable.getNextHighOutputCol().set(0, new BitConst(BitValue.HIGH));
        mealyTable.getNextLowOutputCol().set(2, new BitConst(BitValue.HIGH));

        State Q1 = mealyTable.getCurrentStateCol().get(0);
        mealyTable.getNextHighStateCol().set(2, Q1);
        mealyTable.getNextLowStateCol().set(4, Q1);

        State Q2 = mealyTable.getCurrentStateCol().get(1);
        mealyTable.getNextLowStateCol().set(0, Q2);
        mealyTable.getNextHighStateCol().set(3, Q2);

        assertEquals(
            mealyTable.toString(), 
            "[ Current State, Next Low State, Next High State, Low Output, High Output ]\n" +
            "[ Q1, Q2, Q1, -, 1 ]\n"+
            "[ Q2, Q2, Q2, -, - ]\n"+
            "[ Q3, Q3, Q1, 1, - ]\n"+
            "[ Q4, Q4, Q2, -, - ]\n"+
            "[ Q5, Q1, Q5, -, - ]\n"
        );

        SumOfProducts outputSoP = mealyTable.getOutputSoP();
        assertEquals(outputSoP.toString(), "x.d2'.d1'.d0' + x'.d2'.d1.d0'");

        SumOfProducts stateSoP = mealyTable.getStateSoP("Q1");
        assertEquals(stateSoP.toString(), "x.d2'.d1'.d0' + x.d2'.d1.d0' + x'.d2.d1'.d0'");
    }

}
