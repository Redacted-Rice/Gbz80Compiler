package redactedrice.compiler.instructions;


import java.io.IOException;
import java.util.Map;

import redactedrice.gbcframework.QueuedWriter;
import redactedrice.gbcframework.addressing.AssignedAddresses;
import redactedrice.gbcframework.addressing.BankAddress;

public abstract class BasicInstruction extends FixedLengthInstruction
{
	protected BasicInstruction(int size) 
	{
		super(size);
	}
	
	@Override
	public boolean containsPlaceholder()
	{
		return false;
	}
	
	@Override
	public void replacePlaceholderIfPresent(Map<String, String> placeholderToArgs)
	{
		// Nothing to do!
	}
	
	@Override
	public void writeFixedSizeBytes(QueuedWriter writer, BankAddress unused1, AssignedAddresses unused2) throws IOException
	{
		writeStaticBytes(writer);
	}

	public abstract void writeStaticBytes(QueuedWriter writer) throws IOException;
}
