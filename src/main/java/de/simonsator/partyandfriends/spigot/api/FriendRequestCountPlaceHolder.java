package de.simonsator.partyandfriends.spigot.api;

import de.simonsator.partyandfriends.spigot.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.spigot.api.pafplayers.PAFPlayerManager;

import java.util.UUID;

public interface FriendRequestCountPlaceHolder {
	default Integer getFriendRequestCount(UUID pPlayer) {
		return getFriendRequestCount(PAFPlayerManager.getInstance().getPlayer(pPlayer));
	}

	default Integer getFriendRequestCount(String pPlayer) {
		return getFriendRequestCount(PAFPlayerManager.getInstance().getPlayer(pPlayer));
	}

	default Integer getFriendRequestCount(PAFPlayer pPlayer) {
		return pPlayer.getRequests().size();
	}

}
