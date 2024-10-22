package deepamino.controller.api;

import com.google.gson.Gson;
import deepamino.controller.fasta.FastaHandler;
import deepamino.controller.fetcher.NCBISequenceFetcher;
import deepamino.model.fasta.RawFastaFile;

import static spark.Spark.*;

public record ProteinAPI(FastaHandler fastaHandler) {

    public void run() {
        port(8080);
        getInfo();
    }

    public void postFasta() {
        post("/api/resource/fasta", (req, res) -> {
            RawFastaFile fasta = (new Gson()).fromJson(req.body(), RawFastaFile.class);
            res.status(201);
            return new Gson().toJson(fasta);
        });
    }

    public void getInfo() {
        get("/info", (req, res) -> {
            String id = req.queryParams("id");
            return new Gson().toJson(
                    new NCBISequenceFetcher().fetch(id)
            );
        });
    }

    public void getProtein() {}
}
