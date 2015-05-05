package es.ull.etsii.daa.maxmean;

import java.util.Collection;
import java.util.TreeSet;

/**
 * @author Javier Martín Hernández
 * algoritmo de búsqueda local cuyo entorno se define como aquel que viene dado por eliminar , añadir o intercambiar un nodo.
 *
 */
public class LocalSearch {
private double value = Double.MIN_VALUE;
	
	public double getValue() {
		return value;
	}
	private void setValue(double value) {
		this.value = value;
	}
	/**
	 * @param solution
	 * @param graph
	 * @return solucion mejorada
	 * metodo encargado de mejorar las soluciones que se le pasan.
	 */
	public  Collection<Integer> improveSolution(Collection <Integer> solution, Graph<String> graph) {
		setValue( Double.MIN_VALUE);
		ArrayList2D<Integer> distances = graph.getDistances();
		TreeSet<Integer> nodesRes = new TreeSet<Integer>(solution);
		TreeSet<Integer> notUsed = getNotUsedFromGraph(graph,nodesRes);
		int sum = 0;
		double maximum = 0;
		int repetition = 0;
		while( repetition < 20 ){
			sum = getOverallSum(nodesRes, distances);
			maximum = (double)sum / nodesRes.size();
			TreeSet<Integer> lastres = nodesRes;
			nodesRes = checkNeighbors(nodesRes,notUsed, distances, sum);
			if(nodesRes.containsAll(lastres) && lastres.containsAll(nodesRes))
				repetition++;
			else
				repetition = 0;
		}
		setValue(maximum);
		return nodesRes;
	}
	private TreeSet<Integer> getNotUsedFromGraph(Graph<String> graph, TreeSet<Integer> inSolution) {
		TreeSet<Integer> notused = new TreeSet<Integer>();
		for(int i = 0 ; i < graph.getSizeN(); i++){
			if(!inSolution.contains(i))
				notused.add(i);
		}
		return notused;
	}
	protected TreeSet<Integer> checkNeighbors(TreeSet<Integer> nodesRes,
			TreeSet<Integer> notUsed, ArrayList2D<Integer> distances, int sum) {
		
		nodesRes = getbetter(bestEnviromentWithOneLessnode(nodesRes,distances, sum), nodesRes, distances);
		nodesRes = getbetter(bestEnviromentWithOneMorenode(nodesRes,notUsed,distances, sum), nodesRes, distances);
		nodesRes = getbetter(bestEnviromentWithOneLessnode(bestEnviromentWithOneMorenode(nodesRes,notUsed,distances, sum),distances, sum), nodesRes, distances);
		return nodesRes;
	}
	public  TreeSet<Integer> getbetter (TreeSet<Integer> newres,TreeSet<Integer> oldRes,ArrayList2D<Integer> distances){
		if(mean(getOverallSum(newres, distances), newres.size()) > mean(getOverallSum(oldRes, distances), oldRes.size()))
			return newres;
		else return oldRes;
	}
	
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
	protected TreeSet<Integer> bestEnviromentWithOneMorenode(
		TreeSet<Integer> nodesRes, TreeSet<Integer> notUsed, ArrayList2D<Integer> distances, int sum) {
		TreeSet<Integer> bestLocal = new TreeSet<Integer>(nodesRes);
		int max = Integer.MIN_VALUE;
		int node = -404;
		for (Integer vert : notUsed) {
				Integer temp = getNewCosts(distances, nodesRes, vert);
				if (temp != null && temp > max) {
					max = temp;
					node = vert;
			}
		}
		bestLocal.add(node);
	return bestLocal;
	}
	protected TreeSet<Integer> bestEnviromentWithOneLessnode(
			TreeSet<Integer> nodesRes, ArrayList2D<Integer> distances, int sum) {
		TreeSet<Integer> bestLocal = new TreeSet<Integer>(nodesRes);
		int max = Integer.MIN_VALUE;
		int node = -404;
		for (Integer vert : nodesRes) {
				Integer temp = -getNewCosts(distances, nodesRes, vert);
				if (temp != null && temp > max) {
					max = temp;
					node = vert;
			}
		}
		bestLocal.remove(node);
	return bestLocal;
	}
	public double mean(int costs, int nodes){
		return (double)costs/(double)nodes;
	}
	public int getOverallSum(TreeSet<Integer> nodesRes, ArrayList2D<Integer> distances){
		Integer [] nodes = new Integer[nodesRes.size()];
		nodes = nodesRes.toArray(nodes);
		int sum = 0;
		for (int i = 0; i < nodes.length; i++){
			for (int j = 0; j < i; j++) {
				sum += distances.get(nodes[i], nodes[j]);
			}
		}
		return sum;
	}
}
