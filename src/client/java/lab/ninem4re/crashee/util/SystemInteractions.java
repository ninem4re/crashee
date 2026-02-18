package lab.ninem4re.crashee.util;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import lab.ninem4re.crashee.interfaces.Ntdll;
import org.apache.commons.lang3.SystemUtils;

import java.io.IOException;

public class SystemInteractions {
    public static boolean shutdown(int time) throws IOException {
        String shutdownCommand = null;
        String t = time == 0 ? "now" : String.valueOf(time);

        if (SystemUtils.IS_OS_AIX)
            shutdownCommand = "shutdown -Fh " + t;
        else if (SystemUtils.IS_OS_FREE_BSD || SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC
                || SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_NET_BSD
                || SystemUtils.IS_OS_OPEN_BSD || SystemUtils.IS_OS_UNIX)
            shutdownCommand = "shutdown -h -f " + t;
        else if (SystemUtils.IS_OS_HP_UX)
            shutdownCommand = "shutdown -hy " + t;
        else if (SystemUtils.IS_OS_IRIX)
            shutdownCommand = "shutdown -y -g " + t;
        else if (SystemUtils.IS_OS_SOLARIS || SystemUtils.IS_OS_SUN_OS)
            shutdownCommand = "shutdown -y -i5 -g" + t;
        else if (SystemUtils.IS_OS_WINDOWS)
            shutdownCommand = "shutdown.exe /s /f /t " + (time == 0 ? "0" : String.valueOf(time));
        else
            return false;

        Runtime.getRuntime().exec(shutdownCommand);
        return true;
    }


    public static void restart(int time) throws IOException {
        String restartCommand = null;
        String t = time == 0 ? "now" : String.valueOf(time);

        if (SystemUtils.IS_OS_AIX)
            restartCommand = "shutdown -Fr " + t;
        else if (SystemUtils.IS_OS_FREE_BSD || SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC
                || SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_NET_BSD
                || SystemUtils.IS_OS_OPEN_BSD || SystemUtils.IS_OS_UNIX)
            restartCommand = "shutdown -r -f " + t;
        else if (SystemUtils.IS_OS_HP_UX)
            restartCommand = "shutdown -ry " + t;
        else if (SystemUtils.IS_OS_IRIX)
            restartCommand = "shutdown -y -g " + t + " -i6";
        else if (SystemUtils.IS_OS_SOLARIS || SystemUtils.IS_OS_SUN_OS)
            restartCommand = "shutdown -y -i6 -g" + t;
        else if (SystemUtils.IS_OS_WINDOWS)
            restartCommand = "shutdown.exe /r /f /t " + (time == 0 ? "0" : String.valueOf(time));
        else
            return;

        Runtime.getRuntime().exec(restartCommand);
    }

    public static void bsod() {
        IntByReference previous = new IntByReference();
        IntByReference response = new IntByReference();

        Ntdll ntdllInstance = Ntdll.INSTANCE;

        ntdllInstance.RtlAdjustPrivilege(
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
