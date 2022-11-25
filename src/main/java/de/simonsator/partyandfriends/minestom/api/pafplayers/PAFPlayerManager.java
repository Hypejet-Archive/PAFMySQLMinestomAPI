package de.simonsator.partyandfriends.minestom.api.pafplayers;


import java.util.UUID;

public abstract class PAFPlayerManager {
	private static PAFPlayerManager instance;

	public PAFPlayerManager() {
		instance = this;
	}

	public static PAFPlayerManager getInstance() {
		return instance;
	}

	public abstract PAFPlayer getPlayer(String pPlayer);

	public abstract PAFPlayer getPlayer(UUID pPlayer);

}
