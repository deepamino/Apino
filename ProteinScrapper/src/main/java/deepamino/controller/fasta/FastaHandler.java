package deepamino.controller.fasta;

import deepamino.model.fasta.ProcessedFastaFile;
import deepamino.model.fasta.RawFastaFile;

public interface FastaHandler {
    ProcessedFastaFile retrieveProcessed(RawFastaFile file);
}
