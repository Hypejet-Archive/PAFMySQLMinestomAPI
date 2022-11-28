package de.simonsator.partyandfriends.minestom.main;

import com.moandjiezana.toml.Toml;
import de.simonsator.partyandfriends.minestom.communication.sql.MySQLData;
import de.simonsator.partyandfriends.minestom.pafplayers.manager.PAFPlayerManagerMySQL;
import de.simonsator.partyandfriends.minestom.utilities.disable.Disabler;
import me.heroostech.citystom.Extension;

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

	private Toml getConfig() {
		Toml toml = new Toml();
		File file = Path.of(String.valueOf(getDataDirectory()), "config.toml").toFile();

		if(!file.exists()) {
			try {
				InputStream configResource = getClass().getClassLoader().getResourceAsStream("config.toml");
				if (configResource == null) return null;
				byte[] bytes = configResource.readAllBytes();
				if(!getDataDirectory().toFile().exists())
					getDataDirectory().toFile().mkdir();
				File f = new File(getDataDirectory().toFile(), "config.toml");
				f.createNewFile();
				OutputStream stream = new FileOutputStream(f);
				stream.write(bytes);
				configResource.close();
				stream.close();
				file = f;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return toml.read(file);
	}
}
