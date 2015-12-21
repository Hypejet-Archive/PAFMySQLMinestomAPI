package partyAndFriends.mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.bukkit.entity.Player;

import partyAndFriends.main.Main;
import partyAndFriends.utilities.StringToArray;

public class MySQL {
	private Connection connect;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String database;
	private String url;

	public void verbinde(String pHost, String pUsername, String pPassword, int pPort, String pDatabase)
			throws ClassNotFoundException, SQLException {
		url = "jdbc:mysql://" + pHost + ":" + pPort + "/?user=" + pUsername + "&password=" + pPassword;
		database = pDatabase;
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection(url);
		statement = connect.createStatement();
	}

	public void verbinde() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection(url);
		statement = connect.createStatement();
	}

	public boolean istBefreundetMit(String player, String angeschrieben) {
		int idSender = getIDByPlayerName(player);
		int idAbfrage = getIDByPlayerName(angeschrieben);
		int[] anfragenID = getFreundeArray(idSender);
		int i = 0;
		while (anfragenID.length > i) {
			if (anfragenID[i] == idAbfrage) {
				return true;
			}
			i++;
		}
		return false;
	}

	public int getIDByPlayerName(String pName) {
		try {
			try {
				resultSet = statement.executeQuery(
						"select ID from " + database + ".freunde WHERE SpielerName='" + pName + "' LIMIT 1");
				if (resultSet.next()) {
					return resultSet.getInt("ID");
				} else {
					return -1;
				}
			} catch (SQLException e) {
				try {
					close();
					verbinde();
					return getIDByPlayerName(pName);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
					return -1;
				}
			}
		} catch (NullPointerException e) {
			return -1;
		}
	}

	public int[] getFreundeArray(int idSender) {
		StringTokenizer st = new StringTokenizer(freundeAusgeben(idSender), "|");
		int[] anfragenID = new int[st.countTokens()];
		int dl = 0;
		while (st.hasMoreTokens()) {
			anfragenID[dl] = Integer.parseInt(st.nextToken());
			dl++;
		}
		return anfragenID;
	}

	public ArrayList<String> getAnfragenArrayList(int id) {
		StringTokenizer st = new StringTokenizer(getAnfragen(id), "|");
		ArrayList<String> friends = new ArrayList<>();
		while (st.hasMoreTokens()) {
			friends.add(getNameDesSpielers(Integer.parseInt(st.nextToken())));
		}
		return friends;

	}

	public String getAnfragen(int id) {
		try {
			resultSet = statement.executeQuery(
					"select FreundschaftsAnfragenID from " + database + ".freunde WHERE ID='" + id + "' LIMIT 1");
			resultSet.next();
			return resultSet.getString("FreundschaftsAnfragenID");
		} catch (SQLException e) {
			try {
				close();
				verbinde();
				return getAnfragen(id);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				return "";
			}
		}
	}

	public String getNameDesSpielers(int pID) {
		try {
			resultSet = statement
					.executeQuery("select SpielerName from " + database + ".freunde WHERE ID='" + pID + "' LIMIT 1");
			resultSet.next();
			return resultSet.getString("SpielerName");
		} catch (SQLException e) {
			try {
				close();
				verbinde();
				return getNameDesSpielers(pID);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				return "";
			}
		}
	}

	/**
	 * Query the worth of the settings
	 * 
	 * @param player
	 *            The player
	 * @return Returns the worth of the settings
	 * @author Simonsator
	 * @version 1.0.0
	 */
	public int[] einstellungenAbfragen(Player player) {
		return einstellungenAbfragen(player.getName());
	}

	/**
	 * Query the worth of the settings
	 * 
	 * @param player
	 *            The player
	 * @return Returns the worth of the settings
	 * @author Simonsator
	 * @version 1.0.0
	 */
	public int[] einstellungenAbfragen(String player) {
		try {
			int playerID = getIDByPlayerName(player);
			resultSet = statement.executeQuery(
					"select einstellungAkzeptieren from " + database + ".freunde WHERE ID='" + playerID + "' LIMIT 1");
			int[] feld = new int[5];
			if (resultSet.next()) {
				feld[0] = resultSet.getInt("einstellungAkzeptieren");
			} else {
				feld[0] = 1;
			}
			resultSet = statement.executeQuery("select einstellungPartyNurFreunde from " + database
					+ ".freunde WHERE ID='" + playerID + "' LIMIT 1");
			if (resultSet.next()) {
				feld[1] = resultSet.getInt("einstellungPartyNurFreunde");
			} else {
				feld[1] = 0;
			}
			resultSet = statement.executeQuery(
					"select EinstellungSendMessages from " + database + ".freunde WHERE ID='" + playerID + "' LIMIT 1");
			if (resultSet.next()) {
				feld[2] = resultSet.getInt("EinstellungSendMessages");
			} else {
				feld[2] = 0;
			}
			resultSet = statement.executeQuery(
					"select EinstellungImmerOffline from " + database + ".freunde WHERE ID='" + playerID + "' LIMIT 1");
			if (resultSet.next()) {
				feld[3] = resultSet.getInt("EinstellungImmerOffline");
			} else {
				feld[3] = 0;
			}
			resultSet = statement.executeQuery(
					"select EinstellungJump from " + database + ".freunde WHERE ID='" + playerID + "' LIMIT 1");
			if (resultSet.next()) {
				feld[4] = resultSet.getInt("EinstellungJump");
			} else {
				feld[4] = 0;
			}
			return feld;
		} catch (SQLException e) {
			try {
				close();
				verbinde();
				return einstellungenAbfragen(player);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}

	public String freundeAusgeben(int iD) {
		try {
			resultSet = statement
					.executeQuery("select FreundeID from " + database + ".freunde WHERE ID='" + iD + "' LIMIT 1");
			if (resultSet.next()) {
				return resultSet.getString("FreundeID");
			} else {
				return "";
			}
		} catch (SQLException e) {
			try {
				close();
				verbinde();
				return freundeAusgeben(iD);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				return "";
			}
		}
	}

	public int getClanByID(int pPlayerID) {
		ResultSet resultSet;
		try {
			resultSet = statement
					.executeQuery("select Clan from " + database + ".freunde WHERE ID='" + pPlayerID + "' LIMIT 1");

			if (resultSet.next()) {
				return resultSet.getInt("Clan");
			} else {
				return 0;
			}
		} catch (SQLException e) {
			try {
				Main.main.verbindung.close();
				Main.main.verbindung.verbinde();
				return getClanByID(pPlayerID);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				return 0;
			}
		}
	}

	public String getClanNameByID(int clanID) {
		ResultSet resultSet;
		try {
			resultSet = statement
					.executeQuery("select ClanName from " + database + ".clan WHERE ID='" + clanID + "' LIMIT 1");

			if (resultSet.next()) {
				String empfangen = resultSet.getString("ClanName");
				return empfangen;
			} else {
				return null;
			}
		} catch (SQLException e) {
			try {
				Main.main.verbindung.close();
				Main.main.verbindung.verbinde();
				return getClanNameByID(clanID);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}

	public int[] getClanLeaders(int pClanID) {
		ResultSet resultSet;
		try {
			resultSet = statement
					.executeQuery("select Leader from " + database + ".clan WHERE ID='" + pClanID + "' LIMIT 1");

			if (resultSet.next()) {
				int[] empfangen = StringToArray.stringToIntegerArray(resultSet.getString("Leader"));
				return empfangen;
			} else {
				return null;
			}
		} catch (SQLException e) {
			try {
				Main.main.verbindung.close();
				Main.main.verbindung.verbinde();
				return getClanLeaders(pClanID);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}

	public int[] getPlayersInsideClanAsArray(int clanID) {
		return StringToArray.stringToIntegerArray(getPlayersInsideClanAsString(clanID));
	}

	public String getPlayersInsideClanAsString(int clanID) {
		try {
			ResultSet resultSet = statement
					.executeQuery("select Members from " + database + ".freunde WHERE ID='" + clanID + "' LIMIT 1");
			if (resultSet.next()) {
				return resultSet.getString("Members");
			} else {
				return "";
			}
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException e) {
			Main.main.verbindung.close();
			try {
				Main.main.verbindung.verbinde();
				return getPlayersInsideClanAsString(clanID);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}

	public String getClanTag(int pClanID) {
		ResultSet resultSet;
		try {
			resultSet = statement
					.executeQuery("select ClanTag from " + database + ".clan WHERE ID='" + pClanID + "' LIMIT 1");

			if (resultSet.next()) {
				String empfangen = resultSet.getString("ClanTag");
				return empfangen;
			} else {
				return null;
			}
		} catch (SQLException e) {
			try {
				Main.main.verbindung.close();
				Main.main.verbindung.verbinde();
				return getClanTag(pClanID);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * Closes the MySQL connection.
	 * 
	 * @author Simonsator
	 * @version 1.0.0
	 */
	public void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}