package state_table_solver;

/**
 * <p> The Utilties class is used to store random utiltiy 
 * functions needed throughout the project.
 * 
 * @author Jacob Head
 */

public class Utilities {
    /**
     * Computes the log base 2 of an integer.
     * 
     * @param x The int to take the log base 2 of.
     * @return The log base 2 of x.
     */
    public static double log2(int x) {
        return (Math.log(x) / Math.log(2));
    }
}