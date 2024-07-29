public class ThreatDetector {
    private Network network;

    public ThreatDetector(Network network) {
        this.network = network;
    }

    public boolean isThreatDetected(String source, String destination) {
        BgpRouter sourceRouter = network.getRouterById(source);
        BgpRouter destRouter = network.getRouterById(destination);

        if (sourceRouter != null && destRouter != null) {
            Route route = sourceRouter.getRoutingTable().getRoutes().get(destination);
            if (route != null && !route.getNextHop().equals(destination)) {
                return true;
            }
        }
        return false;
    }
}

