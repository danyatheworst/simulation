package actions;

import entities.creatures.Predator;
import worldMap.Cell;
import worldMap.WorldMap;
import entities.Range;


public class PredatorSpawnAction extends SpawnAction<Predator> {
    public PredatorSpawnAction(WorldMap worldMap, int percentageToSpawn) {
        super(worldMap, percentageToSpawn);
    }

    @Override
    protected Predator spawnEntity(WorldMap worldMap, Cell cell) {

        return new Predator(worldMap, cell, new Range(30, 36), new Range(8, 17), 4);
    }
}
