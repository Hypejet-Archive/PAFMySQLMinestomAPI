package de.simonsator.partyandfriends.spigot.error;

import de.simonsator.partyandfriends.spigot.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ErrorReporter implements Listener {
	private final String ERROR_MESSAGE;

	public ErrorReporter(String pErrorMessage) {
		ERROR_MESSAGE = pErrorMessage;
		System.out.println(pErrorMessage);
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.getInstance());
	}

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent pEvent) {
		pEvent.getPlayer().sendMessage(ERROR_MESSAGE);
	}
}
