package utils;

import java.util.Random;

public class Utils {
    private Utils() {};
    public static <T> void shuffleArray(T[] arr) {
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            T temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }
}
