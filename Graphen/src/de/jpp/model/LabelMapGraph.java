package de.jpp.model;

import de.jpp.model.interfaces.Graph;
import de.jpp.model.interfaces.Edge;

import java.util.Map;
import java.util.Set;
import java.util.*;

/**
 * A LabelMapGraph. <br>
 * The abstract-tag is only set because the tests will not compile otherwise. You should remove it!
 */
public class LabelMapGraph implements Graph<String, Map<String, String>> {

    private final Set<String> nodes;
    private final Set<Edge<String,Map<String,String>>> edges;
    private final Map<String,Set<Edge<String,Map<String,String>>>>adjacencyList;
    
    public LabelMapGraph(){
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
        this.adjacencyList = new HashMap<>();
    }
    @Override
    public boolean addNode(String node) {
        if(node==null){
            throw new NullPointerException("Knoten ist null!");

        }
        boolean addedNode = nodes.add(node);
        if(addedNode){
            adjacencyList.put(node,new HashSet<>());
        }
        return addedNode;
    }

    @Override
    public boolean addNodes(Collection<? extends String> nodes) {
        if (nodes == null){
            throw new NullPointerException("Knoten Collection ist null!");

        }
        boolean addedAny = false;
        for (String node: nodes){
            if (addNodes(node)){
                addedAny = true;
            }
        }
        return addedAny;
    }

    @Override
    public boolean addNodes(String... nodes) {
        if (nodes == null){
            throw new NullPointerException("Knoten-Arr ist null!");

        }
        return addNodes(Arrays.asList(nodes));

    }

    @Override
    public Collection<String> getNodes() {
        return Collections.unmodifiableSet(nodes);
    }

    @Override
    public Edge<String, Map<String, String>> addEdge(String start, String destination, Optional<Map<String, String>> annotation) {
        if(start==null||destination==null){
            throw new NullPointerException("Start & | || Endknoten sind null!");
        }
        if (!nodes.contains(start)){

            throw new IllegalArgumentException("Startknoten " +start+" ist nicht im Graphen!");
        }
        if(!nodes.contains(destination)){
            throw new IllegalArgumentException("Endknoten " + destination + " ist nicht im Graphen!");
        }
        Edge<String,Map<String,String>>edge=new Edge<>(start,destination,annotation);
        return edge;
    }

    @Override
    public boolean removeEdge(Edge<String, Map<String, String>> edge) {
        if(edge==null){
            return false;
        }
        boolean rem = edges.remove(edge);
        if (rem && adjacencyList.containsKey(edge.getStart())){
            adjacencyList.get(edge.getStart()).remove(edge);

        }
        return rem;
    }

    @Override
    public Collection<Edge<String, Map<String, String>>> getNeighbours(String node) {
        if (node == null || !nodes.contains(node)){
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(adjacencyList.get(node));
    }

    @Override
    public Collection<Edge<String, Map<String, String>>> getReachableFrom(String node) {
        return getNeighbours(node);
    }

    @Override
    public Collection<Edge<String, Map<String, String>>> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

    @Override
    public boolean removeNode(String node) {
        if (node == null || !node.contains(node)){
            return false;
        }
        Set<Edge<String,Map<String,String>>> outgoingEdgeSet = new HashSet<>(adjacencyList.get(node));
        for (Edge<String,Map<String,String>> edge : outgoingEdgeSet){
            removeEdge(edge);
        }

        Set<Edge<String,Map<String,String>>> edgeToRemove = new HashSet<>();
        for (Edge<String,Map<String,String>> edge: edges){
            if(edge.getDestination().equals(node)){
                edgeToRemove.add(edge);
            }
        }
        for (Edge<String,Map<String,String>> edge : edgeToRemove){
            removeEdge(edge);
        }
        adjacencyList.remove(node);
        return nodes.remove(node);
    }

    @Override
    public boolean removeNodes(Collection<? extends String> nodes) {
        if(nodes == null){
            return false;
        };

        boolean anyRemove = false;
        for (String node : nodes){
            if(removeNodes(node)){
                anyRemove = true;
            }
        }
        return anyRemove;
    }

    @Override
    public boolean removeNodes(String... nodes) {
        if (nodes == null){
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
    public String toString(){
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append("Labelmapgraph \n");
        stringBuilder.append("Knoten: ").append(nodes).append("\n");
        stringBuilder.append("Kanten: [\n");
        for (Edge<String,Map<String,String>>edge:edges){
            stringBuilder.append(" ").append(edge).append("\n");

        }
        stringBuilder.append(" ]\n");
        return stringBuilder.toString();
    }
}
