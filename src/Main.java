import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		WeightedGraph graph = new WeightedGraph(20, 40);
		graph.printDetails();
		
		// Generate two random vertices (start & destination), if both are equal generate a new vertice for destination
		ArrayList<Vertex> v = graph.getVertexList();
		Random random = new Random();
		Vertex start = v.get((random.nextInt(v.size())));
		Vertex destination = v.get((random.nextInt(v.size())));
		while (start == destination)
			destination = v.get((random.nextInt(v.size())));
		
		// output in console
		System.out.println(".................................................................................");
		System.out.println("Start: " + start.getName() + " Destination: " + destination.getName());
		System.out.println("The shortest path is " + WeightedGraph.findShortestPath(graph, start, destination));
		System.out.println("The cheapest path is " + WeightedGraph.findCheapestPath(graph, start, destination));
	}
}

