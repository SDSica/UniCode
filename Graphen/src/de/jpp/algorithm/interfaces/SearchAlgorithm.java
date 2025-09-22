package de.jpp.algorithm.interfaces;

import de.jpp.factory.SearchStopFactory;
import de.jpp.model.interfaces.Graph;

public interface SearchAlgorithm<N, A, G extends Graph<N, A>> {

    SearchResult<N, A> findPaths(SearchStopStrategy<N> type);

    SearchResult<N, A> findAllPaths();

    ObservableSearchResult<N, A> getSearchResult();

    N getStart();

    G getGraph();

    void stop();


}
