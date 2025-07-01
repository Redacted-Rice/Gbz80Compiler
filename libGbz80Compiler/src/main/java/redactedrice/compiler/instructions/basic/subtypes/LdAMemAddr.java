package redactedrice.compiler.instructions.basic.subtypes;


import java.io.IOException;

import redactedrice.compiler.instructions.basic.Ld;
import redactedrice.gbcframework.QueuedWriter;
import redactedrice.gbcframework.utils.ByteUtils;

public class LdAMemAddr extends Ld {
    public static final int SIZE = 3;
    short addr;
    boolean loadToA;

    public LdAMemAddr(boolean loadToA, short addr) {
        super(SIZE); // size
        this.addr = addr;
        this.loadToA = loadToA;
    }

    @Override
    public void writeStaticBytes(QueuedWriter writer) throws IOException {
        // A, val
        if (loadToA) {
            writer.append((byte) 0xFA);
        }
        // val, A
        else {
            writer.append((byte) 0xEA);
        }
        writer.append(ByteUtils.shortToLittleEndianBytes(addr));
    }
}
