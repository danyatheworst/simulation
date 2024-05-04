package entities.creatures;

import entities.Entity;
import entities.Range;
import entities.inanimate.Grass;
import worldMap.Cell;
import worldMap.WorldMap;

import java.util.HashSet;

public class Predator extends Creature {
    private final Range damageRange;
    public Predator(Cell cell, Range hitPointsRange, Range damageRange, int amountOfFoodAfterDeath) {
        super(cell, hitPointsRange, amountOfFoodAfterDeath);
        this.damageRange = damageRange;
    }

    @Override
    protected HashSet<Class<? extends Entity>> getObstacles() {
        HashSet<Class<? extends Entity>> obstacles = super.getObstacles();
        obstacles.add(Grass.class);
        return obstacles;
    }

    @Override
    protected Class<? extends Entity> getTarget() {
        return Herbivore.class;
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        super.makeMove(worldMap);
        if (this.currentTarget == null) {
            return;
        }
        Herbivore herbivore = (Herbivore) this.currentTarget;
        if (herbivore.isAlive()) {
            this.attack(herbivore);
        } else {
            this.eat(worldMap);
        }
    }

    private void attack(Herbivore target) {
        target.isUnderAttack = true;
        int currentTargetHitPoints = target.getCurrentHitPoints();
        final int damage = this.damageRange.getRandomNumber();
        currentTargetHitPoints -= damage;
        target.setCurrentHitPoints(currentTargetHitPoints);
    }
}
