package worldMap;

import entities.Entity;
import entities.creatures.Creature;

import java.util.HashMap;
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

    public int getNumberOfCells() {
        return this.width * this.height;
    }

    public <T> HashMap<Cell, T> getEntitiesOfType(Class<T> type) {
        return this.entities
                .entrySet()
                .stream()
                .filter(e -> type.isInstance(e.getValue()))
                .map(e -> (Entry<Cell, T>) e)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, HashMap::new));
    };

    public boolean doCreaturesExist() {
        int numberOfLivingCreatures = 0;
        HashMap<Cell, Creature> creatures = this.getEntitiesOfType(Creature.class);
        for (Creature creature : creatures.values()) {
            if (creature.isAlive()) {
                numberOfLivingCreatures += 1;
            }
        }

        return numberOfLivingCreatures > 0;
    }
}
