import entities.Food;
import entities.creatures.Creature;
import worldMap.*;
import worldMap.inputSettings.InputMapSettings;
import worldMap.inputSettings.MapSetting;

public class Main {
    public static void main(String[] args) {
        InputMapSettings inputMapSettings = new InputMapSettings();
        MapSetting mapSetting = inputMapSettings.getMapSetting();
        WorldMap map = new WorldMapBuilder().createMap(mapSetting);
        WorldMapRenderer worldMapRenderer = new WorldMapRenderer(map);
        Simulation simulation = new Simulation(map, worldMapRenderer);
        Thread thread = new Thread(new ConsoleHandler(simulation));
        thread.setDaemon(true);
        thread.start();
        simulation.start();
    }
}
