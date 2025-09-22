package de.jpp.algorithm;

import de.jpp.model.interfaces.Edge;

public record NodeInformation<N, A>(Edge<N, A> predecessor,double distance) {

}
