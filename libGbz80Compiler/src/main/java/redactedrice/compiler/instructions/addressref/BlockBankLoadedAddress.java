package redactedrice.compiler.instructions.addressref;


import java.io.IOException;

import redactedrice.compiler.instructions.AddressRefInstruction;
import redactedrice.gbcframework.QueuedWriter;
import redactedrice.gbcframework.addressing.AssignedAddresses;
import redactedrice.gbcframework.addressing.BankAddress;
import redactedrice.gbcframework.utils.ByteUtils;
import redactedrice.gbcframework.utils.RomUtils;


public class BlockBankLoadedAddress extends AddressRefInstruction
{
	boolean includeBank;
	public static final int SIZE = 2;
	
	public BlockBankLoadedAddress(String addressLabel, boolean includeBank)
	{
		super(addressLabel);
		this.includeBank = includeBank;
	}
	
	public int getSize()
	{
		return SIZE;
	}
	
	@Override
	public int getWorstCaseSize(BankAddress unused1, AssignedAddresses unused2, AssignedAddresses unused3)
	{
		return SIZE;
	}
	
	@Override
	public int writeBytes(QueuedWriter writer, BankAddress unused, AssignedAddresses assignedAddresses) throws IOException
	{
		BankAddress address = assignedAddresses.getThrow(getLabel());
		if (!address.isFullAddress())
		{
			throw new IllegalAccessError("BlockBankLoaded Address tried to write address for " + getLabel() + " but it is not fully assigned: " + address.toString());
		}
		write(writer, address);
		return SIZE;
	}

	public static void write(QueuedWriter writer, BankAddress toWrite) throws IOException
	{
		writer.append(ByteUtils.shortToLittleEndianBytes(RomUtils.convertFromBankOffsetToLoadedOffset(toWrite)));
	}
}
