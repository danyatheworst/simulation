package actions;

import worldMap.WorldMap;

abstract public class Action {
    WorldMap worldMap;

    public Action(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public abstract void perform();
}



