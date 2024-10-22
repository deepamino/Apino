package deepamino.controller.scrapper;

import com.google.gson.Gson;
import deepamino.model.BiObject;
import deepamino.model.fasta.ProcessedFastaFile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeneScrapper implements Scrapper {
    final String nucCoreURL = "https://www.ncbi.nlm.nih.gov/nuccore/";

    @Override
    public String getFromFasta(ProcessedFastaFile fasta) throws IOException {
        List<BiObject> biObjects = new ArrayList<>();
        for (String seqId : fasta.sequences().keySet()) {
            biObjects.add(getScrapping(seqId));
        }

        return new Gson().toJson(biObjects);
    }

    private BiObject getScrapping(String seqId) throws IOException {
        Document doc = Jsoup.connect(nucCoreURL + seqId).get();
        Elements preElements = doc.select("pre.genbank");
        Element infoExtract = preElements.get(0);

        System.out.println(infoExtract.text());
        return null;
    }
}