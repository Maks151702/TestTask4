import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Intersection {
    private final TrafficLight trafficLight;
    private boolean isOccupied = false;
    private final Map<Direction, Queue<Car>> queues = new HashMap<>();

    public Intersection(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
        for (Direction d : Direction.values()) {
            queues.put(d, new LinkedList<>());
        }
    }

    public synchronized void addCar(Car car, Direction direction) {
        queues.get(direction).add(car);
        System.out.println(car.getName() + " стала в чергу на " + direction);
    }

    public synchronized void tryPass(Car car, Direction direction) {
        while (trafficLight.getCurrentGreen() != direction ||
                isOccupied ||
                queues.get(direction).peek() != car) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Починає проїзд
        isOccupied = true;
        queues.get(direction).poll(); // видаляємо з черги
        System.out.println(car.getName() + " їде через перехрестя з " + direction);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(car.getName() + " покинула перехрестя.");
        isOccupied = false;
        notifyAll(); // пробудити інших
    }

    public synchronized void signalChange() {
        notifyAll();
    }
}