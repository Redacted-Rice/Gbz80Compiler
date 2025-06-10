package redactedrice.compiler.instructions.basic;

import java.util.Arrays;

import redactedrice.compiler.CompilerUtils;
import redactedrice.compiler.CompilerConstants.Register;
import redactedrice.compiler.instructions.BasicInstruction;
import redactedrice.compiler.instructions.basic.subtypes.LdAHLIncDec;
import redactedrice.compiler.instructions.basic.subtypes.LdAMemAddr;
import redactedrice.compiler.instructions.basic.subtypes.LdPairShort;
import redactedrice.compiler.instructions.basic.subtypes.LdRegByte;
import redactedrice.compiler.instructions.basic.subtypes.LdRegReg;

public abstract class Ld extends BasicInstruction
{
	protected Ld(int size)
	{
		super(size);
	}

	public static Ld create(String[] args)
	{
		final String SUPPORT_STRING = "Ld only supports (Register, Byte), (Register, Register), (RegisterPair, Short), "
				+ "(a, [hli or hld]), or ([hli or hld], a): Given ";
		if (args.length != 2)
		{
			throw new IllegalArgumentException(SUPPORT_STRING + Arrays.toString(args));
		}
		
		Ld ld = simpleCreateChecks(args);
		if (ld == null)
		{
			ld = complexCreateChecks(args);
		}
		
		if (ld == null)
		{
			throw new IllegalArgumentException(SUPPORT_STRING + Arrays.toString(args));
		}
		
		return ld;
	}
	
	private static Ld simpleCreateChecks(String[] args)
	{		
		try
		{
			return new LdRegByte(
					CompilerUtils.parseRegisterArg(args[0]),
					CompilerUtils.parseByteArg(args[1]));
		}
		catch(IllegalArgumentException iae) 
		{
			// The instruct doesn't fit - try the next one
		}

		try
		{
			return new LdRegReg(
					CompilerUtils.parseRegisterArg(args[0]),
					CompilerUtils.parseRegisterArg(args[1]));
		}
		catch(IllegalArgumentException iae) 
		{
			// The instruct doesn't fit - try the next one
		}

		try
		{
			return new LdPairShort(
						CompilerUtils.parseRegisterPairArg(args[0]),
						CompilerUtils.parseShortArg(args[1]));
		}
		catch(IllegalArgumentException iae) 
		{
			// The instruct doesn't fit
			// Could throw here but kept to preserve the pattern being used for
			// the instructs to support more easily adding future ones without
			// forgetting to add the throw at the end
		}
		
		return null;
	}
	
	private static Ld complexCreateChecks(String[] args)
	{
		boolean firstRegIsA = false;
		try
		{
			// See if first arg is A. This will throw if first arg is not a register
			if (CompilerUtils.parseRegisterArg(args[0]) == Register.A)
			{
				firstRegIsA = true;
			}
		}
		catch(IllegalArgumentException iae) 
		{
			// The instruct doesn't fit - try the next one
		}
		
		if (firstRegIsA)
		{
			try
			{
				// True means we are loading to A as opposed to loading from a
				// Will throw if second arg its not HLi or HLd
				return new LdAHLIncDec(true, CompilerUtils.parseHLIncDecArg(args[1]));
			}
			catch(IllegalArgumentException iae) 
			{
				// The instruct doesn't fit - try the next one
			}

			try
			{
				// True means we are loading to A as opposed to loading from a
				// Will throw if not a valid memory address
				return new LdAMemAddr(true, CompilerUtils.parseMemoryAddressArg(args[1]));
			}
			catch(IllegalArgumentException iae) 
			{
				// The instruct doesn't fit - try the next one
			}
		}
		
		boolean secondRegIsA = false;
		try
		{
			// See if second arg is A. This will throw if first arg is not a register
			if (CompilerUtils.parseRegisterArg(args[1]) == Register.A)
			{
				secondRegIsA = true;
			}
		}
		catch(IllegalArgumentException iae) 
		{
			// The instruct doesn't fit - try the next one
		}
		
		if (secondRegIsA)
		{
			try
			{
				// False means we are loading from A as opposed to loading to a
				// Will throw if second arg its not HLi or HLd
				return new LdAHLIncDec(false, CompilerUtils.parseHLIncDecArg(args[0]));
			}
			catch(IllegalArgumentException iae) 
			{
				// The instruct doesn't fit - try the next one
			}

			try
			{
				// False means we are loading from A as opposed to loading to a
				// Will throw if not a valid memory address
				return new LdAMemAddr(false, CompilerUtils.parseMemoryAddressArg(args[0]));
			}
			catch(IllegalArgumentException iae) 
			{
				// The instruct doesn't fit - try the next one
			}
		}
		
		return null;
	}
}
