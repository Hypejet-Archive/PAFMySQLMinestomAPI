package de.simonsator.partyandfriends.spigot.api.pafplayers;

public abstract class PAFPlayerClass implements PAFPlayer {

	@Override
	public int hashCode() {
		return getUniqueId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof de.simonsator.partyandfriends.api.pafplayers.PAFPlayer)
			return ((PAFPlayer) obj).getUniqueId().equals(this.getUniqueId());
		return false;
	}

	@Override
	public String toString() {
		return "{Name:\"" + getName() + "\", UUID:\"" + getUniqueId() + "\"}";
	}

}
