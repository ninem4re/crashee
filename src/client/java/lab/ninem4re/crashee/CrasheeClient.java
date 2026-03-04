package lab.ninem4re.crashee;

import lab.ninem4re.crashee.config.CrasheeMidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public class CrasheeClient implements ClientModInitializer {
	Identifier MOD_ID = Identifier.of("lab.ninem4re", "crashee");


	@Override
	public void onInitializeClient() {
	}
}