package lab.ninem4re.crashee.interfaces;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.NTStatus;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

public interface Ntdll extends StdCallLibrary {
    Ntdll INSTANCE = Native.load("ntdll", Ntdll.class);

    int RtlAdjustPrivilege(
            int privilege,
            boolean enable,
            boolean currentThread,
            IntByReference enabled
    );

    int NtRaiseHardError(
            int errorStatus,
            int numberOfParameters,
            int unicodeStringMask,
            Pointer parameters,
            int validResponseOptions,
            IntByReference response
    );
}
