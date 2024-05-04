package actions;

import entities.inanimate.Grass;
import worldMap.Cell;
import worldMap.WorldMap;

public class GrassSpawnAction extends SpawnAction<Grass> {
    public GrassSpawnAction(WorldMap worldMap, int percentageToSpawn) {
        super(worldMap, percentageToSpawn);
    }

    @Override
    protected Grass spawnEntity(Cell cell) {
        return new Grass(cell, 2);
    }

}
