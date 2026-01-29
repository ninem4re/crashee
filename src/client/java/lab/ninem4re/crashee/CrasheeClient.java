package lab.ninem4re.crashee;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Identifier;

public class CrasheeClient implements ClientModInitializer {
	Identifier MOD_ID = Identifier.of("lab.ninem4re", "crashee");

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}