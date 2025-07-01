package redactedrice.compiler.instructions.basic.subtypes;


import java.io.IOException;

import redactedrice.compiler.instructions.basic.Sub;
import redactedrice.gbcframework.QueuedWriter;

public class SubByte extends Sub {
    public static final int SIZE = 2;
    byte val;

    public SubByte(byte val) {
        super(SIZE);
        this.val = val;
    }

    @Override
    public void writeStaticBytes(QueuedWriter writer) throws IOException {
        writer.append((byte) 0xD6, val);
    }
}
