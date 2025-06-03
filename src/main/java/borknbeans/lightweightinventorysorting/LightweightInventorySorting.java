package borknbeans.lightweightinventorysorting;

import net.fabricmc.api.ModInitializer;
// import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
// import net.minecraft.text.Text;

// import static net.minecraft.server.command.CommandManager.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LightweightInventorySorting implements ModInitializer {
	public static final String MOD_ID = "lightweight-inventory-sorting";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final String ENCODED_SNAPSHOT = "H4sIAAAAAAAA/8XTwQ6CMAyA4XfpmYMIHtyrGEPGNuLiaEkdEkN4d49EbQRE4/3Ltv7bevB4dRiJb6AOPXgLCmqPzrCuomooekJIwFCLEVQ6JC+m9GxORRM0ni+j3P6bZj+QuSA/T5RuBBp90zhbaGbqJuZeQMsWbXDvD2k1WhcmZ5nLPMdR7EWha0I7op3UY0XedcnmbPztZE9BcimIIcYqUOd44Wric3u8JuknyMmybDgOd5oBulY9BAAA";

	@Override
	public void onInitialize() {
		// CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("lis-load")
		// 	.executes(context -> {
		// 		var snapshot = new SortSnapshotServerside().decode(ENCODED_SNAPSHOT);

		// 		var source = context.getSource();
		// 		var player = source.getPlayer();

		// 		if (player == null) {
		// 			source.sendFeedback(() -> Text.literal("Player is null"), false);
		// 			return 1;
		// 		}

		// 		var i = 9;
		// 		for (var item : snapshot.inventory) {
		// 			player.getInventory().setStack(i, item);
		// 			i++;
		// 		}

		// 		return 1;
		// 	})));
		
		LOGGER.info("Lightweight Inventory Sorting initialized on the server!");
	}
}