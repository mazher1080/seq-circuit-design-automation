package state_table_solver.stateTable;

import org.junit.Test;

import state_table_solver.booleanLogic.BitValue;

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
        //TODO write this test.
    }
}
