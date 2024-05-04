package worldMap;

import java.util.Objects;
import java.util.Random;

public class Cell {
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Cell getRandomCell(int x, int y) {
        Random random = new Random();
        return new Cell(random.nextInt(y), random.nextInt(x));
    }

    public boolean isShiftValid(CellShift cellShift, int width, int height) {
        final int x = this.x + cellShift.x;
        final int y = this.y + cellShift.y;
        final boolean isXValid = x >= 0 && x < height;
        final boolean isYValid = y >= 0 && y < width;

        return isXValid && isYValid;
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
