package redactedrice.compiler.instructions.basic.subtypes;

import java.io.IOException;

import redactedrice.gbcframework.QueuedWriter;
import redactedrice.compiler.CompilerConstants.RegisterPair;
import redactedrice.compiler.instructions.basic.Inc;

public class IncPair extends Inc
{
	RegisterPair pair;

	public IncPair(RegisterPair pair)
	{
		super();
		this.pair = pair;
	}

	@Override
	public void writeStaticBytes(QueuedWriter writer) throws IOException
	{
		writer.append((byte) (0x03 | pair.getValue() << 4));
	}
}
