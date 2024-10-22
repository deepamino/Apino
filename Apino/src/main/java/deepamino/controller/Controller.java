package deepamino.controller;

import deepamino.controller.api.ControllerAPI;
import deepamino.controller.fasta.handlers.FastaNCBIHandler;
import deepamino.controller.fasta.FastaHandler;

public class Controller {
    public void run() {
        FastaHandler fastaHandler = new FastaNCBIHandler();
        new ControllerAPI(fastaHandler).run();
    }
}
