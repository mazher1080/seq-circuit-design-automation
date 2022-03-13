package state_table_solver.stateTable;

import org.junit.Test;

import state_table_solver.booleanLogic.BitValue;
import state_table_solver.booleanLogic.Bit;

import static org.junit.Assert.*;

public class StateTest {
    @Test
    public void toStringTest() {
        State s1 = new State("Q0", "d");

        s1.pushBit(BitValue.HIGH);
        assertEquals(s1.toString(), "d0");

        s1.pushBit(BitValue.LOW);
        assertEquals(s1.toString(), "d1'.d0");

        s1.pushBit(BitValue.UNKNOWN);
        assertEquals(s1.toString(), "d2.d1'.d0");

        s1.popBit();
        assertEquals(s1.toString(), "d1'.d0");

        s1.popBit();
        assertEquals(s1.toString(), "d0");

        s1.pushBit(BitValue.LOW);
        assertEquals(s1.toString(), "d1'.d0");

        s1.pushBit(BitValue.UNKNOWN);
        assertEquals(s1.toString(), "d2.d1'.d0");

        s1.setEncodingId("b");
        assertEquals(s1.toString(), "b2.b1'.b0");

        s1.popBit();
        assertEquals(s1.toString(), "b1'.b0");

        s1.popBit();
        assertEquals(s1.toString(), "b0");

        s1.popBit();
        assertEquals(s1.toString(), "");
    }

    @Test
    public void encodingCountTest() {
        State s1 = new State("Q0", "d");
        BitValue[] expectedBitVals = {BitValue.HIGH, BitValue.LOW, BitValue.LOW};

        s1.pushBit(BitValue.HIGH);
        s1.pushBit(BitValue.LOW);
        s1.setEncodingCount(3);

        assertEquals(s1.bitProduct().length(), 3);
        assertEquals(s1.getEncodingCount(), 3);
        for(int i = 0; i < s1.bitProduct().length(); i++) {
            Bit b = s1.bitProduct().get(i);
            String expectedId = 'd' + Integer.toString(i);
            BitValue expectedBitVal = expectedBitVals[i];
            assertEquals(b.id(), expectedId);
            assertEquals(b.getValue(), expectedBitVal);
        }

        s1.setEncodingCount(2);

        assertEquals(s1.bitProduct().length(), 2);
        assertEquals(s1.getEncodingCount(), 2);
        for(int i = 0; i < s1.bitProduct().length(); i++) {
            Bit b = s1.bitProduct().get(i);
            String expectedId = 'd' + Integer.toString(i);
            BitValue expectedBitVal = expectedBitVals[i];
            assertEquals(b.id(), expectedId);
            assertEquals(b.getValue(), expectedBitVal);
        }

    }
}
