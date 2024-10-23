package deepamino.controller.regex;

import deepamino.model.Article;
import deepamino.model.BiObject;

import java.util.ArrayList;
import java.util.List;

public class InfoParser implements RegexParser<BiObject> {
    @Override
    public BiObject parse(String[] args) {
        String seqId = args[0];
        String content = args[1];

        return new BiObject(seqId,
                locus(content),
                definition(content),
                dbsource(content),
                source(content),
                organism(content),
                comment(content),
                gene(content),
                geneSynonym(content),
                articles(content));
    }

    private String geneSynonym(String content) {
        return RegexUtils.getFirstRegex("(?i)/gene_synonym=\"(.+)", content);
    }

    private String gene(String content) {
        return RegexUtils.getFirstRegex("(?i)/gene=\"(.+)\"", content);
    }

    private String comment(String content) {
        return RegexUtils.getFirstRegex("(?i)COMMENT\\s+(.+(?:\\n\\s+.+)*)", content);
    }

    private String organism(String content) {
        return RegexUtils.getFirstRegex("(?i)ORGANISM\\s+(.+(?:\\n\\s+.+)*)", content);
    }

    private String source(String content) {
        return RegexUtils.getFirstRegex("(?i)SOURCE(.+)", content);
    }

    private String dbsource(String content) {
        return RegexUtils.getFirstRegex("(?i)DBSOURCE(.+)", content);
    }

    private String definition(String content) {
        return RegexUtils.getFirstRegex("(?i)DEFINITION(.+)", content);
    }

    private String locus(String content) {
        return RegexUtils.getFirstRegex("(?i)LOCUS(.+)", content);
    }

    private List<Article> articles(String content) {
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