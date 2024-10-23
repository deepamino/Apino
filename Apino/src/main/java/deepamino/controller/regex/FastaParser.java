package deepamino.controller.regex;

import deepamino.model.Article;
import deepamino.model.BiObject;
import deepamino.model.fasta.ProcessedFastaFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastaParser implements RegexParser<ProcessedFastaFile> {
    @Override
    public ProcessedFastaFile parse(String[] args) {
        String seqId = args[0];
        String content = args[1];

        Map<String, String> fastaMap = new HashMap<>();
        fastaMap.put(seqId, sequence(content));

        return new ProcessedFastaFile(fastaMap);
    }

    private String sequence(String content) {
        String[] contentLines = content.split("\n");
        StringBuilder sequenceBuilder = new StringBuilder();

        for (int i = 1; i < contentLines.length; i++) {
            sequenceBuilder.append(contentLines[i]);
        }

        return sequenceBuilder.toString();
    }
}
