package de.jpp.model;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import java.util.*;

public class BasicGraph<N,A> implements Graph<N,A>  {

    private final Set<N> nodes;
    private final Set<Edge<N,A>> edges;
    private final Map<N,Set<Edge<N,A>>> adjacencyList;

    public BasicGraph(){
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
        this.adjacencyList = new HashMap<>();
    }
    @Override
    public boolean addNode(N node){
        if (node==null){
            throw new NullPointerException("Knoten ist null");
        }

        boolean wasAdded = nodes.add(node);
        if(wasAdded){
            adjacencyList.put(node,new HashSet<>());
        }
        return wasAdded;
    }
    @Override
    public boolean addNodes(Collection<?extends N> nodes){
        if(nodes==null){
            throw new NullPointerException("Collection (Knoten) ist null");
        }
        boolean addedAny = false;
        for(N node:nodes){
            if(addNode(node)){
                addedAny = true;
            }
        }
        return addedAny;
    }

    @Override
    public boolean addNodes(N... nodes) {
        if(nodes == null)  {
            throw new NullPointerException("Knoten Array ist null");
        }
        return addNodes(Arrays.asList(nodes));
    }

    @Override
    public Collection<N>getNodes(){
        return Collections.unmodifiableSet(nodes);
    }

    @Override
    public Edge<N,A> addEdge(N start, N destination, Optional<A> annotation){
        if(start==null || destination==null){
            throw new NullPointerException("Start & || Endknoten sind null");

        }
        if(!nodes.contains(start)){
            throw new IllegalArgumentException("Start " + start + " existiert nicht im Graphen");

        }
        if(!nodes.contains(destination)){
            throw new IllegalArgumentException("Zielknoten " + destination + " existiert nicht im Graphen");
        }
        Edge<N,A> edge= new Edge<>(start,destination,annotation);
        edges.add(edge);
        adjacencyList.get(start).add(edge);

        return edge;
    }

    @Override
    public Edge<N, A> addEdge(N start, N destination) {
        return Graph.super.addEdge(start, destination);
    }

    @Override
    public boolean removeEdge(Edge<N, A> edge) {
        if (edge == null) {
            return false;
        }
        boolean removed = edges.remove(edge);
        if (removed && adjacencyList.containsKey(edge.getStart())) {
            adjacencyList.get(edge.getStart()).remove(edge);
        }
        return removed;
    }
    @Override
    public Collection<Edge<N, A>> getNeighbours(N node) {
        if (node == null || !nodes.contains(node)) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(adjacencyList.get(node));
    }
    @Override
    public Collection<Edge<N, A>> getReachableFrom(N node) {
        return getNeighbours(node);
    }

    @Override
    public Collection<Edge<N, A>> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

    @Override
    public boolean removeNode(N node) {
        if (node == null || !nodes.contains(node)) {
            return false;
        }
        Set<Edge<N, A>> outgoingEdges = new HashSet<>(adjacencyList.get(node));
        for (Edge<N, A> edge : outgoingEdges) {
            removeEdge(edge);
        }
        Set<Edge<N, A>> edgesToRemove = new HashSet<>();
        for (Edge<N, A> edge : edges) {
            if (edge.getDestination().equals(node)) {
                edgesToRemove.add(edge);
            }
        }
        for (Edge<N, A> edge : edgesToRemove) {
            removeEdge(edge);
        }
        adjacencyList.remove(node);
        return nodes.remove(node);
    }
    @Override
    public boolean removeNodes(Collection<? extends N> nodes) {
        if (nodes == null) {
            return false;
        }

        boolean anyRemoved = false;
        for (N node : nodes) {
            if (removeNode(node)) {
                anyRemoved = true;

            }
        }
        return anyRemoved;
    }
    @Override
    public boolean removeNodes(N... nodes) {
        if (nodes == null) {
            return false;
        }
        return removeNodes(Arrays.asList(nodes));
    }

    @Override
    public void clear() {
        nodes.clear();
        edges.clear();
        adjacencyList.clear();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BasicGraph<?, ?> that = (BasicGraph<?, ?>) obj;
        return Objects.equals(this.nodes, that.nodes) &&
                Objects.equals(this.edges, that.edges);
    }
    @Override
    public int hashCode() {
        return Objects.hash(nodes, edges);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BasicGraph{\n");
        sb.append("  Knoten: ").append(nodes).append("\n");
        sb.append("  Kanten: [\n");
        for (Edge<N, A> edge : edges) {
            sb.append("    ").append(edge).append("\n");
        }
        sb.append("  ]\n");
        sb.append("}");
        return sb.toString();
    }
}
