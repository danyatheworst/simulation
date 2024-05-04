package actions;

import entities.inanimate.Rock;
import worldMap.Cell;
import worldMap.WorldMap;

public class RockSpawnAction extends SpawnAction<Rock> {
    public RockSpawnAction(WorldMap worldMap, int percentageToSpawn) {
        super(worldMap, percentageToSpawn);
    }

    @Override
    protected Rock spawnEntity(Cell cell) {
        return new Rock(cell);
    }
}
