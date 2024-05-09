package worldMap.inputSettings;

import entities.creatures.Herbivore;
import entities.creatures.Predator;
import entities.inanimate.Grass;
import entities.inanimate.Rock;
import entities.inanimate.Tree;

import java.util.HashMap;
import java.util.Map;

public class MapSetting {
    public int height;
    public int width;

    public MapSetting() {
        this.setInitialEntitiesPercentage();
    }

    public Map<String, Integer> entitiesPercentage = new HashMap<>();

    private void setInitialEntitiesPercentage() {
        entitiesPercentage.put(Herbivore.class.getName(), 0);
        entitiesPercentage.put(Predator.class.getName(), 0);
        entitiesPercentage.put(Grass.class.getName(), 0);
        entitiesPercentage.put(Rock.class.getName(), 0);
        entitiesPercentage.put(Tree.class.getName(), 0);
    }
}
