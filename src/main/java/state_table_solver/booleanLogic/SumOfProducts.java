package state_table_solver.booleanLogic;

import java.util.List;
import java.util.ArrayList;
import java.io.*;

/** <p> A class representing a Sum of Products form of boolean logic
 * @author Muneeb Azher
 * @author Jacob Head
 * 
*/

public class SumOfProducts implements Serializable {

	private int length;
	private List<BitProduct> bitProducts;

	/**
	 * Class constructor. Zero argument constructor.
	 */
	public SumOfProducts() {
        this.length = 0;
        this.bitProducts = new ArrayList<BitProduct>();
	}

	/**
	 * Class constructor. Multiple argument constructor.
	 * 
	 * @param bitProductArgs All bit products to add to the sum of product.
	 */
	public SumOfProducts(BitProduct... bitProductArgs) {
		List<BitProduct> productList = new ArrayList<BitProduct>();
		for (BitProduct bp : bitProductArgs) {
			productList.add(bp);
		}
		this.bitProducts = productList;
		this.length = bitProductArgs.length;
	}

	/** 
	 * Multiplies two or more sum of products together by distributive law.
	 * 
	 * @param sumOfProductArgs All sum of product arguments to be multiplited together.
	 */
	public SumOfProducts getDistributed(SumOfProducts... sumOfProductArgs) {
		if(sumOfProductArgs.length == 0)
			return null;
		SumOfProducts result = new SumOfProducts();
		result.append(this);
        for (int i = 0; i < sumOfProductArgs.length; i++) {
			result = result.getDistributedHelper(sumOfProductArgs[i]);
		}	
		return result;
	}

	/** Adds a new sum of products to the current sum of products
	 * 
	 * @param sop
	 */
	public void append(SumOfProducts sop) {
        for (int i = 0; i < sop.length(); i++) {
			add(sop.get(i));
        }
	}

	/**
	 * Adds a bit product to the end of the bit product list
	 * 
	 * @param bp Bit product to add.
	 */
	public void add(BitProduct bp) {
		this.bitProducts.add(bp);
		this.length = length() + 1;
	}

	/** Removes a bit product from the sum of products
	 * 
	 * @param i
	 */
	public void remove(int i) {
        this.bitProducts.remove(i);
		this.length = length() - 1;
	}

	/** 
	 * Getter for sum of product length.
	 * 
	 * @return length of SOP
	 */
	public int length() {
		return this.length;
	}

	/** Returns a bit product from the list
	 * 
	 * @param i
	 */
	public BitProduct get(int i) {
		return this.bitProducts.get(i);	
	}

	/** Overrides java Object toString method 
	 * 
	 * @return the string representation of a sum of products
	 */
	@Override
	public String toString() {
       String output = "";
       String plus = " + ";
       for (int i = 0; i < length(); i++) {
		   if (i == length() - 1) {
			   output += this.bitProducts.get(i).toString();
		   }
		   else {
			   output += this.bitProducts.get(i).toString() + plus;
		   }
       }
	   return output;
	}

	
	/**
	 * Multiplies exactly two sum of products together by distributive law.
	 * 
	 * @param sop1 First sum of products to distribute.
	 * @param sop2 Second sum of products to distribute.
	 */
	private SumOfProducts getDistributedHelper(SumOfProducts sop2) {
		SumOfProducts result = new SumOfProducts();
		for(int i = 0; i < this.length(); i++) {
			for(int j = 0; j < sop2.length(); j++) {
				BitProduct bp = new BitProduct();
				bp.append(this.get(i));
				bp.append(sop2.get(j));
				result.add(bp);
			}
		}
		return result;
	}

}