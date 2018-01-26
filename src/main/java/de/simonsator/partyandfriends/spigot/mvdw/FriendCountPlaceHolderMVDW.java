package de.simonsator.partyandfriends.spigot.mvdw;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import de.simonsator.partyandfriends.spigot.api.FriendCountPlaceHolder;
import org.bukkit.plugin.Plugin;

/**
 * @author Simonsator
 * @version 1.0.0 09.04.17
 */
public class FriendCountPlaceHolderMVDW extends FriendCountPlaceHolder implements PlaceholderReplacer {
	private final boolean IS_ONLINE_SERVER;

	public FriendCountPlaceHolderMVDW(Plugin pPlugin) {
		PlaceholderAPI.registerPlaceholder(pPlugin, "friend_count", this);
		IS_ONLINE_SERVER = pPlugin.getConfig().getBoolean("IsOnlineServer");
	}

	@Override
	public String onPlaceholderReplace(PlaceholderReplaceEvent pEvent) {
		if (IS_ONLINE_SERVER)
			return getFriendCount(pEvent.getOfflinePlayer().getUniqueId()).toString();
		else
			return getFriendCount(pEvent.getOfflinePlayer().getName()).toString();
	}

}
