package state_table_solver.booleanLogic;

import java.util.List;
import java.util.ArrayList;
import java.io.*;

/** <p> A class representing a Sum of Products form of boolean logic
 * @author Muneeb Azher
 * 
*/

public class SumOfProducts implements Serializable {

	private int length;
	private List<BitProduct> bitProducts;

	public SumOfProducts() {
        this.length = 0;
        this.bitProducts = new ArrayList<BitProduct>();
	}

	public SumOfProducts(BitProduct... bitProductArgs) {
		List<BitProduct> productList = new ArrayList<BitProduct>();
		for (BitProduct bp : bitProductArgs) {
			productList.add(bp);
		}
		this.bitProducts = productList;
		this.length = bitProductArgs.length;
	}

	/** Multiplies two or more sum of products together by distributive law
	 * 
	 * @param sumOfProductArgs All sum of product arguments to be multiplited together
	 */
	public void distribute(SumOfProducts... sumOfProductArgs) {
        for (SumOfProducts sp : sumOfProductArgs) {
			for (int j = 0; j < sp.length() - 1; j++) {
				BitProduct operand1 = sp.get(j);
				BitProduct operand2 = sp.get(j + 1);
				operand1.append(operand2);
				this.add(operand1);
			}
		}	
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

}