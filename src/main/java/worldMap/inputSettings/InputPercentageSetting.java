package worldMap.inputSettings;

public class InputPercentageSetting extends InputSetting {
    public final String entityName;
    public InputPercentageSetting(String message, int min, int maxNumberOfDigits, String entityName) {
        super(message, min, maxNumberOfDigits);
        this.entityName = entityName;
    }
}
