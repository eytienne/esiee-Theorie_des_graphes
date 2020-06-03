package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.TreeMap;

public class Graph {
	private Map<Integer, List<Integer>> adjList;

	private void pGraph(int v) {
		adjList = new TreeMap<Integer, List<Integer>>();
		for (int i = 0; i < v; i++) {
			adjList.put(i, new ArrayList<Integer>());
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
		return adjList.size();
	}

	public int E() {
		int E = 0;
		for (List<Integer> adjVertices : adjList.values()) {
			E += adjVertices.size();
		}
		return E / 2;
	}

	public void addEdge(int v, int w) {
		adjList.get(Integer.valueOf(v)).add(w);
		adjList.get(Integer.valueOf(w)).add(v);
	}

	public Iterable<Integer> adj(int v) {
		return this.adjList.get(Integer.valueOf(v));
	}

	public List<List<Integer>> getWelshPowellColoration() {
		List<List<Integer>> ret = new LinkedList<List<Integer>>();
		List<Integer> vertices = new ArrayList<>();
		for (Integer entry : adjList.keySet()) {
			vertices.add(entry);
		}
		vertices.sort((a, b) -> adjList.get(b).size() - adjList.get(a).size());
		while (!vertices.isEmpty()) {
			List<Integer> newColor = new LinkedList<>();
			newColor.add(vertices.remove(0));
			for (int i = 0; i < vertices.size(); i++) {
				Integer v = vertices.get(i);
				boolean adjacentToNewColor = false;
				List<Integer> adjToV = adjList.get(v);
				for (Integer c : newColor) {
					if (adjToV.contains(c)) {
						adjacentToNewColor = true;
						break;
					}
				}
				if (!adjacentToNewColor) {
					newColor.add(v);
					vertices.remove(i);
					i--;
				}
			}
			ret.add(newColor);
		}
		return ret;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<Integer, List<Integer>>> it = adjList.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, List<Integer>> entry = it.next();
			sb.append(entry.getKey());
			sb.append(" -> [");
			sb.append(entry.getValue().stream().map((j) -> j.toString()).collect(Collectors.joining(",")));
			sb.append("]" + (it.hasNext() ? "\n" : ""));
		}
		return sb.toString();
	}

}
