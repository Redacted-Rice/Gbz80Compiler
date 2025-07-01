package redactedrice.compiler.instructions.basic.subtypes;


import java.io.IOException;

import redactedrice.gbcframework.QueuedWriter;
import redactedrice.compiler.CompilerConstants.Register;
import redactedrice.compiler.instructions.basic.Ld;

public class LdRegReg extends Ld {
    Register to;
    Register from;

    public LdRegReg(Register loadTo, Register loadFrom) {
        super(1); // size
        to = loadTo;
        from = loadFrom;
    }

    @Override
    public void writeStaticBytes(QueuedWriter writer) throws IOException {
        writer.append((byte) (0x40 | (to.getValue() << 3) | (from.getValue())));
    }
}
