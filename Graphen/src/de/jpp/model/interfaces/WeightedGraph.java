package de.jpp.model.interfaces;

public interface WeightedGraph<N, A> extends Graph<N, A> {

    double getDistance(Edge<N, A> edge);

}
