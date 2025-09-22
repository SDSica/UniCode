package de.jpp.factory;


import de.jpp.io.interfaces.GraphReader;
import de.jpp.io.interfaces.GraphWriter;
import de.jpp.model.LabelMapGraph;
import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;

import java.awt.image.BufferedImage;
import java.util.Map;

public class IOFactory {

    public GraphReader<XYNode, Double, TwoDimGraph, String> getTwoDimGxlReader() {
        throw new UnsupportedOperationException("not supported yet!");
    }

    public GraphReader<XYNode, Double, TwoDimGraph, String> getTwoDimDotReader() {
        throw new UnsupportedOperationException("not supported yet!");
    }

    public GraphReader<XYNode, Double, TwoDimGraph, BufferedImage> getTwoDimImgReader() {
        throw new UnsupportedOperationException("not supported yet!");
    }
    public GraphReader<String, Map<String, String>, LabelMapGraph, String> getLabelMapGraphGxlReader() {
        throw new UnsupportedOperationException("not supported yet!");
    }

    public GraphWriter<XYNode, Double, TwoDimGraph, String> getTwoDimGxlWriter() {
        throw new UnsupportedOperationException("not supported yet!");
    }

    public GraphWriter<XYNode, Double, TwoDimGraph, String> getTwoDimDotWriter() {
        throw new UnsupportedOperationException("not supported yet!");
    }

    public GraphWriter<String, Map<String, String>, LabelMapGraph, String> getLabelMapGraphGxlWriter() {
        throw new UnsupportedOperationException("not supported yet!");
    }


}
