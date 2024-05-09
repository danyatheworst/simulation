package worldMap;

import java.util.Objects;
import java.util.Random;

public class Cell {
    public final int x;
    public final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell shift(CellShift cellShift) {
        return new Cell(this.x + cellShift.x, this.y + cellShift.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell that = (Cell) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
