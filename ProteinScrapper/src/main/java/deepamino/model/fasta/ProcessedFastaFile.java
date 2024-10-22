package deepamino.model.fasta;

import java.util.List;
import java.util.Map;

public record ProcessedFastaFile(Map<String, String> sequences) {
    public List<String> getIds() {
        return sequences.keySet().stream().toList();
    }

    public List<String> getSequences() {
        return sequences.values().stream().toList();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : sequences.keySet()) {
            stringBuilder.append(s + ": ");
            stringBuilder.append(sequences.get(s) + "\n");
        }

        return stringBuilder.toString();
    }
}
