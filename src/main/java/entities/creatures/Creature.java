package entities.creatures;

import entities.Entity;
import entities.Food;
import entities.Range;
import entities.inanimate.Rock;
import entities.inanimate.Tree;
import pathFinder.BFSPathFinder;
import pathFinder.PathFinder;
import utils.Utils;
import worldMap.Cell;
import worldMap.CellShift;
import worldMap.WorldMap;

import java.util.*;

abstract public class Creature extends Food {
    private final int SPEED = 1;
    private int currentHitPoints;
    private final int maxHitPoints;
    public Food currentTarget;
    public Creature(Cell cell, Range hpRange, int amountOfFoodAfterFood) {
        super(cell, amountOfFoodAfterFood);
        this.maxHitPoints = hpRange.getRandomNumber();
        this.currentHitPoints = this.maxHitPoints;
        this.currentTarget = null;
    }

    abstract protected Class<? extends Entity> getTarget();

    public void makeMove(WorldMap worldMap) {
        final int damageFromStarving = (int) (this.maxHitPoints * 0.05);
        this.setCurrentHitPoints(this.currentHitPoints - damageFromStarving);
        if (!this.isAlive()) {
            return;
        }

        if (this.currentHitPoints > this.maxHitPoints * 0.65) {
            this.makeRandomStep(worldMap);
            return;
        }

        Cell currentCell = this.cell;
        Cell targetCell = this.getTargetAround(worldMap);
        final boolean isTarget = !targetCell.equals(currentCell);
        if (isTarget) {
            this.currentTarget = (Food) worldMap.getEntityAt(targetCell);
        } else {
            PathFinder pathFinder = new BFSPathFinder(worldMap);
            List<Cell> path = pathFinder.getPath(currentCell, this.getTarget(), this.getObstacles());
            if (path.isEmpty()) {
                this.makeRandomStep(worldMap);
            } else {
                Cell nextCell = path.get(this.SPEED);
                this.cell = nextCell;
                worldMap.moveEntity(currentCell, nextCell);
            }
        }
    }

    private void makeRandomStep(WorldMap worldMap) {
        CellShift[] shifts = CellShift.shifts_4;
        Utils.shuffleArray(shifts);
        Cell currentCell = this.cell;

        for (CellShift shift : shifts) {
            if (currentCell.isShiftValid(shift, worldMap.width, worldMap.height)) {
                Cell newCell = currentCell.shift(shift);
                    if (worldMap.isCellEmpty(newCell)) {
                        worldMap.moveEntity(currentCell, newCell);
                        break;
                }
            }
        }
    }

    protected void eat(WorldMap worldMap) {
        int currentAmountOfFood = this.currentTarget.getAmount();
        currentAmountOfFood -= 1;

        if (currentAmountOfFood <= 0) {
            worldMap.removeEntity(this.currentTarget.cell);
            return;
        }
        this.currentTarget.isConsumed = true;
        this.currentTarget.setAmount(currentAmountOfFood);

        final int currentHitPoints = (int) (this.currentHitPoints + this.maxHitPoints * 0.35);
        this.setCurrentHitPoints(currentHitPoints);
    };

    protected HashSet<Class<? extends Entity>> getObstacles() {
        HashSet<Class<? extends Entity>> obstacles = new HashSet<>();
        obstacles.add(Tree.class);
        obstacles.add(Rock.class);
        obstacles.add(this.getClass());
        return obstacles;
    }

    private Cell getTargetAround(WorldMap worldMap) {
        CellShift[] shifts = CellShift.shifts_8;
        Cell cell = this.cell;

        for (CellShift shift : shifts) {
            if (cell.isShiftValid(shift, worldMap.width, worldMap.height)) {
                Cell validCell = cell.shift(shift);
                if (!worldMap.isCellEmpty(validCell)) {
                    Entity entity = worldMap.getEntityAt(validCell);
                    final boolean isTarget = entity.getClass() == this.getTarget();
                    if (isTarget) {
                        return validCell;
                    }
                }
            }
        }

        return cell;
    }

    public int getCurrentHitPoints() {
        return this.currentHitPoints;
    }

    public void setCurrentHitPoints(int currentHitPoints) {
        this.currentHitPoints = Math.min(currentHitPoints, this.maxHitPoints);
    }

    public boolean isAlive() {
        return this.currentHitPoints > 0;
    }
}