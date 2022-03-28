package state_table_solver.stateTable;

import java.io.Serializable;

import state_table_solver.VHDLGeneration.VHDLSignal;
import state_table_solver.booleanLogic.*;

/**
 * <p> State is a class which stores a bitproduct with an id. It auto generates the ids of the bits stored
 * in the bit product based on encoding id appended with its index in the bit product.
 * 
 * @author Jacob Head
 */

public class State implements VHDLSignal, Serializable {

	private BitProduct bitProduct;
	private String id;
	private String encodingId;
	private int encodingCount;


	/**
	 * Class Constructor. Initializes with an empty bit product.
	 * 
	 * @param id id of the state.
	 * @param encodingId string representing id of the encoded bits.
	 */
	public State(String id, String encodingId) {
		this.bitProduct = new BitProduct();
		this.id = id;
		this.encodingId = encodingId;
		this.encodingCount = 0;
	}

	/**
	 * Getter for bit product.
	 * 
	 * @return The bit product stored in this state.
	 */
	public BitProduct getBitProduct() {
		return this.bitProduct;
	}

	/**
	 * Getter for id.
	 * 
	 * @return The state id.
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * Setter for id.
	 * 
	 * @param id The new id to set.
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Getter for encoding id.
	 * 
	 * @return The encoding id.
	 */
	public String getEncodingId() {
		return encodingId;
	}

	/**
	 * Setter for encoding id.
	 * 
	 * @param encodingId The new encoding id.
	 */
	public void setEncodingId(String encodingId) {
		this.encodingId = encodingId;
		BitProduct bp = bitProduct();
		for(int i = 0; i < bp.length(); i++) {
			bp.get(i).setId(encodingId + Integer.toString(bp.length() - 1 - i));
		}
	}

	/**
	 * Getter for encoding count.
	 * 
	 * @return The number of encoding variables.
	 */
	public int getEncodingCount() {
		return encodingCount;
	}
	
	/**
	 * Sets the number of Bits needed to represent the state.
	 * Automatically readjusts the state's bit product.
	 * 
	 * @param encodingCount
	 */
	public void setEncodingCount(int encodingCount) {
		this.encodingCount = encodingCount;
		while(bitProduct().length() < this.encodingCount) {
			pushBit(BitValue.LOW);
		}
		while(bitProduct().length() > this.encodingCount) {
			popBit();
		}
	}

	/**
	 * Getter for bit product.
	 * 
	 * @return The bit product.
	 */
	public BitProduct bitProduct() {
		return this.bitProduct;
	}

	/**
	 * Adds a bit to the end bit product and auto generates its label.
	 * 
	 * @param bitVal value of the bit to be added.
	 */
	public void pushBit(BitValue bitVal) {
		BitProduct bp = getBitProduct();
		String bitId = encodingId + Integer.toString(bp.length());
		getBitProduct().addFront(new BitVar(bitId, bitVal));
	}

	/**
	 * Removes a bit from the end of the bit product.
	 */
	public void popBit() {
		BitProduct bp = getBitProduct();
		bp.remove(0);
	}

	/**
     * Outputs a string representation of the state with its encoded bits
     * 
     * @return String representation of the state.
     */
	public String toEncodedString() {
		String output = "";
		String seperator = ".";
		BitProduct bp = getBitProduct();
		for(int i = 0; i < bp.length(); i++) {
			output += bp.get(i).toString();
			if(i != bp.length() - 1) {
				output += seperator;
			}
		}
		return output;
	}

	/**
     * Overrides java Object toString method 
     * @see Object
     * 
     * @return String representation of the state.
     */
	// @Override
	public String toString() {
		return this.getId();
	}

}