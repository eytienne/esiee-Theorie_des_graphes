package main;
import java.io.FileInputStream;
import java.io.IOException;

public class Appli {

	public static void main(String[] args) {
		try {
			Graph g1 = new Graph(new FileInputStream("tinyG.txt"));
			System.out.println("g1 : card V = "+g1.V()+", card E = "+g1.E());
			System.out.println(g1);
		} catch (IOException e) {
			System.err.println("Format incorrect");
		}
	}

}
