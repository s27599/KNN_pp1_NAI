public class Results implements Comparable<Results> {

    private double distance;
    private String name;

    public Results(double distance, String name) {
        this.distance = distance;
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Results{" +
                "distance=" + distance +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Results o) {
        return Double.compare(this.distance, o.distance);
    }


}
