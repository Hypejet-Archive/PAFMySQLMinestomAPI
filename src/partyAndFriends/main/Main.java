package partyAndFriends.main;

import java.sql.SQLException;

import org.bukkit.plugin.java.JavaPlugin;

import partyAndFriends.mySQL.MySQL;

public class Main extends JavaPlugin {
	public static Main main;
	public MySQL verbindung;

	public void onEnable() {
		main = this;
		Main.main.getConfig().options().copyDefaults(true);
		Main.main.saveConfig();
		verbindung = new MySQL();
		try {
			verbindung.verbinde(getConfig().getString("MySQL.Host"), getConfig().getString("MySQL.Username"),
					getConfig().getString("MySQL.Password"), getConfig().getInt("MySQL.Port"),
					getConfig().getString("MySQL.Database"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void onDisable() {
		verbindung.close();
	}
}
