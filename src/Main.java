import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Intersection intersection = new Intersection(null);
        TrafficLight trafficLight = new TrafficLight(intersection);
        intersection = new Intersection(trafficLight);
        trafficLight.start();

        Random random = new Random();

        while (true) {
            Direction dir = Direction.values()[random.nextInt(2)];
            Car car = new Car(intersection, dir);
            car.start();

            try {
                Thread.sleep(1000 + random.nextInt(4000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}