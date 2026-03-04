package lab.ninem4re.crashee;

import eu.midnightdust.lib.config.MidnightConfig;
import lab.ninem4re.crashee.config.CrasheeMidnightConfig;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Crashee implements ModInitializer {
	public static final String MOD_ID = "crashee";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, CrasheeMidnightConfig.class);
	}
}