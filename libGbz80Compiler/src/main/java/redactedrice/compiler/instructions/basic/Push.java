package redactedrice.compiler.instructions.basic;

import java.io.IOException;
import java.util.Arrays;

import redactedrice.gbcframework.QueuedWriter;
import redactedrice.compiler.CompilerUtils;
import redactedrice.compiler.CompilerConstants.PushPopRegisterPair;
import redactedrice.compiler.instructions.BasicInstruction;

public class Push extends BasicInstruction
{
	public static final int SIZE = 1;
	PushPopRegisterPair pair;

	public Push(PushPopRegisterPair pair)
	{
		super(SIZE); // Size
		this.pair = pair;
	}

	public static Push create(String[] args)
	{		
		final String SUPPORT_STRING = "Push only supports (PushPopRegisterPair): Given ";
		if (args.length != 1)
		{
			throw new IllegalArgumentException(SUPPORT_STRING + Arrays.toString(args));
		}
		
		try
		{
			return new Push(CompilerUtils.parsePushPopRegisterPairArg(args[0]));
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
		writer.append((byte) (0xC5 | pair.getValue() << 4));
	}
}
