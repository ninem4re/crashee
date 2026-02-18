package lab.ninem4re.crashee;

import lab.ninem4re.crashee.config.CrasheeMidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public class CrasheeClient implements ClientModInitializer {
	Identifier MOD_ID = Identifier.of("lab.ninem4re", "crashee");

	public static boolean inGame;

	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			inGame = (MinecraftClient.getInstance().world != null);
		});
	}
}