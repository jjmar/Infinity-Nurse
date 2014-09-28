package infinitynurse.backend;

/**
 * Ensures user inputted data is valid
 * @author Justin
 *
 */
public class ValidInput {
	
	/**
	 * Returns whether any of data the user inputed is blank
	 * @param args The data within the entry fields
	 * @return If the user left field(s) blank
	 */
	public static boolean isEmpty(String... args){
		for (String field : args){
			if (field.length() == 0) return true;
		}
		return false;
	}
	
	
}
