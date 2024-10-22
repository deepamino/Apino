package deepamino.controller.scrapper;

import deepamino.model.fasta.ProcessedFastaFile;

import java.io.IOException;

public interface Scrapper {
    Object getFromFasta(ProcessedFastaFile fasta) throws IOException;
}
