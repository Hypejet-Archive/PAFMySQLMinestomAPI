package de.simonsator.partyandfriends.minestom.utilities.disable;

import java.util.ArrayList;
import java.util.List;

public class Disabler {
	private static Disabler instance = null;
	private final List<Deactivated> toDeactivate = new ArrayList<>();


	public void disableAll() {
		for (Deactivated toDeactivated : toDeactivate)
			toDeactivated.onDisable();
	}

	public void registerDeactivated(Deactivated pDeactivated) {
		toDeactivate.add(pDeactivated);
	}

	public static Disabler getInstance() {
		if (instance != null) return instance;
		return instance = new Disabler();
	}

}
