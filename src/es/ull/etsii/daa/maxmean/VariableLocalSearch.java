package es.ull.etsii.daa.maxmean;

import java.util.Random;
import java.util.TreeSet;

/**
 * @author Javier Martín Hernández
 * clase derivada de LocalSearch que añade aleatoriedad, modificando su estructura de entorno dinámicamente
 *
 */
public class VariableLocalSearch extends LocalSearch{
	protected TreeSet<Integer> checkNeighbors(TreeSet<Integer> nodesRes,
			TreeSet<Integer> notUsed, ArrayList2D<Integer> distances, int sum) {
		Random randGenerator = new Random();
		switch (randGenerator.nextInt(3)) {
		case 0:
			nodesRes = getbetter(bestEnviromentWithOneLessnode(nodesRes,distances, sum), nodesRes, distances);
			break;
		case 1:
			nodesRes = getbetter(bestEnviromentWithOneMorenode(nodesRes,notUsed,distances, sum), nodesRes, distances);
			break;
		default:
			nodesRes = getbetter(bestEnviromentWithOneLessnode(bestEnviromentWithOneMorenode(nodesRes,notUsed,distances, sum),distances, sum), nodesRes, distances);
			break;
		}
		return nodesRes;
	}
}
