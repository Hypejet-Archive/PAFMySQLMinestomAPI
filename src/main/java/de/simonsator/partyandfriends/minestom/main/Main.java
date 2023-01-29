package de.simonsator.partyandfriends.minestom.main;

import com.moandjiezana.toml.Toml;
import de.simonsator.partyandfriends.minestom.communication.sql.MySQLData;
import de.simonsator.partyandfriends.minestom.pafplayers.manager.PAFPlayerManagerMySQL;
import de.simonsator.partyandfriends.minestom.utilities.disable.Disabler;
import org.hypejet.hypestom.Extension;

import java.io.*;
import java.nio.file.Path;

public class Main extends Extension {
	private static Main instance;
	private MySQLData mySQLData;

	public MySQLData getMySQLData() {
		return mySQLData;
	}

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void initialize() {
		instance = this;
		mySQLData = new MySQLData(getConfig().getString("Host"),
				getConfig().getString("Username"), getConfig().getString("Password"),
				Math.toIntExact(getConfig().getLong("Port")), getConfig().getString("Database"),
				getConfig().getString("TablePrefix"), getConfig().getBoolean("UseSSL"));
		new PAFPlayerManagerMySQL(mySQLData);
	}

	@Override
	public void terminate() {
		Disabler.getInstance().disableAll();
	}
}
