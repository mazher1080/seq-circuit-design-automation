package state_table_solver.stateTable;

import state_table_solver.booleanLogic.*;
import state_table_solver.Utilities;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * State Table is a class used to store data that relates a state with its
 * next state, and next output.
 * 
 * @author Jacob Head
 */

public abstract class StateTable implements Serializable {

    public final Bit HIGH_INPUT = new BitVar("x", BitValue.HIGH);
    public final Bit LOW_INPUT = new BitVar("x", BitValue.LOW);
    public final Bit HIGH_OUTPUT = new BitVar("y", BitValue.HIGH);
    public final Bit UNKNOWN_OUTPUT = new BitVar("y", BitValue.UNKNOWN);
    public final Bit LOW_OUTPUT = new BitVar("y", BitValue.LOW);
    public final String ENCODING_ID = "d";
    private final LogicMinimizer logicMinimizer = new QMAlgorithm();

	private int stateCount;
	private List<State> currentStateCol;
	private List<State> nextHighStateCol;
	private List<State> nextLowStateCol;
	private List<Bit> nextHighOutputCol;
	private List<Bit> nextLowOutputCol;

    /**
     * Class constructor.
     */
	public StateTable() {
        this.currentStateCol = new ArrayList<State>();
        this.nextHighStateCol = new ArrayList<State>();
        this.nextLowStateCol = new ArrayList<State>();
        this.nextHighOutputCol = new ArrayList<Bit>();
        this.nextLowOutputCol = new ArrayList<Bit>();
        this.stateCount = 0;
	}

    /**
     * Returns the table name.
     * 
     * @return The table name.
     */
    public abstract String getName();

	/**
	 * Gets the non-minimized sum of products for the output.
	 */
	public abstract SumOfProducts getOutputSoP();

    /**
     * Returns the string representaion of the table based on setting provided.
     * 
     * @param isEncodedStates Whether to output the string with states encoded.
     * @return String represenation of table.
     */
    public abstract String toStringUtility(boolean isEncodedStates);

    /**
     * Removes the ouput row(s) from the table at specified index
	 * 
	 * @param rowIndex Index of the row to remove.
	 */
    protected abstract void removeOutputRow(int rowIndex);

    /**
     * Adds the output row(s) to the table with default values
	 * 
	 * @param rowIndex Index of the row to remove.
	 */
    protected abstract void addDefaultOutputRow();

    /**
     * Used for determining a the table class when loading data from a file.
     * 
     * @return The concrete class of the table.
     */
    public abstract Class<?> getTableType();

    /**
     * Getter for state count.
     * 
     * @return The state count.
     */
	public int getStateCount() {
		return this.stateCount;
	}
    
    /**
     * Minimizes the specified state's sum of products, using the 
     * logic minimizer algorithm.
     * @see LogicMinimizer
     * 
     * @param stateId The id of the state to find sum of products for.
     * @return The minimized sop.
     */
    public SumOfProducts getMinStateSoP(String stateId) {
        SumOfProducts sop = getStateSoP(stateId);
        return logicMinimizer.minimizeSoP(sop);
    }

    /**
     * Minimizes the outputs sum of products, using the 
     * logic minimizer algorithm.
     * @see LogicMinimizer
     * 
     * @return The minimized sop.
     */
    public SumOfProducts getMinOutputSoP() {
        SumOfProducts sop = getOutputSoP();
        return logicMinimizer.minimizeSoP(sop);
    }

	/**
     * Setter for state count. Updates the state encoding on all states.
	 * 
	 * @param stateCount The new state count.
	 */
	public void setStateCount(int stateCount) {
        assert (stateCount > 0);
        int oldEncodingCount = (int) Utilities.log2(getStateCount()) + 1;
        int newEncodingCount = (int) Utilities.log2(stateCount) + 1;
        if(newEncodingCount != oldEncodingCount) {
            for(State s : getCurrentStateCol()) {
                s.setEncodingCount(newEncodingCount);
            }
        }
        this.stateCount = stateCount;
	}

    /**
     * Getter for current state column.
     * 
     * @return Current state column.
     */
	public List<State> getCurrentStateCol() {
		return this.currentStateCol;
	}

    /**
     * Getter for next high state column.
     * 
     * @return Next high state column.
     */
	public List<State> getNextHighStateCol() {
		return this.nextHighStateCol;
	}

    /**
     * Getter for next low state column.
     * 
     * @return Next low state column.
     */
	public List<State> getNextLowStateCol() {
		return this.nextLowStateCol;
	}

