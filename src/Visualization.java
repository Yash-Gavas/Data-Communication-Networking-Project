import java.util.List;
import java.util.Map;

public class Visualization {

    private static Network network;

    public static void setNetwork(Network network) {
        Visualization.network = network;
    }

    public static void visualizeNetwork() {
        if (network == null) {
            throw new RuntimeException("Network object is not set. Call setNetwork() before visualizing.");
        }

        List<BgpRouter> routers = network.getRouters();

        System.out.println("BGP Network Visualization:");

        // Visualize connections between routers
        for (BgpRouter router : routers) {
            String routerInfo = "Router " + router.getId() + " at (" + router.getPositionX() + ", " + router.getPositionY() + ")";
            System.out.println(routerInfo);

            Map<String, Route> routes = router.getRoutingTable().getRoutes();
            for (Route route : routes.values()) {
                BgpRouter destinationRouter = network.getRouterById(route.getDestination());
                if (destinationRouter != null) {
                    String connectionInfo = "  -> " + destinationRouter.getId() + " (" + destinationRouter.getPositionX() + ", " + destinationRouter.getPositionY() + ")";
                    if (route.isThreatDetected()) {
                        connectionInfo += " [Threat Detected]";
                    }
                    System.out.println(connectionInfo);
                }
            }
            System.out.println(); // Blank line for clarity
        }
    }
}
