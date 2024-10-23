package deepamino.controller.fetcher;

import deepamino.controller.files.handler.LocalFileHandler;
import deepamino.controller.regex.FastaParser;
import deepamino.controller.regex.RegexParser;
import deepamino.model.fasta.ProcessedFastaFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SequenceNCBIFetcher implements DataFetcher<ProcessedFastaFile> {
    final String apiURL =  "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi";

    public ProcessedFastaFile fetch(String seqId) {
        try {
            return retrieveData(seqId);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    private ProcessedFastaFile retrieveData(String seqId) throws IOException {
        String content = getUrlContent(seqId);
        new LocalFileHandler().save(seqId + ".text", content.toString());

        RegexParser<ProcessedFastaFile> parser = new FastaParser();
        return parser.parse(new String[]{seqId, content.toString()});
    }

    private String getUrlContent(String seqId) throws IOException {
        URL url = new URL(getFetchURL(seqId));

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine).append("\n");
        }

        in.close();
        connection.disconnect();
        return content.toString();
    }

    private String getFetchURL(String seqId) {
        String db = "nucleotide";
        String fetchURL = apiURL
                + "?db=" + db
                + "&id=" + seqId
                + "&rettype=fasta"
                + "&retmode=text";

        return fetchURL;
    }
}
