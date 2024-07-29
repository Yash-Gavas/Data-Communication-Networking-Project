import java.util.HashSet;
import java.util.Set;

public class MitigationTechniques {
    private Set<String> trustedRouters;

    public MitigationTechniques() {
        trustedRouters = new HashSet<>();
    }

    public void addTrustedRouter(String routerId) {
        trustedRouters.add(routerId);
    }

    public boolean isTrustedRouter(String routerId) {
        return trustedRouters.contains(routerId);
    }

    public void applyFilter(Router router, RoutingTable receivedTable, String senderId) {
        if (!isTrustedRouter(senderId)) {
            return;
        }
        router.getRoutingTable().update(receivedTable, senderId);
    }
}
