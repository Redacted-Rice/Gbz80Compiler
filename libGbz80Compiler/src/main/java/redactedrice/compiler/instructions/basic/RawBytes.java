package redactedrice.compiler.instructions.basic;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import redactedrice.gbcframework.QueuedWriter;
import redactedrice.compiler.CompilerUtils;
import redactedrice.compiler.instructions.BasicInstruction;

public class RawBytes extends BasicInstruction
{
	List<byte[]> allBytes;
	
	public RawBytes(byte... bytes) 
	{
		super(bytes.length);
		
		allBytes = new LinkedList<>();
		allBytes.add(bytes);
	}
	
	public RawBytes(byte[]... bytes) 
	{
		super(determineSize(bytes));
		allBytes = new LinkedList<>();
		Collections.addAll(allBytes, bytes);
	}
	
	public RawBytes(List<byte[]> bytes) 
	{
		super(determineSize(bytes));
		allBytes = new LinkedList<>(bytes);
	}
	
	public static RawBytes create(String[] args)
	{
		List<byte[]> asBytes = new LinkedList<>();
		for (String arg : args)
		{
			asBytes.add(CompilerUtils.extractBytesFromStrippedHexString(
					CompilerUtils.extractHexValString(arg)));
		}
		return new RawBytes(asBytes);
	}
	
	private static int determineSize(byte[]... bytes)
	{
		int size = 0;
		for (byte[] set : bytes)
		{
			size += set.length;
		}
		return size;
	}	
	
	private static int determineSize(List<byte[]> bytes)
	{
		int size = 0;
		for (byte[] set : bytes)
		{
			size += set.length;
		}
		return size;
	}

	@Override
	public void writeStaticBytes(QueuedWriter writer) throws IOException
	{
		for (byte[] set : allBytes)
		{
			writer.append(set);
		}
	}
}
