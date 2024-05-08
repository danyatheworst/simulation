package entities.inanimate;

import entities.Food;
import worldMap.Cell;
import worldMap.WorldMap;

public class Grass extends Food {
    public Grass(WorldMap worldMap, Cell cell, int amount) {
        super(worldMap, cell, amount);
    }
}
