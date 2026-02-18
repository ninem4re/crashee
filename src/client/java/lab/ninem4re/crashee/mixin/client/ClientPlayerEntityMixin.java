package lab.ninem4re.crashee.mixin.client;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import lab.ninem4re.crashee.config.CrasheeMidnightConfig;
import lab.ninem4re.crashee.interfaces.Ntdll;
import lab.ninem4re.crashee.util.SystemInteractions;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.fabricmc.loader.impl.util.log.LogLevel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.ClientStatusC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

import static lab.ninem4re.crashee.config.CrasheeMidnightConfig.*;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(at = @At("HEAD"), method = "updateHealth")
    private void init(float health, CallbackInfo ci) {
        if (health <= 0) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.getNetworkHandler() != null) {
                client.getNetworkHandler().sendPacket(
                        new ClientStatusC2SPacket(ClientStatusC2SPacket.Mode.PERFORM_RESPAWN)
                );
            }

            if (modEnabled) {
                if (crashCategory == CrashCategory.PC) {
                    if (crashType == CrashType.BSOD) {
                        SystemInteractions.bsod();
                    } else if (crashType == CrashType.RESTART) {
                        try {
                            SystemInteractions.restart(0);
                            Log.info(LogCategory.LOG, "RESTART");
                        } catch (IOException e) {
                            Log.error(LogCategory.LOG, "Something went wrong while trying to restart the computer! Stacktrace:");
                            e.printStackTrace();
                        }
                    } else if (crashType == CrashType.SHUTDOWN) {
                        try {
                            SystemInteractions.shutdown(0);
                        } catch (IOException e) {
                            Log.error(LogCategory.LOG, "Something went wrong while trying to shut down the computer! Stacktrace:");
                            e.printStackTrace();
                        }
                    }
                } else {
                    MinecraftClient.getInstance().scheduleStop();
                }
            }
        }

    }
}
