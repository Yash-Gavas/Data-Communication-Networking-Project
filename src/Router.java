import java.util.HashMap;
import java.util.Map;

public class Router {
    private String id;
    private RoutingTable routingTable;
    private Network network;
    private Map<String, Boolean> blockedSenders;

    public Router(String id, Network network) {
        this.id = id;
        this.network = network;
        this.routingTable = new RoutingTable();
        this.blockedSenders = new HashMap<>();
        network.addRouter(this);
    }

    public String getId() {
        return id;
    }

    public RoutingTable getRoutingTable() {
        return routingTable;
    }

    public void shareRoutingTable() {
        for (Router router : network.getRouters()) {
            if (!router.equals(this) && !blockedSenders.containsKey(router.getId())) {
                router.receiveRoutingTable(this, routingTable);
            }
        }
    }

    public void receiveRoutingTable(Router sender, RoutingTable table) {
        // Check for tampered destination addresses
        if (isTampered(table)) {
            sendThreatMessage(sender.getId());
            blockSender(sender.getId());
        } else {
            routingTable.update(table, sender.getId());
        }
    }

    private boolean isTampered(RoutingTable table) {
        // Implement tampered detection logic
        for (Route route : table.getRoutes().values()) {
            if (!route.getNextHop().equals(route.getDestination())) {
                return true;
            }
        }
        return false;
    }

    private void sendThreatMessage(String senderId) {
        ThreatMessage threatMessage = new ThreatMessage("Threat detected from " + senderId);
        System.out.println(threatMessage.getMessage());
    }

    private void blockSender(String senderId) {
        blockedSenders.put(senderId, true);
        System.out.println("Blocked sender: " + senderId);
    }
}
