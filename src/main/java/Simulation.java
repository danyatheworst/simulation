import actions.Action;
import actions.MoveCreatureAction;
import actions.PreparationAction;
import entities.creatures.Creature;
import worldMap.Cell;
import worldMap.WorldMap;
import worldMap.WorldMapRenderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Simulation {
    private int stepsCounter = 0;
    public boolean isPaused = false;
    private boolean isFinished = false;
    private final WorldMap worldMap;
    private final WorldMapRenderer worldMapRenderer;
    private final List<Action> turnActions = new ArrayList<>();

    public Simulation(WorldMap worldMap, WorldMapRenderer worldMapRenderer) {
        this.worldMap = worldMap;
        this.worldMapRenderer = worldMapRenderer;
        this.setTurnActions();
    }

    public void start() {
        while (this.doCreaturesExist() && !isFinished) {
            this.nextTurn();
        }
    }

    public void pause() {
        this.isPaused = !this.isPaused;
    }

    public void nextTurn() {
        if (!this.isPaused) {
            for (Action turnAction : this.turnActions) {
                turnAction.perform();
                this.worldMapRenderer.render(stepsCounter);
            }
            this.stepsCounter += 1;
        }
    }

    public void finish() {
        this.isFinished = true;
    }

    private void setTurnActions() {
        this.turnActions.add(new PreparationAction(this.worldMap));
        this.turnActions.add(new MoveCreatureAction(this.worldMap));
    }

    public boolean doCreaturesExist() {
        List<Creature> creatures = this.worldMap.getEntitiesOfType(Creature.class);
        for (Creature creature : creatures) {
            if (creature.isAlive()) {
                return true;
            }
        }

        return false;
    }
}
