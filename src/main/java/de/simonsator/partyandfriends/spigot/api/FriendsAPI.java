package de.simonsator.partyandfriends.spigot.api;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;

import de.simonsator.partyandfriends.spigot.main.Main;

/**
 * The API for the friends system
 *
 * @author Simonsator
 * @version 1.0.0
 */
public class FriendsAPI {

	/**
	 * @param pPlayerID1 The id of the first player
	 * @param pPlayerID2 The id of the second player
	 * @return Returns if the two given players are friends
	 */
	public static boolean isAFriendOf(int pPlayerID1, int pPlayerID2) {
		return Main.getInstance().getConnection().isAFriendOf(pPlayerID1, pPlayerID2);
	}

	/**
	 * @param pPlayerID The id of the player who you want to find the friends of.
	 * @return Returns the ids of the friends of a player
	 * @author Simonsator
	 * @version 1.0.1
	 */
	public static ArrayList<Integer> getFriends(int pPlayerID) {
		return Main.getInstance().getConnection().getFriends(pPlayerID);
	}

	/**
	 * Returns the settings of a player. If it's 0 the setting is false. If it's
	 * 1 it's true.
	 *
	 * @param pPlayerID   The id of the player
	 * @param pSettingsID The ID of the setting
	 * @return Returns the settings of a player in an Array.
	 * <ul>
	 * <li>The first worth in the Array index is the setting if a player
	 * receives friends requests. If it's 0 the player allows friend
	 * request. If it's one he doesn't allows friend requests.</li>
	 * <li>The second worth in the Array index is the setting if a
	 * player can receives party invitations from everybody or only from
	 * friends. If it's 0 the player can get invited by everybody. If
	 * it's two he can only get invited by his friends.</li>
	 * <li>The third worth in the Array index is the setting if friends
	 * can send this player messages If it's 0 he receive messages. If
	 * it's he don't receive messages.</li>
	 * <li>The fourth worth in the Array index is the setting if the
	 * player will be shown as online. If it's 0 he will be shown as
	 * online. If it's 1 he will be shown as offline even if he is
	 * online.</li>
	 * <li>The fifth worth in the Array index is the setting if friends
	 * can jump to this player. If the worth is 0 friends can jump to
	 * him. If it's 1 players cannot jump to him.</li>
	 * </ul>
	 * @author Simonsator
	 * @version 1.0.0
	 */
	public static int getSettingsWorth(int pPlayerID, int pSettingsID) {
		return Main.getInstance().getConnection().getSettingsWorth(pPlayerID, pSettingsID);
	}

	/**
	 * @param pPlayerName The name of the player.
	 * @return Returns the id of the player. It's -1 if the player does not
	 * exist.
	 */
	public static int getPlayerID(String pPlayerName) {
		return Main.getInstance().getConnection().getPlayerID(pPlayerName);
	}

	/**
	 * @param pPlayer The player
	 * @return Returns the id of the player. It's -1 if the player does not
	 * exist.
	 */
	public static int getPlayerID(Player pPlayer) {
		return Main.getInstance().getConnection().getPlayerID(pPlayer.getName());
	}

	/**
	 * @param pPlayerUUID The UUID of the player
	 * @return Returns the id of the player. It's -1 if the player does not
	 * exist.
	 */
	public static int getPlayerID(UUID pPlayerUUID) {
		return Main.getInstance().getConnection().getPlayerID(pPlayerUUID);
	}

	/**
	 * @param pPlayerID The ID of the player
	 * @return Returns the name of the player.
	 */
	public static String getNameByPlayerID(int pPlayerID) {
		return Main.getInstance().getConnection().getName(pPlayerID);
	}
}
