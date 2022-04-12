package state_table_solver.stateTable;

import state_table_solver.booleanLogic.*;

import java.util.List;

/**
 * <p> Mealy Table is a mealy implementation of the state table class. Output depends on 
 * input for mealy.
 * 
 * @author Jacob Head
 */

public class MealyTable extends StateTable {

    /**
     * Class constructor.
     */
    public MealyTable() {
        super();
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
     * Returns the table name.
     * @see StateTable
     * 
     * @return The table name.
     */
    @Override
    public String getName() {
        return "Mealy";
    }

    /**
     * Gets the sum of product for the output when it is high.
     * 
     * @return The output sum of products.
     */
    @Override
    public SumOfProducts getOutputSoP() {
        SumOfProducts resultSoP = new SumOfProducts();
        List<Bit> nextHighOutputCol = getNextHighOutputCol();
        List<Bit> nextLowOutputCol = getNextLowOutputCol();

        for(int i = 0; i < nextHighOutputCol.size(); i++) {
            if(nextHighOutputCol.get(i).getValue() == Bit.HIGH) {
                State curState = getCurrentStateCol().get(i);
                BitProduct stateBits = curState.getBitProduct();

                BitProduct rowBitProduct = new BitProduct();
                rowBitProduct.add(this.HIGH_INPUT);
                rowBitProduct.append(stateBits);

                resultSoP.add(rowBitProduct);
            }
        }

        for(int i = 0; i < nextLowOutputCol.size(); i++) {
            if(nextLowOutputCol.get(i).getValue() == Bit.HIGH) {
                State curState = getCurrentStateCol().get(i);
                BitProduct stateBits = curState.getBitProduct();

                BitProduct rowBitProduct = new BitProduct();
                rowBitProduct.add(this.LOW_INPUT);
                rowBitProduct.append(stateBits);

                resultSoP.add(rowBitProduct);
            }
        }

        return formatSoP(resultSoP);
    }

    /**
     * Removes the output rows at specified index.
	 * 
	 * @param rowIndex Index of the row to remove.
	 */
    @Override
    protected void removeOutputRow(int rowIndex) {
        this.getNextHighOutputCol().remove(rowIndex);
        this.getNextLowOutputCol().remove(rowIndex);
    }

    /**
     * Initializes a new output row with default values.
     */
    @Override
    protected void addDefaultOutputRow() {
        this.getNextHighOutputCol().add(new BitConst(BitValue.UNKNOWN));
        this.getNextLowOutputCol().add(new BitConst(BitValue.UNKNOWN));
    }

    /**
     * Returns the string representaion of the table based on setting provided.
     * 
     * @param isEncodedStates Whether to output the string with states encoded.
     * @return String represenation of table.
     */
    @Override
    public String toStringUtility(boolean isEncodedStates) {
        String columnLabels = "[ Current State, Next Low State, Next High State, Low Output, High Output ]\n";
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
            rowString += this.getNextLowOutputCol().get(i).toString();
            rowString += seperator;
            rowString += this.getNextHighOutputCol().get(i).toString();
            rowString += " ]\n";
            output += rowString;
        }
		return output;
    }
    
}
