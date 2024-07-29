import java.util.HashMap;
import java.util.Map;

public class BgpRoutingTable {
    private Map<String, Route> routes;

    public BgpRoutingTable() {
        this.routes = new HashMap<>();
    }

    public void update(BgpRoutingTable table, String senderId) {
        for (Map.Entry<String, Route> entry : table.getRoutes().entrySet()) {
            String destination = entry.getKey();
            Route route = entry.getValue();
            if (!routes.containsKey(destination) || routes.get(destination).getCost() > route.getCost() + 1) {
                routes.put(destination, new Route(destination, senderId, route.getCost() + 1, route.isThreatDetected()));
            }
        }
    }

    public Map<String, Route> getRoutes() {
        return routes;
    }

    // Optional: Add more methods as needed for managing routes
}
