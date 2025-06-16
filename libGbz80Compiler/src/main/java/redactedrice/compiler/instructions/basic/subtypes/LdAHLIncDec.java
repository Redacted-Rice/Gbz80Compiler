package redactedrice.compiler.instructions.basic.subtypes;

import java.io.IOException;

import redactedrice.compiler.instructions.basic.Ld;
import redactedrice.gbcframework.QueuedWriter;

public class LdAHLIncDec extends Ld
{
	boolean loadToA;
	boolean increment;
	
	public LdAHLIncDec(boolean loadToA, boolean increment)
	{
		super(1); // size
		this.loadToA = loadToA;
		this.increment = increment;
	}
	
	@Override
	public void writeStaticBytes(QueuedWriter writer) throws IOException
	{
		byte val = 0x2;
		if (loadToA)
		{
			val = 0xA;
		}
		if (increment)
		{
			val |= 0x20; // 2 << 4
		}
		else
		{
			val |= 0x30; // 3 << 4
		}
		
		writer.append(val);
	}
}
