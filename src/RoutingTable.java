import java.util.HashMap;
import java.util.Map;

public class RoutingTable {
    private Map<String, Route> routes;

    public RoutingTable() {
        routes = new HashMap<>();
    }

    public void update(RoutingTable table, String senderId) {
        // Update routing table logic
        for (Map.Entry<String, Route> entry : table.getRoutes().entrySet()) {
            String destination = entry.getKey();
            Route route = entry.getValue();
            if (!routes.containsKey(destination) || routes.get(destination).getCost() > route.getCost() + 1) {
                routes.put(destination, new Route(destination, senderId, route.getCost() + 1));
            }
        }
    }

    public Map<String, Route> getRoutes() {
        return routes;
    }
}
