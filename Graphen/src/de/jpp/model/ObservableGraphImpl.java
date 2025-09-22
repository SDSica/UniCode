package de.jpp.model;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import de.jpp.model.interfaces.ObservableGraph;

import java.util.*;
import java.util.function.Consumer;

public class ObservableGraphImpl<N,A> extends BasicGraph<N,A> implements ObservableGraph<N,A> {
    private final List<Consumer<N>> nodeAddedListeners;
    private final List<Consumer<N>> nodeRemovedListeners;
    private final List<Consumer<Edge<N, A>>> edgeAddedListeners;
    private final List<Consumer<Edge<N, A>>> edgeRemovedListeners;
    private final List<Consumer<Collection<Edge<N, A>>>> neighboursListedListeners;
    private final List<Consumer<Collection<Edge<N, A>>>> reachableListedListeners;
    private final List<Consumer<Collection<N>>> nodesListedListeners;
    private final List<Consumer<Collection<Edge<N, A>>>> edgesListedListeners;

    public ObservableGraphImpl(){
        super();
        this.nodeAddedListeners = new ArrayList<>();
        this.nodeRemovedListeners = new ArrayList<>();
        this.edgeAddedListeners = new ArrayList<>();
        this.edgeRemovedListeners = new ArrayList<>();
        this.neighboursListedListeners = new ArrayList<>();
        this.reachableListedListeners = new ArrayList<>();
        this.nodesListedListeners = new ArrayList<>();
        this.edgesListedListeners = new ArrayList<>();

    }
    @Override
    public boolean addNode(N node) {
        boolean result = super.addNode(node);
        if (result) {
            for (Consumer<N> listener : nodeAddedListeners) {
                listener.accept(node);
            }
        }
        return result;
    }
    @Override
    public Edge<N, A> addEdge(N start, N destination, Optional<A> annotation) {
        Edge<N, A> edge = super.addEdge(start, destination, annotation);
        for (Consumer<Edge<N, A>> listener : edgeAddedListeners) {
            listener.accept(edge);
        }

        return edge;
    }
    @Override
    public boolean removeEdge(Edge<N, A> edge) {
        boolean result = super.removeEdge(edge);
        if (result) {
            for (Consumer<Edge<N, A>> listener : edgeRemovedListeners) {
                listener.accept(edge);

            }
        }
        return result;
    }
    @Override
    public boolean removeNode(N node) {
        boolean result = super.removeNode(node);
        if (result) {
            for (Consumer<N> listener : nodeRemovedListeners) {
                listener.accept(node);

            }
        }
        return result;
    }
    @Override
    public Collection<N> getNodes() {
        Collection<N> result = super.getNodes();
        for (Consumer<Collection<N>> listener : nodesListedListeners) {
            listener.accept(result);
        }
        return result;
    }
    @Override
    public Collection<Edge<N, A>> getEdges() {
        Collection<Edge<N, A>> result = super.getEdges();
        for (Consumer<Collection<Edge<N, A>>> listener : edgesListedListeners) {
            listener.accept(result);

        }
        return result;
    }
    @Override
    public Collection<Edge<N, A>> getNeighbours(N node) {
        Collection<Edge<N, A>> result = super.getNeighbours(node);
        for (Consumer<Collection<Edge<N, A>>> listener : neighboursListedListeners) {
            listener.accept(result);
        }
        return result;
    }
    @Override
    public Collection<Edge<N, A>> getReachableFrom(N node) {
        Collection<Edge<N, A>> result = super.getReachableFrom(node);
        for (Consumer<Collection<Edge<N, A>>> listener : reachableListedListeners) {
            listener.accept(result);

        }
        return result;
    }
    @Override
    public void addNodeAddedListener(Consumer<N> listener) {
        if (listener != null) {
            nodeAddedListeners.add(listener);
        }
    }
    @Override
    public void addNodeRemovedListener(Consumer<N> listener) {
        if (listener != null) {
            nodeRemovedListeners.add(listener);
        }
    }
    @Override
    public void addEdgeAddedListener(Consumer<Edge<N, A>> listener) {
        if (listener != null) {
            edgeAddedListeners.add(listener);
        }
    }

    @Override
    public void addEdgeRemovedListener(Consumer<Edge<N, A>> listener) {
        if (listener != null) {
            edgeRemovedListeners.add(listener);
        }
    }

    @Override
    public void removeNodeAddedListener(Consumer<N> listener) {
        nodeAddedListeners.remove(listener);
    }

    @Override
    public void removeNodeRemovedListener(Consumer<N> listener) {
        nodeRemovedListeners.remove(listener);
    }

    @Override
    public void removeEdgeAddedListener(Consumer<Edge<N, A>> listener) {
        edgeAddedListeners.remove(listener);
    }

    @Override
    public void removeEdgeRemovedListener(Consumer<Edge<N, A>> listener) {
        edgeRemovedListeners.remove(listener);
    }

    @Override
    public void addNeighboursListedListener(Consumer<Collection<Edge<N, A>>> listener) {
        if (listener != null) {
            neighboursListedListeners.add(listener);
        }
    }

    @Override
    public void addReachableListedListener(Consumer<Collection<Edge<N, A>>> listener) {
        if (listener != null) {
            reachableListedListeners.add(listener);
        }
    }

    @Override
    public void addNodesListedListener(Consumer<Collection<N>> listener) {
        if (listener != null) {
            nodesListedListeners.add(listener);
        }
    }

    @Override
    public void addEdgesListedListener(Consumer<Collection<Edge<N, A>>> listener) {
        if (listener != null) {
            edgesListedListeners.add(listener);
        }
    }

    @Override
    public void removeNeighboursListedListener(Consumer<Collection<Edge<N, A>>> listener) {
        neighboursListedListeners.remove(listener);
    }

    @Override
    public void removeReachableListedListener(Consumer<Collection<Edge<N, A>>> listener) {
        reachableListedListeners.remove(listener);
    }

    @Override
    public void removeNodesListedListener(Consumer<Collection<N>> listener) {
        nodesListedListeners.remove(listener);
    }

    @Override
    public void removeEdgesListedListener(Consumer<Collection<Edge<N, A>>> listener) {
        edgesListedListeners.remove(listener);
    }
    @Override
    public String toString() {
        return "ObservableGraphImpl" + super.toString().substring("BasicGraph".length());
    }

}


