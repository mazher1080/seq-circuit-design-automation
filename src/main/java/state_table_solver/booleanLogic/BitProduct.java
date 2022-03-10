package state_table_solver.booleanLogic;

import java.util.List;
import java.util.ArrayList;
import java.io.*;

/** 
 * <p> BitProduct is a class which stores a bit with an associted identifier.
 * @author Jacob Head
 */

public class BitProduct implements Serializable {

	private int length;
	private int highBitCount;
	private List<Bit> bits;

	/**
     * Class Constructor. Initializes with no bits.
     */
	public BitProduct() {
		this.bits = new ArrayList<Bit>();
		this.length = 0;
		this.highBitCount = 0;
	}

	/**
	 * Class Constructor. Initializes with specified bits.
	 * 
	 * @param bits the bits to initialize the bit product with.
	 */
	public BitProduct(Bit... bitArgs) {
		List<Bit> bitList = new ArrayList<Bit>();
		for(Bit b : bitArgs) {
			bitList.add(b);
			setHighBitCountBasedOn(b, true);
		}
		this.bits = bitList;
		this.length = bitArgs.length;
	}
	
	/**
	 * Getter for bits.
	 * 
	 * @return The bits associated with the bit product.
	 */
	public List<Bit> getBits() {
		return this.bits;
	}

	/**
	 * Getter for high bit count.
	 * 
	 * @return The number of high bits in this number.
	 */
	public int getHighBitCount() {
		return highBitCount;
	}

	/**
	 * Setter for high bit count.
	 * 
	 * @param highBitCount The new high bit count.
	 */
	public void setHighBitCount(int highBitCount) {
		this.highBitCount = highBitCount;
	}

	/**
	 * Adds bit to end of bit product.
	 * 
	 * @param b The bit to add.
	 */
	public void add(Bit b) {
		getBits().add(b);
		setLength(length() + 1);
		setHighBitCountBasedOn(b, true);
	}

	/**
	 * Appends two bitProducts together.
	 * 
	 * @param bitProduct the bit product to append to the current bit product.
	 */
	public void append(BitProduct bitProduct) {
		for(Bit b : bitProduct.getBits()) {
			add(b);
		}
	}

	/**
	 * Removes a bit from the bit product at a given index.
	 * 
	 * @param i the integer to remove
	 */
	public void remove(int i) {
		getBits().remove(i);
		setLength(length() - 1);
	}

	/**
	 * Getter for the bit product length.
	 * 
	 * @return Length of the bit product.
	 */
	public int length() {
		return this.length;
	}

	/**
	 * Setter for the bit product length.
	 * 
	 * @param length Length to set.
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Gets the bit at the specified index in the bit product.
	 * 
	 * @param i Index to look for the bit.
	 * @return Bit at specified index.
	 */
	public Bit get(int i) {
		return getBits().get(i);
	}

	/**
     * Overrides java Object toString method 
     * @see Object
     * 
     * @return String representation of the bitValue.
     */
	public String toString() {
		String output = "";
		String seperator = ".";
		int bPLength = length();
		for(int i = 0; i < bPLength; i++) {
			output += get(i).toString();
			if(i != bPLength - 1)
				output += seperator;
		}
		return output;
	}

	/**
	 * Sets the high bit count based on whether a bit is high. Can be set to increment or decrement.
	 * 
	 * @param b The bit to check for HIGH value.
	 */
	private void setHighBitCountBasedOn(Bit b, boolean isIncrement) {
		if(b.getValue() == BitValue.HIGH)
			setHighBitCount(isIncrement ? getHighBitCount() + 1 : getHighBitCount() - 1);
	}

}