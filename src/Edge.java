
public class Edge {
	private Vertex v;
	int weight;

	public Edge(Vertex v, int weight) {
		this.v = v;
		this.weight = weight;
	}

	public String getVName() {
		return v.getName();
	}

	public Vertex getV() {
		return v;
	}

	public int getWeight() {
		return weight;
	}
}
