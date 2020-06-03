package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TestWelshPowell {

	/*
	 * Le test reproduit l'exemple du cours, donc avec : 
		A B C D E F G H
		0 1 2 3 4 5 6 7
	 * */
	public static void main(String[] args) {
		try {
			Graph g1 = new Graph(new FileInputStream("testWP.txt"));
			System.out.println("g1 : card V = " + g1.V() + ", card E = " + g1.E());
			System.out.println(g1);
			System.out.println("-------------------");
			List<List<Integer>> coloration = g1.getWelshPowellColoration();
			for (int i = 0; i < coloration.size(); i++) {
				List<Integer> color = coloration.get(i);
				System.out.println("Color " + (i + 1) + ": "
						+ color.stream().map((j) -> j.toString()).collect(Collectors.joining(",")));
			}
		} catch (IOException e) {
			System.err.println("Format incorrect");
		}
	}

}
