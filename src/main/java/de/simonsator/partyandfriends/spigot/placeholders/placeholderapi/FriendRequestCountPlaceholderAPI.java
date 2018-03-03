package de.simonsator.partyandfriends.spigot.placeholders.placeholderapi;

import de.simonsator.partyandfriends.spigot.api.FriendRequestCountPlaceHolder;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FriendRequestCountPlaceholderAPI extends EZPlaceholderHook implements FriendRequestCountPlaceHolder {
	private final boolean IS_ONLINE_SERVER;

	public FriendRequestCountPlaceholderAPI(Plugin pPlugin) {
		super(pPlugin, "friendsapi");
		IS_ONLINE_SERVER = pPlugin.getConfig().getBoolean("IsOnlineServer");
	}

	@Override
	public String onPlaceholderRequest(Player pPlayer, String pIdentifier) {
		if (pIdentifier.equals("friendrequestcount"))
			if (IS_ONLINE_SERVER)
				return getFriendRequestCount(pPlayer.getUniqueId()).toString();
			else
				return getFriendRequestCount(pPlayer.getName()).toString();
		return null;
	}

}
