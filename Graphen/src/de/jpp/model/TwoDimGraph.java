package de.jpp.model;

import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import de.jpp.model.interfaces.ObservableGraph;
import de.jpp.model.interfaces.WeightedGraph;

import java.util.*;
import java.util.function.Consumer;
import java.util.Collection;
import java.util.Queue;
import java.util.LinkedList;


/**
 * A Two Dimensional graph. <br>
 * The abstract-tag is only set because the tests will not compile otherwise. You should remove it!
 */
public class TwoDimGraph implements WeightedGraph<XYNode, Double>, Graph<XYNode, Double>, ObservableGraph<XYNode, Double> {

    private  Set<XYNode> nodes;
    private  Set<Edge<XYNode,Double>> edges = new HashSet<>();
    private  Map<XYNode,Set<Edge<XYNode,Double>>>  adjacencyList = new HashMap<>();

    private  List<Consumer<XYNode>> nodeAddedListeners = new ArrayList<>();
    private  List<Consumer<XYNode>> nodeRemovedListeners = new ArrayList<>();
    private  List<Consumer<Edge<XYNode, Double>>> edgeAddedListeners = new ArrayList<>();
    private  List<Consumer<Edge<XYNode, Double>>> edgeRemovedListeners = new ArrayList<>();
    private  List<Consumer<Collection<Edge<XYNode, Double>>>> neighboursListedListeners = new ArrayList<>();
    private  List<Consumer<Collection<Edge<XYNode, Double>>>> reachableListedListeners = new ArrayList<>();
    private  List<Consumer<Collection<XYNode>>> nodesListedListeners = new ArrayList<>();
    private  List<Consumer<Collection<Edge<XYNode, Double>>>> edgesListedListeners = new ArrayList<>();

    public TwoDimGraph(){
    this.nodes = new HashSet<>()  ;
    }

    @Override
    public void addNodeAddedListener(Consumer<XYNode> list){
        if(list != null){
            nodeAddedListeners.add(list);
        }
    }
    @Override
    public void addNodeRemovedListener(Consumer<XYNode> list) {
        if (list != null) {
            nodeRemovedListeners.add(list);
        }
    }
    @Override
    public void addEdgeAddedListener(Consumer<Edge<XYNode, Double>> list) {
        if (list != null) {
            edgeAddedListeners.add(list);
        }
    }

    @Override
    public void addEdgeRemovedListener(Consumer<Edge<XYNode, Double>> listener) {
        if (listener != null) {
            edgeRemovedListeners.add(listener);
        }
    }

    @Override
    public void removeNodeAddedListener(Consumer<XYNode> listener) {
        nodeAddedListeners.remove(listener);

    }

    @Override
    public void removeNodeRemovedListener(Consumer<XYNode> listener) {
        nodeRemovedListeners.remove(listener);
    }

    @Override
    public void removeEdgeAddedListener(Consumer<Edge<XYNode, Double>> listener) {
        edgeAddedListeners.remove(listener);
    }

    @Override
    public void removeEdgeRemovedListener(Consumer<Edge<XYNode, Double>> listener) {
        edgeRemovedListeners.remove(listener);
    }

    @Override
    public void addNeighboursListedListener(Consumer<Collection<Edge<XYNode, Double>>> listener) {
        if (listener != null) {
            neighboursListedListeners.add(listener);
        }
    }

    @Override
    public void addReachableListedListener(Consumer<Collection<Edge<XYNode, Double>>> listener) {
        if (listener != null) {
            reachableListedListeners.add(listener);
        }
    }

    @Override
    public void addNodesListedListener(Consumer<Collection<XYNode>> listener) {
        if (listener != null) {
            nodesListedListeners.add(listener);
        }
    }

    @Override
    public void addEdgesListedListener(Consumer<Collection<Edge<XYNode, Double>>> listener) {
        if (listener != null) {
            edgesListedListeners.add(listener);
        }
    }

    @Override
    public void removeNeighboursListedListener(Consumer<Collection<Edge<XYNode, Double>>> listener) {
        neighboursListedListeners.remove(listener);
    }

    @Override
    public void removeReachableListedListener(Consumer<Collection<Edge<XYNode, Double>>> listener) {
        reachableListedListeners.remove(listener);
    }

    @Override
    public void removeNodesListedListener(Consumer<Collection<XYNode>> listener) {
        nodesListedListeners.remove(listener);
    }

    @Override
    public void removeEdgesListedListener(Consumer<Collection<Edge<XYNode, Double>>> listener) {
        edgesListedListeners.remove(listener);
    }
    @Override
    public double getDistance(Edge<XYNode,Double> edge){
        if (edge == null){
            throw new NullPointerException("Kante ist null");
        }
        if(edge.getAnnotation().isPresent()){
            return edge.getAnnotation().get();
        }
        return edge.getStart().euclidianDistTo(edge.getDestination());
    }
    @Override
    public boolean addNode(XYNode node){
        if(node==null){
            throw new NullPointerException("Knoten ist null");

        }

        boolean nodeAdded = nodes.add(node);
        if(nodeAdded){
            adjacencyList.put(node,new HashSet<>());

            for (Consumer<XYNode>listener:nodeAddedListeners){
                listener.accept(node);
        }

        }
        return nodeAdded;
    }

