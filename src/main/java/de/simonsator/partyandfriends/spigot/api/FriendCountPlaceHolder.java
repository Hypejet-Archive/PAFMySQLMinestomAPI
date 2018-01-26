package de.simonsator.partyandfriends.spigot.api;

import de.simonsator.partyandfriends.spigot.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.spigot.api.pafplayers.PAFPlayerManager;

import java.util.UUID;

/**
 * @author Simonsator
 * @version 1.0.0 09.04.17
 */
public abstract class FriendCountPlaceHolder {
	public Integer getFriendCount(UUID pPlayer) {
		return getFriendCount(PAFPlayerManager.getInstance().getPlayer(pPlayer));
	}

	public Integer getFriendCount(String pPlayer) {
		return getFriendCount(PAFPlayerManager.getInstance().getPlayer(pPlayer));
	}

	public Integer getFriendCount(PAFPlayer pPlayer) {
		return pPlayer.getFriends().size();
	}
}
