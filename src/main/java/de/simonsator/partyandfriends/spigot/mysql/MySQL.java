package de.simonsator.partyandfriends.spigot.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;

import de.simonsator.partyandfriends.spigot.main.Main;

public class MySQL {

	/**
	 * The MySQL database
	 */
	private String database;
	/**
	 * The url of the SQL server
	 */
	private String url;
	private String tablePrefix;
	private Connection connnection;

	/**
	 * Connects to the MySQL server
	 * 
	 * @param pHost
	 *            The MySQL host
	 * @param pUsername
	 *            The MySQL user
	 * @param pPassword
	 *            The MySQL password
	 * @param pPort
	 *            The port of the MySQL server
	 * @param pDatabase
	 *            The MySQL database
	 * @param pTablePrefix
	 *            The prefix of the MySQL table
	 * @throws ClassNotFoundException
	 *             Will never happen, because it is integrated in Bungeecord
	 * @throws SQLException
	 *             Happens if the plugin cannot connect to the MySQL Server
	 * @author Simonsator
	 * @version 1.0.0
	 * @param pTablePrefix
	 */
	public void firstConnect(String pHost, String pUsername, String pPassword, int pPort, String pDatabase,
			String pTablePrefix) throws ClassNotFoundException, SQLException {
		url = "jdbc:mysql://" + pHost + ":" + pPort + "/?user=" + pUsername + "&password=" + pPassword;
		database = pDatabase;
		tablePrefix = pTablePrefix;
		connnection = createConnection();
	}

	public Connection getConnection() {
		try {
			if (connnection != null && connnection.isValid(6))
				return connnection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connnection = createConnection();
	}

	private Connection createConnection() {
		try {
			closeConnection();
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void closeConnection() {
		try {
			if (connnection != null)
				connnection.close();
		} catch (SQLException e) {
		}
	}

	public int getPlayerID(Player pPlayer) {
		if (Main.getInstance().getConfig().getString("General.OfflineServer").equalsIgnoreCase("true"))
			return getPlayerID(pPlayer.getName());
		return getPlayerID(pPlayer.getUniqueId());
	}

	public int getSettingsWorth(int pPlayerID, int pSettingsID) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = (stmt = con.createStatement()).executeQuery(
					"select settings_worth from `" + database + "`." + tablePrefix + "settings WHERE player_id='"
							+ pPlayerID + "' AND settings_id='" + pSettingsID + "' LIMIT 1");
			if (rs.next()) {
				return rs.getInt("settings_worth");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * Gives out the IDs of the friends of a player
	 * 
	 * @param pPlayerID
	 *            The ID of the player
	 * @return Returns the IDs of the friends of a player
	 * @author Simonsator
	 * @version 1.0.0
	 */
	public ArrayList<Integer> getFriends(int pPlayerID) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<>();
		try {
			rs = (stmt = con.createStatement()).executeQuery("select friend2_id from `" + database + "`." + tablePrefix
					+ "friend_assignment WHERE friend1_id='" + pPlayerID + "'");
			while (rs.next())
				list.add(rs.getInt("friend2_id"));
			stmt.close();
			rs.close();
			rs = (stmt = con.createStatement()).executeQuery("select friend1_id from `" + database + "`." + tablePrefix
					+ "friend_assignment WHERE friend2_id='" + pPlayerID + "'");
			while (rs.next())
				list.add(rs.getInt("friend1_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean isAFriendOf(int pPlayerID1, int pPlayerID2) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = (stmt = con.createStatement()).executeQuery("Select friend1_id FROM `" + database + "`." + tablePrefix
					+ "friend_assignment WHERE (friend1_id = '" + pPlayerID1 + "' AND friend2_id='" + pPlayerID2
					+ "') OR (friend1_id = '" + pPlayerID2 + "' AND friend2_id='" + pPlayerID1 + "') LIMIT 1");
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Returns the name of a player
	 * 
	 * @param pPlayerID
	 *            The ID of the player
	 * @return Returns the name of a player
	 * @author Simonsator
	 * @version 1.0.0
	 */
	public String getName(int pPlayerID) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = (stmt = con.createStatement()).executeQuery("select player_name from `" + database + "`." + tablePrefix
					+ "players WHERE player_id='" + pPlayerID + "' LIMIT 1");
			if (rs.next()) {
				return rs.getString("player_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	/**
	 * Returns the ID of a player
	 * 
	 * @param pUuid
	 *            The UUID of the player
	 * @return Returns the ID of a player
	 * @author Simonsator
	 * @version 1.0.0
	 */
	public int getPlayerID(UUID pUuid) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = (stmt = con.createStatement()).executeQuery("select player_id from `" + database + "`." + tablePrefix
					+ "players WHERE player_uuid='" + pUuid + "' LIMIT 1");
			if (rs.next()) {
				return rs.getInt("player_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * Returns the ID of a player
	 * 
	 * @param pPlayerName
	 *            Name of the player Returns the ID of a player
	 * @return Returns the ID of a player
	 * @author Simonsator
	 * @version 1.0.0
	 */
	public int getPlayerID(String pPlayerName) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = (stmt = con.createStatement()).executeQuery("select player_id from `" + database + "`." + tablePrefix
					+ "players WHERE player_name='" + pPlayerName + "' LIMIT 1");
			if (rs.next()) {
				return rs.getInt("player_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

}