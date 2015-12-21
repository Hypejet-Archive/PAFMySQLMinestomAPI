package partyAndFriends.api;

import partyAndFriends.main.Main;

public class ClansAPI {
	/**
	 * Returns the id of the clan, where the player is in or 0, if he is not in
	 * a clan.
	 * 
	 * @param pPlayerID
	 *            The ID of the player (can be found via Friends API)
	 * @return Returns the id of the clan, where the player is in or 0, if he is
	 *         not in a clan.
	 */
	public static int getClanOf(int pPlayerID) {
		return Main.main.verbindung.getClanByID(pPlayerID);
	}

	/**
	 * Returns the name of a clan
	 * 
	 * @param pClanID
	 *            The ID of the clan
	 * @return Returns the name of a clan
	 */
	public static String getClanNameByID(int pClanID) {
		return Main.main.verbindung.getClanNameByID(pClanID);
	}

	/**
	 * Returns the IDs of the leaders of the clan in an Array.
	 * 
	 * @param pClanID
	 *            The ID of the clan
	 * @return Returns the IDs of the leaders of the clan in an Array
	 */
	public static int[] getClanLeadersIDs(int pClanID) {
		return Main.main.verbindung.getClanLeaders(pClanID);
	}

	/**
	 * Returns the IDs of the normal members of a clan in an Array.
	 * 
	 * @param pClanID
	 *            The ID of the clan.
	 * @return Returns the IDs of the normal members of a clan in an Array.
	 */
	public static int[] getNormalMembersIDOfClan(int pClanID) {
		return Main.main.verbindung.getPlayersInsideClanAsArray(pClanID);
	}

	/**
	 * Returns the Clan Tag of the given Clan.
	 * 
	 * @param pClanID
	 *            The ID of the Clan
	 * @return Returns the Clan Tag of the given Clan.
	 */
	public static String getClanTagByID(int pClanID) {
		return Main.main.verbindung.getClanTag(pClanID);
	}
	
}
