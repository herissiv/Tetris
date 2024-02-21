package inf101v22.grid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid<E> {

    public final List<List<E>> cells;
	private final int columns;
	private final int rows;

    public Grid(int rows, int columns, E initElement) {
		if (rows <= 0 || columns <= 0) {
			throw new IllegalArgumentException();
		}
        
		this.columns = columns;
		this.rows = rows;
        
		this.cells = new ArrayList<>(rows);
		for (int i = 0; i < rows; ++i) {
            List<E> rader = new ArrayList<>(columns);
            for (int j = 0; j < columns; ++j){
                if (initElement==null) {
                    rader.add(null);
                }
                else {
                    rader.add(initElement);
                } 
            }
            cells.add(rader);
		}
	}

    public Grid(int rows, int columns) {
        this(rows,columns,null);
    }

    @Override
    public void set(Coordinate coordinate, E item) {
        /*
        Putter et element E på gitte koordinater (senere brukes denne 
        til å legge inn en Tile på tetrisbrettet)
        */
        cells.get(coordinate.row).set(coordinate.col, item);
    }

    public int getRows() { 
        return this.rows;
    }

    @Override
    public E get(Coordinate coordinate) {
        if (coordinate.row >= this.rows || coordinate.col >= this.columns || coordinate.row < 0 || coordinate.col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return cells.get(coordinate.row).get(coordinate.col);
    }

    public int getCols() {
        return this.columns;
    }

    public boolean coordinateIsOnGrid(Coordinate coordinate) { 
        /*
        Forteller om et gitt koordinat ligger på brettet 
        */ 
        
        if (coordinate.row >= this.rows || coordinate.col >= this.columns || coordinate.row < 0 || coordinate.col < 0) {
            return false;
        }
        else {
            return true;
        }
    }
    public Iterator<CoordinateItem<E>> iterator() {
        ArrayList<CoordinateItem<E>> iterator = new ArrayList<>();
        for (int i = 0; i< this.rows; ++i) { 
            for (int j = 0; j<this.columns; ++j) {
                Coordinate cor = new Coordinate(i, j);
                CoordinateItem<E> corI = new CoordinateItem<>(cor,get(cor));
                iterator.add(corI);
            }
        }
        return iterator.iterator();
    }
}
