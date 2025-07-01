package redactedrice.compiler.instructions.basic.subtypes;


import java.io.IOException;

import redactedrice.gbcframework.QueuedWriter;
import redactedrice.compiler.CompilerConstants.Register;
import redactedrice.compiler.instructions.basic.Ld;

public class LdRegByte extends Ld {
    Register reg;
    byte value;

    public LdRegByte(Register reg, byte value) {
        super(2); // size
        this.reg = reg;
        this.value = value;
    }

    @Override
    public void writeStaticBytes(QueuedWriter writer) throws IOException {
        writer.append((byte) (0x06 | (reg.getValue() << 3)), value);
    }
}
