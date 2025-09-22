package de.jpp.model;
import de.jpp.model.interfaces.Edge;

import java.util.Optional;


public class TwoDimGraphDebugTest {

    public static void main(String[] args) {
        basicDebugTest();
    }

    public static void basicDebugTest() {
        System.out.println("Basic TwoDimGraph Debug Test");

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
        } catch (Exception e) {
            System.out.println("Error creating edge: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n6. Testing direct addEdge (should work after fix):");
        TwoDimGraph graph3 = new TwoDimGraph();
        XYNode nodeC = new XYNode("C", 0.0, 0.0);
        XYNode nodeD = new XYNode("D", 1.0, 1.0);

        try {
            // This should work after the addEdge modification:
            Edge<XYNode, Double> directEdge = graph3.addEdge(nodeC, nodeD, Optional.of(1.414));
            System.out.println("Direct edge created successfully: " + directEdge);
        } catch (Exception e) {
            System.out.println("Direct edge failed: " + e.getMessage());
        }
        System.out.println("\n6. Testing getReachableFrom:");
        XYNode nodeE = new XYNode("E", 5.0, 5.0);
        XYNode nodeF = new XYNode("F", 6.0, 6.0);
        XYNode nodeG = new XYNode("G", 7.0, 7.0);

        graph1.addNode(nodeE);
        graph1.addNode(nodeF);
        graph1.addNode(nodeG);

        graph1.addEdge(nodeE, nodeF, Optional.of(1.0));
        graph1.addEdge(nodeF, nodeG, Optional.of(1.0));

        System.out.println("Neighbors of E: " + graph1.getNeighbours(nodeE).size());
        System.out.println("Reachable from E: " + graph1.getReachableFrom(nodeE).size());
        System.out.println("\n7. Creating test scenario to understand getReachableFrom:");
        TwoDimGraph testGraph = new TwoDimGraph();

// Create nodes similar to the failing test
        XYNode first = new XYNode("first", 1.0, 2.0);
        XYNode second = new XYNode("second", 2.0, 3.0);
        XYNode third = new XYNode("third", 3.0, 4.0);
        XYNode fourth = new XYNode("fourth", 4.0, 5.0);

// Add some edges to create a specific structure
        testGraph.addEdge(first, second, Optional.of(1.0));
        testGraph.addEdge(second, third, Optional.of(1.0));
        testGraph.addEdge(third, fourth, Optional.of(1.0));
        testGraph.addEdge(first, third, Optional.of(1.0));  // Direct connection

        System.out.println("Graph structure:");
        System.out.println("Edges: " + testGraph.getEdges().size());
        for (Edge<XYNode, Double> edge : testGraph.getEdges()) {
            System.out.println("  " + edge);
        }

        System.out.println("\nFrom third node:");
        System.out.println("Neighbors: " + testGraph.getNeighbours(third).size());
        System.out.println("Reachable: " + testGraph.getReachableFrom(third).size());
    }


}
