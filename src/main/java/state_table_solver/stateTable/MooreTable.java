package state_table_solver.stateTable;

import state_table_solver.booleanLogic.SumOfProducts;
import state_table_solver.booleanLogic.Bit;
import state_table_solver.booleanLogic.BitProduct;
import state_table_solver.booleanLogic.BitConst;
import state_table_solver.booleanLogic.BitValue;

import java.util.List;
import java.util.ArrayList;

/**
 * <p> Moore Table is a moore implementation of the state table class. Output does not depend on 
 * input for moore.
 * 
 * @author Jacob Head
 */

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

    /**
     * Returns the table name.
     * 
     * @return The table name.
     */
    @Override
    public String getName() {
        return "Moore";
    }

    /**
     * Used for determining a the table class when loading data from a file.
     * 
     * @return The concrete class of the table.
     */
    @Override
    public Class<?> getTableType() {
        return this.getClass();
    }

    /**
	 * Gets the non-minimized sum of products for the output.
	 */
    @Override
    public SumOfProducts getOutputSoP() {
        SumOfProducts resultSoP = new SumOfProducts();
        List<Bit> outputCol = getOutputCol();

        for(int i = 0; i < outputCol.size(); i++) {
            if(outputCol.get(i).getValue() == BitValue.HIGH) {
                State curState = getCurrentStateCol().get(i);
                BitProduct stateBits = curState.getBitProduct();

                BitProduct rowBitProduct = new BitProduct();
                rowBitProduct.append(stateBits);

                resultSoP.add(rowBitProduct);
            }
        }

        return formatSoP(resultSoP);
    }

    /**
     * Removes the output row at specified index.
	 * 
	 * @param rowIndex Index of the row to remove.
	 */
    @Override
    protected void removeOutputRow(int rowIndex) {
        this.getOutputCol().remove(rowIndex);
    }

    /**
     * Initializes a new output row with default values.
     */
    @Override
    protected void addDefaultOutputRow() {
        this.getOutputCol().add(new BitConst(BitValue.UNKNOWN));
    }

    /**
     * Getter for output col
     * 
     * @return The output col
     */
    public List<Bit> getOutputCol() {
        return outputCol;
    }

    /**
     * Returns the string representaion of the table based on setting provided.
     * 
     * @param isEncodedStates Whether to output the string with states encoded.
     * @return String represenation of table.
     */
    @Override
    public String toStringUtility(boolean isEncodedStates) {
        String columnLabels = "[ Current State, Next Low State, Next High State, Output ]\n";
        String seperator = ", ";
        String output = columnLabels;
        for (int i = 0; i < this.getStateCount(); i++) {
            String rowString = "[ ";
            if(isEncodedStates) {
                rowString += this.getCurrentStateCol().get(i).toEncodedString();
                rowString += seperator;
                rowString += this.getNextLowStateCol().get(i).toEncodedString();
                rowString += seperator;
                rowString += this.getNextHighStateCol().get(i).toEncodedString();
                rowString += seperator;
            } else {
                rowString += this.getCurrentStateCol().get(i).toString();
                rowString += seperator;
                rowString += this.getNextLowStateCol().get(i).toString();
                rowString += seperator;
                rowString += this.getNextHighStateCol().get(i).toString();
                rowString += seperator;
            }
            rowString += this.getOutputCol().get(i).toString();
            rowString += " ]\n";
            output += rowString;
        }
		return output;
    }
    
}
