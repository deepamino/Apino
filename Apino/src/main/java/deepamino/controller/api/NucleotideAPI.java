package deepamino.controller.api;

import com.google.gson.Gson;
import deepamino.controller.fasta.FastaHandler;
import deepamino.controller.fetcher.InfoNCBIFetcher;
import deepamino.controller.fetcher.SequenceNCBIFetcher;

import static spark.Spark.*;

public record NucleotideAPI(FastaHandler fastaHandler) {

    public void run() {
        port(8080);
        getInfo();
        getSequence();
    }

    public void getInfo() {
        get("/info", (req, res) -> {
            String id = req.queryParams("id");
            return new Gson().toJson(
                    new InfoNCBIFetcher().fetch(id)
            );
        });
    }

    public void getSequence() {
        get("/sequence", (req, res) -> {
            String id = req.queryParams("id");
            return new Gson().toJson(
                    new SequenceNCBIFetcher().fetch(id)
            );
        });
    }
}
