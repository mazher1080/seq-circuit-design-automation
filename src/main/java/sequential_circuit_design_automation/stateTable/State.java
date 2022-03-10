package sequential_circuit_design_automation.stateTable;

import sequential_circuit_design_automation.booleanLogic.*;
import java.io.Serializable;

public class State implements Serializable {W

	private BitProduct bitProduct;
	private int stateNumber;
	private int encodingVarCount;

	/**
	 * Class Constructor. Initializes with an already existing bit product.
	 * 
	 * @param bp the bit product to hold in the state.
	 */
	public State(BitProduct bp) {
		this.bitProduct = bp;
	}

	public BitProduct getBitProduct() {
		return this.bitProduct;
	}

	/**
	 * 
	 * @param bitProduct
	 */
	public void setBitProduct(BitProduct bitProduct) {
		this.bitProduct = bitProduct;
	}

	public int getStateNumber() {
		return this.stateNumber;
	}

	/**
	 * 
	 * @param stateNumber
	 */
	public void setStateNumber(int stateNumber) {
		this.stateNumber = stateNumber;
	}

	public int getEncodingVarCount() {
		return this.encodingVarCount;
	}

	/**
	 * 
	 * @param encodingVarCount
	 */
	public void setEncodingVarCount(int encodingVarCount) {
		this.encodingVarCount = encodingVarCount;
	}

}