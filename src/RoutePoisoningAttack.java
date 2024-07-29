public class RoutePoisoningAttack {
    public static void poisonRoute(BgpRouter attacker, String target) {
        BgpRoutingTable routingTable = attacker.getRoutingTable();
        
        // Poison the route by marking it as threatened
        routingTable.getRoutes().put(target, new Route(target, attacker.getId(), 0, true)); // Assuming cost 0 for simplicity
        
        // Share the poisoned routing table
        attacker.shareRoutingTable();
    }
}


