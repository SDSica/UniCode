package de.jpp.model;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import java.util.Optional;
import de.jpp.factory.GraphFactory;
import de.jpp.model.interfaces.ObservableGraph;

public class FactoryTest {
    public static void main(String[] args) {
        System.out.println("Factory Test");


        Graph<String, Double> basicGraph = GraphFactory.createNewGraph();
        basicGraph.addNode("A");
        basicGraph.addNode("B");
        basicGraph.addEdge("A", "B", Optional.of(5.0));
        System.out.println("Basic Graph: " + basicGraph);


        ObservableGraph<String, String> obsGraph = GraphFactory.createNewObservableGraph();
        obsGraph.addNodeAddedListener(node -> System.out.println("Added: " + node));
        obsGraph.addNode("X");  // Should print "Added: X"


        TwoDimGraph twoDim = GraphFactory.createNewTwoDimGraph();
        LabelMapGraph labelMap = GraphFactory.createNewLabelMapGraph();

        System.out.println("Methods work.");
    }
    public static void basicDebugTest() {
        System.out.println("=== Basic TwoDimGraph Debug Test ===");

        TwoDimGraph graph1 = new TwoDimGraph();
        TwoDimGraph graph2 = new TwoDimGraph();

        System.out.println("1. Testing empty graphs:");
        System.out.println("Graph1 empty equals Graph2 empty: " + graph1.equals(graph2)); // Should be TRUE

        System.out.println("\n2. Adding node to graph1:");
        XYNode nodeA = new XYNode("A", 1.0, 2.0);
        boolean addResult = graph1.addNode(nodeA);
        System.out.println("Add result: " + addResult);

        System.out.println("\n3. Testing different graphs:");
        System.out.println("Graph1 (1 node) equals Graph2 (0 nodes): " + graph1.equals(graph2)); // Should be FALSE

        System.out.println("\n4. Testing node existence:");
        System.out.println("Graph1 contains nodeA: " + graph1.getNodes().contains(nodeA));

        System.out.println("\n5. Testing addEuclidianEdge:");
        XYNode nodeB = new XYNode("B", 4.0, 6.0);
        try {
            Edge<XYNode, Double> edge = graph1.addEuclidianEdge(nodeA, nodeB);
            System.out.println("Edge created successfully: " + edge);
        } catch (Exception e) {}}}
