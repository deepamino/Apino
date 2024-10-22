package deepamino.controller.regex;

import deepamino.model.Article;
import deepamino.model.BiObject;

import java.util.ArrayList;
import java.util.List;

public class NCBIRegexParser implements RegexParser<BiObject> {
    @Override
    public BiObject parse(String[] args) {
        String seqId = args[0];
        String content = args[1];

        String locus = RegexUtils.getFirstRegex("(?i)LOCUS(.+)", content);
        String definition = RegexUtils.getFirstRegex("(?i)DEFINITION(.+)", content);
        String dbSource = RegexUtils.getFirstRegex("(?i)DBSOURCE(.+)", content);
        String source = RegexUtils.getFirstRegex("(?i)SOURCE(.+)", content);
        String organism = RegexUtils.getFirstRegex("(?i)ORGANISM\\s+(.+(?:\\n\\s+.+)*)", content);
        String comment = RegexUtils.getFirstRegex("(?i)COMMENT\\s+(.+(?:\\n\\s+.+)*)", content);
        String gene = RegexUtils.getFirstRegex("(?i)/gene=\"(.+)\"", content);
        String geneSynonyms = RegexUtils.getFirstRegex("(?i)/gene_synonym=\"(.+)", content);

        List<Article> articles = getArticles(seqId, content);

        return new BiObject(seqId, locus, definition, dbSource, source, organism, comment, gene, geneSynonyms, articles);
    }

    private List<Article> getArticles(String seqId, String content) {
        List<String> authors = RegexUtils.getRegex("(?i)AUTHORS(.+)", content);
        List<String> title = RegexUtils.getRegex("(?i)TITLE(.+(?:\\n\\s+.+)*)JOURNAL", content);
        List<String> journal = RegexUtils.getRegex("(?i)JOURNAL(.+)", content);

        List<Article> articles = new ArrayList<>();

        for (int i = 0; i < authors.size(); i++)
            articles.add(
                    new Article(authors.get(i), title.get(i), journal.get(i))
            );

        return articles;
    }
}
