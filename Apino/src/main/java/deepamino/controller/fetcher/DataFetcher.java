package deepamino.controller.fetcher;

import deepamino.model.BiObject;

public interface DataFetcher<Type> {
    Type fetch(String seqId);
}
