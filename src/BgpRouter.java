import java.util.HashMap;
import java.util.Map;

public class BgpRouter {
    private String id;
    private double positionX; // X coordinate for visualization
    private double positionY; // Y coordinate for visualization
    private BgpRoutingTable routingTable;
    private Network network;
    private Map<String, Boolean> blockedSenders;

    public BgpRouter(String id, Network network) {
        this.id = id;
        this.positionX = positionX;
        this.positionY = positionY; 
        this.network = network;
        this.routingTable = new BgpRoutingTable();
        this.blockedSenders = new HashMap<>();
        network.addRouter(this);
    }

    public String getId() {
        return id;
    }
     public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public BgpRoutingTable getRoutingTable() {
        return routingTable;
    }

    public void shareRoutingTable() {
        for (BgpRouter router : network.getRouters()) {
            if (!router.equals(this) && !blockedSenders.containsKey(router.getId())) {
                router.receiveRoutingTable(this, routingTable);
            }
        }
    }
    public void receiveRoutingTable(BgpRouter sender, BgpRoutingTable table) {
        if (isTampered(table)) {
            sendThreatMessage(sender.getId());
            blockSender(sender.getId());
        } else {
            routingTable.update(table, sender.getId());
        }
    }

    private boolean isTampered(BgpRoutingTable table) {
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

    public void sendPacket(Packet packet) {
        if (blockedSenders.containsKey(packet.getSource())) {
            System.out.println("Packet from " + packet.getSource() + " to " + packet.getDestination() + " is blocked due to threat detection.");
            return;
        }

        if (packet.getDestination().equals(this.id)) {
            System.out.println("Packet successfully received at " + this.id + ": " + packet.getData());
        } else {
            Route route = routingTable.getRoutes().get(packet.getDestination());
            if (route != null) {
                BgpRouter nextHop = network.getRouterById(route.getNextHop());
                if (nextHop != null) {
                    nextHop.sendPacket(packet);
                } else {
                    System.out.println("Next hop " + route.getNextHop() + " not found. Packet dropped.");
                }
            } else {
                System.out.println("No route to " + packet.getDestination() + ". Packet dropped.");
            }
        }
    }

    public void receiveFile(String filePath) {
        System.out.println("File received at " + id + ": " + filePath);
    }
}
