package de.jpp.io.interfaces;

import de.jpp.model.interfaces.Graph;

public interface GraphWriter<N, A, G extends Graph<N, A>, F> {

    F write(G graph);

}
