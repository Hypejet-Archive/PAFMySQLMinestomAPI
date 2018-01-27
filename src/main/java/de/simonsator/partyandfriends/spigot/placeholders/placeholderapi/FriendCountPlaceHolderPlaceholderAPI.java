package de.simonsator.partyandfriends.spigot.placeholders.placeholderapi;

import de.simonsator.partyandfriends.spigot.api.FriendCountPlaceHolder;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FriendCountPlaceHolderPlaceholderAPI extends EZPlaceholderHook implements FriendCountPlaceHolder {
	private final boolean IS_ONLINE_SERVER;

	public FriendCountPlaceHolderPlaceholderAPI(Plugin pPlugin) {
		super(pPlugin, "friendcount");
		IS_ONLINE_SERVER = pPlugin.getConfig().getBoolean("IsOnlineServer");
	}

	@Override
	public String onPlaceholderRequest(Player pPlayer, String pIdentifier) {
		if (IS_ONLINE_SERVER)
			return getFriendCount(pPlayer.getUniqueId()).toString();
		else
			return getFriendCount(pPlayer.getName()).toString();

	}
}
