package es.ull.etsii.daa.maxmean;

import java.util.TreeSet;

/**
 * @author Javier Martín Hernández
 * algoritmo basado en GreeedyMM_1, con un enfoque opuesto. Parte de el conjunto de todos los nodos y va eliminando
 * los que produzcan la pérdida de valor.
 *
 */
public class GreedyMM_Inverse extends GreedyMM_1 {
	/* (non-Javadoc)
	 * @see es.ull.etsii.daa.maxmean.GreedyMM_1#initialize(java.util.TreeSet, es.ull.etsii.daa.maxmean.ArrayList2D)
	 *	En este caso se incluyen todos los nodos en el conjunto inicial.
	 */
	public  int initialize(TreeSet<Integer> nodesRes, ArrayList2D<Integer> distances){
		int cost = 0;
		for (int i = 0; i < distances.getRows(); i++){
			for (int j = 0; j < i; j++) {
				cost += distances.get(i, j);
			}
			nodesRes.add(i);
		}
		return cost;
	}
	public  vertice getBestVertice(ArrayList2D<Integer> costs,
			TreeSet<Integer> usedNodes) {
		int max = Integer.MIN_VALUE;
		int node = -404;
		for (Integer vert : usedNodes) {
				Integer temp = getNewCosts(costs, usedNodes, vert);
				if (temp != null && temp > max) {
					max = temp;
					node = vert;
			}
		}
		return new vertice(0,node, 0);
	}
	public  boolean loopCondition(Graph<String> graph, TreeSet<Integer> nodesRes){
		return (nodesRes.size() > 1);
	}
	public  boolean simRes(TreeSet<Integer> nodesRes, vertice checking){
		return nodesRes.remove(Integer.valueOf(checking.getTo()));
	}
	public  void simRecover(TreeSet<Integer> nodesRes, vertice checking){
		nodesRes.add(checking.getTo());
	}
	public  int getNewCosts(ArrayList2D<Integer> distances,TreeSet<Integer> nodesRes, int nodetoadd){
		return -super.getNewCosts(distances, nodesRes, nodetoadd);
	}
	public  boolean improves(double old, int sum, vertice toCheck, TreeSet<Integer> nodesRes){
		double newer = mean(sum, nodesRes.size());
		return newer > old;
	}
	public int improvementAction(Integer sum, vertice toCheck, TreeSet<Integer> nodesRes){
		return sum;
	}
}
