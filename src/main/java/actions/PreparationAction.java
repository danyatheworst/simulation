package actions;

import entities.Food;

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
                .forEach(f -> f.isConsumed = false);
        this.worldMap
                .getEntitiesOfType(Herbivore.class)
                .forEach(h -> h.isUnderAttack = false);
    }
}
