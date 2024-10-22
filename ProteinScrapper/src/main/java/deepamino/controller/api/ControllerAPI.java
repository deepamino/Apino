package deepamino.controller.api;

import deepamino.controller.fasta.FastaHandler;

public record ControllerAPI(FastaHandler fastaHandler) {
    public void run() {
        ProteinAPI proteinAPI = new ProteinAPI(fastaHandler);
        proteinAPI.run();
    }
}
