package de.simonsator.partyandfriends.spigot.pafplayers.manager;

import de.simonsator.partyandfriends.communication.sql.MySQLData;
import de.simonsator.partyandfriends.spigot.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.spigot.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.spigot.mysql.MySQL;
import de.simonsator.partyandfriends.spigot.pafplayers.mysql.PAFPlayerMySQL;
import de.simonsator.partyandfriends.utilities.disable.Deactivated;
import de.simonsator.partyandfriends.utilities.disable.Disabler;

import java.util.UUID;

public class PAFPlayerManagerMySQL extends PAFPlayerManager implements Deactivated {
	private static MySQL connection;

	public PAFPlayerManagerMySQL(MySQLData pMySQLData) {
		connection = new MySQL(pMySQLData);
		Disabler.getInstance().registerDeactivated(this);
	}

	public PAFPlayer getPlayer(String pPlayer) {
		return getPlayer(connection.getPlayerID(pPlayer));
	}

	@Override
	public PAFPlayer getPlayer(UUID pPlayer) {
		return getPlayer(getConnection().getPlayerID(pPlayer));
	}

	public PAFPlayer getPlayer(int pPlayerID) {
		return new PAFPlayerMySQL(pPlayerID);
	}

	public static MySQL getConnection() {
		return connection;
	}

	@Override
	public void onDisable() {
		connection.closeConnection();
	}
}
