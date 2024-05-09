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
    private final WorldMap worldMap;
    public Creature(WorldMap worldMap, Cell cell, Range hpRange, int amountOfFoodAfterFood) {
        super(worldMap, cell, amountOfFoodAfterFood);
        this.maxHitPoints = hpRange.getRandomNumber();
        this.currentHitPoints = this.maxHitPoints;
        this.worldMap = worldMap;
    }

    abstract protected Class<? extends Entity> getTarget();

    public void makeMove() {
        this.getDamagedByStarving();
        if (!this.isAlive()) {
            return;
        }

        if (this.currentHitPoints > this.maxHitPoints * 0.65) {
            this.makeRandomStep();
            return;
        }

        Cell currentCell = this.cell;
        Cell targetCell = this.getTargetNear();
        boolean isTargetNear = !targetCell.equals(currentCell);
        if (isTargetNear) {
            Food target = (Food) this.worldMap.getEntityAt(targetCell);
            this.interactWith(target);
        } else {
            PathFinder pathFinder = new BFSPathFinder(this.worldMap);
            List<Cell> path = pathFinder.getPath(currentCell, this.getTarget(), this.getObstacles());
            //an empty path means that target is not found
            if (path.isEmpty()) {
                this.makeRandomStep();
            } else {
                Cell nextCell = path.get(this.SPEED);
                this.cell = nextCell;
                this.worldMap.moveEntity(currentCell, nextCell);
            }
        }
    }

    private void makeRandomStep() {
        CellShift[] shifts = CellShift.oneStepShiftsToFourDirections;
        Utils.shuffleArray(shifts);
        Cell currentCell = this.cell;

        for (CellShift shift : shifts) {
            Cell shiftedCell = cell.shift(shift);
            if (this.worldMap.isCellValid(shiftedCell) && this.worldMap.isCellEmpty(shiftedCell)) {
                this.worldMap.moveEntity(currentCell, shiftedCell);
                break;
            }
        }
    }

    protected void interactWith(Food target) {
        target.decreaseAmount();
        int restoreHitPoints = (int) (this.maxHitPoints * 0.35);
        this.setCurrentHitPoints(this.currentHitPoints + restoreHitPoints);
    }

    protected HashSet<Class<? extends Entity>> getObstacles() {
        HashSet<Class<? extends Entity>> obstacles = new HashSet<>();
        obstacles.add(Tree.class);
        obstacles.add(Rock.class);
        obstacles.add(this.getClass());
        return obstacles;
    }

    private Cell getTargetNear() {
        CellShift[] shifts = CellShift.oneStepShiftToEightDirections;
        Cell cell = this.cell;

//        for (CellShift shift : shifts) {
//            if (cell.isShiftValid(shift, this.worldMap.width, this.worldMap.height)) {
//                Cell validCell = cell.shift(shift);
//                if (!this.worldMap.isCellEmpty(validCell)) {
//                    Entity entity = this.worldMap.getEntityAt(validCell);
//                    boolean isTarget = entity.getClass() == this.getTarget();
//                    if (isTarget) {
//                        return validCell;
//                    }
//                }
//            }
//        }

        for (CellShift shift : shifts) {
            Cell shiftedCell = this.cell.shift(shift);
            if (this.worldMap.isCellValid(shiftedCell) && !this.worldMap.isCellEmpty(shiftedCell)) {
                Entity entity = this.worldMap.getEntityAt(shiftedCell);
                boolean isTarget = entity.getClass() == this.getTarget();
                if (isTarget) {
                    return shiftedCell;
                }
            }
        }

        return cell;
    }

    private void getDamagedByStarving() {
        int damageFromStarving = (int) (this.maxHitPoints * 0.05);
        this.setCurrentHitPoints(this.currentHitPoints - damageFromStarving);
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