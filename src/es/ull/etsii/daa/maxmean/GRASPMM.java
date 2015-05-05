package es.ull.etsii.daa.maxmean;

import java.util.Random;
import java.util.TreeSet;


public class GRASPMM extends GreedyMM_1 {
	public static final int PROBABILIDADMIN = 50;
	/* (non-Javadoc)
	 * @see es.ull.etsii.daa.maxmean.GreedyMM_1#getBestVertice(es.ull.etsii.daa.maxmean.ArrayList2D)
	 * se ha añadido una condicion basada en probabilidad
	 */
	public  vertice getBestVertice(ArrayList2D<Integer> costs) {
		Random randomGenerator = new Random();
		int max = Integer.MIN_VALUE;
		int row = 0;
		int column = 0;
		for (int i = 0; i < costs.getRows(); i++)
			for (int j = 0; j < i; j++) {
				int temp = costs.get(i, j);
				if (temp > max &&  randomGenerator.nextInt(100) >= PROBABILIDADMIN) { // en este punto se decide si mejorar o no el máximo de forma aleatoria
					max = temp;
					row = i;
					column = j;
				}
			}
		costs.set(row, column, null);
		costs.set(column, row,  null);
		return new vertice(row, column, max);
	}
	/* (non-Javadoc)
	 * @see es.ull.etsii.daa.maxmean.GreedyMM_1#getBestVertice(es.ull.etsii.daa.maxmean.ArrayList2D, java.util.TreeSet)
	 *	
	 */
	public  vertice getBestVertice(ArrayList2D<Integer> costs,
			TreeSet<Integer> usedNodes) {
		int max = Integer.MIN_VALUE;
		int row = 0;
		int column = 0;
		Random randomGenerator = new Random();
		for (Integer vert : usedNodes) {
			for (int j = 0; j < costs.getColumns(); j++) {
				Integer temp = costs.get(vert, j);
				if (temp != null && temp > max && randomGenerator.nextInt(100) >= PROBABILIDADMIN) { // en este punto se decide si mejorar o no el máximo de forma aleatoria
					max = temp;
					row = vert;
					column = j;
				}
			}
		}
		costs.set(row, column, null);
		costs.set(column, row,  null);
		return new vertice(row, column, max);
	}
}
