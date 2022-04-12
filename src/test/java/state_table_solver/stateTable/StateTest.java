package state_table_solver.stateTable;

import org.junit.Test;

import state_table_solver.booleanLogic.BitValue;
import state_table_solver.booleanLogic.Bit;

import static org.junit.Assert.*;

public class StateTest {
    @Test
    public void toEncodedStringTest() {
        State s1 = new State("Q0", "d");

        s1.pushBit(Bit.HIGH);
        assertEquals(s1.toEncodedString(), "d0");

        s1.pushBit(BitValue.LOW);
        assertEquals(s1.toEncodedString(), "d1'.d0");

        s1.pushBit(BitValue.UNKNOWN);
        assertEquals(s1.toEncodedString(), "d2.d1'.d0");

        s1.popBit();
        assertEquals(s1.toEncodedString(), "d1'.d0");

        s1.popBit();
        assertEquals(s1.toEncodedString(), "d0");

        s1.pushBit(BitValue.LOW);
        assertEquals(s1.toEncodedString(), "d1'.d0");

        s1.pushBit(BitValue.UNKNOWN);
        assertEquals(s1.toEncodedString(), "d2.d1'.d0");

        s1.setEncodingId("b");
        assertEquals(s1.toEncodedString(), "b2.b1'.b0");

        s1.popBit();
        assertEquals(s1.toEncodedString(), "b1'.b0");

        s1.popBit();
        assertEquals(s1.toEncodedString(), "b0");

        s1.popBit();
        assertEquals(s1.toEncodedString(), "");
    }

    @Test
    public void encodingCountTest() {
        State s1 = new State("Q0", "d");
        BitValue[] expectedBitVals = {BitValue.LOW, BitValue.LOW, Bit.HIGH};

        s1.pushBit(Bit.HIGH);
        s1.pushBit(BitValue.LOW);
        s1.setEncodingCount(3);

        assertEquals(s1.getBitProduct().length(), 3);
        assertEquals(s1.getEncodingCount(), 3);
        for(int i = 0; i < s1.getBitProduct().length(); i++) {
            Bit b = s1.getBitProduct().get(i);
            String expectedId = 'd' + Integer.toString(s1.getBitProduct().length() - 1 - i);
            BitValue expectedBitVal = expectedBitVals[i];
            assertEquals(b.getId(), expectedId);
            assertEquals(b.getValue(), expectedBitVal);
        }

        s1.setEncodingCount(2);

        assertEquals(s1.getBitProduct().length(), 2);
        assertEquals(s1.getEncodingCount(), 2);
        for(int i = 0; i < s1.getBitProduct().length(); i++) {
            Bit b = s1.getBitProduct().get(i);
            String expectedId = 'd' + Integer.toString(s1.getBitProduct().length() - 1 - i);
            BitValue expectedBitVal = expectedBitVals[i + 1];
            assertEquals(b.getId(), expectedId);
            assertEquals(b.getValue(), expectedBitVal);
        }

    }
}
