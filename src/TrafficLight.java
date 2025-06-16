public class TrafficLight extends Thread{
    private Direction currentGreen = Direction.NORTH;
    private final Intersection intersection;

    public TrafficLight(Intersection intersection) {
        this.intersection = intersection;
    }

    public synchronized Direction getCurrentGreen() {
        return currentGreen;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(this){
                currentGreen = Direction.next(currentGreen);
                System.out.println("Світлофор: Зелений напрямок " + currentGreen);
                intersection.signalChange();
            }
        }
    }
}
