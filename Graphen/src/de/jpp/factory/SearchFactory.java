package de.jpp.factory;

import de.jpp.algorithm.interfaces.EstimationFunction;
import de.jpp.algorithm.interfaces.ObservableSearchResult;
import de.jpp.algorithm.interfaces.SearchAlgorithm;
import de.jpp.model.interfaces.Graph;
import de.jpp.model.interfaces.WeightedGraph;

public class SearchFactory<N, A> {

    public ObservableSearchResult<N,A> getSearchResult(){
        throw new UnsupportedOperationException("not supported yet!");
    }

    public <G extends Graph<N, A>> SearchAlgorithm<N, A, G> getDepthFirstSearch(G graph, N start) {
        throw new UnsupportedOperationException("not supported yet!");
    }

    public <G extends Graph<N, A>> SearchAlgorithm<N, A, G> getBreadthFirstSearch(G graph, N start) {
        throw new UnsupportedOperationException("not supported yet!");
    }

    public <G extends WeightedGraph<N, A>> SearchAlgorithm<N, A, G> getDijkstra(G graph, N start) {
        throw new UnsupportedOperationException("not supported yet!");
    }

    public <G extends WeightedGraph<N, A>> SearchAlgorithm<N, A, G> getAStar(G graph, N start, N dest, EstimationFunction<N> estimationFunction) {
        throw new UnsupportedOperationException("not supported yet!");
    }


}
