package de.jpp.factory;

import de.jpp.algorithm.StartToDestStrategy;
import de.jpp.algorithm.interfaces.SearchStopStrategy;

public class SearchStopFactory {

    public <N> SearchStopStrategy<N> expandAllNodes() {
        throw new UnsupportedOperationException("not supported yet!");
    }


    public <N> SearchStopStrategy<N> maxNodeCount(int maxCount) {
        throw new UnsupportedOperationException("not supported yet!");
    }

    public <N> StartToDestStrategy<N> startToDest(N dest) {
        throw new UnsupportedOperationException("not supported yet!");
    }

}
