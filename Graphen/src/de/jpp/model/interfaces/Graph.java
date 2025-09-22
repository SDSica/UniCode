package de.jpp.model.interfaces;

import java.util.Collection;
import java.util.Optional;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;

public interface Graph<N, A> {

    boolean addNode(N node);

    boolean addNodes(Collection<? extends N> nodes);

    boolean addNodes(N... nodes);

    Collection<N> getNodes();

    Edge<N, A> addEdge(N start, N destination, Optional<A> annotation);

    default Edge<N, A> addEdge(N start, N destination) {
        return addEdge(start, destination, Optional.empty());
    }

    boolean removeEdge(Edge<N, A> edge);

    Collection<Edge<N, A>> getNeighbours(N node);

    Collection<Edge<N, A>> getReachableFrom(N node);

    Collection<Edge<N, A>> getEdges();

    boolean removeNode(N node);

    boolean removeNodes(Collection<? extends N> nodes);

    boolean removeNodes(N... nodes);

    void clear();

}
