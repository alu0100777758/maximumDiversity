package es.ull.etsii.daa.maxmean;

import java.util.ArrayList;


/**
 * @author Javier Martín Hernández
 *
 * @param <T> tipo de los nodos
 * 
 * Clase implementa un array bidimensional dinámico.
 */
public class ArrayList2D<T> extends ArrayList<T> {
	private static final long serialVersionUID = 994411887703887055L;
	private int rows;		//	cantidad de filas
	private int columns;	//	cantidad de columnas
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		//if new row is smaller than row -> cut
		this.rows = rows;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	private int getByDimension(int row, int column){
		return row*getColumns() + column;
	}

	public ArrayList2D(int rows, int columns) {
		this(rows,columns, null);
	}
	public ArrayList2D(int rows, int columns, T val) {
		super();
		setRows(rows);
		setColumns(columns);
		for(int i = 0; i < rows * columns;i++)
			add(val);
	}
	@SuppressWarnings("unchecked")
	public ArrayList2D(ArrayList2D<Integer> distanceMatrix) {
		super((ArrayList<T>)distanceMatrix);
		setRows(distanceMatrix.getRows());
		setColumns(distanceMatrix.getColumns());
	}
	public T get(int rows, int columns){
		if(rows >= getRows() || rows < 0 || columns >= getColumns() || columns < 0)
			throw new IllegalArgumentException();
		return super.get(getByDimension(rows, columns));
	}
	public T set(int row, int column, T element){
		if(row >= getRows() || row < 0 || column >= getColumns() || column < 0)
			throw new IllegalArgumentException("input: (" + row + "," +  column + ") -> max: (" + getRows() + "," +  getColumns() + ")");
		return set(getByDimension(row, column), element);
	}
	public void addEmptyColumn(){
		for(int i = 1 ; i <= getRows(); i++)
			add((getColumns())*i+(i-1),null);
		setColumns(getColumns() + 1);	
	}
	public void addEmptyRow() {
		for(int i = 0; i < getColumns(); i++)
			add(null);
		setRows((getRows() + 1));
	}
	public String toString(){
		String res = new String();
		for(int i = 0 ; i < getRows(); i++){
			res += "{";
			for(int j = 0; j < getColumns(); j++){
				res += "[" + get(i, j) + "]";
			}
			res += "}" +"\n";
		}
		return res;
	}
}
