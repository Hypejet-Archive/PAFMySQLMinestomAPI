package de.simonsator.partyandfriends.minestom.api;

import de.simonsator.partyandfriends.minestom.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.minestom.api.pafplayers.PAFPlayerManager;

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
