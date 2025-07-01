package redactedrice.compiler.instructions.basic;


import java.util.Arrays;

import redactedrice.compiler.CompilerUtils;
import redactedrice.compiler.instructions.BasicInstruction;
import redactedrice.compiler.instructions.basic.subtypes.CpByte;
import redactedrice.compiler.instructions.basic.subtypes.CpReg;

public abstract class Cp extends BasicInstruction {
    protected Cp(int size) {
        super(size);
    }

    public static Cp create(String[] args) {
        final String SUPPORT_STRING = "Cp only supports (Byte) or (Register): Given ";
        if (args.length != 1) {
            throw new IllegalArgumentException(SUPPORT_STRING + Arrays.toString(args));
        }

        try {
            return new CpByte(CompilerUtils.parseByteArg(args[0]));
        } catch (IllegalArgumentException iae) {
            // The instruct doesn't fit - try the next one
        }

        try {
            return new CpReg(CompilerUtils.parseRegisterArg(args[0]));
        } catch (IllegalArgumentException iae) {
            // The instruct doesn't fit
            // Could throw here but kept to preserve the pattern being used for
            // the instructs to support more easily adding future ones without
            // forgetting to add the throw at the end
        }

        throw new IllegalArgumentException(SUPPORT_STRING + Arrays.toString(args));
    }
}
