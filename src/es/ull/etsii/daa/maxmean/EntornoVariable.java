package es.ull.etsii.daa.maxmean;

/**
 * @author Javier martín Hernandez
 *
 */
public class EntornoVariable extends Multiarranque {
	public EntornoVariable(){
	super.localSearch = new LocalSearch();
	}
}
