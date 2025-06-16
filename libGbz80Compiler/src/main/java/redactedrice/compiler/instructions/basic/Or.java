package redactedrice.compiler.instructions.basic;

import java.io.IOException;
import java.util.Arrays;

import redactedrice.gbcframework.QueuedWriter;
import redactedrice.compiler.CompilerUtils;
import redactedrice.compiler.CompilerConstants.Register;
import redactedrice.compiler.instructions.BasicInstruction;

public class Or extends BasicInstruction
{
	Register reg;
	
	public Or(Register reg)
	{
		super(1); // Size
		this.reg = reg;
	}

	public static Or create(String[] args)
	{		
		final String SUPPORT_STRING = "Or only supports (Register): Given ";
		if (args.length != 1)
		{
			throw new IllegalArgumentException(SUPPORT_STRING + Arrays.toString(args));
		}
		
		try
		{
			return new Or(CompilerUtils.parseRegisterArg(args[0]));
		}
		catch (IllegalArgumentException iae)
		{
			// The instruct doesn't fit
			// Could throw here but kept to preserve the pattern being used for
			// the instructs to support more easily adding future ones without
			// forgetting to add the throw at the end
		}
		
		throw new IllegalArgumentException(SUPPORT_STRING + Arrays.toString(args));
	}

	@Override
	public void writeStaticBytes(QueuedWriter writer) throws IOException
	{
		writer.append((byte) (0xB0 | reg.getValue()));
	}
}
