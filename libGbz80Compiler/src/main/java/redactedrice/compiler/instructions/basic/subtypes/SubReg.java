package redactedrice.compiler.instructions.basic.subtypes;


import java.io.IOException;

import redactedrice.gbcframework.QueuedWriter;
import redactedrice.compiler.CompilerConstants.Register;
import redactedrice.compiler.instructions.basic.Sub;

public class SubReg extends Sub {
    public static final int SIZE = 1;
    Register reg;

    public SubReg(Register reg) {
        super(SIZE);
        this.reg = reg;
    }

    @Override
    public void writeStaticBytes(QueuedWriter writer) throws IOException {
        writer.append((byte) (0x90 | reg.getValue()));
    }
}
