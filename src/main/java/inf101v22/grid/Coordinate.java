package inf101v22.grid;

import java.util.Objects;

public class Coordinate {
    // TODO: create this class
    public final int row; 
    public final int col; 
    public Coordinate(int row, int col) {
        this.row = row; 
        this.col = col; 
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Coordinate)) {
            return false;
        }
        Coordinate coordinate = (Coordinate) o;
        return row == coordinate.row && col == coordinate.col;
    }

    @Override
    public int hashCode () {
        return Objects.hash(col, row);
    }

    @Override
    public String toString() {
        return "{" +
            " row='" + row + "'" +
            ", col='" + col + "'" +
            " }";
    }


}
