
public class Vertex implements Comparable<Vertex> {
	String name;
	public int pathCost = Integer.MAX_VALUE;

	public Vertex(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPathCost(int cost) {
		pathCost = cost;
	}

	public int getPathCost() {
		return pathCost;
	}

	// Comparing two objects (two vertex) by using Comparable method compareTo
	// Source: https://www.happycoders.eu/de/java/comparator-comparable-compareto/

	public int compareTo(Vertex v) {

		if (this.pathCost <= v.getPathCost())
			return -1;
		if (this.pathCost >= v.getPathCost())
			return 1;
		return 0;
	}
}