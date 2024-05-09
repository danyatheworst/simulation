package worldMap.inputSettings;

public class InputSetting {
    public final String message;
    public final int min;
    public final int maxNumberOfDigits;
    public int max;

    public InputSetting(String message, int min, int maxNumberOfDigits) {
        this.message = message;
        this.min = min;
        this.maxNumberOfDigits = maxNumberOfDigits;
    }

    public InputSetting(String message, int min, int max, int maxNumberOfDigits) {
        this.message = message;
        this.min = min;
        this.max = max;
        this.maxNumberOfDigits = maxNumberOfDigits;
    }
}
