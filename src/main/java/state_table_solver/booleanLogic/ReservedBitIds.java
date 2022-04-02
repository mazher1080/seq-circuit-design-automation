package state_table_solver.booleanLogic;

/** 
 * <p> ReservedBitIds is a class used for validation of bit ids.
 * @author Jacob Head
 */

public class ReservedBitIds {

    public static final String[] CHARS = {"+",".","'"};
    public static final String[] STRINGS = {"-","1","0"};
    
    /**
     * Static function which validates a given id as a valid bit id.
     * 
     * @param id Id to validate.
     * @return True if valid, false otherwise.
     */
    public static boolean validateId(String id) {
        for(String str : ReservedBitIds.STRINGS) {
            if(id == str) 
                return false;
        }
        for(String character : ReservedBitIds.CHARS) {
            if(id.contains(character))
                return false;
        }
        return true;
    }
    
}
