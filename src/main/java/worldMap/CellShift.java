package worldMap;

public class CellShift {
    public final int x;
    public final int y;

    public CellShift(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static CellShift[] oneStepShiftsToFourDirections = new CellShift[]{
            new CellShift(0, -1), new CellShift(1, 0),
            new CellShift(0, 1), new CellShift(-1, 0),
    };

    public static CellShift[] oneStepShiftToEightDirections = new CellShift[]{
            new CellShift(0, -1), new CellShift(1, 0),
            new CellShift(0, 1), new CellShift(-1, 0),
            new CellShift(-1, -1), new CellShift(-1, 1),
            new CellShift(1, -1), new CellShift(1, 1),
    };
}
