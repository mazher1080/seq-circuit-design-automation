package state_table_solver.stateTable;

import org.junit.Test;

import state_table_solver.booleanLogic.Bit;
import state_table_solver.booleanLogic.BitProduct;
import state_table_solver.booleanLogic.BitValue;
import state_table_solver.booleanLogic.BitVar;
import state_table_solver.booleanLogic.SumOfProducts;

public class LogicMinimizerTest {
    @Test
    public void minimizeTest() {
        Bit a = new BitVar("a", BitValue.HIGH);
        Bit b = new BitVar("b", BitValue.HIGH);
        Bit c = new BitVar("c", BitValue.HIGH);
        Bit ap = new BitVar("a", BitValue.LOW);
        Bit bp = new BitVar("b", BitValue.LOW);
        Bit cp = new BitVar("c", BitValue.LOW);

        BitProduct b1 = new BitProduct(ap, bp, cp);
        BitProduct b2 = new BitProduct(ap, b, cp);
        BitProduct b3 = new BitProduct(a, bp, cp);
        BitProduct b4 = new BitProduct(a, bp, c);
        BitProduct b5 = new BitProduct(a, b, cp);
        BitProduct b6 = new BitProduct(a, b, c);

        SumOfProducts sop = new SumOfProducts(b1, b2, b3, b4, b5, b6);
        System.out.println(sop);
    }
}
