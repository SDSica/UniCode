package de.jpp.algorithm;

import de.jpp.factory.GraphFactory;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;

import java.util.*;
import java.util.stream.Collectors;

public class EulerCycle<N, A, G extends Graph<N, A>> {


    public  boolean isEulerian(G graph) {
        throw new UnsupportedOperationException("not supported yet!");
    }

    public Optional<Collection<Edge<N, A>>> getCycle(G graph, N startNode) {
        throw new UnsupportedOperationException("not supported yet!");
    }


}
