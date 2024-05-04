package actions;

import entities.creatures.Creature;
import worldMap.WorldMap;

public class MoveCreatureAction extends Action {
    public MoveCreatureAction(WorldMap worldMap) {
        super(worldMap);
    }

    @Override
    public void perform() {
        this.worldMap
                .getEntitiesOfType(Creature.class)
                .values().stream().filter(Creature::isAlive)
                .forEach((Creature creature) -> creature.makeMove(worldMap));
    }
}
