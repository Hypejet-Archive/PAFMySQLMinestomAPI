package de.simonsator.partyandfriends.minestom.error;

import de.simonsator.partyandfriends.minestom.main.Main;
import me.heroostech.citystom.listener.Listener;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.PlayerSpawnEvent;

public class ErrorReporter implements Listener<PlayerSpawnEvent> {
	private final String ERROR_MESSAGE;

	public ErrorReporter(String pErrorMessage) {
		ERROR_MESSAGE = pErrorMessage;
		System.out.println(pErrorMessage);
		Main.getInstance().registerEvent(this);
	}

	@Override
	public EventNode<PlayerSpawnEvent> events() {
		EventNode<PlayerSpawnEvent> node = EventNode.type("spawn", EventFilter.from(PlayerSpawnEvent.class, Player.class, PlayerSpawnEvent::getPlayer));

		node.addListener(PlayerSpawnEvent.class, pEvent -> {
			pEvent.getPlayer().sendMessage(ERROR_MESSAGE);
		});

		return node;
	}
}
