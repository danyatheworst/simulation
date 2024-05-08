package entities.creatures;

import entities.Entity;
import entities.Food;
import entities.Range;
import entities.inanimate.Grass;
import worldMap.Cell;
import worldMap.WorldMap;

import java.util.HashSet;

public class Predator extends Creature {
    private final Range damageRange;
    public Predator(WorldMap worldMap, Cell cell, Range hitPointsRange, Range damageRange, int amountOfFoodAfterDeath) {
        super(worldMap, cell, hitPointsRange, amountOfFoodAfterDeath);
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
    protected void interactWith(Food target) {
        Herbivore herbivore = (Herbivore) target;
        if (herbivore.isAlive()) {
            this.attack(herbivore);
        } else {
            super.interactWith(target);
        }
    }

    private void attack(Herbivore target) {
        int damage = this.damageRange.getRandomNumber();
        target.cameUnderAttack(damage);
    }
}
