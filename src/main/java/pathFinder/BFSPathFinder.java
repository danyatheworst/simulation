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
        this.cellShifts = CellShift.oneStepShiftsToFourDirections;
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
                Cell shiftedCell = lastCellInPath.shift(shift);
                boolean isValid = worldMap.isCellValid(shiftedCell);
                Entity entity = worldMap.getEntityAt(shiftedCell);
                boolean isEmpty = worldMap.isCellEmpty(shiftedCell);
                boolean isObstacle = !isEmpty && obstacles.contains(entity.getClass());
                boolean isSearched = searched.contains(shiftedCell);

                if (isValid && !isObstacle && !isSearched) {
                    searched.add(shiftedCell);
                    List<Cell> newPath = new ArrayList<>(currentPath);
                    newPath.add(shiftedCell);
                    paths.add(newPath);
                }
            }
        }
        return new ArrayList<>();
    }
}
