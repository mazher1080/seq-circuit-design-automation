package state_table_solver.booleanLogic;

import org.junit.Test;

import static org.junit.Assert.*;

public class BitProductTest {
    @Test
    public void toStringTest() {
        Bit A = new BitVar("A", Bit.HIGH);
        Bit B = new BitVar("B", Bit.HIGH);
        Bit C = new BitVar("C", BitValue.LOW);
        BitProduct bits = new BitProduct(A, B, C);

        BitProduct bits2 = new BitProduct();
        bits2.add(A);
        bits2.add(B);
        bits2.add(C);
        
        assertEquals(bits.toString(), "A.B.C'");
        assertEquals(bits2.toString(), "A.B.C'");
    }

    @Test
    public void highBitCountTest() {
        Bit A = new BitVar("A", Bit.HIGH);
        Bit B = new BitVar("B", Bit.HIGH);
        Bit C = new BitVar("C", BitValue.LOW);
        BitProduct bits = new BitProduct(A, B, C);

        BitProduct bits2 = new BitProduct();
        bits2.add(A);
        bits2.add(B);
        bits2.add(C);
        
        assertEquals(bits.getHighBitCount(), 2);
        assertEquals(bits2.getHighBitCount(), 2);

        Bit constant1 = new BitConst(Bit.HIGH);
        bits.add(constant1);

        assertEquals(bits.getHighBitCount(), 3);
    }

    @Test
    public void appendTest() {
        Bit A = new BitVar("A", Bit.HIGH);
        Bit B = new BitVar("B", Bit.HIGH);
        Bit C = new BitVar("C", BitValue.LOW);
        BitProduct bits = new BitProduct(A, B, C);

        Bit D = new BitVar("D", Bit.HIGH);
        Bit E = new BitVar("E", Bit.HIGH);
        Bit F = new BitVar("F", BitValue.LOW);
        BitProduct bits2 = new BitProduct(D, E, F);

        bits.append(bits2);
        Bit[] correctBits = {A, B, C, D, E, F};

        for(int i = 0; i < correctBits.length; i++) {
            assertEquals(bits.get(i), correctBits[i]);
        }
    }
}
