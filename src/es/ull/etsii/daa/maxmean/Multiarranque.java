package es.ull.etsii.daa.maxmean;

import java.util.Collection;
import java.util.TreeSet;

/**
 * @author Javier Martín Hernández
 * método que usa un algoritmo grasp para elegir el arranque y una búsqueda local para mejorarlos.
 *
 */
public class Multiarranque {
	public static final int MAXREPETITION = 10; // numero de veces que se repetirá si no se modifica el último resyltado
	private double value = 0;
	private GRASPMM algorithm = new GRASPMM();
	protected LocalSearch localSearch = new LocalSearch();
	private TreeSet<Integer> lastSolution = null;
	
	/**
	 * @return
	 */
	public TreeSet<Integer> getLastSolution() {
		return lastSolution;
	}

	/**
	 * @param lastSolution
	 */
	public void setLastSolution(TreeSet<Integer> lastSolution) {
		this.lastSolution = lastSolution;
	}

	/**
	 * @return
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value
	 */
	private void setValue(double value) {
		this.value = value;
	}

	/**
	 * @param graph
	 * @return
	 */
	public Collection<Integer> calc(Graph<String> graph) {
		int repetition = 0;
		TreeSet<Integer> newsolution = new TreeSet<Integer>(localSearch.improveSolution(algorithm.calc(graph), graph));
		setValue(localSearch.getValue());
		setLastSolution(new TreeSet<Integer>(newsolution));
		while (repetition < MAXREPETITION) {
			newsolution = new TreeSet<Integer>(localSearch.improveSolution(algorithm.calc(graph), graph));
			if(localSearch.getValue() > getValue()){
				setValue(localSearch.getValue());
				setLastSolution(newsolution);
			}else
				repetition++;
		}
		return newsolution;

	}
}
