public class Route {
    private String destination;
    private String nextHop;
    private int cost; // Changed to int for representing cost
    private boolean threatDetected;

    public Route(String destination, String nextHop, int cost, boolean threatDetected) {
        this.destination = destination;
        this.nextHop = nextHop;
        this.cost = cost;
        this.threatDetected = threatDetected;
    }

    public String getDestination() {
        return destination;
    }

    public String getNextHop() {
        return nextHop;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isThreatDetected() {
        return threatDetected;
    }

    public void setThreatDetected(boolean threatDetected) {
        this.threatDetected = threatDetected;
    }

    @Override
    public String toString() {
        return "Route{" +
                "destination='" + destination + '\'' +
                ", nextHop='" + nextHop + '\'' +
                ", cost=" + cost +
                ", threatDetected=" + threatDetected +
                '}';
    }
}
