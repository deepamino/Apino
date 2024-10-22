package deepamino.controller.api;

import deepamino.controller.fasta.FastaHandler;

public record ControllerAPI(FastaHandler fastaHandler) {
    public void run() {
        NucleotideAPI nucleotideAPI = new NucleotideAPI(fastaHandler);
        nucleotideAPI.run();
    }
}
