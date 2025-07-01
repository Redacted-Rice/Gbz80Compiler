package redactedrice.compiler.instructions.basic.subtypes;


import java.io.IOException;

import redactedrice.gbcframework.QueuedWriter;
import redactedrice.compiler.CompilerConstants.Register;
import redactedrice.compiler.instructions.basic.Cp;

public class CpReg extends Cp {
    public static final int SIZE = 1;
    Register reg;

    public CpReg(Register reg) {
        super(SIZE);
        this.reg = reg;
    }

    @Override
    public void writeStaticBytes(QueuedWriter writer) throws IOException {
        writer.append((byte) (0xB8 | reg.getValue()));
    }
}
