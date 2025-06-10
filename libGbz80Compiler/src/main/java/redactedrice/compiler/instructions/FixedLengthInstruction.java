package redactedrice.compiler.instructions;


import java.io.IOException;

import redactedrice.gbcframework.QueuedWriter;
import redactedrice.gbcframework.addressing.AssignedAddresses;
import redactedrice.gbcframework.addressing.BankAddress;

public abstract class FixedLengthInstruction implements Instruction
{
	private int size;
	
	protected FixedLengthInstruction(int size) 
	{
		this.size = size;
	}
	
	public int getSize()
	{
		return size;
	}

	@Override
	public int getWorstCaseSize(BankAddress unused1, AssignedAddresses unused2, AssignedAddresses unused3)
	{
		return getSize();
	}
	
	@Override
	public int writeBytes(QueuedWriter writer, BankAddress instructionAddress, AssignedAddresses assignedAddresses) throws IOException
	{
		writeFixedSizeBytes(writer, instructionAddress, assignedAddresses);
		return size;
	}

	public abstract void writeFixedSizeBytes(QueuedWriter writer, BankAddress instructionAddress, AssignedAddresses assignedAddresses) throws IOException;
}
