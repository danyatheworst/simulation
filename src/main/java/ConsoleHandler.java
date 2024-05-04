import java.util.Scanner;

public class ConsoleHandler implements Runnable {
    private static final String PAUSE = "p";
    private static final String FINISH = "f";
    private final Simulation simulation;

    public ConsoleHandler(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (!Thread.interrupted()) {
            String order = scanner.next();
            switch (order) {
                case PAUSE -> simulation.pause();
                case FINISH -> simulation.finish();

            }
        }
    }
}