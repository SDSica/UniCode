package de.jpp.io.interfaces;

import de.jpp.model.interfaces.Graph;

public interface GraphReader<N, A, G extends Graph<N, A>, F> {

    G read(F input) throws ParseException;


}
