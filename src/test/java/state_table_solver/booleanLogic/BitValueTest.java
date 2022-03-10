package state_table_solver.booleanLogic;

import org.junit.Test;

import state_table_solver.booleanLogic.Bit;
import state_table_solver.booleanLogic.BitValue;
import state_table_solver.booleanLogic.BitVar;

import static org.junit.Assert.*;

public class BitValueTest {
    @Test
    public void equalityTest() {
        BitValue highVal1 = BitValue.HIGH;
        BitValue highVal2 = BitValue.HIGH;
        BitValue lowVal1 = BitValue.LOW;
        BitValue lowVal2 = BitValue.LOW;
        BitValue unknownVal1 = BitValue.UNKNOWN;
        BitValue unknownVal2 = BitValue.UNKNOWN;

        assertEquals(highVal1, highVal2);
        assertEquals(lowVal1, lowVal2);
        assertEquals(unknownVal1, unknownVal2);
    }

    @Test 
    public void toStringTest() {
        BitValue highVal = BitValue.HIGH;
        BitValue lowVal = BitValue.LOW;
        BitValue unknownVal = BitValue.UNKNOWN;

        assertEquals(highVal.toString(), "1");
        assertEquals(lowVal.toString(), "0");
        assertEquals(unknownVal.toString(), "-");
    }

    @Test
    public void testNegation() {
        BitValue highVal = BitValue.HIGH;
        BitValue lowVal = BitValue.LOW;
        BitValue unknownVal = BitValue.UNKNOWN;

        assertEquals(highVal, lowVal.negatedValue());
        assertEquals(lowVal, highVal.negatedValue());
        assertEquals(unknownVal, unknownVal.negatedValue());
    }

    @Test
    public void testInvalidId() {
        boolean failedB1 = false;
        boolean failedB2 = false;
        try {
            Bit b1 = new BitVar("'", BitValue.HIGH);
        } catch (AssertionError e) {
            failedB1 = true;
        }

        try {
            Bit b2 = new BitVar("0", BitValue.HIGH);
        } catch (AssertionError e) {
            failedB2 = true;
        }

        assertTrue(failedB1);
        assertTrue(failedB2);
    }
}
