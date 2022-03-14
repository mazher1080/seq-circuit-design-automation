package state_table_solver.stateTable;

import state_table_solver.booleanLogic.*;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;


public abstract class StateTable implements Serializable {

	private int stateCount;
	private List<State> currentStateCol;
	private List<State> nextHighStateCol;
	private List<State> nextLowStateCol;
	private List<Bit> nextHighOutputCol;
	private List<Bit> nextLowOutputCol;

	public StateTable() {
		// TODO - implement StateTable.StateTable
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param bitId
	 */
	private SumOfProducts getSoP(String bitId) {
		// TODO - implement StateTable.getSoP
		throw new UnsupportedOperationException();
	}

	public String toString() {
		// TODO - implement StateTable.toString
		throw new UnsupportedOperationException();
	}

	public int getStateCount() {
		return this.stateCount;
	}

	/**
	 * 
	 * @param stateCount
	 */
	public void setStateCount(int stateCount) {
		this.stateCount = stateCount;
	}

	/**
	 * 
	 * @param curState
	 * @param nextHighState
	 * @param nextLowState
	 * @param nextHighOutput
	 * @param nextLowOutput
	 */
	public void addRow(Bit curState, Bit nextHighState, Bit nextLowState, Bit nextHighOutput, Bit nextLowOutput) {
		// TODO - implement StateTable.addRow
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param rowIndex
	 */
	public void removeRow(int rowIndex) {
		// TODO - implement StateTable.removeRow
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param bitId
	 */
	public SumOfProducts getSimplifiedSop(String bitId) {
		// TODO - implement StateTable.getSimplifiedSop
		throw new UnsupportedOperationException();
	}

	public List<State> getCurrentStateCol() {
		return this.currentStateCol;
	}

	public List<State> getNextHighStateCol() {
		return this.nextHighStateCol;
	}

	public List<State> getNextLowStateCol() {
		return this.nextLowStateCol;
	}

	public List<Bit> getNextHighOutputCol() {
		return this.nextHighOutputCol;
	}

	public List<Bit> getNextLowOutputCol() {
		return this.nextLowOutputCol;
	}

}