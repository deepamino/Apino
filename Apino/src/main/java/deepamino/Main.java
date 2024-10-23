package deepamino;

import deepamino.controller.Controller;
import deepamino.controller.fetcher.SequenceNCBIFetcher;
import deepamino.controller.scrapper.GeneScrapper;
import deepamino.model.fasta.ProcessedFastaFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        new Controller().run();
        Map<String, String> map = new HashMap<>();
        map.put("NM_001301717", "");
        System.out.println(new SequenceNCBIFetcher().fetch("NM_001301717"));
    }
}

