package es.ull.etsii.daa.maxmean;

import java.util.ArrayList;

/**
 * @author Javier Martín Hernández
 *
 * @param <type> tipo de nodos que albergará.
 * 
 * Clase grafo implementada mediante lista de nodos y matriz de distancias.
 * Ofrece las operaciones básicas de inserción de nodos y aristas.
 */
public class Graph<type> {
	private ArrayList2D<Integer> distanceMatrix;	// matriz de distancias
	private ArrayList<type> nodes = new ArrayList<type>();	//	lista de nodos

	private ArrayList2D<Integer> getDistanceMatrix() {
		return distanceMatrix;
	}

	protected void setDistanceMatrix(ArrayList2D<Integer> distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
	}

	/**
	 * @param nodeNumber entero que representa la cantidad de nodos.
	 * por defecto se inicializan las distancias a null.
	 */
	public void setMatrix(int nodeNumber) {
		setMatrix(new ArrayList2D<Integer>(nodeNumber, nodeNumber));
		for (int i = nodes.size(); i < nodeNumber; i++)
			nodes.add(null);
	}

	private void setMatrix(ArrayList2D<Integer> matrix) {
		distanceMatrix = matrix;
	}

	/**
	 * @param node1
	 * @param node2
	 * @return distancia entre node1 y node2
	 */
	public int getDistanceBetween(int node1, int node2) {
		return getDistanceMatrix().get(node1, node2);
	}

	public ArrayList2D<Integer> getDistances() {
		return new ArrayList2D<Integer>(getDistanceMatrix());
	}

	public Graph<type> clone() {
		Graph<type> newgraph = new Graph<type>();
		newgraph.setMatrix(getDistances());
		newgraph.setNodes(new ArrayList<type>(getNodes_p()));
		return newgraph;
	}

	public void addNode(type node) {
		nodes.add(node);
		if (nodes.size() > getDistanceMatrix().getColumns()) {
			getDistanceMatrix().addEmptyColumn();
			getDistanceMatrix().addEmptyRow();
		}
	}

	@SuppressWarnings("unchecked")
	public type[] getNodes() {
		return (type[]) nodes.toArray();
	}

	public void addVertice(int distance, int from, int to) {
		getDistanceMatrix().set(from, to, distance);
	}

	public type getNode(int index) {
		return nodes.get(index);
	}

	public String toString() {
		return getDistanceMatrix().toString();
	}

	public void setNodes(ArrayList<type> nodes) {
		this.nodes = nodes;
	}

	public void setNode(int index, type val) {
		nodes.set(index, val);
	}

	private ArrayList<type> getNodes_p() {
		return this.nodes;
	}
	public int getSizeN(){
		return nodes.size();
	}

}
