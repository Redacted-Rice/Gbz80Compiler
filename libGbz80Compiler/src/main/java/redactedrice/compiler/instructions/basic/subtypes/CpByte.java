package redactedrice.compiler.instructions.basic.subtypes;


import java.io.IOException;

import redactedrice.compiler.instructions.basic.Cp;
import redactedrice.gbcframework.QueuedWriter;

public class CpByte extends Cp {
    public static final int SIZE = 2;
    byte value;

    public CpByte(byte value) {
        super(SIZE);
        this.value = value;
    }

    @Override
    public void writeStaticBytes(QueuedWriter writer) throws IOException {
        writer.append((byte) 0xFE);
        writer.append(value);
    }
}
