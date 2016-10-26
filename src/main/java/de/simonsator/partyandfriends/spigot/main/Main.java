package de.simonsator.partyandfriends.spigot.main;

import de.simonsator.partyandfriends.communication.sql.MySQLData;
import de.simonsator.partyandfriends.spigot.mysql.MySQL;
import de.simonsator.partyandfriends.utilities.disable.Disabler;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	private static Main instance;
	private MySQL connection;
	private MySQLData mySQLData;

	public void onEnable() {
		instance = this;
		getConfig().options().copyDefaults(true);
		saveConfig();
		mySQLData = new MySQLData(getConfig().getString("MySQL.Host"),
				getConfig().getString("MySQL.Username"), getConfig().getString("MySQL.Password"),
				getConfig().getInt("MySQL.Port"), getConfig().getString("MySQL.Database"),
				getConfig().getString("MySQL.TablePrefix"));
		connection = new MySQL(mySQLData);
	}

	public void onDisable() {
		Disabler.getInstance().disableAll();
	}

	public MySQL getConnection() {
		return connection;
	}

	public MySQLData getMySQLData() {
		return mySQLData;
	}

	public static Main getInstance() {
		return instance;
	}
}
