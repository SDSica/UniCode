package de.jpp.algorithm.interfaces;

import java.util.function.BiConsumer;

public interface ObservableSearchResult<N, A> extends SearchResult<N, A> {

    void addNodeOpenedListener(BiConsumer<N, SearchResult<N, A>> onOpen);

    void removeNodeOpenedListener(BiConsumer<N, SearchResult<N, A>> onOpen);

    void addNodeClosedListener(BiConsumer<N, SearchResult<N, A>> onClose);

    void removeNodeClosedListener(BiConsumer<N, SearchResult<N, A>> onClose);

}
