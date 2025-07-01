package redactedrice.compiler.instructions;


import java.util.Map;

import redactedrice.gbcframework.SegmentNamingUtils;

public abstract class AddressRefInstruction implements Instruction {
    private String label;

    protected AddressRefInstruction() {
        this.label = "";
    }

    protected AddressRefInstruction(String label) {
        this.label = label;
    }

    @Override
    public boolean containsPlaceholder() {
        return SegmentNamingUtils.isOnlySubsegmentPartOfLabel(label);
    }

    @Override
    public void replacePlaceholderIfPresent(Map<String, String> placeholderToArgs) {
        label = SegmentNamingUtils.replacePlaceholders(label, placeholderToArgs);
    }

    protected String getLabel() {
        return label;
    }
}
