package deepamino.controller.fasta.handlers;
import deepamino.controller.regex.RegexUtils;
import deepamino.controller.fasta.FastaHandler;
import deepamino.model.fasta.ProcessedFastaFile;
import deepamino.model.fasta.RawFastaFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastaNCBIHandler implements FastaHandler {
    @Override
    public ProcessedFastaFile retrieveProcessed(RawFastaFile rawFastaFile) {
        List<String> ids = ids(rawFastaFile);
        List<String> sequences = sequences(rawFastaFile);

        Map<String, String> id2Seq = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            id2Seq.put(ids.get(i), sequences.get(i));
        }

        return new ProcessedFastaFile(id2Seq);
    }

    public List<String> ids(RawFastaFile fasta) {
        String idRegex = "^>(\\w+\\d+\\.*\\d) ";
        return RegexUtils.getRegexElements(idRegex, fasta.content());
    }

    private List<String> sequences(RawFastaFile fasta) {
        String seqRegex = "\n([ATCG\n]+)";
        return RegexUtils.getRegexElements(seqRegex, fasta.content());
    }
}
