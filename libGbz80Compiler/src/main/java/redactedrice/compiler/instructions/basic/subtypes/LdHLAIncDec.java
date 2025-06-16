package redactedrice.compiler.instructions.basic.subtypes;

import java.io.IOException;

import redactedrice.gbcframework.QueuedWriter;
import redactedrice.compiler.CompilerConstants.Register;
import redactedrice.compiler.instructions.basic.Ld;

public class LdHLAIncDec extends Ld
{
	public static final int SIZE = 1;
	Register reg;
	boolean increment;

	public LdHLAIncDec(Register reg, boolean incrementNotDec)
	{
		super(SIZE);
		this.reg = reg;
		this.increment = incrementNotDec;
	}

	@Override
	public void writeStaticBytes(QueuedWriter writer) throws IOException
	{
		byte val = 0x2;
		if (increment)
		{
			writer.append((byte) (val | 0x20)); // 2 << 4
		}
		else
		{
			writer.append((byte) (val | 0x30)); // 3 << 4
		}
	}
}
