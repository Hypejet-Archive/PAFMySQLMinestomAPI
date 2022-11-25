package de.simonsator.partyandfriends.minestom.pafplayers.manager;

import de.simonsator.partyandfriends.minestom.communication.sql.MySQLData;
import de.simonsator.partyandfriends.minestom.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.minestom.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.minestom.mysql.MySQL;
import de.simonsator.partyandfriends.minestom.pafplayers.mysql.PAFPlayerMySQL;
import de.simonsator.partyandfriends.minestom.utilities.disable.Deactivated;
import de.simonsator.partyandfriends.minestom.utilities.disable.Disabler;

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
