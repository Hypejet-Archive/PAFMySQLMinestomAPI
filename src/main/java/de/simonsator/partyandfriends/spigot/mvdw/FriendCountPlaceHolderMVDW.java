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
	public FriendCountPlaceHolderMVDW(Plugin pPlugin) {
		PlaceholderAPI.registerPlaceholder(pPlugin, "friend_count", this);
	}

	@Override
	public String onPlaceholderReplace(PlaceholderReplaceEvent pEvent) {
		return getFriendCount(pEvent.getOfflinePlayer().getUniqueId()).toString();
	}

}
