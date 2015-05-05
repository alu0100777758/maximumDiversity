package es.ull.etsii.daa.maxmean;

import java.util.Collection;
import java.util.TreeSet;

/**
 * @author Javier Martín Hernández
 * Algoritmo greedy constructivo que parte del mejor vertice
 *
 */
public class GreedyMM_1 {
	private double value = Double.MIN_VALUE;	//	almacena el valor de la solución
	private TreeSet<Integer> lastSolution = null;	//	almacena la solución
	
	public TreeSet<Integer> getLastSolution() {
		return lastSolution;
	}

	public void setLastSolution(TreeSet<Integer> lastSolution) {
		this.lastSolution = lastSolution;
	}
	
	public double getValue() {
		return value;
	}
	private void setValue(double value) {
		this.value = value;
	}
	/**
	 * @param graph
	 * @return nodos que forman parte de la solución
	 *  metodo que resuelve el problema.
	 */
	public  Collection<Integer> calc(Graph<String> graph) {
		ArrayList2D<Integer> distances = graph.getDistances();
		TreeSet<Integer> nodesRes = new TreeSet<Integer>();
		int sum = initialize(nodesRes, distances);	
		double lastMax = mean(sum, nodesRes.size());
		boolean improve = true;
		while (improve && loopCondition(graph, nodesRes)) {
			vertice toCheck = getBestVertice(distances, nodesRes);
			boolean neeedsRecover = simRes(nodesRes, toCheck);	// se anota si se ha realizado alguna modificación en el conjunto resultado
			sum += getNewCosts(distances, nodesRes, toCheck.getTo());
			if (!improves(lastMax , sum ,toCheck , nodesRes)) {// si no mejora se marca y se deshacen las modificaciones si fuera necesario
				improve = false;
				if(neeedsRecover){
					simRecover(nodesRes, toCheck);
				}
			} else {
				sum =  improvementAction(sum, toCheck, nodesRes);
				lastMax = mean(sum, nodesRes.size());
			}
		}
		setValue(lastMax);
		setLastSolution(nodesRes);
		return nodesRes;

	}
	/**
	 * @param nodesRes
	 * @param distances
	 * @return int sum despues de iniciar
	 * metodo encargado de definir el estado inicial del algoritmo.
	 */
	public int initialize(TreeSet<Integer> nodesRes, ArrayList2D<Integer> distances){
		vertice firstBetter = getBestVertice(distances);
		nodesRes.add(firstBetter.getFrom());
		nodesRes.add(firstBetter.getTo());
		return firstBetter.getCost();
	}
	/**
	 * @param sum
	 * @param toCheck
	 * @param nodesRes
	 * @return sum tras realizar la operaci'on de mejora
	 * metodo que se encarga de realizar las operaciones necesarias ante una mejora.
	 */
	public int improvementAction(Integer sum, vertice toCheck, TreeSet<Integer> nodesRes){
		return sum + toCheck.getCost();
	}
	/**
	 * @param costs
	 * @param nodes
	 * @return el valor de la dispersión media.
	 */
	public double mean(int costs, int nodes){
		return (double)costs/(double)nodes;
	}
	/**
	 * @param nodesRes
	 * @param checking
	 * @return si se ha modificado algo
	 * operación que realiza la simulación necesaria para comprobar si el nodo objetivo forma parte de la solución
	 */
	public  boolean simRes(TreeSet<Integer> nodesRes, vertice checking){
		return nodesRes.add(checking.getTo());
	}
	/**
	 * @param nodesRes
	 * @param checking
	 * operacion que deshace la simulacion. en este caso por ejemplo la simulación incluye un nodo y este metodo lo elimina.
	 */
	public  void simRecover(TreeSet<Integer> nodesRes, vertice checking){
		nodesRes.remove(checking.getTo());
	}
	/**
	 * @param old
	 * @param sum
	 * @param toCheck
	 * @param nodesRes
	 * @return true si se considera que la nueva operacion mejora el resultado
	 */
	public  boolean improves(double old, int sum, vertice toCheck, TreeSet<Integer> nodesRes){
		double newer = mean(sum + toCheck.getCost(), nodesRes.size());
		return newer > old;
	}
	/**
	 * @param graph
	 * @param nodesRes
	 * @return true si se sigue cumpliendo la condición para iterar
	 */
	public  boolean loopCondition(Graph<String> graph, TreeSet<Integer> nodesRes){
		return (nodesRes.size() < graph.getSizeN());
	}
	/**
	 * @param distances
	 * @param nodesRes
	 * @param nodetoadd
	 * @return int cifra que se debe sumar al sumatorio tras  insertar el nodo "nodetoadd"
	 */
	public  int getNewCosts(ArrayList2D<Integer> distances,TreeSet<Integer> nodesRes, int nodetoadd){
		int sum = 0;
		for(Integer from: nodesRes){
			Integer dist = distances.get(from,nodetoadd);
			if(dist == null)
				dist = 0;
			sum += dist;
		}
		return sum;
	}
	/**
	 * @param costs
	 * @return el mejor vertice disponible desde una matriz de distancias
	 * pone a null los vertices ya obtenidos.
	 */
	public  vertice getBestVertice(ArrayList2D<Integer> costs) {
		int max = Integer.MIN_VALUE;
		int row = 0;
		int column = 0;
		for (int i = 0; i < costs.getRows(); i++)
			for (int j = 0; j < i; j++) {
				int temp = costs.get(i, j);
				if (temp > max) {
					max = temp;
					row = i;
					column = j;
				}
			}
		costs.set(row, column, null);
		costs.set(column, row,  null);
		return new vertice(row, column, max);
	}

	/**
	 * @param costs
	 * @param usedNodes
	 * @return el mejor vertice disponible alcanzable
	 */
	public  vertice getBestVertice(ArrayList2D<Integer> costs,
			TreeSet<Integer> usedNodes) {
		int max = Integer.MIN_VALUE;
		int row = 0;
		int column = 0;
		for (Integer vert : usedNodes) {
			for (int j = 0; j < costs.getColumns(); j++) {
				Integer temp = costs.get(vert, j);
				if (temp != null && temp > max) {
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