    /**
     * Getter for next high output column.
     * 
     * @return Next high output column.
     */
	public List<Bit> getNextHighOutputCol() {
		return this.nextHighOutputCol;
	}

    /**
     * Getter for next low output column.
     * 
     * @return Next low output column.
     */
	public List<Bit> getNextLowOutputCol() {
		return this.nextLowOutputCol;
	}

    /**
     * Setter for next high output column.
     * 
     * @param nextHighOutputCol New next high output column.
     */
    public void setNextHighOutputCol(List<Bit> nextHighOutputCol) {
        this.nextHighOutputCol = nextHighOutputCol;
    }

    /**
     * Setter for next low output column.
     * 
     * @param nextLowOutputCol New next low output column.
     */
    public void setNextLowOutputCol(List<Bit> nextLowOutputCol) {
        this.nextLowOutputCol = nextLowOutputCol;
    }

    /**
	 * Gets the non-minimzed sum of products for a state based on the state's id.
     * 
	 * @param stateId Id of the state.
	 */
    public SumOfProducts getStateSoP(String stateId) {
        SumOfProducts resultSoP = new SumOfProducts();
        List<State> nextHighStateCol = getNextHighStateCol();
        List<State> nextLowStateCol = getNextLowStateCol();

        for(int i = 0; i < nextHighStateCol.size(); i++) {
            if(nextHighStateCol.get(i).getId() == stateId) {
                State curState = getCurrentStateCol().get(i);
                BitProduct stateBits = curState.getBitProduct();

                BitProduct rowBitProduct = new BitProduct();
                rowBitProduct.add(this.HIGH_INPUT);
                rowBitProduct.append(stateBits);

                resultSoP.add(rowBitProduct);
            }
        }

        for(int i = 0; i < nextLowStateCol.size(); i++) {
            if(nextLowStateCol.get(i).getId() == stateId) {
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
     * Adds a row to the bottom of the state table, initialized with
     * the specified state.
     */
    public void addState(String stateId) {
        State s = new State(stateId, ENCODING_ID);
        String encodingMap = Integer.toBinaryString(getStateCount());
        // Set the binary encoded state value
        for(int i = encodingMap.length() - 1; i >= 0; i--) {
            if(encodingMap.charAt(i) == '1') {
                s.pushBit(BitValue.HIGH);
            } else {
                s.pushBit(BitValue.LOW);
            }
        }
        this.getCurrentStateCol().add(s);
        this.getNextHighStateCol().add(s);
        this.getNextLowStateCol().add(s);
        this.addDefaultOutputRow();
        this.setStateCount(this.getStateCount() + 1);
    }

    /**
     * Removes row at specified index.
	 * 
	 * @param rowIndex Index of the row to remove.
	 */
    public void removeRow(int rowIndex) {
        assert (rowIndex >= 0 && rowIndex < this.getStateCount());

        State stateToRemove = this.getCurrentStateCol().get(rowIndex);

        this.getCurrentStateCol().remove(rowIndex);
        this.getNextHighStateCol().remove(rowIndex);
        this.getNextLowStateCol().remove(rowIndex);

        for(int i = 0; i < this.getNextHighStateCol().size(); i++){
            State defaultState = this.getCurrentStateCol().get(i);
            System.out.println(this.getNextHighStateCol().get(i));
            if(this.getNextHighStateCol().get(i) == stateToRemove) {
                this.getNextHighStateCol().set(i, defaultState);
            }
            if(this.getNextLowStateCol().get(i) == stateToRemove) {
                this.getNextLowStateCol().set(i, defaultState);
            }
        }

        this.removeOutputRow(rowIndex);
        this.setStateCount(this.getStateCount() - 1);
    }

    /**
     * Overrides java Object toString method 
     * @see Object
     * 
     * @return String representation of the table.
     */
    @Override
	public String toString() {
        return toStringUtility(false);
	}

    /**
     * Helper function. If the sum of products is empty set it to a logic low.
     * 
     * @param sopToFormat The sum of product to format before returning
     * @return The sop in proper format.
     */
    protected SumOfProducts formatSoP(SumOfProducts sopToFormat) {
        if(sopToFormat.length() <= 0) {
            SumOfProducts noValueSoP = new SumOfProducts();
            BitProduct lowBitProduct = new BitProduct(new BitConst(BitValue.LOW));
            noValueSoP.add(lowBitProduct);
            return noValueSoP;
        }
        return sopToFormat;
    }

}