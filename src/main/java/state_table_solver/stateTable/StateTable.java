package state_table_solver.stateTable;

import state_table_solver.booleanLogic.Bit;
import state_table_solver.booleanLogic.SumOfProducts;

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
	 * Gets the non-minimzed sum of products for a state based on the state's id.
     * 
	 * @param stateId Id of the state.
	 */
	public abstract SumOfProducts getStateSoP(String stateId);

	/**
	 * Gets the non-minimized sum of products for the output.
	 */
	public abstract SumOfProducts getOutputSoP();

    /**
	 * Initializes an empty row at the bottom of the table.
	 */
	public abstract void addEmptyRow();

	/**
     * Removes row at specified index.
	 * 
	 * @param rowIndex Index of the row to remove.
	 */
	public abstract void removeRow(int rowIndex);

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

}