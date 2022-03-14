package state_table_solver.stateTable;

import state_table_solver.booleanLogic.SumOfProducts;
import state_table_solver.booleanLogic.Bit;

import java.util.List;
import java.util.ArrayList;

public class MooreTable extends StateTable {
    private List<Bit> outputCol;
    

    /**
     * Class constructor.
     */
    public MooreTable() {
        super();
        this.outputCol = new ArrayList<Bit>();
        this.setNextHighOutputCol(this.outputCol);
        this.setNextLowOutputCol(this.outputCol);
    }

    @Override
    public SumOfProducts getStateSoP(String stateId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SumOfProducts getOutputSoP() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addEmptyRow() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeRow(int rowIndex) {
        assert (rowIndex >= 0 && rowIndex < this.getStateCount());

        this.getCurrentStateCol().remove(rowIndex);
        this.getNextHighStateCol().remove(rowIndex);
        this.getNextLowStateCol().remove(rowIndex);
        this.getOutputCol().remove(rowIndex);

        this.setStateCount(this.getStateCount() - 1);
    }
    
    /**
     * Gets the next high output. For moore table output does not depend on input.
     * @see StateTable
     * 
     * @return The next high output.
     */
    @Override
    public List<Bit> getNextHighOutputCol() {
        return getOutputCol();
    }

    /**
     * Gets the next low output. For moore table output does not depend on input.
     * @see StateTable
     * 
     * @return The next low output.
     */
    @Override
    public List<Bit> getNextLowOutputCol() {
        return getOutputCol();
    }

    /**
     * Getter for output col
     * 
     * @return The output col
     */
    public List<Bit> getOutputCol() {
        return outputCol;
    }
    
}
