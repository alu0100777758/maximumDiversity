package es.ull.etsii.daa.maxmean.tests;

import static org.junit.Assert.*;

import java.util.TreeSet;

import org.junit.Test;

import es.ull.etsii.daa.maxmean.ArrayList2D;
import es.ull.etsii.daa.maxmean.GRASPMM;
import es.ull.etsii.daa.maxmean.Graph;
import es.ull.etsii.daa.maxmean.GraphLoader;
import es.ull.etsii.daa.maxmean.GreedyMM_1;
import es.ull.etsii.daa.maxmean.GreedyMM_Inverse;
import es.ull.etsii.daa.maxmean.LocalSearch;
import es.ull.etsii.daa.maxmean.Multiarranque;

public class Tests {

	@Test
	public void ArrayListTest() {
		ArrayList2D<Integer> array = new ArrayList2D<Integer>(10,10);
		assertEquals("Crea el arraylist correctamente", array.get(0,0),null);
		array.set(9, 9, 10);
		assertEquals("inserta correctamente", array.get(9,9).intValue() ,10);
		array.addEmptyRow();
		assertEquals("agrega filas", null, array.get(10,9));
		assertEquals("agrega filas", 10, array.get(9,9).intValue());
		array.addEmptyColumn();
		assertEquals("agrega columnas", null, array.get(10,10));
		assertEquals("agrega columnas",10 ,array.get(9,9).intValue());
		assertEquals("tiene las dimensiones adecuadas",121 ,  array.size());

		
	}
	@Test
	public void GraphTest() {
		Graph<String> grafo = new Graph<String>();
		grafo.setMatrix(10);
		grafo.addNode("pepe");
		grafo.addNode("juan");
		assertEquals("permite insertar nodos","pepe" ,  grafo.getNode(10));
		grafo.setNode(2, "paco");
		assertEquals("permite modificar nodos","paco" ,  grafo.getNode(2));
		grafo.addVertice(10, 0, 1);
		grafo.addVertice(-10, 1, 0);
		assertEquals("permite insertar vertice", 10 , grafo.getDistanceBetween(0, 1));
		assertEquals("permite insertar vertice", -10 , grafo.getDistanceBetween(1, 0));
		assertEquals("Carga las dimensiones",144 ,  grafo.getDistances().size());
		Graph<String> grafo2 = grafo.clone();
		grafo2.setNode(0, "juanluis");
		grafo.setNode(0, "luisJuan");
		assertEquals("Permite ser clonado","juanluis" ,  grafo2.getNode(0));
		assertEquals("Permite ser clonado","luisJuan" ,  grafo.getNode(0));
	}
	@Test
	public void GraphLoaderTest() {
		Graph<String> grafo = new Graph<String>();
		GraphLoader.load("input/max-mean-div-10.txt", grafo);
		assertEquals("Carga las dimensiones",100 ,  grafo.getDistances().size());
		for(int i = 0; i <10; i++)
			assertEquals("Carga los nodos",""+i,  grafo.getNode(i));
		assertEquals("Carga los vertices",2,  grafo.getDistanceBetween(0, 1));
		assertEquals("es simetrico",2,  grafo.getDistanceBetween(1, 0));
	}
	@Test
	public void GreedyMM_1Test() {
		Graph<String> grafo = new Graph<String>();
		GraphLoader.load("input/max-mean-div-10.txt", grafo);
		System.out.println(grafo);
		new GreedyMM_1().calc(grafo);
	}
	@Test
	public void GreedyMM_1_2Test() {
		Graph<String> grafo = new Graph<String>();
		GraphLoader.load("input/max-mean-div-15.txt", grafo);
		System.out.println(grafo);
		new GreedyMM_1().calc(grafo);
	}
	@Test
	public void GreedyMM_1_3Test() {
		Graph<String> grafo = new Graph<String>();
		GraphLoader.load("input/max-mean-div-20.txt", grafo);
		System.out.println(grafo);
		new GreedyMM_1().calc(grafo);
	}
	@Test
	public void GreedyMM_InverseTest() {
		Graph<String> grafo = new Graph<String>();
		GraphLoader.load("input/max-mean-div-10.txt", grafo);
		System.out.println(grafo);
		new GreedyMM_Inverse().calc(grafo);
	}
	@Test
	public void GreedyMM_Inverse_2Test() {
		Graph<String> grafo = new Graph<String>();
		GraphLoader.load("input/max-mean-div-15.txt", grafo);
		System.out.println(grafo);
		new GreedyMM_Inverse().calc(grafo);
	}
	@Test
	public void GreedyMM_Inverse_3Test() {
		Graph<String> grafo = new Graph<String>();
		GraphLoader.load("input/max-mean-div-20.txt", grafo);
		System.out.println(grafo);
		new GreedyMM_Inverse().calc(grafo);
	}
	@Test
	public void GRASPMMTest() {
		Graph<String> grafo = new Graph<String>();
		GraphLoader.load("input/max-mean-div-10.txt", grafo);
		System.out.println(grafo);
		new GRASPMM().calc(grafo);
	}
	@Test
	public void LocalSeatchTest() {
		Graph<String> grafo = new Graph<String>();
		GraphLoader.load("input/max-mean-div-10.txt", grafo);
		System.out.println(grafo);
		TreeSet<Integer> dummy = new TreeSet<Integer>();
		dummy.add(1);
		dummy.add(4);
		dummy.add(2);
		new LocalSearch().improveSolution(dummy, grafo);
	}
	@Test
	public void MultiArranqueTest() {
		Graph<String> grafo = new Graph<String>();
		GraphLoader.load("input/max-mean-div-10.txt", grafo);
		System.out.println(grafo);
		new Multiarranque().calc(grafo);
	}

}
