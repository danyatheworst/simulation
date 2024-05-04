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
    protected Predator spawnEntity(Cell cell) {

        return new Predator(cell, new Range(30, 36), new Range(8, 17), 4);
    }
}
