package de.simonsator.partyandfriends.spigot.placeholders.mvdw;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import de.simonsator.partyandfriends.spigot.api.FriendRequestCountPlaceHolder;
import org.bukkit.plugin.Plugin;

public class FriendRequestCountPlaceHolderMVDW implements PlaceholderReplacer, FriendRequestCountPlaceHolder {
	private final boolean IS_ONLINE_SERVER;

	public FriendRequestCountPlaceHolderMVDW(Plugin pPlugin) {
		PlaceholderAPI.registerPlaceholder(pPlugin, "friend_request_count", this);
		IS_ONLINE_SERVER = pPlugin.getConfig().getBoolean("IsOnlineServer");
	}

	@Override
	public String onPlaceholderReplace(PlaceholderReplaceEvent pEvent) {
		if (IS_ONLINE_SERVER)
			return getFriendRequestCount(pEvent.getOfflinePlayer().getUniqueId()).toString();
		else
			return getFriendRequestCount(pEvent.getOfflinePlayer().getName()).toString();
	}

}
