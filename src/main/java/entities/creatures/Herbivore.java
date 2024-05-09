package entities.creatures;

import entities.Entity;
import entities.Range;
import entities.inanimate.Grass;
import worldMap.Cell;
import worldMap.WorldMap;

import java.util.HashSet;

public class Herbivore extends Creature {
    public Herbivore(WorldMap worldMap, Cell cell, Range hitPointsRange, int amountOfFoodAfterDeath) {
        super(worldMap, cell, hitPointsRange, amountOfFoodAfterDeath);
    }

    protected void cameUnderAttack(int damage) {
        this.interactionCallback.onInteract(this);
        setCurrentHitPoints(this.getCurrentHitPoints() - damage);
    }

    @Override
    protected HashSet<Class<? extends Entity>> getObstacles() {
        HashSet<Class<? extends Entity>> obstacles = super.getObstacles();
        obstacles.add(Predator.class);
        return obstacles;
    }

    @Override
    protected Class<? extends Entity> getTarget() {
        return Grass.class;
    }
}
