package deepamino.controller.api;

import com.google.gson.Gson;
import deepamino.controller.fasta.FastaHandler;
import deepamino.controller.fetcher.NCBISequenceFetcher;

import static spark.Spark.*;

public record NucleotideAPI(FastaHandler fastaHandler) {

    public void run() {
        port(8080);
        getInfo();
    }

    public void getInfo() {
        get("/info", (req, res) -> {
            String id = req.queryParams("id");
            return new Gson().toJson(
                    new NCBISequenceFetcher().fetch(id)
            );
        });
    }
}
