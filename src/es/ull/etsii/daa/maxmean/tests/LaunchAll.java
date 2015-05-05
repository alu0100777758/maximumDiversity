package es.ull.etsii.daa.maxmean.tests;

import es.ull.etsii.daa.maxmean.EntornoVariable;
import es.ull.etsii.daa.maxmean.GRASPMM;
import es.ull.etsii.daa.maxmean.Graph;
import es.ull.etsii.daa.maxmean.GraphLoader;
import es.ull.etsii.daa.maxmean.GreedyMM_1;
import es.ull.etsii.daa.maxmean.GreedyMM_Inverse;
import es.ull.etsii.daa.maxmean.Multiarranque;

public class LaunchAll {
	public static final String PATH = "input/max-mean-div-20.txt";

	public static void main(String[] args) {

		Graph<String> grafo = new Graph<String>();
		GraphLoader.load(PATH, grafo);

		GreedyMM_1 greedy = new GreedyMM_1();
		System.out.println("Greedy1:\n"+greedy.calc(grafo) + "\nValor: " + (greedy.getValue()));
		GreedyMM_Inverse greedyInverse = new GreedyMM_Inverse();
		System.out.println("Greedy inverso:\n"+greedyInverse.calc(grafo) + "\nValor: " + (greedyInverse.getValue()));
		GRASPMM grasp = new GRASPMM();
		System.out.println("Grasp:\n"+grasp.calc(grafo) + "\nValor: " + (grasp.getValue()));
		Multiarranque multiarranque = new Multiarranque();
		System.out.println("\nmultiarranque:\n"+multiarranque.calc(grafo) + "\nValor: " + (multiarranque.getValue()));
		EntornoVariable entornoVariable = new EntornoVariable();
		System.out.println("entorno variable:\n"+entornoVariable.calc(grafo) + "\nValor: " + (entornoVariable.getValue()));
		
//		for (int i = 0; i < 5; i++) {
//			long startTime = System.currentTimeMillis();
//			entornoVariable.calc(grafo);
//			long stopTime = System.currentTimeMillis();
//			long elapsedTime = stopTime - startTime;
//			System.out.println("ID3,20,20,"+i+",100000,"+(entornoVariable.getValue())+","+ (double)elapsedTime/1000 );
//			
//		}

	}

}
