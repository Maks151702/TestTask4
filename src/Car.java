public class Car extends Thread {
    private static int carCount = 0;
    private final int carId;
    private final Intersection intersection;
    private final Direction direction;

    public Car(Intersection intersection, Direction direction) {
        this.intersection = intersection;
        this.direction = direction;

        synchronized (Car.class) {
            carId = ++carCount;
        }

        this.setName(" Машина " + carId);
    }

    @Override
    public void run() {
        synchronized (intersection) {
            intersection.addCar(this, direction);
            intersection.notifyAll();
        }

        intersection.tryPass(this, direction);
    }
}
