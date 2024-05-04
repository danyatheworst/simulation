package entities;

import java.util.Random;

public class Range {
    final int min;
    final int max;

    public Range(int min, int max) {
        this.min = Math.min(min, max);
        this.max = Math.max(min, max);
    }

    public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(this.max - this.min) + this.min;
    }
}
