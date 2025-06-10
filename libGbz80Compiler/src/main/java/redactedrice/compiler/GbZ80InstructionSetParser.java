package redactedrice.compiler;

import java.util.List;

import redactedrice.compiler.instructions.Instruction;
import redactedrice.compiler.instructions.addressref.Call;
import redactedrice.compiler.instructions.addressref.Jp;
import redactedrice.compiler.instructions.addressref.Jr;
import redactedrice.compiler.instructions.basic.Cp;
import redactedrice.compiler.instructions.basic.Dec;
import redactedrice.compiler.instructions.basic.Inc;
import redactedrice.compiler.instructions.basic.Lb;
import redactedrice.compiler.instructions.basic.Ld;
import redactedrice.compiler.instructions.basic.Ldh;
import redactedrice.compiler.instructions.basic.Nop;
import redactedrice.compiler.instructions.basic.Or;
import redactedrice.compiler.instructions.basic.Pop;
import redactedrice.compiler.instructions.basic.Push;
import redactedrice.compiler.instructions.basic.RawBytes;
import redactedrice.compiler.instructions.basic.Ret;
import redactedrice.compiler.instructions.basic.Rst;
import redactedrice.compiler.instructions.basic.Sub;

public class GbZ80InstructionSetParser implements InstructionSetParser
{		
	@Override
	public List<String> getSupportedInstructions() 
	{
		return List.of("lb", "ld", "ldh", "cp", "or", "jr", "jp", "call", "ret", "dec", "inc", "sub", "rst", "pop", "push", "nop", "bytes");
	}

	@Override
	public Instruction parseInstruction(String instruction, String args, String rootSegment)
	{
		String[] splitArgs = CompilerUtils.splitArgs(args);
		switch (instruction)
		{
			// Loading
			case "lb":
				return Lb.create(splitArgs);
			case "ld":
				return Ld.create(splitArgs);
			case "ldh":
				return Ldh.create(splitArgs);
		
			// Logic
			case "cp":
				return Cp.create(splitArgs);
			case "or":
				return Or.create(splitArgs);				
				
			// Flow control
			case "jr":
				// JR is a bit special because we only allow it inside a block and we only
				// allow referencing labels
				return Jr.create(splitArgs, rootSegment);
			case "jp":
				return Jp.create(splitArgs, rootSegment);
			case "call":
				return Call.create(splitArgs, rootSegment);
			case "ret":
				return Ret.create(splitArgs);
				
			// Arithmetic
			case "dec":
				return Dec.create(splitArgs);
			case "inc":
				return Inc.create(splitArgs);
			case "sub":
				return Sub.create(splitArgs);
				
			// Misc
			case "rst":
				return Rst.create(splitArgs);
			case "pop":
				return Pop.create(splitArgs);
			case "push":
				return Push.create(splitArgs);
			case "nop":
				return Nop.create(splitArgs);
			case "bytes":
				return RawBytes.create(splitArgs);
				
			default:
				throw new UnsupportedOperationException("Unrecognized instruction: " + instruction);
		}
	}
}
