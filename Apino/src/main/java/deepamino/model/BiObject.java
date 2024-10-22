package deepamino.model;

import java.util.List;

public record BiObject(String id,
                       String locus,
                       String definition,
                       String dbsource,
                       String source,
                       String organism,
                       String comment,
                       String gene,
                       String geneSynonyms,
                       List<Article> references) {
}
