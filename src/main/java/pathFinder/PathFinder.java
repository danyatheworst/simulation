package pathFinder;


import entities.Entity;
import worldMap.Cell;

import java.util.HashSet;
import java.util.List;

public interface PathFinder {
    <T> List<Cell> getPath(Cell startCell, Class<T> target, HashSet<Class<? extends Entity>> obstacles);
}
