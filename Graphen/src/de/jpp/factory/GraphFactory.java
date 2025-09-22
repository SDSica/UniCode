package de.jpp.factory;

import de.jpp.model.LabelMapGraph;
import de.jpp.model.TwoDimGraph;
import de.jpp.model.interfaces.Graph;
import de.jpp.model.interfaces.ObservableGraph;
import de .jpp.model.BasicGraph;
import de.jpp.model.ObservableGraphImpl;

import java.awt.*;

public class GraphFactory {

    public static <N, A> Graph<N, A> createNewGraph() {

        return new BasicGraph<N,A>();
    }

    public static <N, A> ObservableGraph<N, A> createNewObservableGraph() {
        return new ObservableGraphImpl<>();
    }

    public static TwoDimGraph createNewTwoDimGraph() {
        return new TwoDimGraph();
    }

    public static LabelMapGraph createNewLabelMapGraph() {
        return new LabelMapGraph() ;
    }


}
