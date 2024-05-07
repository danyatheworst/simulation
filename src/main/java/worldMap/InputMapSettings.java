package worldMap;

import entities.creatures.Herbivore;
import entities.creatures.Predator;
import entities.inanimate.Grass;
import entities.inanimate.Rock;
import entities.inanimate.Tree;

import java.util.Scanner;

public class InputMapSettings {
    private int MAX_FILL_PERCENTAGE = 100;
    private final static String INVALID_FORMAT_MESSAGE = "Invalid format";
    private static final Scanner scanner = new Scanner(System.in);
    private static final MapSetting mapSetting = new MapSetting();

    public MapSetting getMapSetting() {
        mapSetting.width = inputNumber(new InputSetting("width", 5, 80, 2));
        mapSetting.height = inputNumber(new InputSetting("height", 5, 80, 2));

        InputPercentageSetting[] inputPercentageSettings = getInputPercentageSettings();

        for (int i = 0; i < inputPercentageSettings.length && this.MAX_FILL_PERCENTAGE > 0; i++) {
            InputPercentageSetting inputPercentageSetting = inputPercentageSettings[i];
            inputPercentageSetting.max = this.MAX_FILL_PERCENTAGE;
            int percentageOfEntity = inputNumber(inputPercentageSetting);
            mapSetting.entitiesPercentage.put(inputPercentageSetting.entityName, percentageOfEntity);
            this.MAX_FILL_PERCENTAGE -= percentageOfEntity;
        }

        return mapSetting;

    }
    private static int inputNumber(InputSetting inputSetting) {
        int min = inputSetting.min;
        int max = inputSetting.max;

        while (true) {
            System.out.println("Please, enter the integer value of " + inputSetting.message + " "
                    + "the map will have (MIN — " + min + " , MAX — " + max + "):");
            String line = scanner.nextLine();

            if (line.isEmpty() || line.length() > inputSetting.maxNumberOfDigits) {
                System.out.println(INVALID_FORMAT_MESSAGE);
                continue;
            }

            boolean isFormatValid = true;
            for (Character character : line.toCharArray()) {
                if (!Character.isDigit(character)) {
                    System.out.println(INVALID_FORMAT_MESSAGE);
                    isFormatValid = false;
                    break;
                }
            }
            if (!isFormatValid) {
                continue;
            }

            int number = Integer.parseInt(line);

            if (number < min || number > max) {
                System.out.println("Your integer value must be between " + min + " and " + max + " (both inclusively)");
                continue;
            }
            return number;
        }
    }

    private static InputPercentageSetting[] getInputPercentageSettings() {
        return new InputPercentageSetting[] {
                new InputPercentageSetting("herbivores", 1, 3, Herbivore.class.getName()),
                new InputPercentageSetting("predators", 1, 2, Predator.class.getName()),
                new InputPercentageSetting("grass", 1, 2, Grass.class.getName()),
                new InputPercentageSetting("rocks", 1, 2, Rock.class.getName()),
                new InputPercentageSetting("trees", 1, 2, Tree.class.getName()),
        };
    }
}
