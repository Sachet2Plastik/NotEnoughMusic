package sachet2plastik.notenoughmusic;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sachet2plastik.notenoughmusic.item.ModItems;
import sachet2plastik.notenoughmusic.sound.ModSound;

public class NotEnoughMusic implements ModInitializer {
	public static final String MOD_ID = "notenoughmusic";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public enum Artists {
		T_EN_M("t_en_m/"),
		LAUDIVIDNI("laudividni/"),
		STEREONYX("stereonyx/");

		private final String Prefix;

		Artists(String prefix) {
			Prefix = prefix;
		}

		public String prefix() {
			return Prefix;
		}
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello From Not Enough Music!");
		ModItems.registerModItems();
		ModSound.registerSounds();
	}
}
