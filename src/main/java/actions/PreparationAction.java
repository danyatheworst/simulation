package actions;

import entities.Food;
import entities.creatures.Creature;
import entities.creatures.Herbivore;
import worldMap.WorldMap;

public class PreparationAction extends Action {
    public PreparationAction(WorldMap worldMap) {
        super(worldMap);
    }

    @Override
    public void perform() {
        this.worldMap
                .getEntitiesOfType(Food.class)
                .forEach((key, value) -> value.isConsumed = false);
        this.worldMap
                .getEntitiesOfType(Herbivore.class)
                .forEach((key, value) -> value.isUnderAttack = false);
        this.worldMap
                .getEntitiesOfType(Creature.class)
                .forEach((key, value) -> value.currentTarget = null);
    }
}