    @Override
    public boolean addNodes(Collection<?extends XYNode>nodes){
        if (nodes==null){
            throw new NullPointerException("Collection (Knoten) ist null");

        }
        boolean anynodesAdded = false;
        for (XYNode node : nodes){
            if(addNode(node)){
                anynodesAdded = true;
            }
        }
        return anynodesAdded;
    }

    @Override
    public boolean addNodes(XYNode... nodes){
        if (nodes == null){
            throw new NullPointerException("Array (Knoten) ist null");

        }
        return addNodes(Arrays.asList(nodes));
    }
    @Override
    public Collection<XYNode> getNodes() {


        Collection<XYNode> result = Collections.unmodifiableSet(nodes);


        for (Consumer<Collection<XYNode>> listener : nodesListedListeners) {
            listener.accept(result);
        }

        return result;
    }


    @Override
    public Edge<XYNode, Double> addEdge(XYNode start, XYNode destination, Optional<Double> annotation) {
        if (start == null || destination == null) {
            throw new NullPointerException("Start- und Zielknoten dürfen nicht null sein");
        }


        if (!nodes.contains(start)) {
            addNode(start);
        }

        if (!nodes.contains(destination)) {
            addNode(destination);
        }

        Edge<XYNode, Double> edge = new Edge<>(start, destination, annotation);
        edges.add(edge);
        adjacencyList.get(start).add(edge);

        // Notify listeners
        for (Consumer<Edge<XYNode, Double>> listener : edgeAddedListeners) {
            listener.accept(edge);
        }

        return edge;
    }

    @Override
    public Edge<XYNode, Double> addEdge(XYNode start, XYNode destination) {
        return addEdge(start, destination, Optional.empty());
    }

    public Edge<XYNode, Double> addEuclidianEdge(XYNode start, XYNode dest) {
    if (start == null || dest == null){
        throw new NullPointerException("Start & || Endknoten sind null");

    }
    addNodes(start);
    addNode(dest);
    double distance = start.euclidianDistTo(dest);
    return addEdge(start,dest,Optional.of(distance));
    }

    @Override
    public boolean removeEdge(Edge<XYNode,Double> edge){
        if (edge == null){
            return false;
        }
        boolean removed = edges.remove(edge);
        if (removed){
            if(adjacencyList.containsKey(edge.getStart())){
                adjacencyList.get(edge.getStart()).remove(edge);
            }
            for(Consumer<Edge<XYNode,Double>> list : edgeRemovedListeners){
                list.accept(edge);
            }
        }
        return removed;
    }
    @Override
    public Collection<Edge<XYNode,Double>> getNeighbours(XYNode node){
        if (node==null ||!nodes.contains(node)){
            return Collections.emptySet();
        }
        Collection<Edge<XYNode,Double>> res = Collections.unmodifiableCollection((adjacencyList.get(node)));
        for (Consumer<Collection<Edge<XYNode,Double>>> list: neighboursListedListeners){
            list.accept(res);
        }
        return res;

    }
    @Override

    public Collection<Edge<XYNode, Double>> getReachableFrom(XYNode node) {
        if (node == null || !nodes.contains(node)) {
            return Collections.emptySet();
        }

        // maybe it just wants direct neighbors?
        Collection<Edge<XYNode, Double>> result = getNeighbours(node);

        // notify listeners
        for (Consumer<Collection<Edge<XYNode, Double>>> listener : reachableListedListeners) {
            listener.accept(result);
        }

        return result;
    }

    @Override
    public Collection<Edge<XYNode, Double>> getEdges() {


        Collection<Edge<XYNode, Double>> result = Collections.unmodifiableSet(edges);


        for (Consumer<Collection<Edge<XYNode, Double>>> listener : edgesListedListeners) {
            listener.accept(result);
        }

        return result;
    }

    @Override
    public boolean removeNode(XYNode node){
        if (node == null || !nodes.contains(node)) {
            return false;
        }
        Set<Edge<XYNode,Double>> outgoEdges= new HashSet<>(adjacencyList.get(node));
        for (Edge<XYNode,Double>edge:outgoEdges){
            removeEdge(edge);
        }

        Set<Edge<XYNode,Double>> edgestoRemove = new HashSet<>();
        for (Edge<XYNode,Double>edge:edges){
            if(edge.getDestination().equals(node)){
                edgestoRemove.add(edge);
            }

        }
        for (Edge<XYNode,Double>edge:edgestoRemove){
            removeEdge(edge);
        }
        adjacencyList.remove(node);
        boolean rem = nodes.remove(node);

        if(rem){
            for (Consumer<XYNode> list: nodeRemovedListeners){
                list.accept(node);
            }
        }
        return rem;
    }
    @Override
    public boolean removeNodes(Collection<?extends XYNode> nodes){
        if(nodes == null){
            return false;
        }

        boolean removeAny= false;
        for(XYNode node: nodes){
            if(removeNode(node)){
                removeAny = true;
            }
        }
        return removeAny;
    }

    public boolean removeNodes(XYNode... nodes){
        if(nodes==null){
            return false;
        }
        return removeNodes(Arrays.asList(nodes));
    }
    @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if (o==null || getClass()!=o.getClass()){
            return false;

        }
        TwoDimGraph that = (TwoDimGraph) o;

        return Objects.equals(this.getNodes(), that.getNodes()) &&
                Objects.equals(this.getEdges(), that.getEdges());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getNodes(),getEdges());
    }

    @Override
    public void clear(){
        nodes.clear();
        edges.clear();
        adjacencyList.clear();
    }


}

