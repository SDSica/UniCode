package de.jpp.algorithm.interfaces;

import de.jpp.algorithm.NodeInformation;
import de.jpp.model.interfaces.Edge;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SearchResult<N, A> {

    NodeStatus getNodeStatus(N node);

    Optional<Edge<N, A>> getPredecessor(N node);

    Optional<NodeInformation<N, A>> getInformation(N node);

    Collection<N> getAllKnownNodes();

    Collection<N> getAllOpenNodes();

    void setClosed(N node);

    void setOpen(N node);

    void setUnknown(N node);

    void setPredecessor(N node, Edge<N,A> predecessor, double distance);

    void setPredecessor(N node, NodeInformation<N,A> information);

    void clear();

    Optional<List<Edge<N, A>>> getPathTo(N dest);

}
