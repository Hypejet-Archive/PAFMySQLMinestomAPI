/**
 * This class converts a String to an Array
 * 
 * @see partyAndFriends.utilities.StringToArrayList
 * @author Simonsator
 * @version 1.0.0
 */
package partyAndFriends.utilities;

import java.util.StringTokenizer;

/**
 * This class converts a String to an Array
 * 
 * @see partyAndFriends.utilities.StringToArrayList
 * @author Simonsator
 * @version 1.0.0
 */
public class StringToArray {
	/**
	 * This method converts a String to an Array. It splits the String every
	 * time there is a "|".
	 * 
	 * @author Simonsator
	 * @version 1.0.0
	 * @param string
	 *            The String, which should be converted to a String
	 * @return Return the created Array
	 */
	public static String[] stringToArray(String string) {
		StringTokenizer friendAliasStringTokenizer = new StringTokenizer(string, "|");
		int friendAliasStringTokenizerLength = friendAliasStringTokenizer.countTokens();
		String[] friendAlias = new String[friendAliasStringTokenizerLength];
		for (int i = 0; i < friendAliasStringTokenizerLength; i++) {
			friendAlias[i] = friendAliasStringTokenizer.nextToken();
		}
		return friendAlias;
	}

	/**
	 * This method converts a String to an int[]. It splits the String every
	 * time there is a "|".
	 * 
	 * @author Simonsator
	 * @version 1.0.0
	 * @param string
	 *            The String, which should be converted to a String
	 * @return Return the created int[] Array
	 */
	public static int[] stringToIntegerArray(String string) {
		StringTokenizer friendAliasStringTokenizer = new StringTokenizer(string, "|");
		int friendAliasStringTokenizerLength = friendAliasStringTokenizer.countTokens();
		int[] friendAlias = new int[friendAliasStringTokenizerLength];
		for (int i = 0; i < friendAliasStringTokenizerLength; i++) {
			friendAlias[i] = Integer.parseInt(friendAliasStringTokenizer.nextToken());
		}
		return friendAlias;
	}
}
