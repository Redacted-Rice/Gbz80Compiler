package redactedrice.compiler.instructions.basic;

import java.io.IOException;
import java.util.Arrays;

import redactedrice.gbcframework.QueuedWriter;
import redactedrice.compiler.CompilerUtils;
import redactedrice.compiler.CompilerConstants.InstructionConditions;
import redactedrice.compiler.instructions.BasicInstruction;

public class Ret extends BasicInstruction
{
	public static final int SIZE = 1;
	InstructionConditions conditions;
	
	public Ret(InstructionConditions retConditions)
	{
		super(1); // Size
		conditions = retConditions;
	}
	
	public Ret()
	{
		this(InstructionConditions.NONE);
	}
	
	public static Ret create(String[] args)
	{		
		final String SUPPORT_STRING = "Ret only supports ([No Args]) or (InstructionCondition): Given ";
		if (args.length > 1)
		{
			throw new IllegalArgumentException(SUPPORT_STRING + Arrays.toString(args));
		}
		
		if (args.length == 0)
		{
			return new Ret();
		}
		else if (args.length == 1)
		{
			try
			{
				return new Ret(CompilerUtils.parseInstructionConditionsArg(args[0]));
			}
			catch (IllegalArgumentException iae)
			{
				// The instruct doesn't fit
				// Could throw here but kept to preserve the pattern being used for
				// the instructs to support more easily adding future ones without
				// forgetting to add the throw at the end
			}
		}

		throw new IllegalArgumentException(SUPPORT_STRING + Arrays.toString(args));
	}

	@Override
	public void writeStaticBytes(QueuedWriter writer) throws IOException
	{
		if (InstructionConditions.NONE == conditions)
		{
			writer.append((byte) 0xC9);
		}
		else
		{
			writer.append((byte) (0xC0 | (conditions.getValue() << 3)));
		}
	}
}
