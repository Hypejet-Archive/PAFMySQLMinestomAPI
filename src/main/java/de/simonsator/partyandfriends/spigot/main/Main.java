package de.simonsator.partyandfriends.spigot.main;

import de.simonsator.partyandfriends.communication.sql.MySQLData;
import de.simonsator.partyandfriends.spigot.pafplayers.manager.PAFPlayerManagerMySQL;
import de.simonsator.partyandfriends.spigot.placeholders.mvdw.FriendCountPlaceHolderMVDW;
import de.simonsator.partyandfriends.spigot.placeholders.mvdw.FriendRequestCountPlaceHolderMVDW;
import de.simonsator.partyandfriends.spigot.placeholders.placeholderapi.FriendCountPlaceHolderPlaceholderAPI;
import de.simonsator.partyandfriends.spigot.placeholders.placeholderapi.FriendRequestCountPlaceholderAPI;
import de.simonsator.partyandfriends.utilities.disable.Disabler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	private static Main instance;
	private MySQLData mySQLData;

	public void onEnable() {
		instance = this;
		getConfig().options().copyDefaults(true);
		saveConfig();
		mySQLData = new MySQLData(getConfig().getString("MySQL.Host"),
				getConfig().getString("MySQL.Username"), getConfig().getString("MySQL.Password"),
				getConfig().getInt("MySQL.Port"), getConfig().getString("MySQL.Database"),
				getConfig().getString("MySQL.TablePrefix"), getConfig().getBoolean("MySQL.UseSSL"));
		new PAFPlayerManagerMySQL(mySQLData);
		final JavaPlugin plugin = this;
		getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
			if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
				new FriendCountPlaceHolderMVDW(plugin);
				new FriendRequestCountPlaceHolderMVDW(plugin);
			}
			if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
				new FriendCountPlaceHolderPlaceholderAPI(plugin).register();
				new FriendRequestCountPlaceholderAPI(plugin).register();
			}
		}, 10);
	}

	public void onDisable() {
		Disabler.getInstance().disableAll();
	}

	public MySQLData getMySQLData() {
		return mySQLData;
	}

	public static Main getInstance() {
		return instance;
	}
}
