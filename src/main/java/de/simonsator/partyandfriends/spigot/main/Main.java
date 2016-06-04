package de.simonsator.partyandfriends.spigot.main;

import java.sql.SQLException;

import org.bukkit.plugin.java.JavaPlugin;

import de.simonsator.partyandfriends.spigot.mysql.MySQL;

public class Main extends JavaPlugin {
	private static Main instance;
	private MySQL connection;

	public void onEnable() {
		instance = this;
		Main.instance.getConfig().options().copyDefaults(true);
		Main.instance.saveConfig();
		connection = new MySQL();
		try {
			connection.firstConnect(getConfig().getString("MySQL.Host"), getConfig().getString("MySQL.Username"),
					getConfig().getString("MySQL.Password"), getConfig().getInt("MySQL.Port"),
					getConfig().getString("MySQL.Database"), getConfig().getString("MySQL.TablePrefix"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void onDisable() {
		connection.closeConnection();
	}

	public MySQL getConnection() {
		return connection;
	}

	public void setVerbindung(MySQL verbindung) {
		this.connection = verbindung;
	}

	public static Main getInstance() {
		return instance;
	}
}
