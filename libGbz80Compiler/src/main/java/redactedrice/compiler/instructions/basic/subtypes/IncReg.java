package redactedrice.compiler.instructions.basic.subtypes;


import java.io.IOException;

import redactedrice.gbcframework.QueuedWriter;
import redactedrice.compiler.CompilerConstants.Register;
import redactedrice.compiler.instructions.basic.Inc;

public class IncReg extends Inc {
    Register reg;

    public IncReg(Register reg) {
        super();
        this.reg = reg;
    }

    @Override
    public void writeStaticBytes(QueuedWriter writer) throws IOException {
        writer.append((byte) (0x04 | reg.getValue() << 3));
    }
}
