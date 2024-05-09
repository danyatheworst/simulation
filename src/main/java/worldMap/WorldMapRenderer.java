package worldMap;

import entities.Entity;
import entities.Food;
import entities.creatures.Herbivore;
import entities.creatures.Predator;
import entities.inanimate.Grass;

import java.util.HashSet;
import java.util.Set;

public class WorldMapRenderer {
    private static final String CLEAR_CONSOLE = "\033[H\033[J";
    private static final String ANSI_RESET = "\033[0m";
    private static final String ANSI_RED = "\033[0;101m";
    private static final String ANSI_YELLOW = "\033[0;103m";
    private static final String HERBIVORE = "\uD83D\uDC30";
    private static final String DEAD_HERBIVORE = "\uD83C\uDF57";
    private static final String PREDATOR = "\uD83D\uDC3A";
    private static final String DEAD_PREDATOR = "\uD83C\uDF56";
    private static final String GRASS = "\uD83E\uDD6C";
    private static final String TREE = "\uD83C\uDF32";
    private static final String ROCK = "\uD83E\uDEA8";
    private static final String GROUND = "\uD83D\uDFEB";

    private final WorldMap worldMap;
    private final Set<Food> interactedEntities = new HashSet<>();

    public WorldMapRenderer(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void render(int stepsCounter) {
        System.out.println(CLEAR_CONSOLE);
        for (int i = 0; i < worldMap.height; i++) {
            for (int j = 0; j < worldMap.width; j++) {
                StringBuilder line = new StringBuilder();
                Entity entity = worldMap.getEntityAt(new Cell(i, j));
                if (entity == null) {
                    line.append(GROUND);
                } else {
                    line.append(this.getSignOf(entity));
                }
                line.append(ANSI_RESET);
                System.out.print(line);
            }
            System.out.println();
        }
        System.out.println("Steps counter: " + stepsCounter);

        System.out.println("p — pause/resume simulation");
        System.out.println("f — finish simulation");

        interactedEntities.clear();

        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String getSignOf(Entity entity) {

        switch (entity.getClass().getSimpleName()) {
            case "Herbivore": {
                Herbivore herbivore = (Herbivore) entity;
                if (!herbivore.isAlive()) {
                    if (interactedEntities.contains(herbivore)) {
                        return ANSI_YELLOW + DEAD_HERBIVORE;
                    }

                    return DEAD_HERBIVORE;
                }
                //herbivore is get attacked
                if (interactedEntities.contains(herbivore)) {
                    return ANSI_RED + HERBIVORE;
                }
                return HERBIVORE;
            }
            case "Predator": {
                Predator predator = (Predator) entity;
                if (!predator.isAlive()) {
                    return DEAD_PREDATOR;
                }
                return PREDATOR;
            }
            case "Grass": {
                assert entity instanceof Grass;
                Grass grass = (Grass) entity;
                if (interactedEntities.contains(grass)) {
                    return ANSI_YELLOW + GRASS;
                }
                return GRASS;
            }
            case "Tree": return TREE;
            case "Rock": return ROCK;
        };
        return GROUND;
    }

    public void addToInteractedEntities(Food food) {
        this.interactedEntities.add(food);
    }
}

