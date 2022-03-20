package state_table_solver.stateTable;

import state_table_solver.booleanLogic.*;

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
	 * Gets the non-minimized sum of products for the output.
	 */
	public abstract SumOfProducts getOutputSoP();

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
     * Getter for state count.
     * 
     * @return The state count.
     */
	public int getStateCount() {
		return this.stateCount;
	}

	/**
     * Setter for state count. Updates the state encoding on all states.
	 * 
	 * @param stateCount The new state count.
	 */
	public void setStateCount(int stateCount) {
		this.stateCount = stateCount;
        for(State s : getCurrentStateCol()) {
            s.setEncodingCount(stateCount);
        }
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
     * Setter for next high output column.
     * 
     * @param nextHighOutputCol New next high output column.
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
    public void addState(State state) {
        this.getCurrentStateCol().add(state);
        this.getNextHighStateCol().add(state);
        this.getNextLowStateCol().add(state);
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

        this.getCurrentStateCol().remove(rowIndex);
        this.getNextHighStateCol().remove(rowIndex);
        this.getNextLowStateCol().remove(rowIndex);
        this.removeOutputRow(rowIndex);
        this.setStateCount(this.getStateCount() - 1);
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