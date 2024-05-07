package worldMap;

import actions.*;
import entities.creatures.Herbivore;
import entities.creatures.Predator;
import entities.inanimate.Grass;
import entities.inanimate.Rock;
import entities.inanimate.Tree;

import java.util.ArrayList;
import java.util.List;

public class WorldMapBuilder {
    private WorldMap worldMap;
    private MapSetting mapSetting;
    private final List<Action> initActions = new ArrayList<>();

    public WorldMap createMap(MapSetting mapSetting) {
        this.worldMap = new WorldMap(mapSetting.width, mapSetting.height);
        this.mapSetting = mapSetting;
        this.setInitActions();
        this.setUpEntities();
        return this.worldMap;
    }

    private void setUpEntities() {
        for (Action initAction : this.initActions) {
            initAction.perform();
        }
    }

    private void setInitActions() {
        this.initActions.add(
                new HerbivoreSpawnAction(this.worldMap, this.mapSetting.entitiesPercentage.get(Herbivore.class.getName()))
        );
        this.initActions.add(
                new PredatorSpawnAction(this.worldMap, this.mapSetting.entitiesPercentage.get(Predator.class.getName()))
        );
        this.initActions.add(
                new GrassSpawnAction(this.worldMap, this.mapSetting.entitiesPercentage.get(Grass.class.getName()))
        );
        this.initActions.add(
                new RockSpawnAction(this.worldMap, this.mapSetting.entitiesPercentage.get(Rock.class.getName()))
        );
        this.initActions.add(
                new TreeSpawnAction(this.worldMap, this.mapSetting.entitiesPercentage.get(Tree.class.getName()))
        );
    }
}
