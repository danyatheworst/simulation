package actions;


import entities.Range;
import entities.creatures.Herbivore;
import worldMap.Cell;
import worldMap.WorldMap;

public class HerbivoreSpawnAction extends SpawnAction<Herbivore> {

    public HerbivoreSpawnAction(WorldMap worldMap, int percentageToSpawn) {
        super(worldMap, percentageToSpawn);
    }

    @Override
    protected Herbivore spawnEntity(Cell cell) {
        return new Herbivore(cell, new Range(35, 51), 3);
    }
}
