package actions;

import entities.inanimate.Tree;
import worldMap.Cell;
import worldMap.WorldMap;

public class TreeSpawnAction extends SpawnAction<Tree> {

    public TreeSpawnAction(WorldMap worldMap, int percentageToSpawn) {
        super(worldMap, percentageToSpawn);
    }

    @Override
    protected Tree spawnEntity(WorldMap worldMap, Cell cell) {
        return new Tree(cell);
    }
}
