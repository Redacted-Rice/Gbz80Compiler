package redactedrice.compiler.instructions.basic.subtypes;


import java.io.IOException;

import redactedrice.gbcframework.QueuedWriter;
import redactedrice.compiler.CompilerConstants.RegisterPair;
import redactedrice.compiler.instructions.basic.Ld;
import redactedrice.gbcframework.utils.ByteUtils;

public class LdPairShort extends Ld {
    RegisterPair pair;
    short value;

    public LdPairShort(RegisterPair pair, short value) {
        super(3); // size
        this.pair = pair;
        this.value = value;
    }

    @Override
    public void writeStaticBytes(QueuedWriter writer) throws IOException {
        writer.append((byte) (0x01 | (pair.getValue() << 4)));
        writer.append(ByteUtils.shortToLittleEndianBytes(value));
    }
}
