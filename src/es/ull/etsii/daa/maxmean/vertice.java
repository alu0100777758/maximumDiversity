package es.ull.etsii.daa.maxmean;

public class vertice {
	private int from;
	private int to;
	private int cost;
	public vertice(int from, int to, int cost) {
		super();
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
