import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class WeightedGraph {
	private int vertices;
	private int edges;

	// make use of hashmap to store adjacency list for each vertex
	// source:
	// https://progressivecoder.com/graph-implementation-in-java-using-hashmap/
	public HashMap<Vertex, List<Edge>> adjacencyList = new HashMap<>();

	public WeightedGraph(int vertices, int edges) {
		this.vertices = vertices;
		this.edges = edges;
		generateRandomEdgesAndVertices(edges, vertices);
	}

	public void generateRandomEdgesAndVertices(int edges, int vertices) {

		for (int i = 0; i < vertices; i++) {
			adjacencyList.put(new Vertex("V" + i), new ArrayList<Edge>());
		}

		int n = 0;
		while (n != edges) {
			ArrayList<Vertex> vList = new ArrayList<Vertex>(adjacencyList.keySet());
			Random r = new Random();
			Vertex from;
			Vertex to;
			from = vList.get((r.nextInt(vertices)));
			to = vList.get((r.nextInt(vertices)));
			while (to == from) {
				to = vList.get((r.nextInt(vertices)));
			}

			int weight = r.nextInt(1000);
			Edge toFrom = new Edge(from, weight);
			Edge fromTo = new Edge(to, weight);

			ArrayList<Edge> edgesFrom = new ArrayList<Edge>();
			ArrayList<Edge> edgesTo = new ArrayList<Edge>();
			edgesFrom = (ArrayList<Edge>) adjacencyList.get(from);
			edgesTo = (ArrayList<Edge>) adjacencyList.get(to);
			edgesFrom.add(fromTo);
			edgesTo.add(toFrom);
			n++;

		}
	}

	public ArrayList getVertexList() {
		ArrayList<Vertex> vList = new ArrayList<Vertex>(adjacencyList.keySet());
		return vList;
	}

	public ArrayList getEdges(Vertex v) {
		return (ArrayList<Edge>) adjacencyList.get(v);
	}

	public void printDetails() {
		ArrayList<Vertex> vList = new ArrayList<Vertex>(adjacencyList.keySet());
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < adjacencyList.size(); i++) {
			edges = (ArrayList<Edge>) adjacencyList.get(vList.get(i));

			System.out.println("_______________");
			System.out.println("Vertex - " + vList.get(i).getName());

			for (Edge edge : edges) {
				System.out.println(edge.getVName() + " Weight:" + edge.getWeight());
			}
			System.out.println("Total edges: " + edges.size());

		}
	}

	// source: https://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
	// https://stackoverflow.com/questions/60105822/dijkstras-algorithm-java-last-edge-always-gets-added-instead-of-shortest
	public static String findShortestPath(WeightedGraph graph, Vertex start, Vertex destination) {
		LinkedList<Vertex> l = new LinkedList<Vertex>();
		Set<Vertex> explored = new HashSet<>();
		HashMap shortestPath = new HashMap();
		shortestPath.put(start, start.getName());
		l.add(start);
		while (!l.isEmpty()) {
			Vertex current = l.remove();

			if (current.equals(destination)) {
				return (String) shortestPath.get(current);
			} else {
				ArrayList<Edge> edges = graph.getEdges(current);

				for (int i = 0; i < edges.size(); i++) {
					Vertex next = edges.get(i).getV();
					if (!explored.contains(next)) {
						l.add(next);
						String shortestP = shortestPath.get(current) + " -> " + next.getName();
						if (shortestPath.containsKey(next)) {
							String in = (String) shortestPath.get(next);
							if (in.length() >= shortestP.length()) {
								shortestPath.put(next, shortestP);
							}
						} else
							shortestPath.put(next, shortestP);
					}
					if (next.equals(destination)) {
						return (String) shortestPath.get(next);
					}
				}
			}
			explored.add(current);
		}
		return "*Error: Path was not found!*";
	}

	public static String findCheapestPath(WeightedGraph graph, Vertex start, Vertex destination) {
		Set<Vertex> visited = new HashSet<Vertex>();
		HashMap cheapestPath = new HashMap();
		ArrayList<Vertex> vertices = graph.getVertexList();
		cheapestPath.put(start, start.getName());
		start.setPathCost(0);
		Queue<Vertex> q = new PriorityQueue<>();
		q.add(start);
		while (!q.isEmpty()) {
			Vertex current = q.remove();
			ArrayList<Edge> edges = graph.getEdges(current);
			for (int i = 0; i < edges.size(); i++) {
				Vertex next = edges.get(i).getV();
				if (!visited.contains(next)) {
					q.add(next);
					int newPathCost = edges.get(i).getWeight() + current.getPathCost();
					if (next.getPathCost() >= newPathCost) {
						next.setPathCost(newPathCost);
						String cheapestV = cheapestPath.get(current) + " -> " + next.getName();
						cheapestPath.put(next, cheapestV);
					}
				}
			}
			visited.add(current);
		}
		String result;
		if (!(cheapestPath.get(destination) == null))
			result = cheapestPath.get(destination) + " and the total cost is " + destination.getPathCost() + ".";
		else
			result = "*Error: Path was not found!*";
		return result;
	}

}