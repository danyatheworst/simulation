package worldMap;

import java.util.Scanner;

public class InputMapSettings {
    private int MAX_FILL_PERCENTAGE = 100;
    private final static String INVALID_FORMAT_MESSAGE = "Invalid format";
    private static final Scanner scanner = new Scanner(System.in);
    private static final MapSetting mapSetting = new MapSetting();

    public MapSetting getMapSetting() {
        mapSetting.width = inputSize("width");
        mapSetting.height = inputSize("height");
        mapSetting.herbivorePercentage = inputPercentagesOfEntity("herbivore");
        mapSetting.predatorPercentage = inputPercentagesOfEntity("predator");
        mapSetting.grassPercentage = inputPercentagesOfEntity("grass");
        mapSetting.rockPercentage = inputPercentagesOfEntity("rock");
        mapSetting.treePercentage = inputPercentagesOfEntity("tree");
        return mapSetting;
    }

    private static int inputSize(String printMessage) {
        while (true) {
            System.out.println("Please, enter the integer value (with no spaces) of " + printMessage + " "
                    + "the map will have (MIN — 5, MAX — 80):");
            String line = scanner.nextLine();

            if (line.length() > 2) {
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

            int size = Integer.parseInt(line);

            if (size < 5 || size > 80) {
                System.out.println("Your integer value must be between 5 and 80 (both inclusively)");
                continue;
            }
            return size;
        }
    }
    
    private int inputPercentagesOfEntity(String printMessage) {
        if (this.MAX_FILL_PERCENTAGE <= 0) {
            return 0;
        }

        while (true) {
            int currentFillPercentageOfMap = 100 - MAX_FILL_PERCENTAGE;
            System.out.println(
                    "Your map is currently filled with entities on" + " " + currentFillPercentageOfMap
                            + " " + "percentages");
            System.out.println(
                    "Please, enter the percentages (integer value with no spaces) of" + " " + printMessage + " " +
                            "the map will have. (MIN — 1, MAX —" + " " + this.MAX_FILL_PERCENTAGE + ")"
            );
            String line = scanner.nextLine();

            if (line.length() > 3) {
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

            int percentageOfEntity = Integer.parseInt(line);
            if (percentageOfEntity < 1 || percentageOfEntity > this.MAX_FILL_PERCENTAGE) {
                System.out.println(
                        "Sorry, mate, your integer value must be between 1 and" + " " + this.MAX_FILL_PERCENTAGE +
                                " (both inclusively). Try again.");

                continue;
            }
            
            this.MAX_FILL_PERCENTAGE -= percentageOfEntity;
            return percentageOfEntity;
        }
    }
}
