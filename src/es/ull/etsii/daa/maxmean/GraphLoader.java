package es.ull.etsii.daa.maxmean;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Javier Martín Hernández
 *	clase encargada de cargar un grafo desde fichero.
 */
public class GraphLoader {
	public static void load(String filepath, Graph<String> graph){
		Scanner s = null;
		try {
			s = new Scanner(new File(filepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int size = s.nextInt();
		graph.setMatrix(size);
        for(int visited = 0; visited < size ; visited++){
            for(int visiting = visited + 1; visiting < size; visiting++){
                int cost = s.nextInt();
                graph.addVertice(cost, visited, visiting);
                graph.addVertice(cost, visiting, visited);
            }
            graph.setNode(visited, "" + visited);
        }
	}

}
