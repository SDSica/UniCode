package de.jpp.model.interfaces;

import java.util.Collection;
import java.util.function.Consumer;

public interface ObservableGraph<N, A> extends Graph<N, A> {


    void addNodeAddedListener(Consumer<N> listener);

    void addNodeRemovedListener(Consumer<N> listener);

    void addEdgeAddedListener(Consumer<Edge<N, A>> listener);

    void addEdgeRemovedListener(Consumer<Edge<N, A>> listener);

    void removeNodeAddedListener(Consumer<N> listener);

    void removeNodeRemovedListener(Consumer<N> listener);

    void removeEdgeAddedListener(Consumer<Edge<N, A>> listener);

    void removeEdgeRemovedListener(Consumer<Edge<N, A>> listener);

    void addNeighboursListedListener(Consumer<Collection<Edge<N, A>>> listener);

    void addReachableListedListener(Consumer<Collection<Edge<N, A>>> listener);

    void addNodesListedListener(Consumer<Collection<N>> listener);

    void addEdgesListedListener(Consumer<Collection<Edge<N, A>>> listener);

    void removeNeighboursListedListener(Consumer<Collection<Edge<N, A>>> listener);

    void removeReachableListedListener(Consumer<Collection<Edge<N, A>>> listener);

    void removeNodesListedListener(Consumer<Collection<N>> listener);

    void removeEdgesListedListener(Consumer<Collection<Edge<N, A>>> listener);

}
