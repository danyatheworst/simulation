package pathFinder;

import entities.Entity;
import worldMap.Cell;
import worldMap.CellShift;
import worldMap.WorldMap;

import java.util.*;

public class BFSPathFinder implements PathFinder {

    private final WorldMap worldMap;
    private final CellShift[] cellShifts;

    public BFSPathFinder(WorldMap worldMap) {
        this.worldMap = worldMap;
        this.cellShifts = CellShift.shifts_4;
    }

    public  <T> List<Cell> getPath(Cell startCell, Class<T> target, HashSet<Class<? extends Entity>> obstacles) {
        List<Cell> startPath = new ArrayList<>();
        startPath.add(startCell);
        Queue<List<Cell>> paths = new LinkedList<>();
        paths.add(startPath);
        HashSet<Cell> searched = new HashSet<>();

        while (!paths.isEmpty()) {
            if (worldMap.getEntitiesOfType(target).isEmpty()) {
                return new ArrayList<>();
            }

            List<Cell> currentPath = paths.poll();
            Cell lastCellInPath = currentPath.get(currentPath.size() - 1);
            if (target.isInstance(worldMap.getEntityAt(lastCellInPath))) {
                return currentPath;
            }

            for (CellShift shift : cellShifts) {
                final boolean isValid = lastCellInPath.isShiftValid(shift, worldMap.width, worldMap.height);
                Cell newCell = lastCellInPath.shift(shift);
                Entity entity = worldMap.getEntityAt(newCell);
                final boolean isEmpty = worldMap.isCellEmpty(newCell);
                final boolean isObstacle = !isEmpty && obstacles.contains(entity.getClass());
                final boolean isSearched = searched.contains(newCell);

                if (isValid && !isObstacle && !isSearched) {
                    searched.add(newCell);
                    List<Cell> newPath = new ArrayList<>(currentPath);
                    newPath.add(newCell);
                    paths.add(newPath);
                }
            }
        }
        return new ArrayList<>();
    }
}
