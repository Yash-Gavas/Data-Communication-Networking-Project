public class Main {
    public static void main(String[] args) {
        Network network = new Network();

        Router routerA = new Router("A", network);
        Router routerB = new Router("B", network);
        Router routerC = new Router("C", network);

        routerA.getRoutingTable().update(new RoutingTable(), "A");
        routerB.getRoutingTable().update(new RoutingTable(), "B");
        routerC.getRoutingTable().update(new RoutingTable(), "C");

        // Initial sharing of routing tables
        network.simulate();

        // Simulate route poisoning attack
        RoutePoisoningAttack.poisonRoute(routerC, "A");

        // Apply mitigation techniques
        MitigationTechniques mitigation = new MitigationTechniques();
        mitigation.addTrustedRouter("A");
        mitigation.addTrustedRouter("B");

        for (Router router : network.getRouters()) {
            mitigation.applyFilter(router, router.getRoutingTable(), "C");
        }

        // Print routing tables
        for (Router router : network.getRouters()) {
            System.out.println("Router " + router.getId() + " Routing Table: " + router.getRoutingTable().getRoutes());
        }
    }
}
