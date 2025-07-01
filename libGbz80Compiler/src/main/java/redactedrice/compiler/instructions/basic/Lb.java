package redactedrice.compiler.instructions.basic;


import java.io.IOException;
import java.util.Arrays;

import redactedrice.gbcframework.QueuedWriter;
import redactedrice.compiler.CompilerUtils;
import redactedrice.compiler.CompilerConstants.RegisterPair;
import redactedrice.compiler.instructions.BasicInstruction;

public class Lb extends BasicInstruction {
    public static final int SIZE = 3;
    private RegisterPair pair;
    private byte value1;
    private byte value2;

    public Lb(RegisterPair pair, byte value1, byte value2) {
        super(SIZE);
        this.pair = pair;
        this.value1 = value1;
        this.value2 = value2;
    }

    public static Lb create(String[] args) {
        final String SUPPORT_STRING = "Lb only supports (RegisterPair, byte, byte): Given ";
        if (args.length != 3) {
            throw new IllegalArgumentException(SUPPORT_STRING + Arrays.toString(args));
        }

        try {
            return new Lb(CompilerUtils.parseRegisterPairArg(args[0]),
                    CompilerUtils.parseByteArg(args[1]), CompilerUtils.parseByteArg(args[2]));
        } catch (IllegalArgumentException iae) {
            // The instruct doesn't fit
            // Could throw here but kept to preserve the pattern being used for
            // the instructs to support more easily adding future ones without
            // forgetting to add the throw at the end
        }

        throw new IllegalArgumentException(SUPPORT_STRING + Arrays.toString(args));
    }

    // @Override
    public void writeStaticBytes(QueuedWriter writer) throws IOException {
        writer.append((byte) (0x01 | (pair.getValue() << 4)), value2, value1);
    }
}
