package state_table_solver.stateTable;

import java.io.Serializable;

import state_table_solver.booleanLogic.SumOfProducts;

/**
 * <p> LogicMinimizer is a strategy interface used to minimize an SoP based on
 * a certain algorithm.
 * 
 * @author Jacob Head
 */

public interface LogicMinimizer extends Serializable {

    /**
     * Method used to minimize a SumOfProducts
     * @see SumOfProducts
     * 
     * @return The minimized sum of products
     */
    public SumOfProducts minimizeSoP(SumOfProducts sop);
}
