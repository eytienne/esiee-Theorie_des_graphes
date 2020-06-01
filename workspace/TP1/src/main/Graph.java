package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.TreeMap;

public class Graph {
	private Map<Integer, List<Integer>> adjList;

	private void pGraph(int v) {
		this.adjList = new TreeMap<Integer, List<Integer>>();
		for (int i = 0; i < v; i++) {
			this.adjList.put(i, new ArrayList<Integer>());
		}
	}

	public Graph(int v) {
		pGraph(v);
	}

	public Graph(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		int V = Integer.parseInt(br.readLine());
		pGraph(V);
		int E = Integer.parseInt(br.readLine());
		int count = 0;
		while (count++ < E) {
			String line = br.readLine();
			String[] vertices = line.split(" ");
			this.addEdge(Integer.parseInt(vertices[0]), Integer.parseInt(vertices[1]));
		}
	}

	public int V() {
		return this.adjList.size();
	}

	public int E() {
		int E = 0;
		for (List<Integer> adjVertices : this.adjList.values()) {
			E += adjVertices.size();
		}
		return E / 2;
	}

	public void addEdge(int v, int w) {
		this.adjList.get(Integer.valueOf(v)).add(w);
		this.adjList.get(Integer.valueOf(w)).add(v);
	}

	public Iterable<Integer> adj(int v) {
		return this.adjList.get(Integer.valueOf(v));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<Integer, List<Integer>>> it = this.adjList.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, List<Integer>> entry = it.next();
			sb.append(entry.getKey());
			sb.append(" -> [");
			boolean first = true;
			for (Integer adjVertex : entry.getValue()) {
				sb.append((!first ? "," : "") + adjVertex);
				first = false;
			}
			sb.append("]" + (it.hasNext() ? "\n" : ""));
		}
		return sb.toString();
	}

}
