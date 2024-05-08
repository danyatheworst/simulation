package entities.creatures;

import entities.Entity;
import entities.Range;
import entities.inanimate.Grass;
import worldMap.Cell;
import worldMap.WorldMap;

import java.util.HashSet;

public class Herbivore extends Creature {
    public boolean isUnderAttack;

    public Herbivore(WorldMap worldMap, Cell cell, Range hitPointsRange, int amountOfFoodAfterDeath) {
        super(worldMap, cell, hitPointsRange, amountOfFoodAfterDeath);
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        super.makeMove(worldMap);

        if (this.currentTarget == null) {
            return;
        }

        this.eat();
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
