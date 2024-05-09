package worldMap;

import entities.Entity;
import entities.creatures.Creature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WorldMap {
    public final int width;
    public final int height;
    private final HashMap<Cell, Entity> entities;

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities =  new HashMap<>();
    }

    public Entity getEntityAt(Cell cell) {
        return this.entities.get(cell);
    }

    public void addEntity(Cell cell, Entity entity) {
        entity.cell = cell;
        this.entities.put(cell, entity);
    }

    public void moveEntity(Cell from, Cell to) {
        Entity entity = getEntityAt(from);
        this.addEntity(to, entity);
        this.removeEntity(from);
    }
    public void removeEntity(Cell cell) {
        this.entities.remove(cell);
    }

    public boolean isCellEmpty(Cell cell) {
        return !this.entities.containsKey(cell);
    }
    public boolean isCellValid(Cell cell) {
        int x = cell.x;
        int y = cell.y;
        boolean isXValid = x >= 0 && x < this.height;
        boolean isYValid = y >= 0 && y < this.width;

        return isXValid && isYValid;
    }

    public int getNumberOfCells() {
        return this.width * this.height;
    }


    public <T> List<T> getEntitiesOfType(Class<T> type) {
        return this.entities
                .values()
                .stream()
                .filter(type::isInstance)
                .map(entity -> (T) entity) // Приводим значение к типу T
                .collect(Collectors.toList());
    }
}
