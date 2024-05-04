package entities;

import worldMap.Cell;

abstract public class Entity {
    public Cell cell;

    public Entity(Cell cell) {
        this.cell = cell;
    }
}
