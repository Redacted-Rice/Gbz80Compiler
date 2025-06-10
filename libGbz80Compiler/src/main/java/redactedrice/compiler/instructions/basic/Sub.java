package redactedrice.compiler.instructions.basic;

import java.util.Arrays;

import redactedrice.compiler.CompilerUtils;
import redactedrice.compiler.CompilerConstants.Register;
import redactedrice.compiler.instructions.BasicInstruction;
import redactedrice.compiler.instructions.basic.subtypes.SubByte;
import redactedrice.compiler.instructions.basic.subtypes.SubReg;

public abstract class Sub extends BasicInstruction
{
	protected Sub(int size)
	{
		super(size);
	}

	public static Sub create(String[] args)
	{
		final String SUPPORT_STRING = "Sub only supports (A, Byte) or (A, Register): Given ";
		if (args.length != 2)
		{
			throw new IllegalArgumentException(SUPPORT_STRING + Arrays.toString(args));
		}
		
		// Ensure first arg is register A
		try
		{
			if (CompilerUtils.parseRegisterArg(args[0]) != Register.A)
			{
				throw new IllegalArgumentException(); // Caught below
			}
		}
		catch (IllegalArgumentException iae) 
		{
			throw new IllegalArgumentException(SUPPORT_STRING + " a register that is not A for first arg: " + Arrays.toString(args));
		}
			
		try
		{
			return new SubByte(CompilerUtils.parseByteArg(args[1]));
		}
		catch(IllegalArgumentException iae) 
		{
			// The instruct doesn't fit - try the next one
		}

		try
		{
			return new SubReg(CompilerUtils.parseRegisterArg(args[1]));
		}
		catch(IllegalArgumentException iae) 
		{
			// The instruct doesn't fit
			// Could throw here but kept to preserve the pattern being used for
			// the instructs to support more easily adding future ones without
			// forgetting to add the throw at the end
		}	

		throw new IllegalArgumentException(SUPPORT_STRING + Arrays.toString(args));
	}
}
