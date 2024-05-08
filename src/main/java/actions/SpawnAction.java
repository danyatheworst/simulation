package actions;

import entities.Entity;
import worldMap.Cell;
import worldMap.WorldMap;

public abstract class SpawnAction <T extends Entity> extends Action {
    protected final int percentageToSpawn;
    
    protected SpawnAction(WorldMap worldMap, int percentageToSpawn) {
        super(worldMap);
        this.percentageToSpawn = percentageToSpawn;
    }

    protected Cell getRandomEmptyCell() {
        while (true) {
            Cell randomCell = Cell.getRandomCell(this.worldMap.width, this.worldMap.height);
            if (this.worldMap.isCellEmpty(randomCell)) {
                return randomCell;
            }
        }
    }

    @Override
    public void perform() {
        final int numberOfEntitiesToSpawn = (int)
                (((double) this.percentageToSpawn / 100.0) * this.worldMap.getNumberOfCells());
        int currentNumberOfEntites = 0;
        while (currentNumberOfEntites < numberOfEntitiesToSpawn) {
            Cell emptyCell = getRandomEmptyCell();
            Entity newEntity = spawnEntity(worldMap, emptyCell);
            this.worldMap.addEntity(emptyCell, newEntity);
            currentNumberOfEntites += 1;
        }
    }

    protected abstract T spawnEntity(WorldMap worldMap, Cell cell);
}
