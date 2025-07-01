package redactedrice.compiler;


import java.util.List;

import redactedrice.compiler.instructions.Instruction;

public interface InstructionSetParser {
    public Instruction parseInstruction(String instruction, String args, String rootSegment);

    public List<String> getSupportedInstructions();
}
