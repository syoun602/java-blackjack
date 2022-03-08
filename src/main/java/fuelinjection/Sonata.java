package fuelinjection;

public class Sonata extends Car {

    private static final String NAME = "Sonata";
    private static final double DISTANCE_PER_LITER = 10;

    private final double tripDistance;

    public Sonata(double tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    double getTripDistance() {
        return this.tripDistance;
    }

    @Override
    String getName() {
        return NAME;
    }
}
