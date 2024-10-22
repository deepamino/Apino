package deepamino.controller.fetcher;

import deepamino.model.BiObject;

public interface DataFetcher {
    BiObject fetch(String seqId);
}
