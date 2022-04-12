package state_table_solver.booleanLogic;

import org.junit.Test;

import static org.junit.Assert.*;

public class SumOfProductsTest {
    @Test
    public void toStringTest() {
        Bit a = new BitVar("A", Bit.HIGH);
        Bit b = new BitVar("B", Bit.HIGH);
        Bit c = new BitVar("C", BitValue.LOW);
        BitProduct bits = new BitProduct(a, b, c);

        Bit d = new BitVar("D", Bit.HIGH);
        Bit e = new BitVar("E", Bit.HIGH);
        Bit f = new BitVar("F", BitValue.LOW);
        BitProduct bits2 = new BitProduct(d, e, f);

        SumOfProducts sop = new SumOfProducts(bits, bits2);
        assertEquals(sop.toString(), "A.B.C' + D.E.F'");

        Bit g = new BitConst(Bit.HIGH);
        Bit h = new BitVar("H", BitValue.LOW);
        Bit i = new BitVar("I", BitValue.LOW);
        BitProduct bits3 = new BitProduct(g, h, i);

        sop.add(bits3);;
        assertEquals(sop.toString(), "A.B.C' + D.E.F' + 1.H'.I'");
    }

    @Test
    public void distributeTest() {
        Bit a = new BitVar("A", Bit.HIGH);
        Bit b = new BitVar("B", Bit.HIGH);
        Bit c = new BitVar("C", BitValue.LOW);
        BitProduct bits = new BitProduct(a, b, c);

        Bit d = new BitVar("D", Bit.HIGH);
        Bit e = new BitVar("E", Bit.HIGH);
        Bit f = new BitVar("F", BitValue.LOW);
        BitProduct bits2 = new BitProduct(d, e, f);

        SumOfProducts sop1 = new SumOfProducts(bits, bits2);

        Bit l = new BitVar("L", Bit.HIGH);
        Bit m = new BitVar("M", BitValue.LOW);
        Bit n = new BitVar("N", BitValue.UNKNOWN);
        BitProduct bits3 = new BitProduct(l, m, n);

        Bit o = new BitVar("O", Bit.HIGH);
        Bit p = new BitVar("P", BitValue.LOW);
        Bit q = new BitVar("Q", BitValue.LOW);
        BitProduct bits4 = new BitProduct(o, p ,q);

        SumOfProducts sop2 = new SumOfProducts(bits3, bits4);
        SumOfProducts result = sop1.getDistributed(sop2);

        String correctResult = "A.B.C'.L.M'.N + A.B.C'.O.P'.Q' + D.E.F'.L.M'.N + D.E.F'.O.P'.Q'";
        assertEquals(correctResult, result.toString());
    }

    @Test
    public void appendTest() {
        Bit x = new BitVar("X", Bit.HIGH);
        Bit y = new BitVar("Y", Bit.HIGH);
        BitProduct bits1 = new BitProduct(x, y);

        Bit i = new BitVar("I", Bit.HIGH);
        Bit j = new BitVar("J", Bit.HIGH);
        BitProduct bits2 = new BitProduct(i, j);
        SumOfProducts sop1 = new SumOfProducts(bits1, bits2);

        Bit a = new BitVar("A", Bit.HIGH);
        Bit b = new BitVar("B", Bit.HIGH);
        BitProduct bits3 = new BitProduct(a, b);

        Bit c = new BitVar("C", Bit.HIGH);
        Bit d = new BitVar("D", Bit.HIGH);
        BitProduct bits4 = new BitProduct(c, d);
        SumOfProducts sop2 = new SumOfProducts(bits3, bits4);
        sop1.append(sop2);
        BitProduct correctProducts[] = {bits1, bits2, bits3, bits4};

        for (int num = 0; num < sop1.length(); num++) {
            assertEquals(correctProducts[num], sop1.get(num));
        }
    }
    
    @Test
    public void lengthTest() {
        Bit a = new BitVar("A", Bit.HIGH);
        Bit b = new BitVar("B", BitValue.LOW);
        BitProduct operand1 = new BitProduct(a, b);

        Bit c = new BitVar("C", BitValue.LOW);
        Bit d = new BitVar("D", Bit.HIGH);
        BitProduct operand2 = new BitProduct(c, d);

        SumOfProducts sop = new SumOfProducts(operand1, operand2);
        int sopLength = sop.length();
        assertEquals(2, sopLength);

        Bit e = new BitVar("E", BitValue.LOW);
        Bit f = new BitVar("F", Bit.HIGH);
        BitProduct operand3 = new BitProduct(e, f);
        sop.add(operand3);
        sopLength = sop.length();
        assertEquals(3, sopLength);

        sop.remove(1);
        sopLength = sop.length();
        assertEquals(2, sopLength);
    }
}
