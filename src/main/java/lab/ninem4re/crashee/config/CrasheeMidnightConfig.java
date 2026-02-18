package lab.ninem4re.crashee.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class CrasheeMidnightConfig extends MidnightConfig {
    @Entry(width = 200)
    public static boolean modEnabled = true;

    @Entry(width = 200)
    public static CrashCategory crashCategory = CrashCategory.PC;

    public enum CrashCategory {
        PC, MINECRAFT
    }

    @Entry(width = 200)
    public static CrashType crashType = CrashType.BSOD;

    public enum CrashType {
        BSOD, SHUTDOWN, RESTART
    }
}
