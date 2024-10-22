package deepamino.model.fasta;

public record RawFastaFile(String content) {
    public String content() {
        return content;
    }
}

